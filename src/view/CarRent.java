package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import pojo.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 晚吟
 */
public class CarRent extends JFrame {
    public CarRent() {
        initComponents();
        setWelcomeMessage();
        loadCarData();
        addEventListeners();
    }

    /**
     * 设置欢迎信息，根据用户类型展示不同的欢迎词
     */
    private void setWelcomeMessage() {
        if ("客户".equals(MainFrame1.userType)) {
            User admin = MainFrame1.admin;
            label2.setText("【欢迎客户】" + admin.getName());
        }
    }

    /**
     * 从数据库加载车辆数据到 comboBox1
     */
    private void loadCarData() {
        try (Connection conn = DBUtil.getCon()) {
            String sql = "SELECT name FROM medicines"; // 查询车辆名称
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // 清空之前的内容
            comboBox1.removeAllItems();

            // 添加车辆数据到 comboBox1
            while (rs.next()) {
                String carName = rs.getString("name");
                comboBox1.addItem(carName);
            }

            if (comboBox1.getItemCount() == 0) {
                comboBox1.addItem("无可买药品");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "加载药品数据失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * 为按钮添加事件监听器
     */
    private void addEventListeners() {
        // 确认按钮事件
        button1.addActionListener(e -> {
            String selectedCar = (String) comboBox1.getSelectedItem();
            if (selectedCar == null || "无药品".equals(selectedCar)) {
                JOptionPane.showMessageDialog(this, "当前没有药品！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "你买了" + selectedCar, "确认购买", JOptionPane.INFORMATION_MESSAGE);
                // TODO: 处理车辆租借逻辑
            }
        });

        // 重置按钮事件
        button2.addActionListener(e -> {
            comboBox1.setSelectedIndex(0); // 重置选择到第一个项目
        });
    }

    /**
     * 初始化 UI 组件
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        comboBox1 = new JComboBox();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "54dlu, $lcgap, 115dlu",
            "38dlu, 47dlu, $lgap, 50dlu, $lgap"));

        //---- label1 ----
        label1.setText("\u5ba2\u6237\uff1a");
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(label2, CC.xy(3, 1));

        //---- label3 ----
        label3.setText("\u836f\u54c1\u7c7b\u578b\uff1a");
        contentPane.add(label3, CC.xy(1, 2, CC.RIGHT, CC.DEFAULT));
        contentPane.add(comboBox1, CC.xy(3, 2));

        //---- button1 ----
        button1.setText("\u786e\u8ba4");
        contentPane.add(button1, CC.xy(1, 4, CC.RIGHT, CC.DEFAULT));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        contentPane.add(button2, CC.xy(3, 4, CC.CENTER, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox comboBox1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
