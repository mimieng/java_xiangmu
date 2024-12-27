/*
 * Created by JFormDesigner on Sun Dec 22 15:59:30 CST 2024
 */

package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author 晚吟
 */
public class aboutUs extends JFrame {
    public aboutUs() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "87dlu, $lcgap, 137dlu, $lcgap, 91dlu",
            "62dlu, $lgap, 41dlu, $lgap, 46dlu, 2*($lgap, default)"));

        //---- label1 ----
        label1.setText("\u674e\u52c7\u51fa\u54c1");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 30f));
        contentPane.add(label1, CC.xywh(1, 1, 5, 7, CC.CENTER, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
