package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class borrowList extends JFrame {
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;

    public borrowList() {
        initComponents();
    }

    private void initComponents() {
        label1 = new JLabel("图书名称：");
        textField1 = new JTextField();
        button1 = new JButton("查询");
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "72dlu, $lcgap, 64dlu, $lcgap, 61dlu",
                "26dlu, $lgap, 58dlu, $lgap, 62dlu"));

        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 1));
        contentPane.add(button1, CC.xy(5, 1, CC.CENTER, CC.DEFAULT));

        scrollPane1.setViewportView(table1);
        contentPane.add(scrollPane1, CC.xywh(1, 3, 5, 3));

        // 设置表格的默认模型
        table1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "图书名称", "借阅人", "借阅日期"}
        ));

        // 禁止编辑表格内容
        table1.setEnabled(false);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearchAction();
            }
        });

        pack();
        setLocationRelativeTo(getOwner());
    }

    private void handleSearchAction() {
        String bookName = textField1.getText().trim();

        // 从数据库获取数据并显示
        loadBorrowRecords(bookName);
    }

    private void loadBorrowRecords(String bookName) {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);  // 清空表格内容

        String sql;
        if (bookName.isEmpty()) {
            sql = "SELECT * FROM borrow_records";  // 查询所有记录
        } else {
            sql = "SELECT * FROM borrow_records WHERE book_name LIKE ?";  // 根据图书名称查询
        }

        try (Connection con = DBUtil.getCon();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            if (!bookName.isEmpty()) {
                pstmt.setString(1, "%" + bookName + "%");
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // 获取数据并添加到表格中
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("book_name"),
                        rs.getString("borrower"),
                        rs.getString("borrow_date")
                };
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "查询失败，请检查数据库连接！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }


}
