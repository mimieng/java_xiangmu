package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import dao.QueryDao; // 假设 QueryDao 类在 dao 包中
import pojo.Query; // 假设 Query 类在 pojo 包中
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author 晚吟
 */
public class EquipQueryFrame1 extends JFrame {

    private JTable table1;
    private JTextField txt_name1;
    private JButton btn_query;

    public EquipQueryFrame1() {
        initComponents(); // 初始化界面
    }

    // 查询操作，用于搜索装备
    private void queryAction() {
        String name = txt_name1.getText().trim(); // 获取用户输入的装备名称，并去除前后空格
        QueryDao dao = new QueryDao(); // 创建 QueryDao 对象

        // 查询数据库中的装备信息
        List<Query> results;
        if (name.isEmpty()) {
            // 如果查询输入为空，则查询所有装备
            results = dao.queryAllEquipment();
        } else {
            // 如果查询输入不为空，则按名称查询装备
            results = dao.queryEquipment(name);
        }

        // 在表格中显示查询结果
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0); // 清空表格中的现有数据
        for (Query q : results) {
            model.addRow(new Object[]{q.getId(), q.getName(), q.getQuantity()});
        }
    }

    // 初始化界面的方法
    private void initComponents() {
        // 初始化组件
        JLabel label1 = new JLabel("装备名称：");
        txt_name1 = new JTextField();
        btn_query = new JButton("查询");
        JScrollPane scrollPane1 = new JScrollPane();
        table1 = new JTable();

        // 设置表格的列名
        table1.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "名称", "数量"}));

        // 设置窗口
        setTitle("装备列表窗口");
        setIconImage(new ImageIcon(getClass().getResource("/班级管理.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "90dlu, $lcgap, 107dlu, $lcgap, 108dlu",
                "54dlu, $lgap, 76dlu, $lgap, 56dlu"));

        // 添加组件到窗口
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name1, CC.xy(3, 1));
        btn_query.setFont(btn_query.getFont().deriveFont(btn_query.getFont().getSize() + 4f));
        contentPane.add(btn_query, CC.xy(5, 1, CC.CENTER, CC.DEFAULT));
        scrollPane1.setViewportView(table1);
        contentPane.add(scrollPane1, CC.xywh(1, 3, 5, 1));

        // 为查询按钮添加事件监听器
        btn_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryAction();
            }
        });

        // 完成布局
        pack();
        setLocationRelativeTo(getOwner());
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认关闭操作
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}