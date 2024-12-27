/*
 * Created by JFormDesigner on Sun Dec 22 14:59:01 CST 2024
 */

package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author 晚吟
 */
public class addmedicines extends JFrame {
    public addmedicines() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();

        //======== this ========
        setTitle("\u6dfb\u52a0\u836f\u54c1");
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "48dlu, $lcgap, 150dlu",
            "49dlu, $lgap, 31dlu, $lgap, 29dlu, $lgap, 33dlu, 2*($lgap, default)"));

        //---- label1 ----
        label1.setText("\u65e0\u6743\u9650");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 31f));
        contentPane.add(label1, CC.xywh(1, 1, 3, 7, CC.CENTER, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
