/*
 * Created by JFormDesigner on Tue Dec 17 13:45:05 CST 2024
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import pojo.Admin;
import pojo.User;

/**
 * @author 晚吟
 */
public class MainUser extends JFrame {
    public static String usertype;
    public static User user;
    public MainUser(String userType, User users) {
        initComponents();
        usertype = userType;
        user= users;
        menuItem2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditPswFrame2().setVisible(true);
            }
        });
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new libraryList2().setVisible(true);
            }
        });
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Addborrow().setVisible(true);
            }
        });
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new borrowList().setVisible(true);
            }
        });
        menuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new huantushu().setVisible(true);
            }
        });
        menuItem7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new about().setVisible(true);
            }
        });

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu4 = new JMenu();
        menuItem3 = new JMenuItem();
        menu6 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menu8 = new JMenu();
        menuItem6 = new JMenuItem();
        menu11 = new JMenu();
        menuItem7 = new JMenuItem();
        panel1 = new JPanel();

        //======== this ========
        setTitle("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "1920px",
            "1200px"));

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u7cfb\u7edf\u8bbe\u7f6e");

                //---- menuItem1 ----
                menuItem1.setText("\u4fee\u6539\u5bc6\u7801");
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("\u9000\u51fa\u7cfb\u7edf");
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);

            //======== menu4 ========
            {
                menu4.setText("\u56fe\u4e66\u5927\u5168");

                //---- menuItem3 ----
                menuItem3.setText("\u56fe\u4e66\u5217\u8868");
                menu4.add(menuItem3);
            }
            menuBar1.add(menu4);

            //======== menu6 ========
            {
                menu6.setText("\u501f\u9605\u56fe\u4e66");

                //---- menuItem4 ----
                menuItem4.setText("\u501f\u9605\u56fe\u4e66");
                menu6.add(menuItem4);

                //---- menuItem5 ----
                menuItem5.setText("\u501f\u9605\u5217\u8868");
                menu6.add(menuItem5);
            }
            menuBar1.add(menu6);

            //======== menu8 ========
            {
                menu8.setText("\u5f52\u8fd8\u56fe\u4e66");

                //---- menuItem6 ----
                menuItem6.setText("\u5f52\u8fd8\u56fe\u4e66");
                menu8.add(menuItem6);
            }
            menuBar1.add(menu8);

            //======== menu11 ========
            {
                menu11.setText("\u5173\u4e8e\u6211\u4eec");

                //---- menuItem7 ----
                menuItem7.setText("\u5173\u4e8e\u6211\u4eec");
                menu11.add(menuItem7);
            }
            menuBar1.add(menu11);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0xffcccc));
            panel1.setLayout(new FormLayout(
                "1920px",
                "1200px"));
        }
        contentPane.add(panel1, CC.xy(1, 1));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenu menu4;
    private JMenuItem menuItem3;
    private JMenu menu6;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenu menu8;
    private JMenuItem menuItem6;
    private JMenu menu11;
    private JMenuItem menuItem7;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
