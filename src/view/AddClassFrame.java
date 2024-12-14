/*
 * Created by JFormDesigner on Sat Dec 14 19:06:36 CST 2024
 */

package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import dao.ClassDao;
import pojo.StudentClass;

/**
 * @author 晚吟
 */
public class AddClassFrame extends JFrame {
    public AddClassFrame() {
        initComponents();
        button2.addActionListener(e -> {
            textField1.setText("");
            textField2.setText("");
        });
        button1.addActionListener(e -> {
            String name = textField1.getText();
            String info = textField2.getText();
            if (name.equals("") || info.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入完整信息", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            StudentClass studentClass = new StudentClass(0, name, info);
            ClassDao classDao = new ClassDao();
            boolean flag= classDao.addClass(studentClass);
            if (flag){
                JOptionPane.showMessageDialog(null, "添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "添加失败", "失败", JOptionPane.ERROR_MESSAGE);
            }



        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u6dfb\u52a0\u73ed\u7ea7");
        setIconImage(new ImageIcon(getClass().getResource("/\u6dfb\u52a0.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "75dlu, $lcgap, 117dlu, $lcgap, 46dlu",
            "default, $lgap, 39dlu, $lgap, 57dlu, $lgap, 49dlu"));

        //---- label1 ----
        label1.setText("\u6dfb\u52a0\u73ed\u7ea7\uff1a");
        label1.setIcon(new ImageIcon(getClass().getResource("/\u6dfb\u52a0.png")));
        contentPane.add(label1, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 3, CC.FILL, CC.DEFAULT));

        //---- label2 ----
        label2.setText("\u73ed\u7ea7\u4fe1\u606f\uff1a");
        label2.setIcon(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u4ecb\u7ecd.png")));
        contentPane.add(label2, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(3, 5, CC.DEFAULT, CC.FILL));

        //---- button1 ----
        button1.setText("\u63d0\u4ea4");
        button1.setIcon(new ImageIcon(getClass().getResource("/\u786e\u8ba4.png")));
        contentPane.add(button1, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        button2.setIcon(new ImageIcon(getClass().getResource("/\u91cd\u7f6e.png")));
        contentPane.add(button2, CC.xy(3, 7, CC.RIGHT, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
