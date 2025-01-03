/*
 * Created by JFormDesigner on Sun Dec 01 11:58:31 CST 2024
 */

package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import dao.AdminDao;
import pojo.Admin;
import util.StringUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 晚吟
 */
public class EditPswFrame extends JFrame  {
    public EditPswFrame() {

        initComponents();
        if (MainFrame.userType.equals("系统管理员")) {
            Admin admin = MainFrame.admin;
            label_user.setText("【欢迎系统管理员】" + admin.getName());
        }
        btn_sure.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String old = pf_old.getText().toString();
                String new1 = pf_new1.getText().toString();
                String new2 = pf_new2.getText().toString();
                if (StringUtil.isEmpty(old)){
                    JOptionPane.showMessageDialog(EditPswFrame.this, "请输入旧密码！");
                }
                if (StringUtil.isEmpty(new1)){
                    JOptionPane.showMessageDialog(EditPswFrame.this, "请输入新密码！");
                }
                if (StringUtil.isEmpty(new2)){
                    JOptionPane.showMessageDialog(EditPswFrame.this, "请输入确认密码！");
                }
                if (!new1.equals(new2)){
                    JOptionPane.showMessageDialog(EditPswFrame.this, "两次输入的密码不一致！");
                }
                AdminDao adminDao = new AdminDao();
                Admin admin =  MainFrame.admin;
                admin.setPassword(old);
                String str = adminDao.editPassword(admin, new1);
                JOptionPane.showMessageDialog(EditPswFrame.this, str);


            }
        });
        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pf_old.setText("");
                pf_new1.setText("");
                pf_new2.setText("");
            }
        });

        }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label_user = new JLabel();
        Ipsw = new JLabel();
        pf_old = new JTextField();
        Ipswnew = new JLabel();
        pf_new1 = new JTextField();
        label5 = new JLabel();
        pf_new2 = new JTextField();
        btn_sure = new JButton();
        btn_reset = new JButton();

        //======== this ========
        setTitle("\u4fee\u6539\u5bc6\u7801");
        setIconImage(new ImageIcon(getClass().getResource("/\u4fee\u6539\u5bc6\u7801.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "89dlu, $lcgap, 155dlu, $lcgap, 67dlu",
            "49dlu, 2*($lgap, 47dlu), $lgap, 67dlu, $lgap, 46dlu"));

        //---- label1 ----
        label1.setText("\u5f53\u524d\u7528\u6237\uff1a");
        label1.setIcon(new ImageIcon(getClass().getResource("/\u7528\u6237\u540d.png")));
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(label_user, CC.xy(3, 1));

        //---- Ipsw ----
        Ipsw.setText("\u539f\u5bc6\u7801\uff1a");
        Ipsw.setIcon(new ImageIcon(getClass().getResource("/password.png")));
        contentPane.add(Ipsw, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(pf_old, CC.xy(3, 3));

        //---- Ipswnew ----
        Ipswnew.setText("\u4fee\u6539\u5bc6\u7801\uff1a");
        Ipswnew.setIcon(new ImageIcon(getClass().getResource("/\u4fee\u6539\u5bc6\u7801.png")));
        contentPane.add(Ipswnew, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(pf_new1, CC.xy(3, 5));

        //---- label5 ----
        label5.setText("\u786e\u8ba4\u6309\u94ae\uff1a");
        label5.setIcon(new ImageIcon(getClass().getResource("/\u4fee\u6539\u5bc6\u7801.png")));
        contentPane.add(label5, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(pf_new2, CC.xy(3, 7));

        //---- btn_sure ----
        btn_sure.setText("\u786e\u8ba4");
        btn_sure.setIcon(new ImageIcon(getClass().getResource("/\u786e\u8ba4.png")));
        contentPane.add(btn_sure, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));

        //---- btn_reset ----
        btn_reset.setText("\u91cd\u7f6e");
        btn_reset.setIcon(new ImageIcon(getClass().getResource("/\u91cd\u7f6e.png")));
        contentPane.add(btn_reset, CC.xy(3, 9, CC.RIGHT, CC.DEFAULT));
        setSize(615, 495);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label_user;
    private JLabel Ipsw;
    private JTextField pf_old;
    private JLabel Ipswnew;
    private JTextField pf_new1;
    private JLabel label5;
    private JTextField pf_new2;
    private JButton btn_sure;
    private JButton btn_reset;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
