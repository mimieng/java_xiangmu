/*
 * Created by JFormDesigner on Tue Dec 17 14:13:22 CST 2024
 */

package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import dao.AdminDao;
import pojo.Admin;
import pojo.User;
import util.StringUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.MainUser.user;

/**
 * @author 晚吟
 */
public class EditPswFrame2 extends JFrame {
    public EditPswFrame2() {
        initComponents();
        if (MainUser.usertype.equals("学生")) {
            User admin = user;
            label2.setText("【学生】" + admin.getName());
        }
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String old = textField1.getText().toString();
                String new1 = textField2.getText().toString();
                String new2 = textField3.getText().toString();
                if (StringUtil.isEmpty(old)){
                    JOptionPane.showMessageDialog(EditPswFrame2.this, "请输入旧密码！");
                }
                if (StringUtil.isEmpty(new1)){
                    JOptionPane.showMessageDialog(EditPswFrame2.this, "请输入新密码！");
                }
                if (StringUtil.isEmpty(new2)){
                    JOptionPane.showMessageDialog(EditPswFrame2.this, "请输入确认密码！");
                }
                if (!new1.equals(new2)){
                    JOptionPane.showMessageDialog(EditPswFrame2.this, "两次输入的密码不一致！");
                }
                AdminDao adminDao = new AdminDao();
                Admin admin =  MainFrame.admin;
                admin.setPassword(old);
                String str = adminDao.editPassword(admin, new1);
                JOptionPane.showMessageDialog(EditPswFrame2.this, str);


            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
            }
        });

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        textField1 = new JTextField();
        label4 = new JLabel();
        textField3 = new JTextField();
        label5 = new JLabel();
        textField2 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u4fee\u6539\u5bc6\u7801");
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "53dlu, $lcgap, 90dlu, $lcgap, 52dlu",
            "2*(38dlu, $lgap), 33dlu, $lgap, 35dlu, $lgap, 33dlu"));

        //---- label1 ----
        label1.setText("\u5f53\u524d\u7528\u6237\uff1a");
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(label2, CC.xy(3, 1));

        //---- label3 ----
        label3.setText("\u539f\u5bc6\u7801\uff1a");
        contentPane.add(label3, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 3));

        //---- label4 ----
        label4.setText("\u4fee\u6539\u5bc6\u7801\uff1a");
        contentPane.add(label4, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(3, 5));

        //---- label5 ----
        label5.setText("\u786e\u5b9a\u5bc6\u7801\uff1a");
        contentPane.add(label5, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(3, 7));

        //---- button1 ----
        button1.setText("\u786e\u8ba4");
        contentPane.add(button1, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        contentPane.add(button2, CC.xy(3, 9, CC.RIGHT, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1;
    private JLabel label4;
    private JTextField textField3;
    private JLabel label5;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
