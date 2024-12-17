package view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import util.DBUtil;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author 晚吟
 */
public class booklist extends JFrame {
    public booklist() {
        initComponents();
        initCustomComponents();
    }

    private void initCustomComponents() {
        loadBookCategories(comboBox1);
        loadBookCategories(comboBox2);
        loadTableData();

        // 查询按钮事件
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBooks();
            }
        });

        // 修改按钮事件
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBook();
            }
        });

        // 删除按钮事件
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        // 表格行选择事件
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    populateFieldsFromTable();
                }
            }
        });
    }

    private void loadBookCategories(JComboBox comboBox) {
        try (Connection connection = DBUtil.getCon();
             Statement stmt = connection.createStatement()) {
            String query = "SELECT DISTINCT category FROM books";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                comboBox.addItem(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "加载类别失败！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTableData() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "名称", "作者", "类别", "库存"}, 0);
        try (Connection connection = DBUtil.getCon();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("category"), rs.getInt("stock")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "加载图书列表失败！", "错误", JOptionPane.ERROR_MESSAGE);
        }
        table1.setModel(model);
    }

    private void searchBooks() {
        String title = textField1.getText().trim();
        String category = (String) comboBox1.getSelectedItem();

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "名称", "作者", "类别", "库存"}, 0);
        try (Connection connection = DBUtil.getCon();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM books WHERE title LIKE ? AND category LIKE ?")) {
            pstmt.setString(1, "%" + title + "%");
            pstmt.setString(2, category == null ? "%" : category);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("category"), rs.getInt("stock")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "查询失败！", "错误", JOptionPane.ERROR_MESSAGE);
        }
        table1.setModel(model);
    }

    private void updateBook() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要修改的图书！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) table1.getValueAt(selectedRow, 0);
        String title = textField2.getText().trim();
        String author = textField3.getText().trim();
        String category = (String) comboBox2.getSelectedItem();
        String stockText = textField4.getText().trim();

        if (title.isEmpty() || author.isEmpty() || category == null || stockText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请填写完整信息！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int stock;
        try {
            stock = Integer.parseInt(stockText);
            if (stock < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "库存数量必须是非负整数！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection connection = DBUtil.getCon();
             PreparedStatement pstmt = connection.prepareStatement("UPDATE books SET title = ?, author = ?, category = ?, stock = ? WHERE id = ?")) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, category);
            pstmt.setInt(4, stock);
            pstmt.setInt(5, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "图书修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "图书修改失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBook() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要删除的图书！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) table1.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "确定要删除这本图书吗？", "确认", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection connection = DBUtil.getCon();
                 PreparedStatement pstmt = connection.prepareStatement("DELETE FROM books WHERE id = ?")) {
                pstmt.setInt(1, id);

                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "图书删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(this, "图书删除失败！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "数据库错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void populateFieldsFromTable() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow != -1) {
            textField2.setText((String) table1.getValueAt(selectedRow, 1));
            textField3.setText((String) table1.getValueAt(selectedRow, 2));
            comboBox2.setSelectedItem(table1.getValueAt(selectedRow, 3));
            textField4.setText(String.valueOf(table1.getValueAt(selectedRow, 4)));
        }
    }


        private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        comboBox1 = new JComboBox();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label3 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        comboBox2 = new JComboBox();
        button2 = new JButton();
        label5 = new JLabel();
        textField3 = new JTextField();
        label6 = new JLabel();
        textField4 = new JTextField();
        button3 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "36dlu, $lcgap, 59dlu, $lcgap, 63dlu, $lcgap, 51dlu, $lcgap, 71dlu, $lcgap, 58dlu",
            "31dlu, $lgap, 68dlu, $lgap, 39dlu, $lgap, 44dlu, $lgap, 45dlu"));

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u540d\u79f0\uff1a");
        contentPane.add(label1, CC.xy(3, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(5, 1));

        //---- label2 ----
        label2.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");
        contentPane.add(label2, CC.xy(7, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(comboBox1, CC.xy(9, 1));

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        contentPane.add(button1, CC.xy(11, 1));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, CC.xywh(1, 3, 11, 3));

        //---- label3 ----
        label3.setText("\u56fe\u4e66\u540d\u79f0\uff1a");
        contentPane.add(label3, CC.xy(3, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(5, 7));

        //---- label4 ----
        label4.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");
        contentPane.add(label4, CC.xy(7, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(comboBox2, CC.xy(9, 7));

        //---- button2 ----
        button2.setText("\u786e\u8ba4\u4fee\u6539");
        contentPane.add(button2, CC.xy(11, 7));

        //---- label5 ----
        label5.setText("\u56fe\u4e66\u4f5c\u8005\uff1a");
        contentPane.add(label5, CC.xy(3, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(5, 9));

        //---- label6 ----
        label6.setText("\u56fe\u4e66\u6570\u91cf\uff1a");
        contentPane.add(label6, CC.xy(7, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField4, CC.xy(9, 9));

        //---- button3 ----
        button3.setText("\u5220\u9664\u56fe\u4e66");
        contentPane.add(button3, CC.xy(11, 9));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JComboBox comboBox1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label3;
    private JTextField textField2;
    private JLabel label4;
    private JComboBox comboBox2;
    private JButton button2;
    private JLabel label5;
    private JTextField textField3;
    private JLabel label6;
    private JTextField textField4;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
