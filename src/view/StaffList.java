package view;

import com.jgoodies.forms.factories.CC;
import dao.StaffDao;
import pojo.Staff;

import javax.swing.*;
import com.jgoodies.forms.layout.*;
import java.awt.event.*;
import java.util.List;

public class StaffList extends JFrame {

    private JLabel label1;
    private JTextField textField1, textField2, textField3, textField4, textField5;
    private JButton button1, button2, button3;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JRadioButton radioButton1, radioButton2;
    private ButtonGroup genderGroup;
    private StaffDao staffDao;
    private int selectedStaffId = -1;

    public StaffList() {
        staffDao = new StaffDao();  // 初始化StaffDao
        initComponents();
        loadStaffData();  // 加载员工数据
    }

    private void initComponents() {
        // 初始化组件
        label1 = new JLabel("员工姓名：");
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();

        button1 = new JButton("查询");
        button2 = new JButton("确认");
        button3 = new JButton("删除");

        // 设置JRadioButton
        radioButton1 = new JRadioButton("男");
        radioButton2 = new JRadioButton("女");
        genderGroup = new ButtonGroup();
        genderGroup.add(radioButton1);
        genderGroup.add(radioButton2);

        // 设置表格
        table1 = new JTable();
        scrollPane1 = new JScrollPane(table1);

        // 设置表单布局
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "55dlu, $lcgap, 56dlu, $lcgap, 60dlu, $lcgap, 70dlu",
                "25dlu, $lgap, 35dlu, $lgap, 41dlu, $lgap, 26dlu, $lgap, 19dlu, $lgap, 23dlu"));

        // 添加组件
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xywh(3, 1, 3, 1));
        contentPane.add(button1, CC.xy(7, 1));

        // 设置表格
        contentPane.add(scrollPane1, CC.xywh(1, 3, 7, 3));

        contentPane.add(new JLabel("姓名："), CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(3, 7));

        contentPane.add(new JLabel("性别："), CC.xy(5, 7, CC.RIGHT, CC.DEFAULT));
        JPanel genderPanel = new JPanel();
        genderPanel.add(radioButton1);
        genderPanel.add(radioButton2);
        contentPane.add(genderPanel, CC.xy(7, 7));

        contentPane.add(new JLabel("职位："), CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(3, 9));

        contentPane.add(new JLabel("年龄："), CC.xy(5, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField4, CC.xy(7, 9));

        contentPane.add(new JLabel("密码："), CC.xy(1, 11, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField5, CC.xy(3, 11));

        contentPane.add(button2, CC.xy(5, 11));
        contentPane.add(button3, CC.xy(7, 11));

        // 设置按钮事件
        button1.addActionListener(e -> searchStaff());  // 查询按钮事件
        button2.addActionListener(e -> confirmAction());  // 确认按钮事件
        button3.addActionListener(e -> deleteStaff());  // 删除按钮事件
        table1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tableRowClick(e);
            }
        });

        pack();
        setLocationRelativeTo(getOwner());
    }

    // 加载所有员工信息到表格
    private void loadStaffData() {
        List<Staff> staffList = staffDao.getAllStaff();
        String[][] data = new String[staffList.size()][6];
        for (int i = 0; i < staffList.size(); i++) {
            Staff staff = staffList.get(i);
            data[i][0] = String.valueOf(staff.getId());
            data[i][1] = staff.getName();
            data[i][2] = staff.getGender();
            data[i][3] = staff.getPosition();
            data[i][4] = String.valueOf(staff.getAge());
            data[i][5] = staff.getPassword();
        }

        String[] columnNames = {"ID", "姓名", "性别", "职位", "年龄", "密码"};
        table1.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    // 查询员工信息
    private void searchStaff() {
        String name = textField1.getText();
        List<Staff> staffList = staffDao.getStaffByName(name);
        String[][] data = new String[staffList.size()][6];
        for (int i = 0; i < staffList.size(); i++) {
            Staff staff = staffList.get(i);
            data[i][0] = String.valueOf(staff.getId());
            data[i][1] = staff.getName();
            data[i][2] = staff.getGender();
            data[i][3] = staff.getPosition();
            data[i][4] = String.valueOf(staff.getAge());
            data[i][5] = staff.getPassword();
        }

        String[] columnNames = {"ID", "姓名", "性别", "职位", "年龄", "密码"};
        table1.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    // 确认按钮事件，更新员工信息
    private void confirmAction() {
        if (selectedStaffId == -1) {
            JOptionPane.showMessageDialog(this, "请先选择一个员工！");
            return;
        }

        String name = textField2.getText();
        String gender = radioButton1.isSelected() ? "男" : "女";
        String position = textField3.getText();
        int age = Integer.parseInt(textField4.getText());
        String password = textField5.getText();

        Staff updatedStaff = new Staff();
        updatedStaff.setId(selectedStaffId);
        updatedStaff.setName(name);
        updatedStaff.setGender(gender);
        updatedStaff.setPosition(position);
        updatedStaff.setAge(age);
        updatedStaff.setPassword(password);

        boolean success = staffDao.updateStaff(updatedStaff);
        if (success) {
            JOptionPane.showMessageDialog(this, "员工信息更新成功！");
            loadStaffData();  // 刷新数据
        } else {
            JOptionPane.showMessageDialog(this, "更新失败！");
        }
    }

    // 删除员工
    private void deleteStaff() {
        if (selectedStaffId == -1) {
            JOptionPane.showMessageDialog(this, "请先选择一个员工！");
            return;
        }

        boolean success = staffDao.deleteStaffById(selectedStaffId);
        if (success) {
            JOptionPane.showMessageDialog(this, "员工删除成功！");
            loadStaffData();  // 刷新数据
        } else {
            JOptionPane.showMessageDialog(this, "删除失败！");
        }
    }

    // 表格点击事件，显示选择的员工信息
    private void tableRowClick(MouseEvent e) {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow >= 0) {
            selectedStaffId = Integer.parseInt(table1.getValueAt(selectedRow, 0).toString());
            textField2.setText(table1.getValueAt(selectedRow, 1).toString());
            radioButton1.setSelected("男".equals(table1.getValueAt(selectedRow, 2).toString()));
            radioButton2.setSelected("女".equals(table1.getValueAt(selectedRow, 2).toString()));
            textField3.setText(table1.getValueAt(selectedRow, 3).toString());
            textField4.setText(table1.getValueAt(selectedRow, 4).toString());
            textField5.setText(table1.getValueAt(selectedRow, 5).toString());
        }
    }
}
