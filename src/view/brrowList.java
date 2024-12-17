package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import javax.swing.table.*;
import java.sql.*;
import util.DBUtil; // Import the DBUtil class

public class brrowList extends JFrame {
    private JLabel labelBookName, labelBorrower, labelNewBookName;
    private JTextField textFieldBookName, textFieldNewBookName;
    private JComboBox<String> comboBoxBorrower;  // JComboBox for selecting borrower
    private JButton buttonSearch, buttonUpdate, buttonDelete;
    private JScrollPane scrollPane;
    private JTable table;

    public brrowList() {
        initComponents();
        loadTableData(); // Initial data load
        loadBorrowers(); // Load borrower names into JComboBox
    }

    private void initComponents() {
        labelBookName = new JLabel("图书名称：");
        textFieldBookName = new JTextField();
        buttonSearch = new JButton("查询");

        labelBorrower = new JLabel("借阅人：");
        comboBoxBorrower = new JComboBox<>();  // Use JComboBox for borrower selection
        buttonUpdate = new JButton("确认修改");

        labelNewBookName = new JLabel("图书名称：");
        textFieldNewBookName = new JTextField();
        buttonDelete = new JButton("确认删除");

        table = new JTable();
        scrollPane = new JScrollPane(table);

        // Layout setup
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "53dlu, $lcgap, 59dlu, $lcgap, 133dlu, $lcgap, 78dlu",
                "47dlu, $lgap, 55dlu, $lgap, 44dlu, $lgap, 52dlu, 2*($lgap, 36dlu)"));

        // Add components to layout
        contentPane.add(labelBookName, CC.xy(3, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textFieldBookName, CC.xy(5, 1));
        contentPane.add(buttonSearch, CC.xy(7, 1, CC.LEFT, CC.DEFAULT));
        contentPane.add(scrollPane, CC.xywh(1, 3, 7, 5));
        contentPane.add(labelBorrower, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(comboBoxBorrower, CC.xy(3, 9));  // Add JComboBox for borrower
        contentPane.add(buttonUpdate, CC.xy(7, 9, CC.CENTER, CC.DEFAULT));
        contentPane.add(labelNewBookName, CC.xy(1, 11, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textFieldNewBookName, CC.xy(3, 11));
        contentPane.add(buttonDelete, CC.xy(7, 11, CC.CENTER, CC.DEFAULT));

        // Button actions
        buttonSearch.addActionListener(e -> searchRecords());
        buttonUpdate.addActionListener(e -> updateRecord());
        buttonDelete.addActionListener(e -> deleteRecord());

        // Pack and center the window
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void loadTableData() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"图书名称", "借阅人", "借阅时间"}, 0);
        try (Connection conn = DBUtil.getCon();
             PreparedStatement stmt = conn.prepareStatement("SELECT book_name, borrower, borrow_date FROM borrow_records");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String bookName = rs.getString("book_name");
                String borrowerName = rs.getString("borrower");
                Date bookDate = rs.getDate("borrow_date");
                model.addRow(new Object[]{bookName, borrowerName, bookDate});
            }
        } catch (SQLException e) {
            showError("加载数据错误", e);
        }
        table.setModel(model);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String bookName = (String) table.getValueAt(selectedRow, 0);
                    String borrowerName = (String) table.getValueAt(selectedRow, 1);
                    // Populate text fields with selected row data
                    textFieldBookName.setText(bookName);
                    comboBoxBorrower.setSelectedItem(borrowerName);  // Set selected borrower in JComboBox
                    textFieldNewBookName.setText(bookName);
                }
            }
        });
    }

    private void loadBorrowers() {
        try (Connection conn = DBUtil.getCon();
             PreparedStatement stmt = conn.prepareStatement("SELECT name FROM user");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                comboBoxBorrower.addItem(rs.getString("name"));  // Add each user name to JComboBox
            }
        } catch (SQLException e) {
            showError("加载借阅人列表错误", e);
        }
    }

    private void searchRecords() {
        String bookName = textFieldBookName.getText().trim();
        if (!bookName.isEmpty()) {
            String query = "SELECT book_name, borrower, borrow_date FROM borrow_records WHERE book_name LIKE ?";
            DefaultTableModel model = new DefaultTableModel(new String[]{"图书名称", "借阅人", "借阅时间"}, 0);
            try (Connection conn = DBUtil.getCon();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + bookName + "%");
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String book = rs.getString("book_name");
                        String borrower = rs.getString("borrower");
                        Date bookDate = rs.getDate("borrow_date");
                        model.addRow(new Object[]{book, borrower, bookDate});
                    }
                }
            } catch (SQLException e) {
                showError("查询错误", e);
            }
            table.setModel(model);
        }
    }

    private void updateRecord() {
        String bookName = textFieldBookName.getText().trim();
        String borrowerName = (String) comboBoxBorrower.getSelectedItem();  // Get selected borrower from JComboBox
        String newBookName = textFieldNewBookName.getText().trim();

        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1 && !bookName.isEmpty() && borrowerName != null && !newBookName.isEmpty()) {
            String oldBookName = (String) table.getValueAt(selectedRow, 0);
            String query = "UPDATE borrow_records SET book_name = ?, borrower = ? WHERE book_name = ?";

            try (Connection conn = DBUtil.getCon();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newBookName);
                stmt.setString(2, borrowerName);
                stmt.setString(3, oldBookName);
                stmt.executeUpdate();
                loadTableData();
            } catch (SQLException e) {
                showError("更新错误", e);
            }
        }
    }

    private void deleteRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String bookName = (String) table.getValueAt(selectedRow, 0);
            String query = "DELETE FROM borrow_records WHERE book_name = ?";

            try (Connection conn = DBUtil.getCon();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, bookName);
                stmt.executeUpdate();
                loadTableData();
            } catch (SQLException e) {
                showError("删除错误", e);
            }
        }
    }

    private void showError(String message, SQLException e) {
        JOptionPane.showMessageDialog(this, message + "\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
