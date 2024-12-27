package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import util.DBUtil;

/**
 * @author 晚吟
 */
public class AddEquipFrame2 extends JFrame {
    public AddEquipFrame2() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        textField3 = new JTextField();
        label3 = new JLabel();
        textField4 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "51dlu, $lcgap, 109dlu",
            "36dlu, 2*($lgap, 35dlu), 2*($lgap, 34dlu)"));

        //---- label1 ----
        label1.setText("\u8bbe\u5907\u540d\u79f0\uff1a");
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 1));

        //---- label2 ----
        label2.setText("\u79df\u501f\u4eba\uff1a");
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(3, 3));

        //---- label4 ----
        label4.setText("\u6570\u91cf\uff1a");
        contentPane.add(label4, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(3, 5));

        //---- label3 ----
        label3.setText("\u5b66\u53f7\uff1a");
        contentPane.add(label3, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField4, CC.xy(3, 7));

        //---- button1 ----
        button1.setText("\u786e\u8ba4");
        contentPane.add(button1, CC.xy(1, 9, CC.CENTER, CC.DEFAULT));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        contentPane.add(button2, CC.xy(3, 9, CC.CENTER, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

        // Add ActionListeners
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEquipmentBorrow();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
    }

    private void addEquipmentBorrow() {
        String equipmentName = textField1.getText();
        String borrowerName = textField2.getText();
        String quantityStr = textField3.getText();
        String studentId = textField4.getText();

        // Validate input
        if (equipmentName.isEmpty() || borrowerName.isEmpty() || quantityStr.isEmpty() || studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "\u8bf7\u586b\u5199\u6240\u6709\u5b57\u6bb5!", "\u9519\u8bef", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "\u8bf7\u8f93\u5165\u5408\u6cd5\u6570\u91cf!", "\u9519\u8bef", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insert into database
        String sql = "INSERT INTO equipment_borrow (equipment_name, borrower_name, quantity, borrower_student_id, borrow_date, status) " +
                "VALUES (?, ?, ?, ?, NOW(), '已借')";

        try (Connection conn = DBUtil.getCon(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipmentName);
            pstmt.setString(2, borrowerName);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, studentId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "\u6210\u529f\u6dfb\u52a0\u8bbe\u5907\u79df\u501f\u8bb0\u5f55!", "\u6210\u529f", JOptionPane.INFORMATION_MESSAGE);
                resetFields();
            } else {
                JOptionPane.showMessageDialog(this, "\u6dfb\u52a0\u5931\u8d25!", "\u9519\u8bef", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "\u6570\u636e\u5e93\u64cd\u4f5c\u5931\u8d25!", "\u9519\u8bef", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label4;
    private JTextField textField3;
    private JLabel label3;
    private JTextField textField4;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
