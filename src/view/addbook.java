package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import util.DBUtil;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author 晚吟
 */
public class addbook extends JFrame {
    public addbook() {
        initComponents();
        initCustomComponents();
    }

    private void initCustomComponents() {
        // 初始化类别下拉框
        try (Connection connection = DBUtil.getCon();
             Statement stmt = connection.createStatement()) {
            String query = "SELECT DISTINCT category FROM books";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                comboBox1.addItem(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "加载类别失败！", "错误", JOptionPane.ERROR_MESSAGE);
        }

        // 添加确认按钮的事件监听器
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        // 添加重置按钮的事件监听器
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
    }

    private void addBook() {
        // 获取用户输入
        String title = textField1.getText().trim();
        String author = textField2.getText().trim();
        String category = (String) comboBox1.getSelectedItem();
        String stockText = textField3.getText().trim();

        // 校验输入
        if (title.isEmpty() || author.isEmpty() || category == null || stockText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请填写完整信息！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int stock;
        try {
            stock = Integer.parseInt(stockText);
            if (stock < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "库存数量必须是非负整数！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 插入数据库
        try (Connection connection = DBUtil.getCon();
             PreparedStatement pstmt = connection.prepareStatement(
                     "INSERT INTO books (title, author, category, stock) VALUES (?, ?, ?, ?)");) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, category);
            pstmt.setInt(4, stock);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "图书添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                resetFields();
            } else {
                JOptionPane.showMessageDialog(this, "图书添加失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        textField1.setText("");
        textField2.setText("");
        comboBox1.setSelectedIndex(-1);
        textField3.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        comboBox1 = new JComboBox();
        label4 = new JLabel();
        textField3 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "64dlu, $lcgap, 133dlu",
                "36dlu, $lgap, 40dlu, $lgap, 38dlu, $lgap, 31dlu, $lgap, 29dlu"));

        //---- label1 ----
        label1.setText("图书名称：");
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 1));

        //---- label2 ----
        label2.setText("图书作者：");
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(3, 3));

        //---- label3 ----
        label3.setText("图书类别：");
        contentPane.add(label3, CC.xy(1, 5, CC.RIGHT, CC.CENTER));
        contentPane.add(comboBox1, CC.xy(3, 5));

        //---- label4 ----
        label4.setText("图书数量：");
        contentPane.add(label4, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(3, 7));

        //---- button1 ----
        button1.setText("确认");
        contentPane.add(button1, CC.xy(1, 9));

        //---- button2 ----
        button2.setText("重置");
        contentPane.add(button2, CC.xy(3, 9, CC.CENTER, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JComboBox comboBox1;
    private JLabel label4;
    private JTextField textField3;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


}
