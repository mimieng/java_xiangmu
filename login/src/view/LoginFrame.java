package view;

import dao.UserDao;
import dao.UserDaoImpl;
import pojo.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginFrame extends javax.swing.JFrame{
    private JLabel jLabel1, jLabel2;
    private JTextField tcount, tpsw;
    private JButton btn_login, btn_reset;
    public LoginFrame(){
        jLabel1 = new JLabel("账号");
        jLabel2 = new JLabel("密码");
        tcount = new JTextField(10);
        tpsw = new JTextField(10);
        btn_login = new JButton("登录");
        btn_reset = new JButton("重置");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 30, 30);
        this.setLayout(layout);
        this.add(jLabel1);
        this.add(tcount);
        this.add(jLabel2);
        this.add(tpsw);
        this.add(btn_login);
        this.add(btn_reset);
        this.setTitle("登录界面");
        this.setBounds(800, 400, 250, 250);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn_reset.addActionListener(e -> {
            tcount.setText("");
            tpsw.setText("");
        });
        btn_login.addActionListener(e -> {
            String count = tcount.getText().toString();
            String psw = tpsw.getText().toString();
            UserDao dao = new UserDaoImpl();
            User user = null;
            try {
                user = dao.login(count, psw);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            if (user==null){
                JOptionPane.showMessageDialog(LoginFrame.this, "登录失败");
            }else{
                StudentFrame sf = new StudentFrame(user);
                LoginFrame.this.dispose();
            }

        });
    }
    public static void main(String[] args){
        new LoginFrame();
    }
}
