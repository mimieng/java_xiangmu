/*
 * Created by JFormDesigner on Tue Dec 24 20:45:46 CST 2024
 */

package view;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author 晚吟
 */
public class AddEquipFrame1 extends JFrame {
    public AddEquipFrame1() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "default, $lcgap, 190dlu",
            "default, $lgap, 41dlu, $lgap, 94dlu"));

        //---- label1 ----
        label1.setText("\u65e0\u6743\u9650");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 32f));
        contentPane.add(label1, CC.xywh(1, 1, 3, 5, CC.CENTER, CC.DEFAULT));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
