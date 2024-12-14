/*
 * Created by JFormDesigner on Sun Nov 24 17:11:16 CST 2024
 */

package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import dao.StudentDao;
import dao.TeacherDao;
import pojo.Student;
import pojo.StudentClass;
import pojo.Teacher;
import util.StringUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 晚吟
 */
public class AddTeaFrame extends JFrame {
    private  ButtonGroup group;
    public AddTeaFrame() {
        //1.页面初始化的的代码，放到构造方法的第一行
        initComponents();
        //2.单选按钮二选一的效果
        group=new ButtonGroup();
        group.add(rb_male);
        group.add(rb_female);
        btn_sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1.从界面获取用户输入的数据，封装到一个Teacher对象中
                String teaName=txt_name.getText().toString();
                String teaPsw=txt_psw.getText().toString();
                String teaTitle=txt_name2.getText().toString();
                String sex=rb_male.isSelected()?rb_male.getText():rb_female.getText();
                if(StringUtil.isEmpty(teaName))
                {
                    JOptionPane.showConfirmDialog(AddTeaFrame.this,"老师姓名不能为空，请输入老师姓名！！");
                    return;
                }
                if(StringUtil.isEmpty(teaTitle))
                {
                    JOptionPane.showConfirmDialog(AddTeaFrame.this,"老师职称不能为空，请输入老师职称！！");
                    return;
                }
                if(StringUtil.isEmpty(txt_age.getText().toString()))
                {
                    JOptionPane.showConfirmDialog(AddTeaFrame.this,"老师年龄不能为空，请输入老师年龄！！");
                    return;
                }
                if(StringUtil.isEmpty(teaPsw))
                {
                    JOptionPane.showConfirmDialog(AddTeaFrame.this,"老师密码不能为空，请输入老师密码！！");
                    return;
                }
                int teaAge= Integer.parseInt(txt_age.getText().toString());
                Teacher tea=new Teacher();
               tea.setName(teaName);
               tea.setSex(sex);
               tea.setTitle(teaTitle);
               tea.setAge(teaAge);
              tea.setPassword(teaPsw);
                //2.dao.addStudent(stu)
                TeacherDao  dao =new TeacherDao();
                boolean flag= dao.addTeacher(tea);
                //3.提示
                if(flag){
                    JOptionPane.showConfirmDialog(AddTeaFrame.this,"恭喜你添加成功！！！");
                }else{
                    JOptionPane.showConfirmDialog(AddTeaFrame.this,"添加失败！！！");
                }
                //4.清空界面
                resetValue();
            }
        });
        //5.btn_reset进行ActinoEvent事件处理
        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetValue();
            }
        });
    }

    private void resetValue() {
            txt_psw.setText("");
            txt_name.setText("");
            txt_age.setText("");
            txt_name2.setText("");
            group.clearSelection();
            rb_male.setSelected(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        txt_name = new JTextField();
        label2 = new JLabel();
        rb_male = new JRadioButton();
        rb_female = new JRadioButton();
        label3 = new JLabel();
        txt_name2 = new JTextField();
        label4 = new JLabel();
        txt_age = new JTextField();
        label5 = new JLabel();
        txt_psw = new JTextField();
        btn_sure = new JButton();
        btn_reset = new JButton();

        //======== this ========
        setTitle("\u6dfb\u52a0\u6559\u5e08");
        setIconImage(new ImageIcon(getClass().getResource("/\u8bfe\u7a0b\u5217\u8868.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "88dlu, $lcgap, 33dlu, $lcgap, 30dlu, $lcgap, 58dlu, $lcgap, 33dlu",
            "40dlu, 5*($lgap, 30dlu)"));

        //---- label1 ----
        label1.setText("\u6559\u5e08\u59d3\u540d\uff1a");
        label1.setIcon(new ImageIcon(getClass().getResource("/\u8001\u5e08.png")));
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name, CC.xywh(3, 1, 6, 1));

        //---- label2 ----
        label2.setText("\u6559\u5e08\u6027\u522b\uff1a");
        label2.setIcon(new ImageIcon(getClass().getResource("/\u6027\u522b.png")));
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));

        //---- rb_male ----
        rb_male.setText("\u7537");
        contentPane.add(rb_male, CC.xy(3, 3));

        //---- rb_female ----
        rb_female.setText("\u5973");
        contentPane.add(rb_female, CC.xy(5, 3));

        //---- label3 ----
        label3.setText("\u6559\u5e08\u804c\u79f0\uff1a");
        label3.setIcon(new ImageIcon(getClass().getResource("/\u804c\u79f0\u8bc4\u5b9a.png")));
        contentPane.add(label3, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name2, CC.xywh(3, 5, 5, 1));

        //---- label4 ----
        label4.setText("\u6559\u5e08\u5e74\u9f84\uff1a");
        label4.setIcon(new ImageIcon(getClass().getResource("/\u5e74\u9f84.png")));
        contentPane.add(label4, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_age, CC.xywh(3, 7, 5, 1));

        //---- label5 ----
        label5.setText("\u767b\u9646\u5bc6\u7801");
        label5.setIcon(new ImageIcon(getClass().getResource("/password.png")));
        contentPane.add(label5, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_psw, CC.xywh(3, 9, 5, 1));

        //---- btn_sure ----
        btn_sure.setText("\u786e\u8ba4\u6dfb\u52a0");
        btn_sure.setIcon(new ImageIcon(getClass().getResource("/\u786e\u8ba4.png")));
        contentPane.add(btn_sure, CC.xywh(1, 11, 3, 1, CC.RIGHT, CC.DEFAULT));

        //---- btn_reset ----
        btn_reset.setText("\u91cd\u7f6e\u8868\u5355");
        btn_reset.setIcon(new ImageIcon(getClass().getResource("/\u91cd\u7f6e.png")));
        contentPane.add(btn_reset, CC.xywh(5, 11, 3, 1, CC.RIGHT, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField txt_name;
    private JLabel label2;
    private JRadioButton rb_male;
    private JRadioButton rb_female;
    private JLabel label3;
    private JTextField txt_name2;
    private JLabel label4;
    private JTextField txt_age;
    private JLabel label5;
    private JTextField txt_psw;
    private JButton btn_sure;
    private JButton btn_reset;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
