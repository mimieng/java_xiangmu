/*
 * Created by JFormDesigner on Fri Dec 13 11:35:48 CST 2024
 */

package view;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

/**
 * @author 25341
 */
public class AboutUs extends JFrame {
    public AboutUs() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u5173\u4e8e\u6211\u4eec");
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "38dlu, $lcgap, 54dlu, $lcgap, 98dlu, $lcgap, 89dlu",
            "41dlu, $lgap, 17dlu, $lgap, 26dlu, $lgap, 4dlu, $lgap, 55dlu"));

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/logo.png")));
        contentPane.add(label1, CC.xywh(3, 1, 2, 1, CC.RIGHT, CC.DEFAULT));

        //---- label2 ----
        label2.setText("\u3010\u539f\u6765\u5982\u6b64\u3011 \u51fa\u54c1");
        contentPane.add(label2, CC.xywh(5, 1, 3, 1, CC.LEFT, CC.DEFAULT));

        //---- label3 ----
        label3.setText("\u7f51\u5740\uff1ahttp://programmer.ischoolbar.com");
        contentPane.add(label3, CC.xywh(5, 3, 3, 1, CC.LEFT, CC.DEFAULT));

        //---- label4 ----
        label4.setText("\u4f53\u80b2\u5668\u6750\u635f\u574f\u540e\u4e0d\u7528\u8d54\u507f\uff01");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 4f));
        contentPane.add(label4, CC.xywh(3, 5, 5, 1, CC.CENTER, CC.DEFAULT));

        //---- button1 ----
        button1.setText("\u7531\u4e8e\u81ea\u8d23\u6211\u8fd8\u662f\u60f3\u8d54\u507f");
        contentPane.add(button1, CC.xywh(3, 9, 3, 1, CC.LEFT, CC.DEFAULT));

        //---- button2 ----
        button2.setText("\u592a\u597d\u4e86 \u4e0d\u7528\u8d54\u507f\u4e86\uff01");
        contentPane.add(button2, CC.xy(7, 9, CC.LEFT, CC.DEFAULT));
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
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
