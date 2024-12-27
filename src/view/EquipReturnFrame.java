package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import dao.ManageDao;
import pojo.Manage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class EquipReturnFrame extends JFrame {

    // UI组件声明
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    private JButton button2;

    public EquipReturnFrame() {
        initComponents();
    }

    private void initComponents() {
        label1 = new JLabel();
        textField1 = new JTextField();
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

        setTitle("归还器材列表");
        setIconImage(new ImageIcon(getClass().getResource("/学生管理.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "71dlu, $lcgap, 86dlu, $lcgap, 54dlu, $lcgap, 110dlu, $lcgap, 122dlu",
                "58dlu, $lgap, 55dlu, $lgap, 103dlu, $lgap, 61dlu, $lgap, 73dlu, $lgap, default"));

        label1.setText("器材名称：");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 1));

        button1.setText("查询");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 4f));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryEquipment();
            }
        });
        contentPane.add(button1, CC.xy(9, 1, CC.CENTER, CC.DEFAULT));

        scrollPane1.setViewportView(table1);
        contentPane.add(scrollPane1, CC.xywh(3, 3, 5, 3));

        label3.setText("归还人姓名：");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 3f));
        contentPane.add(label3, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(3, 7));

        label4.setText("归还数量：");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 3f));
        contentPane.add(label4, CC.xy(5, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField4, CC.xy(7, 7));

        label5.setText("借用人学号：");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 3f));
        contentPane.add(label5, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField5, CC.xy(3, 9));

        button2.setText("确认归还");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 4f));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmReturn();
            }
        });
        contentPane.add(button2, CC.xy(9, 9, CC.CENTER, CC.DEFAULT));

        // 添加选择监听器
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table1.getSelectedRow();
                    if (selectedRow != -1) {
                        textField3.setText((String) table1.getValueAt(selectedRow, 1)); // 归还人姓名
                        textField4.setText(String.valueOf(table1.getValueAt(selectedRow, 3))); // 归还数量
                        textField5.setText((String) table1.getValueAt(selectedRow, 5)); // 借用人学号
                    }
                }
            }
        });

        pack();
        setLocationRelativeTo(getOwner());
    }

    private void queryEquipment() {
        String equipmentName = textField1.getText();

        if (equipmentName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入设备名称！", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = new DefaultTableModel();
        table1.setModel(model);
        model.setRowCount(0);  // Clear existing data

        List<Manage> equipmentList = ManageDao.queryEquipment(equipmentName);

        if (!equipmentList.isEmpty()) {
            String[] columnNames = {"设备名称", "借用人", "借用日期", "数量", "状态", "借还人学号", "归还日期"};
            model.setColumnIdentifiers(columnNames);

            for (Manage manage : equipmentList) {
                model.addRow(new Object[]{
                        manage.getName(),
                        manage.getBorrowerName(),
                        manage.getBorrowDate(),
                        manage.getQuantity(),
                        manage.getStatus(),
                        manage.getBorrowerStudentId(),
                        manage.getReturnDate() != null ? manage.getReturnDate() : ""
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "没有找到相关设备！", "信息", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void confirmReturn() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要归还的设备！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String returnerName = textField3.getText();
        String studentId = textField5.getText();
        String returnDate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        int returnQuantity = Integer.parseInt(textField4.getText());

        String borrowerName = (String) table1.getValueAt(selectedRow, 1);

        if (ManageDao.updateReturnDate(borrowerName, returnQuantity, studentId, returnDate)) {
            JOptionPane.showMessageDialog(this, "设备已成功归还！", "成功", JOptionPane.INFORMATION_MESSAGE);
            queryEquipment(); // 刷新表格
        } else {
            JOptionPane.showMessageDialog(this, "归还失败，请重试！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}



