package view;

import javax.swing.*;
import java.awt.*;

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
        this.setSize(250, 250);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        new LoginFrame();
    }
}
