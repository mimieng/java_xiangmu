/*
 * Created by JFormDesigner on Tue Dec 17 13:22:39 CST 2024
 */

package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author 晚吟
 */
public class about extends JFrame {
    public about() {
        initComponents();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String baiduUrl = "https://www.baidu.com/";
                try {
                    String os = System.getProperty("os.name").toLowerCase();
                    if (os.contains("win")) {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + baiduUrl);
                    } else if (os.contains("mac")) {
                        Runtime.getRuntime().exec("open " + baiduUrl);
                    } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                        Runtime.getRuntime().exec("xdg-open " + baiduUrl);
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(about.this, "Failed to open Baidu: " + ex.getMessage());
                }
            }
        });

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        button1 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "45dlu, $lcgap, 154dlu",
            "53dlu, $lgap, 23dlu, $lgap, 22dlu, $lgap, 25dlu, $lgap, 21dlu"));

        //---- label1 ----
        label1.setText("\u95ee\u9898\u54a8\u8be2");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 25f));
        contentPane.add(label1, CC.xywh(1, 1, 3, 1, CC.CENTER, CC.DEFAULT));

        //---- label2 ----
        label2.setText("\u7535\u8bdd\uff1a");
        contentPane.add(label2, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));

        //---- label3 ----
        label3.setText("123456789");
        contentPane.add(label3, CC.xy(3, 3));

        //---- label4 ----
        label4.setText("\u90ae\u7bb1\uff1a");
        contentPane.add(label4, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));

        //---- label5 ----
        label5.setText("123456789");
        contentPane.add(label5, CC.xy(3, 5));

        //---- label6 ----
        label6.setText("\u5730\u5740\uff1a");
        contentPane.add(label6, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));

        //---- label7 ----
        label7.setText("123456789");
        contentPane.add(label7, CC.xy(3, 7));

        //---- button1 ----
        button1.setText("\u66f4\u591a\u95ee\u9898\u8bf7\u70b9\u51fb");
        contentPane.add(button1, CC.xy(3, 9, CC.LEFT, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
