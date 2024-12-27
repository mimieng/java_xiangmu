package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import dao.ManageDao;
import pojo.Manage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class EquipManageFrame extends JFrame {

    private JLabel label1, label2, label3, label4, label5;
    private JTextField textField1, textField2, textField3, textField4, textField5;
    private JButton button1, button2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private DefaultTableModel tableModel;
    private ManageDao manageDao;

    public EquipManageFrame() {
        manageDao = new ManageDao();  // Initialize DAO for database interaction
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();
        button2 = new JButton();

        //======== this ========
        setTitle("借还器材列表");
        setIconImage(new ImageIcon(getClass().getResource("/用户名.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "65dlu, $lcgap, 77dlu, $lcgap, 56dlu, $lcgap, 94dlu, $lcgap, 88dlu",
                "71dlu, $lgap, 90dlu, $lgap, 30dlu, $lgap, 53dlu, $lgap, 34dlu, $lgap, 49dlu"));

        // ---- label1 ----
        label1.setText("器材名称：");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 1));

        // ---- label2 ----
        label2.setText("所借班级：");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 4f));
        contentPane.add(label2, CC.xy(5, 1));
        contentPane.add(textField2, CC.xy(7, 1));

        // ---- button1 (查询) ----
        button1.setText("查询");
        button1.setIcon(new ImageIcon(getClass().getResource("/搜索.png")));
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 4f));
        button1.addActionListener(e -> searchEquipment());
        contentPane.add(button1, CC.xy(9, 1, CC.CENTER, CC.DEFAULT));

        // ---- table1 ----
        tableModel = new DefaultTableModel();
        table1.setModel(tableModel);
        tableModel.setColumnIdentifiers(new String[]{"ID", "器材名称", "类别", "借用人", "学号", "借出日期", "归还日期", "数量", "状态"});
        scrollPane1.setViewportView(table1);
        contentPane.add(scrollPane1, CC.xywh(3, 3, 5, 3));

        // ---- label3 ----
        label3.setText("借用人姓名：");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 4f));
        contentPane.add(label3, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(3, 7));

        // ---- label4 ----
        label4.setText("借用个数：");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 4f));
        contentPane.add(label4, CC.xy(5, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField4, CC.xy(7, 7));

        // ---- label5 ----
        label5.setText("借用人学号：");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 4f));
        contentPane.add(label5, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField5, CC.xy(3, 9));

        // ---- button2 (确认借出) ----
        button2.setText("确认借出");
        button2.setIcon(new ImageIcon(getClass().getResource("/确认.png")));
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 2f));
        button2.addActionListener(e -> borrowEquipment());
        contentPane.add(button2, CC.xy(7, 9, CC.RIGHT, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
    }

    // Search equipment by name and display results in table
    private void searchEquipment() {
        String name = textField1.getText().trim();
        List<Manage> borrowList = manageDao.searchEquipmentByName(name);

        // Clear existing table data
        tableModel.setRowCount(0);

        // Populate the table with the results
        for (Manage manage : borrowList) {
            tableModel.addRow(new Object[]{
                    manage.getId(),
                    manage.getName(),
                    manage.getCategory(),
                    manage.getBorrowerName(),
                    manage.getBorrowerStudentId(),
                    manage.getBorrowDate(),
                    manage.getReturnDate(),
                    manage.getQuantity(),
                    manage.getStatus()
            });
        }
    }

    // Borrow equipment by adding a new record
    private void borrowEquipment() {
        String name = textField1.getText().trim();
        String category = textField2.getText().trim();
        String borrowerName = textField3.getText().trim();
        String borrowerStudentId = textField5.getText().trim();
        int quantity = Integer.parseInt(textField4.getText().trim());
        String status = "借出";  // Default status: borrowed

        // Handle borrow date and return date (validation)
        String borrowDateStr = textField3.getText().trim(); // assuming this field contains a date
        String returnDateStr = textField4.getText().trim(); // assuming this field contains a date

        LocalDate borrowDate = (borrowDateStr.isEmpty()) ? LocalDate.now() : LocalDate.parse(borrowDateStr);
        LocalDate returnDate = (returnDateStr.isEmpty()) ? borrowDate.plusDays(7) : LocalDate.parse(returnDateStr);

        Manage manage = new Manage();
        manage.setName(name);
        manage.setCategory(category);
        manage.setBorrowerName(borrowerName);
        manage.setBorrowerStudentId(borrowerStudentId);
        manage.setQuantity(quantity);
        manage.setStatus(status);
        manage.setBorrowDate(borrowDate);
        manage.setReturnDate(returnDate);

        boolean isSuccess = manageDao.borrowEquipment(manage);
        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "借出成功！");
            clearFields();  // Clear the form fields after borrowing
        } else {
            JOptionPane.showMessageDialog(this, "借出失败，请重试！");
        }
    }

    // Clear input fields
    private void clearFields() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
    }
}
