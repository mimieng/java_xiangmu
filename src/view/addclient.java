package view;

import dao.ClientDao;
import pojo.Client;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 晚吟
 */
public class addclient extends JFrame {
    public addclient() {
        // 调用组件初始化方法
        initComponents();
    }

    // 添加客户到数据库
    private void addClientToDatabase() {
        String name = textField1.getText();
        String password = textField2.getText();

        // 获取选中的性别
        String gender = null;
        if (radioButton1.isSelected()) {
            gender = "男";
        } else if (radioButton2.isSelected()) {
            gender = "女";
        } else if (radioButton3.isSelected()) {
            gender = "保密";
        }

        // 检查输入框是否为空
        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "所有字段必须填写", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (gender == null) {
            JOptionPane.showMessageDialog(this, "请选择客户性别", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 封装客户信息到 Client 对象
        Client client = new Client(name, password, gender);

        // 使用 ClientDao 类来插入数据
        ClientDao clientDao = new ClientDao();
        boolean success = clientDao.addClient(client);

        if (success) {
            JOptionPane.showMessageDialog(this, "客户信息已添加", "成功", JOptionPane.INFORMATION_MESSAGE);
            textField1.setText("");  // 清空输入框
            textField2.setText("");
//            genderGroup.clearSelection();  // 清除性别选择
        } else {
            JOptionPane.showMessageDialog(this, "添加失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JPanel panel1;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:off

    // 初始化组件
    private void initComponents() {
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        panel1 = new JPanel();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u6dfb\u52a0\u5ba2\u6237");
        setIconImage(new ImageIcon(getClass().getResource("/\u5b66\u751f\u7ba1\u7406.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "68dlu, $lcgap, 133dlu",
                "36dlu, $lgap, 50dlu, $lgap, 25dlu, $lgap, 51dlu"));

        //---- label1 ----
        label1.setText("\u5ba2\u6237\u59d3\u540d\uff1a");
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 1));

        //---- label2 ----
        label2.setText("\u767b\u5f55\u5bc6\u7801\uff1a");
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(3, 3));

        //---- label3 ----
        label3.setText("\u5ba2\u6237\u6027\u522b\uff1a");
        contentPane.add(label3, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                    "34dlu, $lcgap, 47dlu, $lcgap, 42dlu",
                    "default"));

            //---- radioButton1 ----
            radioButton1.setText("\u7537");
            panel1.add(radioButton1, CC.xy(1, 1));

            //---- radioButton2 ----
            radioButton2.setText("\u5973");
            panel1.add(radioButton2, CC.xy(3, 1));

            //---- radioButton3 ----
            radioButton3.setText("\u4fdd\u5bc6");
            panel1.add(radioButton3, CC.xy(5, 1));
        }
        contentPane.add(panel1, CC.xy(3, 5));

        // 将性别单选按钮添加到 ButtonGroup 中，确保只能选择一个
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioButton1);
        genderGroup.add(radioButton2);
        genderGroup.add(radioButton3);

        //---- button1 ----
        button1.setText("\u786e\u8ba4");
        contentPane.add(button1, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        contentPane.add(button2, CC.xy(3, 7, CC.CENTER, CC.DEFAULT));

        // 按钮事件监听器
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClientToDatabase(); // 按钮点击时调用添加客户的方法
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");  // 清空文本框
                textField2.setText("");
                genderGroup.clearSelection();  // 清除性别选择
            }
        });

        pack();
        setLocationRelativeTo(getOwner());
    }




}
