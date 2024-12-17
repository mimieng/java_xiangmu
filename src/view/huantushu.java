/*
 * Created by JFormDesigner on Tue Dec 17 15:30:11 CST 2024
 */

package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author 晚吟
 */
public class huantushu extends JFrame {
    public huantushu() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();

        //======== this ========
        setTitle("\u5f52\u8fd8\u56fe\u4e66");
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "default, $lcgap, 181dlu",
            "91dlu, 31dlu, 2*($lgap), default"));

        //---- label1 ----
        label1.setText("\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\u5f52\u8fd8");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 22f));
        contentPane.add(label1, CC.xy(3, 1, CC.CENTER, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
