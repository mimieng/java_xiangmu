package view;

import dao.CarDao;
import pojo.Car;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class medicinesManageFrame1 extends JFrame {

    private JLabel label1, label2, label3, label4, label5, label6;
    private JTextField txt_name, txt_name2, txt_info, txt_money;
    private JComboBox<String> cb_category, cb_category2;
    private JButton btn_query, btn_update, btn_delete;
    private JScrollPane scrollPane1;
    private JTable table1;
    private DefaultTableModel tableModel;

    private CarDao carDao = new CarDao(); // 创建CarDao实例

    public medicinesManageFrame1() {
        initComponents();
    }

    private void initComponents() {
        // Initialize components
        label1 = new JLabel("药品名称：");
        txt_name = new JTextField();
        label2 = new JLabel("所属类别：");
        cb_category = new JComboBox<>(new String []{"抗生素", "止痛药", "退烧药","解热镇痛药","心血管药","胃药","降压药","止泻药","勃起功能障碍","心血管药"});
        btn_query = new JButton("查询");
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
//        label3 = new JLabel("车辆名称：");
//        txt_name2 = new JTextField();
//        label4 = new JLabel("车辆简介：");
//        txt_info = new JTextField();
//        btn_update = new JButton("确认修改");
//        label5 = new JLabel("所属类别：");
//        cb_category2 = new JComboBox<>(new String[]{"suv","超豪华轿车","豪华轿车"});
//        label6 = new JLabel("月租：");
//        txt_money = new JTextField();
//        btn_delete = new JButton("删除");

        // Set title and icon
        setTitle("药品列表窗口");
        setIconImage(new ImageIcon(getClass().getResource("/1.jpg")).getImage());

        // Set default close operation to hide the window rather than exiting the app
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  // Use HIDE_ON_CLOSE to hide the window without exiting the app

        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "25dlu, $lcgap, 75dlu, $lcgap, 74dlu, 3*($lcgap, 75dlu), $lcgap, 25dlu",
                "40dlu, $lgap, 120dlu, $lgap, 41dlu, $lgap, 42dlu"));

        // Add components
        contentPane.add(label1, CC.xy(3, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name, CC.xy(5, 1));
        contentPane.add(label2, CC.xy(7, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(cb_category, CC.xy(9, 1));
        contentPane.add(btn_query, CC.xy(11, 1));

        // Set up JTable and its model
        tableModel = new DefaultTableModel();
        tableModel.addColumn("药品名称");
        tableModel.addColumn("所属类别");
        tableModel.addColumn("药品简介");
        tableModel.addColumn("价格");

        table1.setModel(tableModel);
        scrollPane1.setViewportView(table1);
        contentPane.add(scrollPane1, CC.xywh(3, 3, 10, 1, CC.FILL, CC.FILL));

//        contentPane.add(label3, CC.xy(3, 5, CC.RIGHT, CC.DEFAULT));
//        contentPane.add(txt_name2, CC.xy(5, 5));
//        contentPane.add(label4, CC.xy(7, 5, CC.RIGHT, CC.DEFAULT));
//        contentPane.add(txt_info, CC.xy(9, 5, CC.DEFAULT, CC.FILL));
//        contentPane.add(btn_update, CC.xy(11, 5));
//
//        contentPane.add(label5, CC.xy(3, 7));
//        contentPane.add(cb_category2, CC.xy(5, 7));
//        contentPane.add(label6, CC.xy(7, 7, CC.RIGHT, CC.DEFAULT));
//        contentPane.add(txt_money, CC.xy(9, 7));
//        contentPane.add(btn_delete, CC.xy(11, 7));

        pack();
        setLocationRelativeTo(null);

        // Add action listeners
        btn_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryData();
            }
        });

//        btn_update.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateData();
//            }
//        });
//
//        btn_delete.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                deleteData();
//            }
//        });

        // Add ListSelectionListener to JTable to capture row selection
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table1.getSelectedRow();
                    if (selectedRow >= 0) {
                        // Update input fields with the selected row's data
                        txt_name2.setText((String) tableModel.getValueAt(selectedRow, 0));
                        cb_category2.setSelectedItem(tableModel.getValueAt(selectedRow, 1));
                        txt_info.setText((String) tableModel.getValueAt(selectedRow, 2));
                        Object moneyValue = tableModel.getValueAt(selectedRow, 3);
                        if (moneyValue instanceof Double) {
                            txt_money.setText(Double.toString((Double) moneyValue));
                        } else if (moneyValue instanceof String) {
                            txt_money.setText((String) moneyValue);
                        } else {
                            txt_money.setText(String.valueOf(moneyValue));
                        }
                    }
                }
            }

        });
    }

    private void queryData() {
        // 从数据库查询数据
        String name = txt_name.getText().trim();
        String category = (String) cb_category.getSelectedItem();

        // 使用CarDao查询数据
        List<Car> cars = carDao.queryCars(name, category);

        // 清空表格中的数据
        tableModel.setRowCount(0);

        // 将查询到的车辆信息添加到表格中
        for (Car car : cars) {
            tableModel.addRow(new Object[]{car.getName(), car.getCategory(), car.getInfo(), car.getMonthlyRent()});
        }
    }

    private void updateData() {
        // 更新选中行的数据
        int selectedRow = table1.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.setValueAt(txt_name2.getText(), selectedRow, 0);
            tableModel.setValueAt(cb_category2.getSelectedItem(), selectedRow, 1);
            tableModel.setValueAt(txt_info.getText(), selectedRow, 2);
            tableModel.setValueAt(txt_money.getText(), selectedRow, 3);
        }
    }

    private void deleteData() {
        // 删除选中行的数据
        int selectedRow = table1.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        }
    }

    // 在窗口关闭时执行额外的操作，比如释放资源等
    private void setupWindowListener() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // 在关闭窗口时执行一些操作
                System.out.println("窗口关闭时执行操作");
                dispose();  // 释放资源，销毁窗口
            }
        });
    }
    
    }