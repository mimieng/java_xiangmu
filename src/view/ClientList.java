package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import dao.ClientDao;
import pojo.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClientList extends JFrame {
    private int selectedClientId = -1; // Track the selected client ID for update/delete operations

    public ClientList() {
        initComponents();
        loadClientData();  // Load client data from the database when the form is initialized
    }

    // Load client data from the database and populate the table
    private void loadClientData() {
        ClientDao clientDao = new ClientDao();
        List<Client> clientList = clientDao.getAllClients();

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Gender", "Password"}, 0);
        for (Client client : clientList) {
            model.addRow(new Object[]{client.getId(), client.getName(), client.getGender(), client.getPassword()});
        }
        table1.setModel(model);
    }

    // Populate the input fields when a row is selected
    private void populateFieldsFromTable() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow >= 0) {
            selectedClientId = (int) table1.getValueAt(selectedRow, 0); // Get the client ID
            String name = table1.getValueAt(selectedRow, 1).toString();
            String gender = table1.getValueAt(selectedRow, 2).toString();
            String password = table1.getValueAt(selectedRow, 3).toString();

            // Set the text fields with the selected client info
            textField2.setText(name);
            textField3.setText(password);

            // Set the gender radio buttons based on the selected gender
            if ("Male".equals(gender)) {
                radioButton1.setSelected(true);
            } else if ("Female".equals(gender)) {
                radioButton2.setSelected(true);
            } else {
                radioButton3.setSelected(true);
            }
        }
    }

    // Confirm the modification of the selected client
    private void confirmUpdate() {
        if (selectedClientId == -1) {
            JOptionPane.showMessageDialog(this, "请选择一个客户进行修改", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = textField2.getText();
        String password = textField3.getText();
        String gender = radioButton1.isSelected() ? "男" : radioButton2.isSelected() ? "女" : "保密";

        // Create a Client object with the new details
        Client client = new Client();
        client.setId(selectedClientId);
        client.setName(name);
        client.setPassword(password);
        client.setGender(gender);

        // Call the update method in ClientDao
        ClientDao clientDao = new ClientDao();
        boolean success = clientDao.updateClient(client);

        if (success) {
            JOptionPane.showMessageDialog(this, "客户信息已更新", "成功", JOptionPane.INFORMATION_MESSAGE);
            loadClientData(); // Reload client data
        } else {
            JOptionPane.showMessageDialog(this, "更新失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Confirm the deletion of the selected client
    private void confirmDelete() {
        if (selectedClientId == -1) {
            JOptionPane.showMessageDialog(this, "请选择一个客户进行删除", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "确认删除该客户吗?", "删除确认", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Call the delete method in ClientDao
            ClientDao clientDao = new ClientDao();
            boolean success = clientDao.deleteClient(selectedClientId);

            if (success) {
                JOptionPane.showMessageDialog(this, "客户已删除", "成功", JOptionPane.INFORMATION_MESSAGE);
                loadClientData(); // Reload client data
            } else {
                JOptionPane.showMessageDialog(this, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Initialize components
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        panel1 = new JPanel();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        button2 = new JButton();
        label4 = new JLabel();
        textField3 = new JTextField();
        button3 = new JButton();

        //======== this ========
        setTitle("客户列表");
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "51dlu, $lcgap, 92dlu, $lcgap, 105dlu",
                "28dlu, $lgap, 26dlu, $lgap, 36dlu, $lgap, 34dlu, $lgap, 30dlu, $lgap, 29dlu, $lgap, 27dlu"));

        //---- label1 ----
        label1.setText("客户名称：");
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 1));

        //---- button1 ----
        button1.setText("查询");
        contentPane.add(button1, CC.xy(5, 1, CC.CENTER, CC.DEFAULT));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, CC.xywh(1, 3, 5, 5));

        //---- label2 ----
        label2.setText("客户名称：");
        contentPane.add(label2, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(3, 9));

        //---- label3 ----
        label3.setText("客户性别：");
        contentPane.add(label3, CC.xy(1, 11, CC.RIGHT, CC.DEFAULT));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                    "25dlu, $lcgap, 29dlu, $lcgap, 42dlu",
                    "default"));

            //---- radioButton1 ----
            radioButton1.setText("男");
            panel1.add(radioButton1, CC.xy(1, 1));

            //---- radioButton2 ----
            radioButton2.setText("女");
            panel1.add(radioButton2, CC.xy(3, 1));

            //---- radioButton3 ----
            radioButton3.setText("保密");
            panel1.add(radioButton3, CC.xy(5, 1));
        }
        contentPane.add(panel1, CC.xy(3, 11));

        //---- button2 ----
        button2.setText("确认修改");
        contentPane.add(button2, CC.xy(5, 11, CC.CENTER, CC.DEFAULT));

        //---- label4 ----
        label4.setText("登录密码：");
        contentPane.add(label4, CC.xy(1, 13, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(3, 13));

        //---- button3 ----
        button3.setText("确认删除");
        contentPane.add(button3, CC.xy(5, 13, CC.CENTER, CC.DEFAULT));

        // Add a listener to the table for row selection
        table1.getSelectionModel().addListSelectionListener(e -> populateFieldsFromTable());

        // Add search button action listener
        button1.addActionListener(e -> {
            String name = textField1.getText();
            searchClient(name);
        });

        // Add confirm modify button action listener
        button2.addActionListener(e -> confirmUpdate());

        // Add confirm delete button action listener
        button3.addActionListener(e -> confirmDelete());

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:off
    }

    // Perform search based on client name
    private void searchClient(String name) {
        ClientDao clientDao = new ClientDao();
        List<Client> clientList = clientDao.getClientsByName(name);

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Gender", "Password"}, 0);
        for (Client client : clientList) {
            model.addRow(new Object[]{client.getId(), client.getName(), client.getGender(), client.getPassword()});
        }
        table1.setModel(model);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JPanel panel1;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JButton button2;
    private JLabel label4;
    private JTextField textField3;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:off
}
