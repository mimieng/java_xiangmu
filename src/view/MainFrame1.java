/*
 * Created by JFormDesigner on Tue Dec 17 19:11:10 CST 2024
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import pojo.User;

/**
 * @author chentao
 */
public class MainFrame1 extends JFrame {
    public static String userType;
    public  static User admin;
    public MainFrame1(String mUserType, User mAdmin) {
        userType = mUserType;
        admin = mAdmin;
        initComponents();
        item_psw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditPswFrame1 editPswFrame = new EditPswFrame1();
                editPswFrame.setVisible(true);
            }
        });
        item_quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        item_addKu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new CarRent().setVisible(true);
            }
        });
//        item_KuList.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new ClientList().setVisible(true);
//            }
//        });
//        item_addYu.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new addstaff().setVisible(true);
//            }
//        });
//        item_YuList.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new StaffList().setVisible(true);
//            }
//        });
        item_addCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new addmedicines().setVisible(true);
            }
        });
        item_CarList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new medicinesManageFrame1().setVisible(true);
            }
        });
        item_aboutUs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new aboutUs().setVisible(true);
            }
        });
            }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        menu2 = new JMenu();
        item_psw = new JMenuItem();
        item_quit = new JMenuItem();
        menu3 = new JMenu();
        item_addCar = new JMenuItem();
        item_CarList = new JMenuItem();
        menu4 = new JMenu();
        item_addKu = new JMenuItem();
        menu6 = new JMenu();
        item_aboutUs = new JMenuItem();
        panel1 = new JPanel();

        //======== this ========
        setTitle("\u7cfb\u7edf\u7ba1\u7406\u5458\u4e3b\u754c\u9762");
        setIconImage(new ImageIcon(getClass().getResource("/\u5b66\u751f\u7ba1\u7406.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "2560px",
            "1600px"));

        //======== menuBar1 ========
        {

            //======== menu2 ========
            {
                menu2.setText("\u7cfb\u7edf\u8bbe\u7f6e");
                menu2.setIcon(new ImageIcon(getClass().getResource("/\u7cfb\u7edf\u8bbe\u7f6e.png")));

                //---- item_psw ----
                item_psw.setText("\u4fee\u6539\u5bc6\u7801");
                item_psw.setIcon(new ImageIcon(getClass().getResource("/\u4fee\u6539\u5bc6\u7801.png")));
                menu2.add(item_psw);

                //---- item_quit ----
                item_quit.setText("\u9000\u51fa\u7cfb\u7edf");
                item_quit.setIcon(new ImageIcon(getClass().getResource("/\u9000\u51fa.png")));
                menu2.add(item_quit);
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("\u836f\u54c1\u7ba1\u7406");
                menu3.setIcon(new ImageIcon(getClass().getResource("/1.jpg")));

                //---- item_addCar ----
                item_addCar.setText("\u6dfb\u52a0\u836f\u54c1");
                item_addCar.setIcon(new ImageIcon(getClass().getResource("/\u6dfb\u52a0.png")));
                menu3.add(item_addCar);

                //---- item_CarList ----
                item_CarList.setText("\u836f\u54c1\u5217\u8868");
                item_CarList.setIcon(new ImageIcon(getClass().getResource("/1.jpg")));
                menu3.add(item_CarList);
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            {
                menu4.setText("\u4e70\u836f");
                menu4.setIcon(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u7ba1\u7406.png")));

                //---- item_addKu ----
                item_addKu.setText("\u4e70\u836f");
                item_addKu.setIcon(new ImageIcon(getClass().getResource("/\u6dfb\u52a0.png")));
                menu4.add(item_addKu);
            }
            menuBar1.add(menu4);

            //======== menu6 ========
            {
                menu6.setText("\u5e2e\u52a9");
                menu6.setIcon(new ImageIcon(getClass().getResource("/\u5e2e\u52a9.png")));

                //---- item_aboutUs ----
                item_aboutUs.setText("\u5173\u4e8e\u6211\u4eec");
                item_aboutUs.setIcon(new ImageIcon(getClass().getResource("/\u5173\u4e8e\u6211\u4eec.png")));
                menu6.add(item_aboutUs);
            }
            menuBar1.add(menu6);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setBackground(Color.darkGray);
            panel1.setLayout(new FormLayout(
                "default",
                "default"));
        }
        contentPane.add(panel1, CC.xy(1, 1, CC.FILL, CC.FILL));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu menu2;
    private JMenuItem item_psw;
    private JMenuItem item_quit;
    private JMenu menu3;
    private JMenuItem item_addCar;
    private JMenuItem item_CarList;
    private JMenu menu4;
    private JMenuItem item_addKu;
    private JMenu menu6;
    private JMenuItem item_aboutUs;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on




}
