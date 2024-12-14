/*
 * Created by JFormDesigner on Sun Dec 01 11:31:19 CST 2024
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import pojo.Admin;

/**
 * @author 晚吟
 */
public class MainFrame extends JFrame {
    public static String usertype;
    public static Admin admin;
    public MainFrame(String type , Admin admins) {
        usertype=type;
        admin = admins;
        initComponents();
        item_psw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditPswFrame().setVisible(true);

            }
        });
        item_addStu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStuFrame().setVisible(true);
            }
        });
        item_stuList.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new StuManageFrame().setVisible(true);
            }
        });
        item_addClass.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddClassFrame().setVisible(true);
            }
        });
        item_classList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassFrame().setVisible(true);
            }
        });
        item_addTea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTeaFrame().setVisible(true);
            }
        });
        item_teaList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeaManageFrame().setVisible(true);
            }
        });
        item_aboutUs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new settingFrame().setVisible(true);
            }
        });


    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        item_psw = new JMenuItem();
        menuItem1 = new JMenuItem();
        menu2 = new JMenu();
        item_addStu = new JMenuItem();
        item_stuList = new JMenuItem();
        menu8 = new JMenu();
        item_addClass = new JMenuItem();
        item_classList = new JMenuItem();
        menu9 = new JMenu();
        item_addTea = new JMenuItem();
        item_teaList = new JMenuItem();
        menu10 = new JMenu();
        item_aboutUs = new JMenuItem();
        panel1 = new JPanel();

        //======== this ========
        setTitle("\u5b66\u751f\u4fe1\u606f\u7cfb\u7edf");
        setIconImage(new ImageIcon(getClass().getResource("/\u5b66\u751f\u7ba1\u7406.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "1920px",
            "1200px"));

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u7cfb\u7edf\u8bbe\u7f6e");
                menu1.setIcon(new ImageIcon(getClass().getResource("/\u7cfb\u7edf\u8bbe\u7f6e.png")));

                //---- item_psw ----
                item_psw.setText("\u4fee\u6539\u5bc6\u7801");
                item_psw.setIcon(new ImageIcon(getClass().getResource("/\u4fee\u6539\u5bc6\u7801.png")));
                menu1.add(item_psw);

                //---- menuItem1 ----
                menuItem1.setText("\u9000\u51fa\u7a0b\u5e8f");
                menuItem1.setIcon(new ImageIcon(getClass().getResource("/\u9000\u51fa.png")));
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u5b66\u751f\u7ba1\u7406");
                menu2.setIcon(new ImageIcon(getClass().getResource("/\u5b66\u751f\u7ba1\u7406.png")));

                //---- item_addStu ----
                item_addStu.setText("\u6dfb\u52a0\u5b66\u751f");
                item_addStu.setIcon(new ImageIcon(getClass().getResource("/\u6dfb\u52a0.png")));
                menu2.add(item_addStu);

                //---- item_stuList ----
                item_stuList.setText("\u5b66\u751f\u5217\u8868");
                item_stuList.setIcon(new ImageIcon(getClass().getResource("/\u7528\u6237\u5217\u8868.png")));
                menu2.add(item_stuList);
            }
            menuBar1.add(menu2);

            //======== menu8 ========
            {
                menu8.setText("\u73ed\u7ea7\u7ba1\u7406");
                menu8.setIcon(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u7ba1\u7406.png")));

                //---- item_addClass ----
                item_addClass.setText("\u6dfb\u52a0\u73ed\u7ea7");
                item_addClass.setIcon(new ImageIcon(getClass().getResource("/\u6dfb\u52a0.png")));
                menu8.add(item_addClass);

                //---- item_classList ----
                item_classList.setText("\u73ed\u7ea7\u5217\u8868");
                item_classList.setIcon(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u5217\u8868.png")));
                menu8.add(item_classList);
            }
            menuBar1.add(menu8);

            //======== menu9 ========
            {
                menu9.setText("\u6559\u5e08\u7ba1\u7406");
                menu9.setIcon(new ImageIcon(getClass().getResource("/\u8001\u5e08.png")));

                //---- item_addTea ----
                item_addTea.setText("\u6dfb\u52a0\u6559\u5e08");
                item_addTea.setIcon(new ImageIcon(getClass().getResource("/\u6dfb\u52a0.png")));
                menu9.add(item_addTea);

                //---- item_teaList ----
                item_teaList.setText("\u6559\u5e08\u5217\u8868");
                item_teaList.setIcon(new ImageIcon(getClass().getResource("/\u8001\u5e08.png")));
                menu9.add(item_teaList);
            }
            menuBar1.add(menu9);

            //======== menu10 ========
            {
                menu10.setText("\u5e2e\u52a9");
                menu10.setIcon(new ImageIcon(getClass().getResource("/\u5e2e\u52a9.png")));

                //---- item_aboutUs ----
                item_aboutUs.setText("\u5173\u4e8e\u6211\u4eec");
                item_aboutUs.setIcon(new ImageIcon(getClass().getResource("/\u5173\u4e8e\u6211\u4eec.png")));
                menu10.add(item_aboutUs);
            }
            menuBar1.add(menu10);
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
    private JMenuItem item_psw;
    private JMenuItem menuItem1;
    private JMenu menu2;
    private JMenuItem item_addStu;
    private JMenuItem item_stuList;
    private JMenu menu8;
    private JMenuItem item_addClass;
    private JMenuItem item_classList;
    private JMenu menu9;
    private JMenuItem item_addTea;
    private JMenuItem item_teaList;
    private JMenu menu10;
    private JMenuItem item_aboutUs;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
