

package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * @author 晚吟
 */
public class librarylist extends JFrame {
    public librarylist() {
        initComponents();
        initCustomComponents();
    }

    private void initCustomComponents() {
        // 初始化类别下拉框
        try (Connection connection = DBUtil.getCon()) {
            String query = "SELECT DISTINCT category FROM books";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            comboBox1.addItem("全部");
            while (rs.next()) {
                comboBox1.addItem(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "加载类别失败！", "错误", JOptionPane.ERROR_MESSAGE);
        }

        // 添加查询按钮的事件监听器
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performQuery();
            }
        });
    }

    private void performQuery() {
    // 获取用户输入
    String bookName = textField1.getText().trim();
    String category = (String) comboBox1.getSelectedItem();

    // SQL 查询
    String query = "SELECT * FROM books WHERE title LIKE ?";
    if (!"全部".equals(category)) {
        query += " AND category = ?";
    }

    try (Connection connection = DBUtil.getCon(); PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setString(1, "%" + bookName + "%");
        if (!"全部".equals(category)) {
            pstmt.setString(2, category);
        }

        ResultSet rs = pstmt.executeQuery();

        // 更新表格数据
        String[] columnNames = {"图书名称", "类别", "作者", "库存"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        while (rs.next()) {
            tableModel.addRow(new Object[]{
                    rs.getString("title"),
                    rs.getString("category"),
                    rs.getString("author"),
                    rs.getInt("stock")
            });
        }
        table1.setModel(tableModel);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "查询失败！", "错误", JOptionPane.ERROR_MESSAGE);
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

        //======== this ========
        setTitle("\u56fe\u4e66\u5217\u8868");
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "47dlu, $lcgap, 98dlu, $lcgap, 70dlu, $lcgap, 90dlu, $lcgap, 79dlu",
            "57dlu, $lgap, 50dlu, $lgap, 69dlu"));

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u540d\u79f0\uff1a");
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 1));

        //---- label2 ----
        label2.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");
        contentPane.add(label2, CC.xy(5, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(comboBox1, CC.xy(7, 1));

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        contentPane.add(button1, CC.xy(9, 1, CC.CENTER, CC.DEFAULT));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, CC.xywh(1, 3, 9, 3));
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
