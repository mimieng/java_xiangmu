package view;

import dao.StuDao;
import dao.StuDaoImpl;
import pojo.Student;
import pojo.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class StudentFrame extends javax.swing.JFrame{
    private User user;
    JLabel jl1 = new JLabel("请输入学号:");
    JLabel jl2 = new JLabel("学号:");
    JLabel jl3 = new JLabel("姓名:");
    JLabel jl4 = new JLabel("分数:");
    JTextField jtf1 = new JTextField(10);
    JTextField jtf2 = new JTextField(20);
    JTextField jtf3 = new JTextField(20);
    JTextField jtf4 = new JTextField(20);
    JButton jb1 = new JButton("查找");
    JButton jb2 = new JButton("添加");
    JButton jb3 = new JButton("删除");
    JButton jb4 = new JButton("修改");
    public StudentFrame(User user)

    {   this.user = user;

        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 30, 30);
        this.setLayout(layout);
        this.add(jl1);
        this.add(jtf1);
        this.add(jb1);
        this.add(jl2);
        this.add(jtf2);
        this.add(jl3);
        this.add(jtf3);
        this.add(jl4);
        this.add(jtf4);
        this.add(jb2);
        this.add(jb3);
        this.add(jb4);
        this.setBounds(800, 400, 350, 350);
        this.setTitle(""+user.getName()+"学生管理");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        jb2.addActionListener(e -> {
            Student stu = StudentFrame.this.getStudent();
            StuDao stuDao = new StuDaoImpl();
            Boolean flag;
            try {
                flag = stuDao.addStudent(stu);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (flag) {
                JOptionPane.showMessageDialog(StudentFrame.this, "添加成功！");
            } else {
                JOptionPane.showMessageDialog(StudentFrame.this, "添加失败！");
            }
            StudentFrame.this.setNull();
        });
        jb1.addActionListener(e -> {
            int id = Integer.parseInt(jtf1.getText());
            StuDao stuDao = new StuDaoImpl();
            Student stu = null;
            try {
                stu = stuDao.findStudentById(id);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (stu != null) {
                StudentFrame.this.setStudent(stu);
            } else {
               JOptionPane.showMessageDialog(StudentFrame.this, "没有"+id+"学生！");
            }
        });
        jb3.addActionListener(e -> {
            int id = Integer.parseInt(jtf1.getText());
            StuDao stuDao = new StuDaoImpl();
            Boolean flag= null;
            try {
                flag = stuDao.deleteStudent(id);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (flag) {
                JOptionPane.showMessageDialog(StudentFrame.this, "删除成功！");
            } else {
               JOptionPane.showMessageDialog(StudentFrame.this,"刪除失敗");
            }
        });
        jb4.addActionListener(e -> {
            Student stu = StudentFrame.this.getStudent();
            StuDao stuDao = new StuDaoImpl();

            Boolean flag= null;
            try {
                flag = stuDao.updateStudent(stu);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (flag) {
                JOptionPane.showMessageDialog(StudentFrame.this, "修改成功！");
            } else {
                JOptionPane.showMessageDialog(StudentFrame.this, "修改失败！");
            }
        });
    }
    public void setStudent(Student stu){
        jtf2.setText(stu.getId()+"");
        jtf3.setText(stu.getName());
        jtf4.setText(stu.getScore()+"");
    }
    public void  setNull()
    {

        jtf2.setText("");
        jtf3.setText("");
        jtf4.setText("");
    }
  public Student getStudent()
  {
      Student stu = new Student();
      stu.setId(Integer.parseInt(jtf2.getText()));
      stu.setName(jtf3.getText());
      stu.setScore(Double.parseDouble(jtf4.getText()));
      return stu;
  }
}
