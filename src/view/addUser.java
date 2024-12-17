package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.DBUtil;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author 晚吟
 */
public class addUser extends JFrame {
    public addUser() {
        initComponents();
        initCustomComponents();
    }

    private void initCustomComponents() {
        // 单选按钮组，确保性别只能选择一个
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioButton1);
        genderGroup.add(radioButton2);

        // 确认添加按钮事件
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserToDatabase();
            }
        });

        // 重置按钮事件
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
    }

    private void addUserToDatabase() {
        String name = textField1.getText().trim();
        String gender = radioButton1.isSelected() ? "男" : (radioButton2.isSelected() ? "女" : null);
        String ageText = textField2.getText().trim();
        String password = textField3.getText().trim();

        // 检查输入是否完整
        if (name.isEmpty() || gender == null || ageText.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请填写完整信息！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 年龄验证
        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "年龄必须是正整数！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 数据库操作
        try (Connection connection = DBUtil.getCon();
             PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (name, gender, age, password) VALUES (?, ?, ?, ?)");) {
            pstmt.setString(1, name);
            pstmt.setString(2, gender);
            pstmt.setInt(3, age);
            pstmt.setString(4, password);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "用户添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                resetForm();
            } else {
                JOptionPane.showMessageDialog(this, "用户添加失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        radioButton1.setSelected(false);
        radioButton2.setSelected(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        panel1 = new JPanel();
        radioButton1 = new JRadioButton();
        checkBox1 = new JCheckBox();
        radioButton2 = new JRadioButton();
        label3 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        textField3 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "80dlu, $lcgap, 110dlu, $lcgap, 45dlu",
            "34dlu, $lgap, 42dlu, $lgap, 38dlu, $lgap, 51dlu, $lgap, 35dlu"));

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\u79f0\uff1a");
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 1));

        //---- label2 ----
        label2.setText("\u7528\u6237\u6027\u522b\uff1a");
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "default, $lcgap, 36dlu, $lcgap, 38dlu",
                "default"));

            //---- radioButton1 ----
            radioButton1.setText("\u7537");
            panel1.add(radioButton1, CC.xy(1, 1));

            //---- checkBox1 ----
            checkBox1.setText("\u4fdd\u5bc6");
            panel1.add(checkBox1, CC.xy(3, 1));

            //---- radioButton2 ----
            radioButton2.setText("\u4fdd\u5bc6");
            panel1.add(radioButton2, CC.xy(5, 1));
        }
        contentPane.add(panel1, CC.xy(3, 3));

        //---- label3 ----
        label3.setText("\u7528\u6237\u5e74\u9f84\uff1a");
        contentPane.add(label3, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(3, 5));

        //---- label4 ----
        label4.setText("\u7528\u6237\u5bc6\u7801\uff1a");
        contentPane.add(label4, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(3, 7));

        //---- button1 ----
        button1.setText("\u786e\u8ba4\u6dfb\u52a0");
        contentPane.add(button1, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        contentPane.add(button2, CC.xy(3, 9, CC.RIGHT, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JPanel panel1;
    private JRadioButton radioButton1;
    private JCheckBox checkBox1;
    private JRadioButton radioButton2;
    private JLabel label3;
    private JTextField textField2;
    private JLabel label4;
    private JTextField textField3;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
