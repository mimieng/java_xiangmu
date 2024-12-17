package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import javax.swing.table.*;
import java.sql.*;
import util.DBUtil; // Import the DBUtil class

public class libraryList2 extends JFrame {
    private JLabel labelBookName;
    private JTextField textFieldBookName;
    private JButton buttonSearch;
    private JScrollPane scrollPane;
    private JTable table;

    public libraryList2() {
        initComponents();
        loadTableData(); // Initial data load
    }

    private void initComponents() {
        // 定义组件
        labelBookName = new JLabel("图书名称：");
        textFieldBookName = new JTextField();
        buttonSearch = new JButton("查询");

        table = new JTable();
        scrollPane = new JScrollPane(table);

        // 布局设置
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "53dlu, $lcgap, 59dlu, $lcgap, 133dlu",
                "47dlu, $lgap, 55dlu, $lgap, 36dlu"));

        // 添加组件到布局
        contentPane.add(labelBookName, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textFieldBookName, CC.xy(3, 1));
        contentPane.add(buttonSearch, CC.xy(5, 1, CC.LEFT, CC.DEFAULT));

        contentPane.add(scrollPane, CC.xywh(1, 3, 5, 3));

        // 事件监听器
        buttonSearch.addActionListener(e -> searchBooks());

        // 窗口设置
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void loadTableData() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "图书名称", "类别", "作者", "库存"}, 0);
        try (Connection conn = DBUtil.getCon();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String category = rs.getString("category");
                String author = rs.getString("author");
                int stock = rs.getInt("stock");
                model.addRow(new Object[]{id, title, category, author, stock});
            }
        } catch (SQLException e) {
            showError("加载数据错误", e);
        }
        table.setModel(model);
    }

    private void searchBooks() {
        String bookName = textFieldBookName.getText().trim();
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "图书名称", "类别", "作者", "库存"}, 0);
        String query = "SELECT * FROM books WHERE title LIKE ?";
        try (Connection conn = DBUtil.getCon();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + bookName + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String category = rs.getString("category");
                    String author = rs.getString("author");
                    int stock = rs.getInt("stock");
                    model.addRow(new Object[]{id, title, category, author, stock});
                }
            }
        } catch (SQLException e) {
            showError("查询错误", e);
        }
        table.setModel(model);
    }

    private void showError(String message, SQLException e) {
        JOptionPane.showMessageDialog(this, message + "\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }



}
