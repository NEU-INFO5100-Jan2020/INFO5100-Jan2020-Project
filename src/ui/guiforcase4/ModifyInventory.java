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
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    addComponents(frame, panel);
    frame.setVisible(true);
  }

  private void addComponents(JFrame frame, JPanel panel) {
    JLabel jl = new JLabel("Dealer" + dID);
    jl.setFont(new Font("Arial", Font.PLAIN, 18));
    jl.setForeground(Color.BLUE);
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
//Ekie edit here
    JTextField tf1 = new JTextField(10);
    tf1.setBounds(160, 50, 160, 25);
    tf1.setText(Integer.toString(modifyV.getVehicleId()));
    tf1.setEditable(false);
    JTextField tf2 = new JTextField(10);
    tf2.setBounds(160, 80, 160, 25);
    tf2.setText(Integer.toString(modifyV.getVin()));
    JTextField tf3 = new JTextField(10);
    tf3.setBounds(160, 110, 160, 25);
    tf3.setText(modifyV.getMake());
    tf3.setEditable(false);
    JTextField tf4 = new JTextField(10);
    tf4.setBounds(160, 140, 160, 25);
    tf4.setText(modifyV.getModel());
    tf4.setEditable(false);
    JTextField tf5 = new JTextField(10);
    tf5.setBounds(160, 170, 160, 25);
    tf5.setText(Integer.toString(modifyV.getYear()));
    tf5.setEditable(false);
    JTextField tf6 = new JTextField(10);
    tf6.setBounds(160, 230, 160, 25);
    tf6.setText(Float.toString(modifyV.getPrice()));

    JTextField tf7 = new JTextField(10);
    tf7.setBounds(160, 290, 160, 25);
    tf7.setText(Integer.toString(modifyV.getMileage()));

    JTextField[] jtfs = new JTextField[]{tf1, tf2, tf3, tf4, tf5,tf6,tf7};
    for (int i = 0; i < jtfs.length; i++) {
      jtfs[i].setFont(new Font("Arial", Font.PLAIN, 15));
      panel.add(jtfs[i]);
    }

    //Two combobox for color and category
    JComboBox cmb1 = new JComboBox();
    cmb1.setBounds(160, 260, 160, 25);
    cmb1.addItem("White");
    cmb1.addItem("Red");
    cmb1.addItem("Gray");
    cmb1.setSelectedItem(modifyV.getColor());
    JComboBox cmb2 = new JComboBox();
    cmb2.setBounds(160, 200, 160, 25);
    cmb2.addItem("New");
    cmb2.addItem("Used");
    cmb2.setSelectedItem(modifyV.getCategory());
    /*if(cmb2.getSelectedItem()=="Used"){
      cmb2.setEditable(false);
    }*/ //Cannot change used vehicles to new

    panel.add(cmb1);
    panel.add(cmb2);

    JButton btn1 = new JButton("Confirm");
    JButton btn2 = new JButton("Back");
    Dimension preferredSize = new Dimension(120, 40);
    JButton[] jButtons = new JButton[]{btn1, btn2};
    for (int i = 0; i < jButtons.length; i++) {
      jButtons[i].setPreferredSize(preferredSize);
      jButtons[i].setBounds(60 + i * 160, 350, 120, 40);
      jButtons[i].setBackground(Color.WHITE);
      jButtons[i].setOpaque(true);
      jButtons[i].setFont(new Font("Arial", Font.PLAIN, 15));
      panel.add(jButtons[i]);
    }
    btn1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        VehicleManagerImpl vmi =new VehicleManagerImpl();
        try {
          if(Integer.parseInt(tf2.getText())<10000 & Integer.parseInt(tf2.getText())>0){
            if(Float.parseFloat(tf6.getText())>0){
              if(Integer.parseInt(tf7.getText())>=0){
                modifyV.setVin(Integer.parseInt(tf2.getText()));
                modifyV.setPrice(Float.parseFloat(tf6.getText()));
                modifyV.setMileage(Integer.parseInt(tf7.getText()));
                modifyV.setColor((String) cmb1.getSelectedItem());
                modifyV.setCategory((String)cmb2.getSelectedItem());
                vmi.updateVehicle(modifyV);
                JOptionPane.showMessageDialog(panel, "Vehicle " + modifyV.getVehicleId() + " has been updated");
                new InventoryInformation(dID);
                frame.dispose();
              }
              else{
                JOptionPane.showMessageDialog(panel, "Please enter the correct mileage");
              }
            }
            else{
              JOptionPane.showMessageDialog(panel, "Please enter the correct price");
            }
          }
          else{
            JOptionPane.showMessageDialog(panel, "Please enter four digits in VIN");
          }
        }catch (Exception ex){
          JOptionPane.showMessageDialog(panel, "Please input valid numbers!");
        }
      }
    });
    btn2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new InventoryInformation(dID);
        frame.dispose();
      }
    });
  }
}

