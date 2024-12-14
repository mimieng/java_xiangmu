/*
 * Created by JFormDesigner on Sun Nov 24 18:20:17 CST 2024
 */

package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @晚吟
 */
public class settingFrame extends JFrame {
    public settingFrame() {
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
                    JOptionPane.showMessageDialog(settingFrame.this, "Failed to open Baidu: " + ex.getMessage());
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
        button1 = new JButton();

        //======== this ========
        setTitle("\u5173\u4e8e\u6211\u4eec");
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "77dlu, $lcgap, 78dlu, $lcgap, 79dlu, $lcgap, 57dlu, $lcgap, default",
            "3*(25dlu, $lgap), 21dlu, $lgap, default"));

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/logo.png")));
        contentPane.add(label1, CC.xywh(1, 1, 1, 5, CC.RIGHT, CC.FILL));

        //---- label2 ----
        label2.setText("\u665a\u541f\u6709\u9650\u516c\u53f8");
        contentPane.add(label2, CC.xywh(3, 1, 3, 1, CC.DEFAULT, CC.FILL));

        //---- label3 ----
        label3.setText("\u7f51\u5740;http:www//.....");
        contentPane.add(label3, CC.xywh(3, 3, 3, 1, CC.DEFAULT, CC.FILL));

        //---- label4 ----
        label4.setText("\u7b80\u5355\u9879\u76ee\uff0c\u540e\u671f\u66f4\u65b0");
        contentPane.add(label4, CC.xywh(3, 5, 3, 1));

        //---- button1 ----
        button1.setText("\u66f4\u65b0\u4e2d\uff01");
        contentPane.add(button1, CC.xywh(3, 7, 4, 1, CC.LEFT, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
