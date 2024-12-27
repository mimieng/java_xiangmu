package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import dao.QueryDao;
import pojo.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class EquipQueryFrame extends JFrame {

    private JTable table1;
    private JTextField txt_name1, txt_name2, txt_num;
    private JButton btn_query, btn_update, btn_delete;

    public EquipQueryFrame() {
        initComponents();
    }

    private void initComponents() {
        // Initialize components
        JLabel label1 = new JLabel("装备名称：");
        txt_name1 = new JTextField();
        btn_query = new JButton("查询");
        JScrollPane scrollPane1 = new JScrollPane();
        table1 = new JTable();
        JLabel label2 = new JLabel("装备名称：");
        txt_name2 = new JTextField();
        btn_update = new JButton("确认修改");
        JLabel label3 = new JLabel("装备个数：");
        txt_num = new JTextField();
        btn_delete = new JButton("删除");

        // Set up the window
        setTitle("装备列表窗口");
        setIconImage(new ImageIcon(getClass().getResource("/班级名称.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "90dlu, $lcgap, 107dlu, $lcgap, 108dlu, $lcgap, 103dlu",
                "54dlu, $lgap, 76dlu, $lgap, 56dlu, $lgap, 57dlu, $lgap, 66dlu"));

        // Equip name label and text field for query
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name1, CC.xy(3, 1));

        // Query button
        btn_query.setFont(btn_query.getFont().deriveFont(btn_query.getFont().getSize() + 4f));
        contentPane.add(btn_query, CC.xy(5, 1, CC.CENTER, CC.DEFAULT));

        // Table for displaying query results
        scrollPane1.setViewportView(table1);
        contentPane.add(scrollPane1, CC.xywh(3, 3, 3, 3));

        // Equip name label and text field for update
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 4f));
        contentPane.add(label2, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name2, CC.xy(3, 7));

        // Confirm update button
        btn_update.setFont(btn_update.getFont().deriveFont(btn_update.getFont().getSize() + 4f));
        contentPane.add(btn_update, CC.xy(5, 7, CC.CENTER, CC.DEFAULT));

        // Equip quantity label and text field
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 4f));
        contentPane.add(label3, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_num, CC.xy(3, 9));

        // Delete button
        btn_delete.setFont(btn_delete.getFont().deriveFont(btn_delete.getFont().getSize() + 4f));
        contentPane.add(btn_delete, CC.xy(5, 9, CC.CENTER, CC.DEFAULT));

        // Add action listeners
        btn_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryAction();
            }
        });

        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAction();
            }
        });

        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAction();
            }
        });

        // Add mouse listener for table to display selected row data in input fields
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table1.getSelectedRow();  // Get the selected row
                if (row != -1) {
                    // Get the data from the selected row and set them in the input fields
                    txt_name2.setText((String) table1.getValueAt(row, 1)); // Set name to txt_name2
                    txt_num.setText(table1.getValueAt(row, 2).toString()); // Set quantity to txt_num
                }
            }
        });

        // Finalize layout
        pack();
        setLocationRelativeTo(getOwner());
    }

    // Query action for searching equipment
    private void queryAction() {
        String name = txt_name1.getText(); // Get input from the user for equipment name
        QueryDao dao = new QueryDao();

        // Query the database for matching equipment
        List<Query> results = dao.queryEquipment(name);

        // Display the query results in the table
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "名称", "数量"}, 0);
        for (Query q : results) {
            model.addRow(new Object[]{q.getId(), q.getName(), q.getQuantity()});
        }
        table1.setModel(model);
    }

    // Update action for modifying selected equipment
    private void updateAction() {
        // Get the updated name and quantity from the input fields
        String name = txt_name2.getText();
        String numStr = txt_num.getText();

        if (name.isEmpty() || numStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "设备名称和数量不能为空！");
            return;
        }

        // Parse quantity
        int quantity;
        try {
            quantity = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "数量必须是一个有效的整数！");
            return;
        }

        // Get the selected equipment ID from the table
        int row = table1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "请选择要修改的设备！");
            return;
        }
        int id = (int) table1.getValueAt(row, 0); // Get the ID of the selected row

        // Create a Query object to hold the updated data
        Query updatedEquip = new Query(id, name, quantity);

        // Call the DAO to update the equipment in the database
        QueryDao dao = new QueryDao();
        boolean success = dao.updateEquipment(updatedEquip);

        if (success) {
            JOptionPane.showMessageDialog(this, "设备信息更新成功！");
            // Refresh the table after update
            queryAction();
        } else {
            JOptionPane.showMessageDialog(this, "设备信息更新失败！");
        }
    }

    // Delete action for removing selected equipment
    private void deleteAction() {
        // Get the selected equipment ID from the table
        int row = table1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "请选择要删除的设备！");
            return;
        }

        int id = (int) table1.getValueAt(row, 0); // Get the ID of the selected row

        // Ask for confirmation
        int result = JOptionPane.showConfirmDialog(this, "确认删除该设备吗？", "删除设备", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Call the DAO to delete the equipment from the database
            QueryDao dao = new QueryDao();
            boolean success = dao.deleteEquipment(id);

            if (success) {
                JOptionPane.showMessageDialog(this, "设备删除成功！");
                // Refresh the table after deletion
                queryAction();
            } else {
                JOptionPane.showMessageDialog(this, "设备删除失败！");
            }
        }
    }

    // Main method to run the application


}
