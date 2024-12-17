package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import pojo.User;
import util.DBUtil;

import static view.MainUser.user;

public class addbrrow extends JFrame {
    public addbrrow() {
        initComponents();
        initCustomComponents();

    }

    private void initCustomComponents() {
        // 提交按钮事件
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBorrowRecord();
            }
        });

        // 重置按钮事件
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
    }

    private void addBorrowRecord() {
        String bookName = textField1.getText().trim();
        String borrower = textField2.getText().trim();

        if (bookName.isEmpty() || borrower.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请填写完整信息！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection connection = DBUtil.getCon();
             PreparedStatement pstmt = connection.prepareStatement(
                     "INSERT INTO borrow_records (book_name, borrower) VALUES (?, ?)")) {
            pstmt.setString(1, bookName);
            pstmt.setString(2, borrower);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "借阅信息添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                resetFields(); // 清空输入框
            } else {
                JOptionPane.showMessageDialog(this, "借阅信息添加失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        textField1.setText(""); // 清空图书名称
        textField2.setText(""); // 清空借阅人
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "49dlu, $lcgap, 52dlu, $lcgap, 61dlu, $lcgap, 29dlu",
            "41dlu, 2*($lgap, 40dlu), $lgap, 37dlu"));

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u540d\u79f0\uff1a");
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xywh(3, 1, 3, 1));

        //---- label2 ----
        label2.setText("\u501f\u9605\u4eba\uff1a");
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xywh(3, 3, 3, 1));

        //---- button1 ----
        button1.setText("\u63d0\u4ea4");
        contentPane.add(button1, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        contentPane.add(button2, CC.xy(5, 5, CC.CENTER, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
