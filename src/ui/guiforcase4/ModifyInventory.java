package ui.guiforcase4;

import dto.Vehicle;
import persist.VehicleManagerImpl;
import service.ColorJsonPopulator;
import service.DealerUtilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifyInventory extends JFrame {
  int dID;
  String path;
  String dealerName;
  Vehicle modifyV;
  DealerUtilities dealerU;
  private JFrame frame;
  private JPanel panel;
  private JPanel p;
  private JLabel vinErrorMsg;
  private JLabel priceErrorMsg;
  private JLabel mileageErrorMsg;
  private String vinPattern;
  private String pricePattern;
  private String mileagePattern;
  Map<String,String> colors = ColorJsonPopulator.populateColorContainer();

  public ModifyInventory(Vehicle modifyV, int dID, String dealerName) {
    this.modifyV = modifyV;
    this.dID = dID;
    this.dealerName = dealerName;
    initialFrame();
  }

  private void initialFrame() {
    frame = new JFrame("Modifying Vehicles of Dealer " + this.dealerName);
    panel = new JPanel(null);
    frame.setSize(460, 720);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    addComponents();
    frame.setVisible(true);
  }

  private void addComponents() {
    JLabel jl = new JLabel(this.dealerName);
    jl.setFont(new Font("Arial", Font.PLAIN, 18));
    jl.setForeground(Color.BLACK);
    jl.setHorizontalAlignment(SwingConstants.CENTER);
    jl.setBounds(0, 10, 570, 30);

    panel.add(jl);
    String[] jLabelTexts = new String[]{"VehicleId:", "VIN:", "Make:", "Model:", "Year:", "Category:", "Price:",
        "Color:", "Miles:", "Rating:", "Image:"};//delete the image button, since db team says the field isn't working

    JLabel[] jls = new JLabel[11];
    for (int i = 0; i < jls.length; i++) {
      jls[i] = new JLabel();
      jls[i].setText(jLabelTexts[i]);
      jls[i].setBounds(60, 50 + i * 50, 80, 25);
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
    vinText.setBounds(160, 100, 160, 25);
    vinText.setText(Integer.toString(modifyV.getVin()));

    // Give a hint when having wrong input with VIN
    vinErrorMsg = new JLabel("VIN must have four digits");
    vinErrorMsg.setBounds(160, 118, 180, 25);
    vinErrorMsg.setFont(new Font("Arial", Font.PLAIN, 10));
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
    makeText.setBounds(160, 150, 160, 25);
    makeText.setText(modifyV.getMake());
    makeText.setEditable(false);
    //Vehicle Model textField
    JTextField modelText = new JTextField(10);
    modelText.setBounds(160, 200, 160, 25);
    modelText.setText(modifyV.getModel());
    modelText.setEditable(false);
    //Vehicle Year textField
    JTextField yearText = new JTextField(10);
    yearText.setBounds(160, 250, 160, 25);
    yearText.setText(Integer.toString(modifyV.getYear()));
    yearText.setEditable(false);
    //Vehicle Price textField
    JTextField priceText = new JTextField(10);
    priceText.setBounds(160, 350, 160, 25);
    priceText.setText(Float.toString(modifyV.getPrice()));
    // Give a hint when having wrong input with Price
    priceErrorMsg = new JLabel("Price must be non-negative");
    priceErrorMsg.setBounds(160, 368, 250, 25);
    priceErrorMsg.setFont(new Font("Arial", Font.PLAIN, 10));
    priceErrorMsg.setForeground(frame.getBackground());
    panel.add(priceErrorMsg);
    pricePattern = "\\d*(\\.)?\\d*$";
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
    mileageText.setBounds(160, 450, 160, 25);
    mileageText.setText(Integer.toString(modifyV.getMileage()));
    // Give a hint when having wrong input with Mileage
    mileageErrorMsg = new JLabel("Mileage must be zero or bigger");
    mileageErrorMsg.setBounds(160, 468, 220, 25);
    mileageErrorMsg.setFont(new Font("Arial", Font.PLAIN, 10));
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

    //Vehicle Image textField
    path = "Please select an image";
    JTextField imageText = new JTextField(10);
    imageText.setBounds(160, 550, 160, 25);
    imageText.setText(path);
    //Add textField to the panel
    JTextField[] jtfs = new JTextField[]{vehIDText, vinText, makeText, modelText, yearText, priceText, mileageText, imageText};
    for (int i = 0; i < jtfs.length; i++) {
      jtfs[i].setFont(new Font("Arial", Font.PLAIN, 15));
      panel.add(jtfs[i]);
    }

    //Three combobox for color and category
    JComboBox categoryText = new JComboBox();
    categoryText.setBounds(160, 300, 160, 25);
    categoryText.addItem("New");
    categoryText.addItem("Used");
    /*categoryText.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          if(categoryText.getSelectedItem().toString().equals("New")) {
            mileageText.setText("0");
            mileageText.setEditable(false);
          }else{
            mileageText.setText("");
            mileageText.setEditable(true);
          }
        }
      }
    });*/
    categoryText.setSelectedItem(modifyV.getCategory());

    JComboBox colorText = new JComboBox();
    colorText.setBounds(160, 400, 160, 25);
    colorText.setFont(new Font("Arial", Font.PLAIN, 15));
    for (Map.Entry<String, String> entry : colors.entrySet()) {
      int r = Integer.parseInt(entry.getValue().substring(1,3),16);
      int g = Integer.parseInt(entry.getValue().substring(3,5),16);
      int b = Integer.parseInt(entry.getValue().substring(5,7),16);
      Color c = new Color(r,g,b);
      p = new ColorCell(c,entry.getKey());
      colorText.addItem(p);

    }
    ListCellRenderer renderer = new PanelComboBoxCellRenderer();
    colorText.setRenderer(renderer);
    colorText.setSelectedItem(modifyV.getColor());

    JComboBox ratingText = new JComboBox();
    ratingText.setBounds(160, 500, 160, 25);
    ratingText.addItem("1");
    ratingText.addItem("2");
    ratingText.addItem("3");
    ratingText.addItem("4");
    ratingText.addItem("5");
    ratingText.setSelectedItem(modifyV.getRatings());

    panel.add(colorText);
    panel.add(categoryText);
    panel.add(ratingText);

    JButton uploadBtn = new JButton("Upload");
    uploadBtn.setPreferredSize(new Dimension(75,25));
    uploadBtn.setBounds(330, 550, 75, 25);
    uploadBtn.setOpaque(true);
    uploadBtn.setFont(new Font("Arial", Font.PLAIN, 12));
    panel.add(uploadBtn);
    uploadBtn.addActionListener(e -> {
      {
        JFileChooser fc=new JFileChooser("C:\\");
        fc.addChoosableFileFilter(new FileNameExtensionFilter(
                "Image files", ImageIO.getReaderFileSuffixes()));
        int val=fc.showOpenDialog(null);    //open the file selection window
        if(val==fc.APPROVE_OPTION)
        {
          imageText.setText(fc.getSelectedFile().toString());
          path = fc.getSelectedFile().toString();
        }
        else
        {
          //No selection of images
          imageText.setText("No image selected");
        }
      }
    });

    JButton confirmBtn = new JButton("Confirm");
    JButton backBtn = new JButton("Back");

    Dimension preferredSize = new Dimension(120, 40);
    JButton[] jButtons = new JButton[]{confirmBtn, backBtn};
    for (int i = 0; i < jButtons.length; i++) {
      jButtons[i].setPreferredSize(preferredSize);
      jButtons[i].setBounds(60 + i * 160, 600, 120, 40);
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
            modifyV.setColor(colorText.getSelectedItem().toString());
            modifyV.setCategory((String) categoryText.getSelectedItem());
            vmi.updateVehicle(modifyV);
            if(!path.equals("Please select an image"))
            {
              if (path.endsWith(".jpg") || path.endsWith(".jpeg") ||path.endsWith(".bmp") || path.endsWith(".png")) {
                dealerU.addImageToAzureBlob(path, Integer.parseInt(vinText.getText()));
              } else {
                JOptionPane.showMessageDialog(panel, "Please select a valid image to upload (jpg, jpeg, bmp, png)");
              }
            }
            JOptionPane.showMessageDialog(panel, "Vehicle " + modifyV.getVehicleId() + " has been updated");
            frame.dispose();
            new InventoryInformation(dID, dealerName);
          }
          else {
            JOptionPane.showMessageDialog(panel, "Duplicate VIN! Please reenter");
          }
        }
       else {
          JOptionPane.showMessageDialog(panel, "Please check the input");
        }
      }catch (Exception ex){
        JOptionPane.showMessageDialog(panel, "Please input valid numbers!");
      }
    });

    //back button actionListener
    backBtn.addActionListener(e -> {
      new InventoryInformation(dID, dealerName);
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