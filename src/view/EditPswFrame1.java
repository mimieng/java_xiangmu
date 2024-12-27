/*
 * Created by JFormDesigner on Sun Dec 01 16:12:30 CST 2024
 */

package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import dao.AdminDao;
import pojo.Admin;
import pojo.Student;
import util.StringUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 25341
 */
public class EditPswFrame1 extends JFrame {
    public EditPswFrame1() {
        initComponents();
        //1.设置标签提示label
        if(MainFrame1.userType.equals("系统管理员")) {
          Student admin=MainFrame1.admin;
          label_user.setText("【系统管理员】"+admin.getName());
        }
        //2.btn_sure的事件处理
        btn_sure.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //动态效果
                //（1）获取用户输入的数据
                String oldPsw=pf_old.getText().toString();
                String newPsw1=pf_new1.getText().toString();
                String newPsw2=pf_new2.getText().toString();
                if(StringUtil.isEmpty(oldPsw)){
                    JOptionPane.showMessageDialog(EditPswFrame1.this,"原密码不能为空，请输入原密码");
                    return;
                }
                if(StringUtil.isEmpty(newPsw1)){
                    JOptionPane.showMessageDialog(EditPswFrame1.this,"新密码不能为空，请输入新密码");
                    return;
                }
                if(StringUtil.isEmpty(newPsw2)){
                    JOptionPane.showMessageDialog(EditPswFrame1.this,"确认密码不能为空");
                }
                if(!newPsw1.equals(newPsw2)){
                    JOptionPane.showMessageDialog(EditPswFrame1.this,"新密码和确认密码不一致,请重新输入");
                    return;
                }
                //（2）dao.edipsw()
                AdminDao dao=new AdminDao();
                Admin admin=MainFrame.admin;
                admin.setPassword(oldPsw);
                String  str = dao.editPassword(admin,newPsw1);
                //(3)提示
                JOptionPane.showMessageDialog( EditPswFrame1.this,str);
            }
        });
        //3.btn_reset的事件处理
        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //清空界面
                pf_old.setText("");
                pf_new1.setText("");
                pf_new2.setText("");
            }
        });
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        lUser = new JLabel();
        label_user = new JLabel();
        lpsw = new JLabel();
        pf_old = new JPasswordField();
        lpswnew = new JLabel();
        pf_new1 = new JPasswordField();
        label5 = new JLabel();
        pf_new2 = new JPasswordField();
        btn_sure = new JButton();
        btn_reset = new JButton();

        //======== this ========
        setTitle("\u4fee\u6539\u5bc6\u7801");
        setIconImage(new ImageIcon(getClass().getResource("/\u8bfe\u7a0b\u5217\u8868.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "103dlu, $lcgap, 99dlu",
            "37dlu, $lgap, 42dlu, $lgap, 39dlu, 2*($lgap, 38dlu)"));

        //---- lUser ----
        lUser.setText("\u5f53\u524d\u7528\u6237\uff1a");
        lUser.setIcon(new ImageIcon(getClass().getResource("/\u7528\u6237\u540d.png")));
        contentPane.add(lUser, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));

        //---- label_user ----
        label_user.setText("\u3010\u7cfb\u7edf\u7ba1\u7406\u5458\u3011admin");
        contentPane.add(label_user, CC.xy(3, 1));

        //---- lpsw ----
        lpsw.setText("\u539f\u5bc6\u7801\uff1a");
        lpsw.setIcon(new ImageIcon(getClass().getResource("/password.png")));
        contentPane.add(lpsw, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(pf_old, CC.xy(3, 3));

        //---- lpswnew ----
        lpswnew.setText("\u65b0\u5bc6\u7801\uff1a");
        lpswnew.setIcon(new ImageIcon(getClass().getResource("/\u4fee\u6539\u5bc6\u7801.png")));
        contentPane.add(lpswnew, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(pf_new1, CC.xy(3, 5));

        //---- label5 ----
        label5.setText("\u786e\u8ba4\u65b0\u5bc6\u7801\uff1a");
        label5.setIcon(new ImageIcon(getClass().getResource("/\u4fee\u6539\u5bc6\u7801.png")));
        contentPane.add(label5, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(pf_new2, CC.xy(3, 7));

        //---- btn_sure ----
        btn_sure.setText("\u786e\u8ba4");
        btn_sure.setIcon(new ImageIcon(getClass().getResource("/\u786e\u8ba4.png")));
        contentPane.add(btn_sure, CC.xy(1, 9, CC.CENTER, CC.DEFAULT));

        //---- btn_reset ----
        btn_reset.setText("\u91cd\u7f6e");
        btn_reset.setIcon(new ImageIcon(getClass().getResource("/\u91cd\u7f6e.png")));
        contentPane.add(btn_reset, CC.xy(3, 9, CC.CENTER, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel lUser;
    private JLabel label_user;
    private JLabel lpsw;
    private JPasswordField pf_old;
    private JLabel lpswnew;
    private JPasswordField pf_new1;
    private JLabel label5;
    private JPasswordField pf_new2;
    private JButton btn_sure;
    private JButton btn_reset;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
