/*
 * Created by JFormDesigner on Sun Nov 24 20:27:30 CST 2024
 */

package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import dao.StudentDao;
import dao.TeacherDao;
import pojo.Student;
import pojo.Teacher;
import util.StringUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

/**
 * @author 晚吟
 */
public class TeaManageFrame extends JFrame {
    private ButtonGroup group;
    public TeaManageFrame() {
        //1.自动生成的初始化界面的代码，放到构造方法的第一行
        initComponents();
        //2.二个单选按钮实现单选的效果
        group=new ButtonGroup();
        group.add(rb_male);
        group.add(rb_famale);
        //3.设置表组件的显示模式
        DefaultTableModel dtm=new DefaultTableModel();
        dtm.setDataVector(new  Object[][]{},new String[]{"教师ID","教师姓名","学生性别","教师职称","教师年龄","登陆密码"});
        table1.setModel(dtm);
       //4.查询按钮的ActionEvent事件的处理
        btn_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1.获取用户从界面输入的姓名和班级信息
                String teaName=txt_name.getText().toString();
                Teacher tea=new Teacher();
                tea.setName(teaName);
                //2.do查询,把查询到的结果设置到table中
                setTable(tea);
            }
        });
       //5.选中表中的某一项时，将该学生数据设置到到页面下方的控制件上
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedTableRow();
            }
        });
      //6.btn_update的事件处理，实现修改数据的功能
        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1.判断一下表中数据有没有被选中，如果没有被选中数据，需要提示用户要选中数据才能修改
                int srow=table1.getSelectedRow();
                if(srow==-1)
                {
                    JOptionPane.showMessageDialog(TeaManageFrame.this,"请选中要修改的数据!!");
                    return ;
                }
                //2.从界面获取用户修改过后的数据
                String teaName=txt_name2.getText().toString();
                String teaTiltle=txt_tiltle.getText().toString();
                // int teaAge= Integer.parseInt(txt_age.getText().toString());
                String teaPsw=txt_psw.getText().toString();
                if(StringUtil.isEmpty(teaName))
                {
                    JOptionPane.showMessageDialog(TeaManageFrame.this,"请输入姓名!!");
                    return;
                }
                if(StringUtil.isEmpty(teaTiltle))
                {
                    JOptionPane.showMessageDialog(TeaManageFrame.this,"请输入职称!!");
                    return;
                }

                if(StringUtil.isEmpty(txt_age.getText().toString()))
                {
                    JOptionPane.showMessageDialog(TeaManageFrame.this,"请输入年龄!!");
                    return;
                }
                if(StringUtil.isEmpty(teaPsw)) {
                    JOptionPane.showMessageDialog(TeaManageFrame.this, "请输入密码!!");
                    return;
                }
                String teaSex=rb_male.isSelected()?rb_male.getText():rb_famale.getText();
                //把数据封装到一个teacher 对象中
                Teacher tea=new Teacher();
                //从选中的行中获取学生的编号
                int teaId=Integer.parseInt(table1.getValueAt(srow,0).toString());
                //封装stu对象
                tea.setName(teaName);
                tea.setSex(teaSex);
                tea.setTitle(teaTiltle);
                tea.setAge( Integer.parseInt(txt_age.getText().toString()));
                tea.setPassword(teaPsw);
                tea.setId(teaId);
                //3.dso.update
                TeacherDao dao =new TeacherDao();
                boolean flag=dao.update(tea);
                //4.根据返回值提示不同信息
                if(flag)
                {
                    JOptionPane.showMessageDialog(TeaManageFrame.this,"修改成功！！");
                }else{
                    JOptionPane.showMessageDialog(TeaManageFrame.this,"修改失败！！");
                }
                //及时更新新表格组件中的数据
                setTable(new Teacher());
            }
        });
        //7.删除按钮的事件处理过程
        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1.判断用户有没有选中表中的数据，没有则提示
                int srow=table1.getSelectedRow();
                if(srow==-1)
                {
                    JOptionPane.showMessageDialog(TeaManageFrame.this,"请在表中选中待删除的数据");
                    return;
                }
                //2.从表中获取选中的那条数据的学生编号，dao.delete()
                int teaId=Integer.parseInt(table1.getValueAt(srow,0).toString());
                TeacherDao dao=new TeacherDao();
                boolean flag=dao.delete(teaId);
                //3.根据返回值不同执行不同的提示
                if(flag)
                {
                    JOptionPane.showMessageDialog(TeaManageFrame.this,"删除成功!!");
                }else {
                    JOptionPane.showMessageDialog(TeaManageFrame.this,"删除失败!!");
                }
                //4.及时更新表中数据
                setTable(new Teacher());
            }
        });

    }

    private void selectedTableRow() {
        //1。把选中的这一行数据的每一个字段的字段值提取出来
        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        int srow=table1.getSelectedRow();
        String teaName=dtm.getValueAt(srow,1).toString();
        String teaSex=dtm.getValueAt(srow,2).toString();
        String teaTiltle=dtm.getValueAt(srow,3).toString();
        int teaAge= Integer.parseInt(dtm.getValueAt(srow,4).toString());
        String teaPsw=dtm.getValueAt(srow,5).toString();
        //2.设置到界面上
        txt_name2.setText(teaName);
        txt_psw.setText(teaPsw);
        txt_tiltle.setText(teaTiltle);
        txt_age.setText(teaAge+"");
        //把性别设置到单选按钮上
        group.clearSelection();
        if(teaSex.equals(rb_male.getText()))
        {
            rb_male.setSelected(true);
        }
        if(teaSex.equals(rb_famale.getText()))
        {
            rb_famale.setSelected(true);
        }

    }

    private void setTable(Teacher tea) {
       //1.dao.getStudentList()
       TeacherDao dao = new TeacherDao();
       List<Teacher> stuList = dao.getTeacherList(tea);
      DefaultTableModel dtm= (DefaultTableModel) table1.getModel();
       dtm.setRowCount(0);
       for(Teacher teach:stuList){
           Vector v=new Vector();
           v.add(teach.getId());
           v.add(teach.getName());
           v.add(teach.getSex());
           v.add(teach.getTitle());
           v.add(teach.getAge());
           v.add(teach.getPassword());
           dtm.addRow(v);
       }
   }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        txt_name = new JTextField();
        btn_query = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label3 = new JLabel();
        label4 = new JLabel();
        txt_name2 = new JTextField();
        label5 = new JLabel();
        panel1 = new JPanel();
        rb_male = new JRadioButton();
        rb_famale = new JRadioButton();
        label6 = new JLabel();
        txt_tiltle = new JTextField();
        label7 = new JLabel();
        txt_age = new JTextField();
        label8 = new JLabel();
        txt_psw = new JTextField();
        btn_update = new JButton();
        btn_delete = new JButton();

        //======== this ========
        setTitle("\u6559\u5e08\u4fe1\u606f\u7ba1\u7406");
        setIconImage(new ImageIcon(getClass().getResource("/\u8001\u5e08.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "25dlu, $lcgap, 75dlu, $lcgap, 84dlu, 2*($lcgap, 75dlu), $lcgap, 25dlu",
            "40dlu, $lgap, 120dlu, $lgap, 28dlu, 3*($lgap, 40dlu)"));

        //---- label1 ----
        label1.setText("\u8001\u5e08\u59d3\u540d\uff1a");
        label1.setIcon(new ImageIcon(getClass().getResource("/\u8001\u5e08.png")));
        contentPane.add(label1, CC.xy(3, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name, CC.xywh(5, 1, 2, 1));

        //---- btn_query ----
        btn_query.setText("\u67e5\u8be2");
        btn_query.setIcon(new ImageIcon(getClass().getResource("/\u641c\u7d22.png")));
        contentPane.add(btn_query, CC.xy(7, 1, CC.RIGHT, CC.DEFAULT));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, CC.xywh(3, 3, 8, 2, CC.DEFAULT, CC.FILL));

        //---- label3 ----
        label3.setText("\u6559\u5e08\u4fe1\u606f\u4fee\u6539");
        contentPane.add(label3, CC.xy(3, 5, CC.LEFT, CC.BOTTOM));

        //---- label4 ----
        label4.setText("\u6559\u5e08\u59d3\u540d\uff1a");
        label4.setIcon(new ImageIcon(getClass().getResource("/\u8001\u5e08.png")));
        contentPane.add(label4, CC.xy(3, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_name2, CC.xy(5, 7));

        //---- label5 ----
        label5.setText("\u6559\u5e08\u6027\u522b\uff1a");
        label5.setIcon(new ImageIcon(getClass().getResource("/\u6027\u522b.png")));
        contentPane.add(label5, CC.xy(7, 7, CC.RIGHT, CC.DEFAULT));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "default, $lcgap, default",
                "default"));

            //---- rb_male ----
            rb_male.setText("\u7537");
            panel1.add(rb_male, CC.xy(1, 1));

            //---- rb_famale ----
            rb_famale.setText("\u5973");
            panel1.add(rb_famale, CC.xy(3, 1));
        }
        contentPane.add(panel1, CC.xy(9, 7));

        //---- label6 ----
        label6.setText("\u6559\u5e08\u804c\u79f0\uff1a");
        label6.setIcon(new ImageIcon(getClass().getResource("/\u804c\u79f0\u8bc4\u5b9a.png")));
        contentPane.add(label6, CC.xy(3, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_tiltle, CC.xy(5, 9));

        //---- label7 ----
        label7.setText("\u6559\u5e08\u5e74\u9f84\uff1a");
        label7.setIcon(new ImageIcon(getClass().getResource("/\u5e74\u9f84.png")));
        contentPane.add(label7, CC.xy(7, 9, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_age, CC.xy(9, 9));

        //---- label8 ----
        label8.setText("\u767b\u9646\u5bc6\u7801\uff1a");
        label8.setIcon(new ImageIcon(getClass().getResource("/\u5bc6\u7801.png")));
        contentPane.add(label8, CC.xy(3, 11, CC.RIGHT, CC.DEFAULT));
        contentPane.add(txt_psw, CC.xy(5, 11));

        //---- btn_update ----
        btn_update.setText("\u786e\u8ba4\u4fee\u6539");
        btn_update.setIcon(new ImageIcon(getClass().getResource("/\u786e\u8ba4.png")));
        contentPane.add(btn_update, CC.xy(7, 11, CC.RIGHT, CC.DEFAULT));

        //---- btn_delete ----
        btn_delete.setText("\u5220\u9664\u4fe1\u606f");
        btn_delete.setIcon(new ImageIcon(getClass().getResource("/\u5220\u9664.png")));
        contentPane.add(btn_delete, CC.xy(9, 11, CC.RIGHT, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField txt_name;
    private JButton btn_query;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label3;
    private JLabel label4;
    private JTextField txt_name2;
    private JLabel label5;
    private JPanel panel1;
    private JRadioButton rb_male;
    private JRadioButton rb_famale;
    private JLabel label6;
    private JTextField txt_tiltle;
    private JLabel label7;
    private JTextField txt_age;
    private JLabel label8;
    private JTextField txt_psw;
    private JButton btn_update;
    private JButton btn_delete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
