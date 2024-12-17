package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import util.DBUtil;

/**
 * @author 晚吟
 */
public class userList extends JFrame {
    private DefaultTableModel tableModel;

    public userList() {
        initComponents();
        initCustomComponents();
    }

    private void initCustomComponents() {
        // 初始化表格模型
        tableModel = new DefaultTableModel(new String[]{"ID", "用户名", "性别", "年龄", "密码"}, 0);
        table1.setModel(tableModel);

        // 查询按钮事件
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUserData();
            }
        });

        // 修改按钮事件
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        // 删除按钮事件
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        // 添加表格行选择事件
        table1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displaySelectedRowData();
            }
        });
    }

    private void displaySelectedRowData() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow != -1) {
            // 填充用户数据到输入框
            textField2.setText(tableModel.getValueAt(selectedRow, 1).toString()); // 用户名
            textField3.setText(tableModel.getValueAt(selectedRow, 2).toString()); // 性别
            textField4.setText(tableModel.getValueAt(selectedRow, 3).toString()); // 年龄
        }
    }


    private void loadUserData() {
        tableModel.setRowCount(0); // 清空表格数据
        String searchName = textField1.getText().trim();

        try (Connection connection = DBUtil.getCon();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT * FROM user WHERE name LIKE ?")) {
            pstmt.setString(1, "%" + searchName + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getString("password")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "查询失败！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUser() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请选择要修改的用户！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);
        String name = textField2.getText().trim();
        String gender = textField3.getText().trim();
        String ageText = textField4.getText().trim();
        String password = textField4.getText().trim();

        if (name.isEmpty() || gender.isEmpty() || ageText.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请填写完整信息！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageText);

            try (Connection connection = DBUtil.getCon();
                 PreparedStatement pstmt = connection.prepareStatement(
                         "UPDATE user SET name=?, gender=?, age=?, password=? WHERE id=?")) {
                pstmt.setString(1, name);
                pstmt.setString(2, gender);
                pstmt.setInt(3, age);
                pstmt.setString(4, password);
                pstmt.setInt(5, userId);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "用户修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    loadUserData();
                } else {
                    JOptionPane.showMessageDialog(this, "用户修改失败！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "年龄必须是整数！", "警告", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteUser() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请选择要删除的用户！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "确定要删除该用户吗？", "确认", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try (Connection connection = DBUtil.getCon();
             PreparedStatement pstmt = connection.prepareStatement("DELETE FROM user WHERE id=?")) {
            pstmt.setInt(1, userId);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "用户删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                loadUserData();
            } else {
                JOptionPane.showMessageDialog(this, "用户删除失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "72dlu, $lcgap, 66dlu, $lcgap, 58dlu, $lcgap, 60dlu",
                "33dlu, $lgap, 49dlu, $lgap, 46dlu, $lgap, 29dlu, $lgap, 35dlu, $lgap, 28dlu"));

        //---- label1 ----
        label1.setText("用户姓名：");
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xywh(3, 1, 3, 1));

        //---- button1 ----
        button1.setText("查询");
        contentPane.add(button1, CC.xy(7, 1));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, CC.xywh(1, 3, 7, 3));

        //---- label2 ----
        label2.setText("用户姓名：");
        contentPane.add(label2, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(3, 7));

        //---- label3 ----
        label3.setText("用户性别：");
        contentPane.add(label3, CC.xy(5, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(7, 7));

        //---- label4 ----
        label4.setText("用户年龄：");
        contentPane.add(label4, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField4, CC.xy(3, 9));

        //---- button2 ----
        button2.setText("确认修改");
        contentPane.add(button2, CC.xy(1, 11, CC.RIGHT, CC.DEFAULT));

        //---- button3 ----
        button3.setText("删除信息");
        contentPane.add(button3, CC.xy(5, 11));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
