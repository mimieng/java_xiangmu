/*
 * Created by JFormDesigner on Sun Dec 01 13:40:26 CST 2024
 */

package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import dao.ClassDao;
import dao.StudentDao;
import pojo.Student;
import pojo.StudentClass;
import util.StringUtil;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author 晚吟
 */
public class AddStuFrame extends JFrame {
    private ButtonGroup group;
    public AddStuFrame() {
        initComponents();
        setStuClassInfo();
        group = new ButtonGroup();
        group.add( rb_male);
        group.add(rb_famale);
        group.add(rb_secret);
        btn_reset.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetvalues();
            }
        });
        btn_sure.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txt_name.getText().toString();
                String psw = txt_psw.getText().toString();
                StudentClass stuClass = (StudentClass) cb_class.getSelectedItem();
                String sex = rb_male.isSelected()? rb_male.getText() : rb_famale.isSelected() ? rb_famale.getText() :rb_secret.getText(); ;
                if (StringUtil.isEmpty(name)){
                    JOptionPane.showMessageDialog(null, "请输入用户名！");

                }
                if (StringUtil.isEmpty(psw)){
                    JOptionPane.showMessageDialog(null, "请输入密码！");
                }else if (StringUtil.isEmpty(sex)){
                    JOptionPane.showMessageDialog(null, "请选择性别！");
                }else {
                    Student student = new Student();
                    student.setName(name);
                    student.setPassword(psw);
                    student.setClassId(stuClass.getId());
                    student.setSex(sex);

                }
                StudentDao studentDao = new StudentDao();
                boolean flag = studentDao.addStudent(new Student());
                if (flag) {
                    JOptionPane.showMessageDialog(null, "添加成功！");
                }else{
                    JOptionPane.showMessageDialog(null, "添加失败！");
                }

            }
        });
    }
    private void setStuClassInfo() {
        ClassDao classDao = new ClassDao();
        List<StudentClass> list = classDao.getClassList(new StudentClass());
        for (StudentClass sc : list) {
            cb_class.addItem(sc);
        }

    }
    private void resetvalues() {
        txt_name.setText("");
        group.clearSelection();
        txt_psw.setText("");
        cb_class.setSelectedIndex(0);

        rb_male.setSelected(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        txt_name = new JTextField();
        label2 = new JLabel();
        cb_class = new JComboBox();
        label3 = new JLabel();
        txt_psw = new JTextField();
        label4 = new JLabel();
        rb_male = new JRadioButton();
        rb_famale = new JRadioButton();
        rb_secret = new JRadioButton();
        btn_sure = new JButton();
        btn_reset = new JButton();

        //======== this ========
        setTitle("\u6dfb\u52a0\u5b66\u751f");
        setIconImage(new ImageIcon(getClass().getResource("/\u6dfb\u52a0.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "54dlu, $lcgap, 37dlu, $lcgap, 30dlu, $lcgap, 48dlu, $lcgap, 47dlu",
            "31dlu, $lgap, 22dlu, $lgap, 31dlu, $lgap, 25dlu, $lgap, 47dlu"));

        //---- label1 ----
        label1.setText("\u5b66\u751f\u59d3\u540d\uff1a");
        label1.setIcon(new ImageIcon(getClass().getResource("/\u5b66\u751f\u7ba1\u7406.png")));
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name, CC.xywh(3, 1, 5, 1));

        //---- label2 ----
        label2.setText("\u6240\u5c5e\u73ed\u7ea7\uff1a");
        label2.setIcon(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u540d\u79f0.png")));
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        contentPane.add(cb_class, CC.xywh(3, 3, 5, 1));

        //---- label3 ----
        label3.setText("\u767b\u5f55\u5bc6\u7801\uff1a");
        label3.setIcon(new ImageIcon(getClass().getResource("/password.png")));
        contentPane.add(label3, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_psw, CC.xywh(3, 5, 5, 1));

        //---- label4 ----
        label4.setText("\u5b66\u751f\u6027\u522b\uff1a");
        label4.setIcon(new ImageIcon(getClass().getResource("/\u6027\u522b.png")));
        contentPane.add(label4, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));

        //---- rb_male ----
        rb_male.setText("\u7537");
        contentPane.add(rb_male, CC.xy(3, 7, CC.RIGHT, CC.DEFAULT));

        //---- rb_famale ----
        rb_famale.setText("\u5973");
        contentPane.add(rb_famale, CC.xy(5, 7, CC.RIGHT, CC.DEFAULT));

        //---- rb_secret ----
        rb_secret.setText("\u4fdd\u5bc6");
        contentPane.add(rb_secret, CC.xy(7, 7, CC.RIGHT, CC.DEFAULT));

        //---- btn_sure ----
        btn_sure.setText("\u786e\u8ba4");
        btn_sure.setIcon(new ImageIcon(getClass().getResource("/\u786e\u8ba4.png")));
        contentPane.add(btn_sure, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));

        //---- btn_reset ----
        btn_reset.setText("\u91cd\u7f6e");
        btn_reset.setIcon(new ImageIcon(getClass().getResource("/\u91cd\u7f6e.png")));
        contentPane.add(btn_reset, CC.xy(7, 9));
        setSize(435, 325);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField txt_name;
    private JLabel label2;
    private JComboBox cb_class;
    private JLabel label3;
    private JTextField txt_psw;
    private JLabel label4;
    private JRadioButton rb_male;
    private JRadioButton rb_famale;
    private JRadioButton rb_secret;
    private JButton btn_sure;
    private JButton btn_reset;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
