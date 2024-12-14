/*
 * Created by JFormDesigner on Sun Dec 08 14:56:47 CST 2024
 */

package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import dao.ClassDao;

import dao.StudentDao;
import pojo.Student;
import pojo.StudentClass;
import util.StringUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @author 晚吟
 */
public class StuManageFrame extends JFrame {
    private ButtonGroup group;
    private List<StudentClass> Classlist;

    public StuManageFrame() {
        initComponents();

        group = new ButtonGroup();
        group.add(rb_male);
        group.add(rb_female);
        group.add(rb_secret);
        setStuClassInfo();
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setDataVector(new Object[][]{},new String []{"学生编号","学生姓名","学生班级","学生性别","学生密码"});
        table1.setModel(model);
        btn_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txt_name.getText();
                StudentClass stuStudent=(StudentClass)cb_class.getSelectedItem();
                Student stu=new Student();
                stu.setName(name);
                stu.setClassId(stuStudent.getId());
                setTable(stu);

            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedTableRow();
            }

        });
        btn_update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(StuManageFrame.this, "请选择要修改的数据！");
                }
                String name = txt_name2.getText();
                String password = pf_psw.getText();
                if(StringUtil.isEmpty(name)){
                    JOptionPane.showMessageDialog(StuManageFrame.this, "请输入学生姓名！");
                    return;
                }
                if (StringUtil.isEmpty(password)){
                    JOptionPane.showMessageDialog(StuManageFrame.this, "请输入学生密码！");
                    return;
                }
                StudentClass stuClass=(StudentClass)cb_class2.getSelectedItem();
                String stuSex=rb_male.isSelected()?rb_male.getText():rb_female.isSelected()?rb_female.getText():rb_secret.getText();
                Student stu=new Student();
                int id = Integer.parseInt(table1.getValueAt(row, 0).toString());
                stu.setId(id);
                stu.setName(name);
                stu.setPassword(password);
                stu.setClassId(stuClass.getId());
                stu.setSex(stuSex);
                StudentDao dao=new StudentDao();
                boolean flag = dao.update(stu);
                if (flag){
                    JOptionPane.showMessageDialog(StuManageFrame.this, "修改成功！");

                }else {
                    JOptionPane.showMessageDialog(StuManageFrame.this, "修改失败！");
                }
                setTable(new Student());

            }
        });
        btn_delete.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(StuManageFrame.this, "请选择要删除的数据！");
                }
                int id = Integer.parseInt(table1.getValueAt(row, 0).toString());
                StudentDao dao=new StudentDao();
                boolean flag = dao.delete(id);
                if (flag){
                    JOptionPane.showMessageDialog(StuManageFrame.this, "删除成功！");

                }else {
                    JOptionPane.showMessageDialog(StuManageFrame.this, "删除失败！");
                }
                setTable(new Student());
            }
        });
    }
    private void selectedTableRow() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        int row = table1.getSelectedRow();
        String name = (String) model.getValueAt(row, 1);
        String classname = (String) model.getValueAt(row, 2);
        String sex = (String) model.getValueAt(row, 3);
        String password = (String) model.getValueAt(row, 4);
        txt_name2.setText(name);
        pf_psw.setText(password);
        //把stuclass设置到下拉列表
        for (int i = 0; i < cb_class2.getItemCount(); i++){
            StudentClass sc= (StudentClass) cb_class2.getItemAt(i);
            if (sc.getName().equals(classname)){
                cb_class2.setSelectedIndex(i);
            }

        }
        group.clearSelection();
        if (sex.equals(rb_male.getText())){
            rb_male.setSelected(true);

        }
        if (sex.equals(rb_female.getText())){
            rb_female.setSelected(true);
        }
        if (sex.equals(rb_secret.getText())){
            rb_secret.setSelected(true);
        }


    }
    private void setTable(Student stu) {
        StudentDao dao=new StudentDao();
        List<Student> list = dao.getStudentList(stu);
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        for (Student stu1 : list) {
            Object[] row = {stu1.getId(), stu1.getName(), getClassNameById(stu1.getClassId()), stu1.getSex(), stu1.getPassword()};
            model.addRow(row);
        }
    }

    private String getClassNameById(int classId){
        for (StudentClass sc : Classlist)
        {
            if(sc.getId()==classId){
                return sc.getName();
            }
        }
        return " ";

    }
    private void setStuClassInfo(){
        ClassDao dao=new ClassDao();

        Classlist = dao.getClassList(new StudentClass());
        for (StudentClass sc : Classlist){
            cb_class.addItem(sc);
            cb_class2.addItem(sc);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        txt_name = new JTextField();
        label2 = new JLabel();
        cb_class = new JComboBox();
        btn_query = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label3 = new JLabel();
        txt_name2 = new JTextField();
        label4 = new JLabel();
        panel1 = new JPanel();
        rb_male = new JRadioButton();
        rb_female = new JRadioButton();
        rb_secret = new JRadioButton();
        btn_update = new JButton();
        label5 = new JLabel();
        cb_class2 = new JComboBox();
        label6 = new JLabel();
        pf_psw = new JTextField();
        btn_delete = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "68dlu, $lcgap, 67dlu, $lcgap, 71dlu, $lcgap, 83dlu, 73dlu, $lcgap, 87dlu, 2*($lcgap), 82dlu",
            "58dlu, $lgap, 90dlu, $lgap, 38dlu, $lgap, 59dlu"));

        //---- label1 ----
        label1.setText("\u5b66\u751f\u59d3\u540d");
        label1.setIcon(new ImageIcon(getClass().getResource("/\u5b66\u751f\u7ba1\u7406.png")));
        contentPane.add(label1, CC.xy(3, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name, CC.xy(5, 1));

        //---- label2 ----
        label2.setText("\u6240\u5c5e\u73ed\u7ea7");
        label2.setIcon(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u540d\u79f0.png")));
        contentPane.add(label2, CC.xy(8, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(cb_class, CC.xy(10, 1));

        //---- btn_query ----
        btn_query.setText("\u67e5\u8be2");
        btn_query.setIcon(new ImageIcon(getClass().getResource("/\u641c\u7d22.png")));
        contentPane.add(btn_query, CC.xy(13, 1, CC.LEFT, CC.DEFAULT));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, CC.xywh(3, 3, 8, 1, CC.FILL, CC.FILL));

        //---- label3 ----
        label3.setText("\u5b66\u751f\u59d3\u540d");
        label3.setIcon(new ImageIcon(getClass().getResource("/\u5b66\u751f\u7ba1\u7406.png")));
        contentPane.add(label3, CC.xy(3, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name2, CC.xy(5, 5));

        //---- label4 ----
        label4.setText("\u5b66\u751f\u6027\u522b");
        label4.setIcon(new ImageIcon(getClass().getResource("/\u6027\u522b.png")));
        contentPane.add(label4, CC.xy(8, 5, CC.RIGHT, CC.DEFAULT));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "27dlu, $lcgap, 24dlu, $lcgap, 32dlu",
                "default"));

            //---- rb_male ----
            rb_male.setText("\u7537");
            panel1.add(rb_male, CC.xy(1, 1));

            //---- rb_female ----
            rb_female.setText("\u5973");
            panel1.add(rb_female, CC.xy(3, 1));

            //---- rb_secret ----
            rb_secret.setText("\u4fdd\u5bc6");
            panel1.add(rb_secret, CC.xy(5, 1));
        }
        contentPane.add(panel1, CC.xy(10, 5));

        //---- btn_update ----
        btn_update.setText("\u786e\u8ba4\u4fee\u6539");
        btn_update.setIcon(new ImageIcon(getClass().getResource("/\u786e\u8ba4.png")));
        contentPane.add(btn_update, CC.xy(13, 5, CC.LEFT, CC.DEFAULT));

        //---- label5 ----
        label5.setText("\u6240\u5c5e\u73ed\u7ea7");
        label5.setIcon(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u540d\u79f0.png")));
        contentPane.add(label5, CC.xy(3, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(cb_class2, CC.xy(5, 7));

        //---- label6 ----
        label6.setText("\u767b\u5f55\u5bc6\u7801");
        label6.setIcon(new ImageIcon(getClass().getResource("/password.png")));
        contentPane.add(label6, CC.xy(8, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(pf_psw, CC.xy(10, 7));

        //---- btn_delete ----
        btn_delete.setText("\u5220\u9664\u5b66\u751f");
        btn_delete.setIcon(new ImageIcon(getClass().getResource("/\u5220\u9664.png")));
        contentPane.add(btn_delete, CC.xy(13, 7, CC.LEFT, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField txt_name;
    private JLabel label2;
    private JComboBox cb_class;
    private JButton btn_query;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label3;
    private JTextField txt_name2;
    private JLabel label4;
    private JPanel panel1;
    private JRadioButton rb_male;
    private JRadioButton rb_female;
    private JRadioButton rb_secret;
    private JButton btn_update;
    private JLabel label5;
    private JComboBox cb_class2;
    private JLabel label6;
    private JTextField pf_psw;
    private JButton btn_delete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

