package ui.guiforcase4;

import dto.Dealer;
import dto.Vehicle;
import persist.DealerManagerImpl;
import persist.VehicleManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class ModifyInventory extends JFrame {
  int dID;
  Vehicle modifyV;

  public ModifyInventory(Vehicle modifyV) {
    this.modifyV = modifyV;
    this.dID = modifyV.getDealerId();
    initialFrame();
  }

  private void initialFrame() {
    JFrame frame = new JFrame("Modifying Inventory of DealerID " + dID);
    JPanel panel = new JPanel(null);
    frame.setSize(400, 480);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.add(panel);
    addComponents(frame, panel);
    frame.setVisible(true);
  }

  private void addComponents(JFrame frame, JPanel panel) {
    JLabel jl = new JLabel("Dealer" + dID);
    jl.setFont(new Font("Arial", Font.PLAIN, 18));
    jl.setForeground(Color.BLACK);
    jl.setHorizontalAlignment(JTextField.CENTER);
    jl.setBounds(160, 10, 80, 30);

    panel.add(jl);
    String[] jLabelTexts = new String[]{"VehicleId:", "VIN:", "Make:", "Model:", "Year:", "Category:", "Price:",
        "Color:", "Miles:"};//delete the image button, since db team says the field isn't working

    JLabel[] jls = new JLabel[9];
    for (int i = 0; i < jls.length; i++) {
      jls[i] = new JLabel();
      jls[i].setText(jLabelTexts[i]);
      jls[i].setBounds(60, 50 + i * 30, 80, 25);
      jls[i].setFont(new Font("Arial", Font.PLAIN, 15));
      panel.add(jls[i]);
    }

    //TextField for showing Vehicle Info
    JTextField vehIDText = new JTextField(10);
    vehIDText.setBounds(160, 50, 160, 25);
    vehIDText.setText(Integer.toString(modifyV.getVehicleId()));
    vehIDText.setEditable(false);

    JTextField vinText = new JTextField(10);
    vinText.setBounds(160, 80, 160, 25);
    vinText.setText(Integer.toString(modifyV.getVin()));

    JTextField makeText = new JTextField(10);
    makeText.setBounds(160, 110, 160, 25);
    makeText.setText(modifyV.getMake());
    makeText.setEditable(false);

    JTextField modelText = new JTextField(10);
    modelText.setBounds(160, 140, 160, 25);
    modelText.setText(modifyV.getModel());
    modelText.setEditable(false);

    JTextField yearText = new JTextField(10);
    yearText.setBounds(160, 170, 160, 25);
    yearText.setText(Integer.toString(modifyV.getYear()));
    yearText.setEditable(false);

    JTextField priceText = new JTextField(10);
    priceText.setBounds(160, 230, 160, 25);
    priceText.setText(Float.toString(modifyV.getPrice()));

    JTextField mileageText = new JTextField(10);
    mileageText.setBounds(160, 290, 160, 25);
    mileageText.setText(Integer.toString(modifyV.getMileage()));

    JTextField[] jtfs = new JTextField[]{vehIDText, vinText, makeText, modelText, yearText, priceText, mileageText};

    for (int i = 0; i < jtfs.length; i++) {
      jtfs[i].setFont(new Font("Arial", Font.PLAIN, 15));
      panel.add(jtfs[i]);
    }

    //Two combobox for color and category
    JComboBox colorText = new JComboBox();
    colorText.setBounds(160, 260, 160, 25);
    colorText.addItem("White");
    colorText.addItem("Red");
    colorText.addItem("Gray");
    colorText.setSelectedItem(modifyV.getColor());

    JComboBox categoryText = new JComboBox();
    categoryText.setBounds(160, 200, 160, 25);
    categoryText.addItem("New");
    categoryText.addItem("Used");
    categoryText.setSelectedItem(modifyV.getCategory());

    panel.add(colorText);
    panel.add(categoryText);

    JButton confirmBtn = new JButton("Confirm");
    JButton backBtn = new JButton("Back");

    Dimension preferredSize = new Dimension(120, 40);
    JButton[] jButtons = new JButton[]{confirmBtn, backBtn};
    for (int i = 0; i < jButtons.length; i++) {
      jButtons[i].setPreferredSize(preferredSize);
      jButtons[i].setBounds(60 + i * 160, 350, 120, 40);
      jButtons[i].setBackground(Color.WHITE);
      jButtons[i].setOpaque(true);
      jButtons[i].setFont(new Font("Arial", Font.PLAIN, 15));
      panel.add(jButtons[i]);
    }

    //confirm button actionListener
    confirmBtn.addActionListener(actionEvent -> {
      VehicleManagerImpl vmi =new VehicleManagerImpl();
      try {
         modifyV.setVin(Integer.parseInt(vinText.getText()));
         modifyV.setPrice(Float.parseFloat(priceText.getText()));
         modifyV.setMileage(Integer.parseInt(mileageText.getText()));
         modifyV.setColor((String) colorText.getSelectedItem());
         modifyV.setCategory((String)categoryText.getSelectedItem());
         vmi.updateVehicle(modifyV);
         JOptionPane.showMessageDialog(panel, "Vehicle " + modifyV.getVehicleId() + " has been updated");
         new InventoryInformation(dID);
         frame.dispose();
      }catch (Exception ex){
        JOptionPane.showMessageDialog(panel, "Please input valid numbers!");
      }
    });
    //back button actionListener
    backBtn.addActionListener(e -> {
      new InventoryInformation(dID);
      frame.dispose();
    });
  }
}