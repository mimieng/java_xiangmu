package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import dao.AdminDao;
import dao.StudentDao;
import dao.UserDao;
import pojo.Admin;
import pojo.Student;
import pojo.User;
import util.StringUtil;

public class LoginFrame extends JFrame implements ActionListener {
    private JLabel label1, label2, label3, label4;
    private JTextField txt_name;
    private JPasswordField pf_psw;
    private JComboBox<String> cb_type;
    private JButton btn_login, btn_reset;

    public LoginFrame() {
        initComponents();
        initActions();
    }

    private void initActions() {
        btn_login.addActionListener(this);
        btn_reset.addActionListener(e -> resetFields());
    }

    private void resetFields() {
        txt_name.setText("");
        pf_psw.setText("");
        cb_type.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_login) {
            login();
        }
    }

    private void login() {
        String username = txt_name.getText();
        String password = new String(pf_psw.getPassword());
        String userType = (String) cb_type.getSelectedItem();

        if (StringUtil.isEmpty(username)) {
            showMessage("请输入用户名！");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            showMessage("请输入密码！");
            return;
        }

        if ("系统管理员".equals(userType)) {
            adminLogin(username, password);
        } else if ("学生".equals(userType)) {
            studentLogin(username, password);
        }
    }

    private void adminLogin(String username, String password) {
        Admin admin = new Admin();
        admin.setName(username);
        admin.setPassword(password);

        try {
            Admin rstAdmin = new AdminDao().login(admin);
            if (rstAdmin != null) {
                showMessage("系统管理员登录成功！");
                new MainFrame("系统管理员", rstAdmin).setVisible(true);
                dispose();
            } else {
                showMessage("用户名或密码错误！");
            }
        } catch (Exception ex) {
            showMessage("登录异常：" + ex.getMessage());
        }
    }

    private void studentLogin(String username, String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);

        try {
            User rstStudent = new UserDao().login(user);
            if (rstStudent != null) {
                showMessage("学生登录成功！");
                new MainUser("学生", rstStudent).setVisible(true);
                dispose();

            } else {
                showMessage("用户名或密码错误！");
            }
        } catch (Exception ex) {
            showMessage("登录异常：" + ex.getMessage());
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void initComponents() {
        label1 = new JLabel("欢迎来到图书管理系统", new ImageIcon(getClass().getResource("/logo.png")), JLabel.CENTER);
        label1.setFont(new Font("微软雅黑", Font.BOLD, 24));

        label2 = new JLabel("用户名：", new ImageIcon(getClass().getResource("/用户名.png")), JLabel.RIGHT);
        txt_name = new JTextField();

        label3 = new JLabel("密   码：", new ImageIcon(getClass().getResource("/密码.png")), JLabel.RIGHT);
        pf_psw = new JPasswordField();

        label4 = new JLabel("用户类型：", new ImageIcon(getClass().getResource("/userType.png")), JLabel.RIGHT);
        cb_type = new JComboBox<>(new String[]{"系统管理员", "学生"});

        btn_login = new JButton("登录", new ImageIcon(getClass().getResource("/登录.png")));
        btn_reset = new JButton("重置", new ImageIcon(getClass().getResource("/重置.png")));

        setLayout(new FormLayout(
                "100dlu, $lcgap, 150dlu",
                "50dlu, $lgap, 40dlu, $lgap, 40dlu, $lgap, 40dlu, $lgap, 40dlu"));

        add(label1, CC.xywh(1, 1, 3, 1, CC.CENTER, CC.DEFAULT));
        add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));
        add(txt_name, CC.xy(3, 3));
        add(label3, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        add(pf_psw, CC.xy(3, 5));
        add(label4, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));
        add(cb_type, CC.xy(3, 7));
        add(btn_login, CC.xy(1, 9, CC.RIGHT, CC.DEFAULT));
        add(btn_reset, CC.xy(3, 9));

        setTitle("登录窗口");
        setIconImage(new ImageIcon(getClass().getResource("/学生.png")).getImage());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


}
