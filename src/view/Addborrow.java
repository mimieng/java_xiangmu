package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import pojo.User;
import util.DBUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static view.MainUser.user;

public class Addborrow extends JFrame {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox<String> bookComboBox; // 图书下拉框
    private JButton button1;
    private JButton button2;

    public Addborrow() {
        initComponents();
        if (MainUser.usertype.equals("学生")) {
            User admin = user;
            label2.setText("【学生】" + admin.getName());
        }
    }

    private void initComponents() {
        label1 = new JLabel("借阅人：");
        label2 = new JLabel(); // 借阅人默认为“小段”，也可以改成输入框
        label3 = new JLabel("图书名称：");

        bookComboBox = new JComboBox<>(getBooksFromDatabase()); // 初始化下拉框数据
        button1 = new JButton("确认");
        button2 = new JButton("重置");

        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "71dlu, $lcgap, 133dlu",
                "36dlu, $lgap, 37dlu, $lgap, 39dlu"));

        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(label2, CC.xy(3, 1, CC.LEFT, CC.DEFAULT));
        contentPane.add(label3, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(bookComboBox, CC.xy(3, 3)); // 使用下拉框替代文本框
        contentPane.add(button1, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(button2, CC.xy(3, 5, CC.CENTER, CC.DEFAULT));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleConfirmAction();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleResetAction();
            }
        });

        pack();
        setLocationRelativeTo(getOwner());
    }

    private void handleConfirmAction() {
        String borrower = label2.getText().trim();
        String selectedBook = (String) bookComboBox.getSelectedItem(); // 获取选中的书名
        String borrowDate = getCurrentTime();

        if (insertBorrowRecord(selectedBook, borrower, borrowDate)) {
            JOptionPane.showMessageDialog(this, "借阅成功！\n图书名称：" + selectedBook + "\n借阅人：" + borrower, "成功", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "借阅失败，请检查数据库！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleResetAction() {
        bookComboBox.setSelectedIndex(0); // 重置下拉框选项
    }

    // 获取当前时间字符串
    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    // 从数据库的 books 表获取书名列表
    private String[] getBooksFromDatabase() {
        List<String> books = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getCon();
            String sql = "SELECT title FROM books";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                books.add(rs.getString("title"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "无法获取书籍列表，请检查数据库！", "错误", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBUtil.closeCon(con);
        }

        return books.toArray(new String[0]);
    }

    // 插入借阅记录到 borrow_records 表
    private boolean insertBorrowRecord(String bookName, String borrower, String borrowDate) {
        String sql = "INSERT INTO borrow_records (book_name, borrower, borrow_date) VALUES (?, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getCon();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, bookName); // 直接保存图书名称
            pstmt.setString(2, borrower);
            pstmt.setString(3, borrowDate);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBUtil.closeCon(con);
        }
    }


}
