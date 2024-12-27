package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import dao.StaffDao;
import pojo.Staff;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class addstaff extends JFrame {
    public addstaff() {
        initComponents();
        loadPositions();  // 加载职位到下拉框
        addActionListeners();
    }

    // 加载职位选项到下拉框
    private void loadPositions() {
        StaffDao staffDao = new StaffDao();
        List<String> positions = staffDao.getAllPositions();

        // 清空现有职位，确保没有重复
        comboBox1.removeAllItems();

        // 添加默认选项
        comboBox1.addItem("请选择职位");

        // 将所有职位添加到 JComboBox 中
        for (String position : positions) {
            comboBox1.addItem(position);
        }
    }

    // Add ActionListeners for buttons
    private void addActionListeners() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewStaff();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    // 清空输入框
    private void clearFields() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        radioButton1.setSelected(false);
        radioButton2.setSelected(false);
        comboBox1.setSelectedIndex(0);  // 选中默认项
    }

    // 添加新员工
    private void addNewStaff() {
        String name = textField1.getText();
        String gender = radioButton1.isSelected() ? "男" : (radioButton2.isSelected() ? "女" : "保密");
        String position = (String) comboBox1.getSelectedItem();
        String ageText = textField2.getText();
        String password = textField3.getText();

        // 验证输入
        if (name.isEmpty() || position == null || position.equals("请选择职位") || ageText.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请填写所有字段并选择职位", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入有效的年龄", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 创建 Staff 对象
        Staff staff = new Staff();
        staff.setName(name);
        staff.setGender(gender);
        staff.setPosition(position);
        staff.setAge(age);
        staff.setPassword(password);

        // 将员工添加到数据库
        StaffDao staffDao = new StaffDao();
        boolean success = staffDao.addStaff(staff);

        if (success) {
            JOptionPane.showMessageDialog(this, "员工添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "添加员工失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        panel1 = new JPanel();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        label3 = new JLabel();
        comboBox1 = new JComboBox();
        label4 = new JLabel();
        textField2 = new JTextField();
        label5 = new JLabel();
        textField3 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u6dfb\u52a0\u5458\u5de5");
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "56dlu, $lcgap, 133dlu",
            "28dlu, $lgap, 23dlu, 2*($lgap, 26dlu), $lgap, 31dlu, $lgap, 29dlu"));

        //---- label1 ----
        label1.setText("\u5458\u5de5\u59d3\u540d\uff1a");
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 1));

        //---- label2 ----
        label2.setText("\u5458\u5de5\u6027\u522b\uff1a");
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "51dlu, $lcgap, 77dlu",
                "default"));

            //---- radioButton1 ----
            radioButton1.setText("\u7537");
            panel1.add(radioButton1, CC.xy(1, 1));

            //---- radioButton2 ----
            radioButton2.setText("\u5973");
            panel1.add(radioButton2, CC.xy(3, 1));
        }
        contentPane.add(panel1, CC.xy(3, 3));

        //---- label3 ----
        label3.setText("\u5458\u5de5\u804c\u79f0\uff1a");
        contentPane.add(label3, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(comboBox1, CC.xy(3, 5));

        //---- label4 ----
        label4.setText("\u5458\u5de5\u5e74\u9f84\uff1a");
        contentPane.add(label4, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(3, 7));

        //---- label5 ----
        label5.setText("\u5458\u5de5\u5bc6\u7801\uff1a");
        contentPane.add(label5, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(3, 9));

        //---- button1 ----
        button1.setText("\u786e\u8ba4");
        contentPane.add(button1, CC.xy(1, 11, CC.RIGHT, CC.DEFAULT));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        contentPane.add(button2, CC.xy(3, 11, CC.CENTER, CC.DEFAULT));
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
    private JRadioButton radioButton2;
    private JLabel label3;
    private JComboBox comboBox1;
    private JLabel label4;
    private JTextField textField2;
    private JLabel label5;
    private JTextField textField3;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:off
}
