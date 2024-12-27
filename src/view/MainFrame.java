/*
 * Created by JFormDesigner on Fri Dec 20 21:53:31 CST 2024
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
 * @author 25341
 */
public class MainFrame extends JFrame {
    public static String userType;
    public static Admin admin;

    public MainFrame(String mUserType,Admin madmin) {
        userType=mUserType;
        admin=madmin;
        initComponents();
        item_psw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditPswFrame().setVisible(true);
            }
        });
        item_add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEquipFrame().setVisible(true);
            }
        });
        item_equip.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new EquipQueryFrame().setVisible(true);
            }
        });
        item_lend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new EquipManageFrame().setVisible(true);
            }
        });
        item_return.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new EquipReturnFrame().setVisible(true);
            }
        });
        item_AboutUs.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutUs().setVisible(true);
            }
        });
        item_quit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(MainFrame.this,"确认退出码？")==JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });
            }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        item_psw = new JMenuItem();
        item_quit = new JMenuItem();
        menuBar2 = new JMenuBar();
        menu2 = new JMenu();
        item_equip = new JMenuItem();
        item_add = new JMenuItem();
        menuBar3 = new JMenuBar();
        menu3 = new JMenu();
        item_lend = new JMenuItem();
        item_return = new JMenuItem();
        menuBar4 = new JMenuBar();
        menu4 = new JMenu();
        item_AboutUs = new JMenuItem();
        panel1 = new JPanel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/\u5b66\u751f\u7ba1\u7406.png")).getImage());
        setTitle("\u5b66\u751f\u4f53\u80b2\u5668\u6750\u7ba1\u7406\u7cfb\u7edf\u4e3b\u9875\u9762");
        setBackground(Color.pink);
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "110dlu, $lcgap, 82dlu, $lcgap, 67dlu, $lcgap, 151dlu",
            "56dlu, $lgap, 58dlu, $lgap, 222dlu"));

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u7cfb\u7edf\u8bbe\u7f6e");
                menu1.setIcon(new ImageIcon(getClass().getResource("/\u7cfb\u7edf\u8bbe\u7f6e.png")));

                //---- item_psw ----
                item_psw.setText("\u5bc6\u7801\u4fee\u6539");
                item_psw.setIcon(new ImageIcon(getClass().getResource("/\u4fee\u6539\u5bc6\u7801.png")));
                menu1.add(item_psw);

                //---- item_quit ----
                item_quit.setText("\u9000\u51fa\u7cfb\u7edf");
                item_quit.setIcon(new ImageIcon(getClass().getResource("/\u9000\u51fa.png")));
                menu1.add(item_quit);
            }
            menuBar1.add(menu1);

            //======== menuBar2 ========
            {

                //======== menu2 ========
                {
                    menu2.setText("\u5668\u6750\u67e5\u8be2");
                    menu2.setIcon(new ImageIcon(getClass().getResource("/\u641c\u7d22.png")));

                    //---- item_equip ----
                    item_equip.setText("\u5668\u6750\u5927\u5168");
                    item_equip.setIcon(new ImageIcon(getClass().getResource("/\u73ed\u7ea7\u4ecb\u7ecd.png")));
                    menu2.add(item_equip);

                    //---- item_add ----
                    item_add.setText("\u6dfb\u52a0\u5668\u6750");
                    item_add.setIcon(new ImageIcon(getClass().getResource("/\u6dfb\u52a0.png")));
                    menu2.add(item_add);
                }
                menuBar2.add(menu2);
            }
            menuBar1.add(menuBar2);

            //======== menuBar3 ========
            {

                //======== menu3 ========
                {
                    menu3.setText("\u5668\u6750\u7ba1\u7406");
                    menu3.setIcon(new ImageIcon(getClass().getResource("/\u7528\u6237\u540d.png")));

                    //---- item_lend ----
                    item_lend.setText("\u501f\u51fa\u60c5\u51b5");
                    item_lend.setIcon(new ImageIcon(getClass().getResource("/\u7528\u6237\u5217\u8868.png")));
                    menu3.add(item_lend);

                    //---- item_return ----
                    item_return.setText("\u5f52\u8fd8\u60c5\u51b5");
                    item_return.setIcon(new ImageIcon(getClass().getResource("/\u7528\u6237\u540d.png")));
                    menu3.add(item_return);
                }
                menuBar3.add(menu3);
            }
            menuBar1.add(menuBar3);

            //======== menuBar4 ========
            {

                //======== menu4 ========
                {
                    menu4.setText("\u5e2e\u52a9");
                    menu4.setIcon(new ImageIcon(getClass().getResource("/\u5e2e\u52a9.png")));

                    //---- item_AboutUs ----
                    item_AboutUs.setText("\u5173\u4e8e\u6211\u4eec");
                    item_AboutUs.setIcon(new ImageIcon(getClass().getResource("/\u5173\u4e8e\u6211\u4eec.png")));
                    menu4.add(item_AboutUs);
                }
                menuBar4.add(menu4);
            }
            menuBar1.add(menuBar4);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setBackground(Color.pink);
            panel1.setLayout(new FormLayout(
                "default, $lcgap, default",
                "2*(default, $lgap), 147dlu"));
        }
        contentPane.add(panel1, CC.xywh(1, 1, 7, 5));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem item_psw;
    private JMenuItem item_quit;
    private JMenuBar menuBar2;
    private JMenu menu2;
    private JMenuItem item_equip;
    private JMenuItem item_add;
    private JMenuBar menuBar3;
    private JMenu menu3;
    private JMenuItem item_lend;
    private JMenuItem item_return;
    private JMenuBar menuBar4;
    private JMenu menu4;
    private JMenuItem item_AboutUs;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
