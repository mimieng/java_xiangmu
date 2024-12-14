/*
 * Created by JFormDesigner on Sat Dec 14 19:37:46 CST 2024
 */

package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.mysql.cj.xdevapi.Table;
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
public class ClassFrame extends JFrame {
    private List<StudentClass> Classlist;
    public ClassFrame() {
        initComponents();
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setDataVector(new Object[][]{},new String []{"班级编号","班级名称","学生信息介绍"});
        table1.setModel(model);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();

                StudentClass stu=new StudentClass();
                stu.setName(name);

                setTable(new StudentClass());

            }

        });

        button3.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                StudentClass stu=new StudentClass();
                int row = table1.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(ClassFrame.this, "请选择要删除的数据！");
                    return;
                }
                int id = Integer.parseInt(table1.getValueAt(row, 0).toString());
                ClassDao dao=new ClassDao();
                boolean flag = dao.delete(id);
                if (flag){
                    JOptionPane.showMessageDialog(ClassFrame.this, "删除成功！");


                }else {
                    JOptionPane.showMessageDialog(ClassFrame.this, "删除失败！");

                }
                setTable(new StudentClass());
            }


        });
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(ClassFrame.this, "请选择要修改的数据！");
                }
                String name = textField2.getText();
                String name1 =textField3.getText();
                if(StringUtil.isEmpty(name)){
                    JOptionPane.showMessageDialog(ClassFrame.this, "请输入班级名称！");
                    return;
                }
                if (StringUtil.isEmpty(name1)){
                    JOptionPane.showMessageDialog(ClassFrame.this, "请输入班级信息");
                    return;
                }
                StudentClass stu=new StudentClass();
                int id = Integer.parseInt(table1.getValueAt(row, 0).toString());
                stu.setId(id);
                stu.setName(name);
                stu.setInfo(name1);

                ClassDao dao=new ClassDao();
                boolean flag = dao.update(stu);
                if (flag){
                    JOptionPane.showMessageDialog(ClassFrame.this, "修改成功！");

                }else {
                    JOptionPane.showMessageDialog(ClassFrame.this, "修改失败！");
                }
                setTable(new StudentClass());

            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedTableRow();
            }

        });

    } private void setTable(StudentClass stu) {
        ClassDao dao=new ClassDao();
        List<StudentClass> list = dao.getClassList(stu);
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        for (StudentClass stu1 : list) {
            Object[] row = {stu1.getId(), stu1.getName(), stu1.getInfo()};
            model.addRow(row);
        }
    }
    private void selectedTableRow() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        int row = table1.getSelectedRow();

        if (row != -1) { // 确保选中了一行

            String name = (String) model.getValueAt(row, 1);
            String info = (String) model.getValueAt(row, 2);


            textField2.setText(name);
            textField3.setText(info);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label2 = new JLabel();
        textField2 = new JTextField();
        button2 = new JButton();
        label3 = new JLabel();
        textField3 = new JTextField();
        button3 = new JButton();

        //======== this ========
        setTitle("\u73ed\u7ea7\u5217\u8868");
        setIconImage(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u5217\u8868.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "76dlu, $lcgap, 115dlu, $lcgap, 113dlu",
            "50dlu, $lgap, 95dlu, $lgap, 47dlu, $lgap, 71dlu"));

        //---- label1 ----
        label1.setText("\u73ed\u7ea7\u540d\u79f0");
        label1.setIcon(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u540d\u79f0.png")));
        contentPane.add(label1, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField1, CC.xy(3, 1));

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        button1.setIcon(new ImageIcon(getClass().getResource("/\u641c\u7d22.png")));
        contentPane.add(button1, CC.xy(5, 1, CC.CENTER, CC.DEFAULT));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, CC.xywh(1, 3, 5, 1, CC.DEFAULT, CC.FILL));

        //---- label2 ----
        label2.setText("\u73ed\u7ea7\u540d\u79f0\uff1a");
        label2.setIcon(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u540d\u79f0.png")));
        contentPane.add(label2, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField2, CC.xy(3, 5));

        //---- button2 ----
        button2.setText("\u786e\u8ba4\u4fee\u6539");
        button2.setIcon(new ImageIcon(getClass().getResource("/\u786e\u8ba4.png")));
        contentPane.add(button2, CC.xy(5, 5, CC.CENTER, CC.DEFAULT));

        //---- label3 ----
        label3.setText("\u73ed\u7ea7\u4fe1\u606f\uff1a");
        label3.setIcon(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u4ecb\u7ecd.png")));
        contentPane.add(label3, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        contentPane.add(textField3, CC.xy(3, 7, CC.DEFAULT, CC.FILL));

        //---- button3 ----
        button3.setText("\u5220\u9664");
        button3.setIcon(new ImageIcon(getClass().getResource("/\u5220\u9664.png")));
        contentPane.add(button3, CC.xy(5, 7, CC.CENTER, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label2;
    private JTextField textField2;
    private JButton button2;
    private JLabel label3;
    private JTextField textField3;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
