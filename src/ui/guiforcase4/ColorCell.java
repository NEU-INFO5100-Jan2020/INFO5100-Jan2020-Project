package ui.guiforcase4;

import javax.swing.*;
import java.awt.*;

class ColorCell extends JPanel {
  private Color cellColor;
  private JPanel colorLabel;
  private JLabel colorNameLabel;
  private String colorName;

  public ColorCell(Color color, String name) {
    this.cellColor = color;
    this.colorName = name;
    colorLabel = new JPanel();
    colorLabel.setBackground(color);
    colorNameLabel = new JLabel(name);
    colorNameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    colorNameLabel.setForeground(Color.BLACK);
    colorLabel.setPreferredSize(new Dimension(20, 15));
    setLayout(new FlowLayout(FlowLayout.LEFT, 6, 2));
    add(colorLabel);
    add(colorNameLabel);
  }

  public Color getCellColor() {
    return cellColor;
  }

  public void setCellColor(Color cellColor) {
    this.cellColor = cellColor;
  }

  public String getColorName() {
    return colorName;
  }

  public void setColorName(String colorName) {
    this.colorName = colorName;
  }
}
