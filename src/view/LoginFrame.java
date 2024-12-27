/*
 * Created by JFormDesigner on Sun Dec 01 15:27:00 CST 2024
 */

package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import dao.AdminDao;
import dao.StudentDao;
import pojo.Admin;
import pojo.Student;
import util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 25341
 */
public class LoginFrame extends JFrame implements ActionListener {
    public LoginFrame() {

        initComponents();
        btn_login.addActionListener(this);
        btn_reset.addActionListener(this);
    }

      public void actionPerformed(ActionEvent e) {
          JButton button = (JButton) e.getSource();
          if(button == btn_login) {
              String userName = txt_name.getText();
              String password= pf_psw.getText().toString();
              String userType = cb_type.getSelectedItem().toString();
              if(StringUtil.isEmpty(userName)) {
                  JOptionPane.showMessageDialog(this,"用户名不能为空，请输入用户名！！！");
                  return ;
              }
              if(StringUtil.isEmpty(password)) {
                  JOptionPane.showMessageDialog(this,"密码不能为空，请输入密码!!!");
                  return ;
              }
              if(userType.equals("系统管理员")){
                  AdminDao dao=new AdminDao();
                  Admin admin=new Admin();
                  admin.setName(userName);
                  admin.setPassword(password);
                  Admin rstAdmin=dao.login(admin);
                  if(rstAdmin!=null) {
                      //登录成功的提示
                      JOptionPane.showMessageDialog(this,"欢迎【"+userType+"】"+userName+"登录本系统");
                      //打开主页面
                      //关闭自生
                      this.dispose();
                      new MainFrame(userType,rstAdmin).setVisible(true);
                  }else{
                      //失败的提示
                      JOptionPane.showMessageDialog(this,"用户名或密码错误");
                  }
              }



              if(userType.equals("学生"))
              {
                  StudentDao dao=new StudentDao();
                  Student student=new Student();
                  student.setName(userName);
                  student.setPassword(password);
                  Student rstAdmin=dao.login(student);
                  if(rstAdmin!=null) {
                      //登录成功的提示
                      JOptionPane.showMessageDialog(this,"欢迎【"+userType+"】"+userName+"登录本系统");
                      //打开主页面
                      //关闭自生
                      this.dispose();
                      new MainFrame1(userType,rstAdmin).setVisible(true);
                  }else{
                      //失败的提示
                      JOptionPane.showMessageDialog(this,"用户名或密码错误");
                  }

              }
          }

          if((button == btn_reset)) {
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
        pf_psw = new JPasswordField();
        label4 = new JLabel();
        cb_type = new JComboBox<>();
        btn_login = new JButton();
        btn_reset = new JButton();

        //======== this ========
        setBackground(Color.pink);
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "90dlu, $lcgap, 118dlu, $lcgap, 58dlu",
            "60dlu, 3*($lgap, 30dlu), $lgap, 60dlu"));

        //---- label1 ----
        label1.setText("\u5b66\u751f\u4f53\u80b2\u5668\u6750\u7cfb\u7edf\u767b\u5f55\u754c\u9762");
        label1.setIcon(new ImageIcon(getClass().getResource("/logo.png")));
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
        contentPane.add(label1, CC.xywh(1, 1, 5, 1, CC.CENTER, CC.DEFAULT));

        //---- label2 ----
        label2.setText("\u7528\u6237\u540d\uff1a");
        label2.setIcon(new ImageIcon(getClass().getResource("/\u7528\u6237\u540d.png")));
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name, CC.xy(3, 3));

        //---- label3 ----
        label3.setText("\u5bc6\u7801\uff1a");
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
            "\u5b66\u751f"
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
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField txt_name;
    private JLabel label3;
    private JPasswordField pf_psw;
    private JLabel label4;
    private JComboBox<String> cb_type;
    private JButton btn_login;
    private JButton btn_reset;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
