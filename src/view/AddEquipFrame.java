package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import dao.QueryDao;  // 引入数据库操作类
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEquipFrame extends JFrame {

    private JLabel label1;
    private JTextField txt_name;
    private JLabel label2;
    private JTextField textField1;
    private JButton btn_sure;
    private JButton btn_reset;

    public AddEquipFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        txt_name = new JTextField();
        label2 = new JLabel();
        textField1 = new JTextField();
        btn_sure = new JButton();
        btn_reset = new JButton();

        //======== this ========
        setTitle("添加器目信息");
        setIconImage(new ImageIcon(getClass().getResource("/添加.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "105dlu, $lcgap, 131dlu, $lcgap, 115dlu",
                "54dlu, $lgap, 107dlu, $lgap, 106dlu, $lgap, default"));

        //---- label1 ----
        label1.setText("器材名称：");
        label1.setIcon(new ImageIcon(getClass().getResource("/班级名称.png")));
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name, CC.xy(3, 1));

        //---- label2 ----
        label2.setText("器材个数：");
        label2.setIcon(new ImageIcon(getClass().getResource("/班级名称.png")));
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 3));

        //---- btn_sure ----
        btn_sure.setText("确认");
        btn_sure.setIcon(new ImageIcon(getClass().getResource("/确认.png")));
        btn_sure.setFont(btn_sure.getFont().deriveFont(btn_sure.getFont().getSize() + 4f));
        contentPane.add(btn_sure, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));

        //---- btn_reset ----
        btn_reset.setText("重置");
        btn_reset.setIcon(new ImageIcon(getClass().getResource("/重置.png")));
        btn_reset.setFont(btn_reset.getFont().deriveFont(btn_reset.getFont().getSize() + 4f));
        contentPane.add(btn_reset, CC.xy(5, 5, CC.LEFT, CC.DEFAULT));

        // Action listeners
        btn_sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertAction();
            }
        });

        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // Reset input fields
    private void resetFields() {
        txt_name.setText("");
        textField1.setText("");
    }

    // Insert action for adding equipment
    private void insertAction() {
        String name = txt_name.getText().trim();  // Get equipment name
        String quantityStr = textField1.getText().trim();  // Get equipment quantity

        // Validate input
        if (name.isEmpty() || quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "设备名称和数量不能为空！", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate quantity
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "数量必须是一个有效的整数！", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a QueryDao object and insert the equipment
        QueryDao dao = new QueryDao();  // Create a new DAO object
        boolean success = dao.insertEquipment(name, quantity);  // Insert into database

        // Show message based on success or failure
        if (success) {
            JOptionPane.showMessageDialog(this, "设备添加成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
            resetFields();  // Reset fields after successful insertion
        } else {
            JOptionPane.showMessageDialog(this, "设备添加失败！", "失败", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method for testing
}