package ui.guiforcase4;

import dto.Vehicle;
import persist.VehicleManagerImpl;
import service.DealerUtilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifyInventory extends JFrame {
  int dID;
  Vehicle modifyV;
  DealerUtilities dealerU;
  private JFrame frame;
  private JPanel panel;
  private JLabel vinErrorMsg;
  private JLabel priceErrorMsg;
  private JLabel mileageErrorMsg;
  private String vinPattern;
  private String pricePattern;
  private String mileagePattern;

  public ModifyInventory(Vehicle modifyV) {
    this.modifyV = modifyV;
    this.dID = modifyV.getDealerId();
    initialFrame();
  }

  private void initialFrame() {
    frame = new JFrame("Modifying Inventory of DealerID " + dID);
    panel = new JPanel(null);
    frame.setSize(570, 480);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    addComponents();
    frame.setVisible(true);
  }

  private void addComponents() {
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

    //VIN textField
    JTextField vinText = new JTextField(10);
    vinText.setBounds(160, 80, 160, 25);
    vinText.setText(Integer.toString(modifyV.getVin()));

    // Give a hint when having wrong input with VIN
    vinErrorMsg = new JLabel("VIN must have four digits");
    vinErrorMsg.setBounds(330, 80, 180, 25);
    vinErrorMsg.setForeground(frame.getBackground());
    panel.add(vinErrorMsg);
    vinPattern = "\\d{4}";
    vinText.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent documentEvent) {
        vinValidateInput(vinText.getText());
      }
      @Override
      public void removeUpdate(DocumentEvent documentEvent) {
        vinValidateInput(vinText.getText());
      }
      @Override
      public void changedUpdate(DocumentEvent documentEvent) { }
    });

    //Vehicle Make textField
    JTextField makeText = new JTextField(10);
    makeText.setBounds(160, 110, 160, 25);
    makeText.setText(modifyV.getMake());
    makeText.setEditable(false);
    //Vehicle Model textField
    JTextField modelText = new JTextField(10);
    modelText.setBounds(160, 140, 160, 25);
    modelText.setText(modifyV.getModel());
    modelText.setEditable(false);
    //Vehicle Year textField
    JTextField yearText = new JTextField(10);
    yearText.setBounds(160, 170, 160, 25);
    yearText.setText(Integer.toString(modifyV.getYear()));
    yearText.setEditable(false);
    //Vehicle Price textField
    JTextField priceText = new JTextField(10);
    priceText.setBounds(160, 230, 160, 25);
    priceText.setText(Float.toString(modifyV.getPrice()));
    // Give a hint when having wrong input with Price
    priceErrorMsg = new JLabel("Price must be positive with one decimal");
    priceErrorMsg.setBounds(330, 230, 250, 25);
    priceErrorMsg.setForeground(frame.getBackground());
    panel.add(priceErrorMsg);
    pricePattern = "\\d+\\.\\d+$";
    priceText.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent documentEvent) {
        priceValidateInput(priceText.getText());
      }
      @Override
      public void removeUpdate(DocumentEvent documentEvent) {
        priceValidateInput(priceText.getText());
      }
      @Override
      public void changedUpdate(DocumentEvent documentEvent) { }
    });
    //Vehicle Mileage textField
    JTextField mileageText = new JTextField(10);
    mileageText.setBounds(160, 290, 160, 25);
    mileageText.setText(Integer.toString(modifyV.getMileage()));
    // Give a hint when having wrong input with Mileage
    mileageErrorMsg = new JLabel("Mileage must be zero or bigger");
    mileageErrorMsg.setBounds(330, 290, 220, 25);
    mileageErrorMsg.setForeground(frame.getBackground());
    panel.add(mileageErrorMsg);
    mileagePattern = "\\d+$";
    mileageText.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent documentEvent) {
        mileageValidateInput(mileageText.getText());
      }
      @Override
      public void removeUpdate(DocumentEvent documentEvent) {
        mileageValidateInput(mileageText.getText());
      }
      @Override
      public void changedUpdate(DocumentEvent documentEvent) { }
    });

    //Add textField to the panel
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
        //get the vehicle based on VIN
        dealerU = new DealerUtilities();
        Vehicle vinVeh = new Vehicle();
        Collection<Vehicle> veh = vmi.getVehiclesBasedOnDealerId(dID);
        for (Vehicle v : veh) {
          if (v.getVin() == Integer.parseInt(vinText.getText())) {
            vinVeh = v;
          }
        }
        //Condition 1: whether the input is correct or not; Condition 2: VIN duplicate or not OR VIN is not changed;
        if (vinValidateInput(vinText.getText()) && priceValidateInput(priceText.getText())
                && mileageValidateInput(mileageText.getText()))
        {
          if ((Integer.toString(modifyV.getVin()).equals(vinText.getText())) || dealerU.validateVin(vinVeh))
          {
            modifyV.setVin(Integer.parseInt(vinText.getText()));
            modifyV.setPrice(Float.parseFloat(priceText.getText()));
            modifyV.setMileage(Integer.parseInt(mileageText.getText()));
            modifyV.setColor((String) colorText.getSelectedItem());
            modifyV.setCategory((String) categoryText.getSelectedItem());
            vmi.updateVehicle(modifyV);
            JOptionPane.showMessageDialog(panel, "Vehicle " + modifyV.getVehicleId() + " has been updated");
            frame.dispose();
            new InventoryInformation(dID);
          }
          else {
            JOptionPane.showMessageDialog(panel, "Duplicate VIN! Please reenter");
          }
        }
       else{
          JOptionPane.showMessageDialog(panel, "Please check all the input");
        }
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

  private boolean vinValidateInput(String input) {
    String text = input;
    Pattern r = Pattern.compile(vinPattern);
    Matcher m = r.matcher(text);
    if (m.matches()) {
      vinErrorMsg.setForeground(frame.getBackground());
      return true;
    }
    else {
      vinErrorMsg.setForeground(Color.RED);
      return false;
    }
  }

  private boolean priceValidateInput(String input) {
    String text = input;
    Pattern r = Pattern.compile(pricePattern);
    Matcher m = r.matcher(text);
    if (m.matches()) {
      priceErrorMsg.setForeground(frame.getBackground());
      return true;
    }
    else {
      priceErrorMsg.setForeground(Color.RED);
      return false;
    }
  }

  private boolean mileageValidateInput(String input) {
    String text = input;
    Pattern r = Pattern.compile(mileagePattern);
    Matcher m = r.matcher(text);
    if (m.matches()) {
      mileageErrorMsg.setForeground(frame.getBackground());
      return true;
    }
    else {
      mileageErrorMsg.setForeground(Color.RED);
      return false;
    }
  }
}