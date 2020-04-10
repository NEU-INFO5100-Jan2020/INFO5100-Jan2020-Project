package ui.UC2_SearchVehicles;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

class Render implements ListCellRenderer {
    protected static Border noFocusBorder = new EmptyBorder(10,0,10,0);

//    protected static TitledBorder focusBorder = new TitledBorder(LineBorder.createGrayLineBorder(),
//            "Your Option");
    protected static TitledBorder focusBorder = new TitledBorder(LineBorder.createGrayLineBorder(),
                "Your Option");


    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);
        renderer.setBorder(cellHasFocus ? focusBorder : noFocusBorder);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        return renderer;
    }
}