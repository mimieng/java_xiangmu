/*
 * Created by JFormDesigner on Sun Dec 01 09:58:16 CST 2024
 */

package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import dao.AdminDao;
import dao.UserDao;
import pojo.Admin;
import pojo.User;
import util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author 晚吟
 */
public class LoginFrame extends JFrame implements ActionListener {


    public LoginFrame() {
        initComponents();
        btn_login.addActionListener(this);
        btn_reset.addActionListener(this);

    }
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == btn_login) {
            String name = txt_name.getText();
            String psw = pf_psw.getText();
            String type = (String) cb_type.getSelectedItem();
            if (StringUtil.isEmpty(name)){
                JOptionPane.showMessageDialog(null, "请输入用户名！");
            }else if (StringUtil.isEmpty(psw)){
                JOptionPane.showMessageDialog(null, "请输入密码！");
            }else if (type.equals("")){
                JOptionPane.showMessageDialog(null, "请选择用户类别！");
            }
            if (type.equals("系统管理员")){
                AdminDao adminDao = new AdminDao();
                Admin admin = new Admin();
                admin.setName(name);
                admin.setPassword(psw);
                Admin rstAdmin=adminDao.login(admin);
                if (rstAdmin != null){
                    JOptionPane.showMessageDialog(null, "登录成功！");
                    new MainFrame(type, rstAdmin).setVisible(true);
                    this.dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "登录失败！");
                }

            }
            if (type.equals("客户")) {
                UserDao adminDao = new UserDao();
                User admin = new User();
                admin.setName(name);
                admin.setPassword(psw);
                User rstAdmin = adminDao.login(admin);
                if (rstAdmin != null) {
                    JOptionPane.showMessageDialog(null, "登录成功！");
                    new MainFrame1(type, rstAdmin).setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "登录失败！");
                }
            }
        }
        if (button == btn_reset) {
            txt_name.setText("");
            pf_psw.setText("");
            cb_type.setSelectedIndex(0);
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        txt_name = new JTextField();
        label3 = new JLabel();
        pf_psw = new JTextField();
        label4 = new JLabel();
        cb_type = new JComboBox<>();
        btn_login = new JButton();
        btn_reset = new JButton();

        //======== this ========
        setTitle("\u767b\u5f55\u7a97\u53e3");
        setIconImage(new ImageIcon(getClass().getResource("/\u5b66\u751f.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "109dlu, $lcgap, 141dlu, $lcgap, 100dlu",
            "50dlu, $lgap, 38dlu, $lgap, 47dlu, $lgap, 48dlu, $lgap, 37dlu"));

        //---- label1 ----
        label1.setText("\u6b22\u8fce\u6765\u5230\u836f\u5e97\u7ba1\u7406\u767b\u5f55\u7cfb\u7edf");
        label1.setIcon(new ImageIcon(getClass().getResource("/OIP-C.jpg")));
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 20f));
        contentPane.add(label1, CC.xywh(1, 1, 5, 1, CC.CENTER, CC.DEFAULT));

        //---- label2 ----
        label2.setText("\u7528\u6237\u540d\uff1a");
        label2.setIcon(new ImageIcon(getClass().getResource("/\u7528\u6237\u540d.png")));
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name, CC.xy(3, 3));

        //---- label3 ----
        label3.setText("\u5bc6  \u7801\uff1a");
        label3.setIcon(new ImageIcon(getClass().getResource("/\u5bc6\u7801.png")));
        contentPane.add(label3, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(pf_psw, CC.xy(3, 5));

        //---- label4 ----
        label4.setText("\u7528\u6237\u7c7b\u578b\uff1a");
        label4.setIcon(new ImageIcon(getClass().getResource("/userType.png")));
        contentPane.add(label4, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));

        //---- cb_type ----
        cb_type.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u7cfb\u7edf\u7ba1\u7406\u5458",
            "\u5ba2\u6237"
        }));
        contentPane.add(cb_type, CC.xy(3, 7));

        //---- btn_login ----
        btn_login.setText("\u767b\u5f55");
        btn_login.setIcon(new ImageIcon(getClass().getResource("/\u767b\u5f55.png")));
        contentPane.add(btn_login, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));

        //---- btn_reset ----
        btn_reset.setText("\u91cd\u7f6e");
        btn_reset.setIcon(new ImageIcon(getClass().getResource("/\u91cd\u7f6e.png")));
        contentPane.add(btn_reset, CC.xy(3, 9, CC.RIGHT, CC.DEFAULT));
        setSize(690, 440);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField txt_name;
    private JLabel label3;
    private JTextField pf_psw;
    private JLabel label4;
    private JComboBox<String> cb_type;
    private JButton btn_login;
    private JButton btn_reset;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
