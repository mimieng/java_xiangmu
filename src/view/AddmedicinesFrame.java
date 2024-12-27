package view;

import com.jgoodies.forms.factories.CC;
import dao.CarDao;  // 引入DAO类
import pojo.Car;  // 引入Car类
import javax.swing.*;
import com.jgoodies.forms.layout.*;

public class AddmedicinesFrame extends JFrame {

    private JLabel label1, label2, label4, label3;
    private JTextField txt_name, txt_info, txt_money;
    private JComboBox<String> cb_category;
    private JButton btn_sure, btn_reset;
    private CarDao carDao;

    public AddmedicinesFrame() {
        carDao = new CarDao();  // 初始化DAO
        initComponents();
    }

    private void initComponents() {
        // 初始化组件
        label1 = new JLabel("药品名称：");
        txt_name = new JTextField();
        label2 = new JLabel("所属类别：");
        cb_category = new JComboBox<>(new String[]{"抗生素", "止痛药", "退烧药","解热镇痛药","心血管药","胃药","降压药","止泻药","勃起功能障碍","心血管药"});
        label4 = new JLabel("药品描述：");
        txt_info = new JTextField();
        label3 = new JLabel("价格：");
        txt_money = new JTextField();
        btn_sure = new JButton("确认");
        btn_reset = new JButton("重置");

        // 设置窗体属性
        setTitle("添加药品窗口");
        setIconImage(new ImageIcon(getClass().getResource("")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "87dlu, $lcgap, 35dlu, $lcgap, 30dlu, $lcgap, 40dlu, $lcgap, 33dlu",
                "40dlu, $lgap, 30dlu, $lgap, 40dlu, 2*($lgap, 30dlu)"));

        // 设置组件
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name, CC.xywh(3, 1, 5, 1));

        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(cb_category, CC.xywh(3, 3, 6, 1));

        contentPane.add(label4, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_info, CC.xywh(3, 5, 3, 1));

        contentPane.add(label3, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_money, CC.xywh(3, 7, 5, 1));

        contentPane.add(btn_sure, CC.xywh(2, 9, 3, 1, CC.CENTER, CC.DEFAULT));
        contentPane.add(btn_reset, CC.xywh(7, 9, 3, 1, CC.LEFT, CC.DEFAULT));

        // 按钮事件
        btn_sure.addActionListener(e -> submitCarInfo());  // 确认按钮事件
        btn_reset.addActionListener(e -> resetForm());  // 重置按钮事件

        pack();
        setLocationRelativeTo(getOwner());
    }

    // 提交汽车信息
    private void submitCarInfo() {
        String name = txt_name.getText();
        String category = (String) cb_category.getSelectedItem();
        String info = txt_info.getText();
        String rentStr = txt_money.getText();

        // 数据验证
        if (name.isEmpty() || info.isEmpty() || rentStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请填写所有字段！");
            return;
        }

        try {
            double rent = Double.parseDouble(rentStr);

            // 创建 Car 对象
            Car car = new Car();
            car.setName(name);
            car.setCategory(category);
            car.setInfo(info);
            car.setMonthlyRent(rent);

            // 插入数据到数据库
            boolean success = carDao.addCar(car);
            if (success) {
                JOptionPane.showMessageDialog(this, "药品信息添加成功！");
                resetForm();  // 清空表单
            } else {
                JOptionPane.showMessageDialog(this, "添加失败！");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入有效的价格！");
        }
    }

    // 重置表单
    private void resetForm() {
        txt_name.setText("");
        cb_category.setSelectedIndex(0);
        txt_info.setText("");
        txt_money.setText("");
    }






}
