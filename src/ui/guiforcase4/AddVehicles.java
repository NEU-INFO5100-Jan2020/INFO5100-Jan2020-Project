package ui.guiforcase4;

import com.toedter.calendar.JYearChooser;
import dto.Vehicle;
import persist.ExtractSingleColumnFromDB;
import persist.VehicleManagerImpl;
import service.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddVehicles extends JFrame {
  int dID;
  String dealerName;
  JFrame frame;
  JPanel p;
  JLabel jl1;
  JLabel jl2;
  JLabel jl3;
  String vinPattern;
  String pricePattern;
  String mileagePattern;
  String path;
  ExtractSingleColumnFromDB connect = new ExtractSingleColumnFromDB();
  Vehicle vehicle = new Vehicle();
  VehicleManagerImpl vmi = new VehicleManagerImpl();
  DealerUtilities du = new DealerUtilities();
  List<MakeModelVer2> makeModelVer2s = MakeModelJsonPopulator.populateMakeModel();
  Map<String, String> colors = ColorJsonPopulator.populateColorContainer();

  public AddVehicles(int dID, String dealerName) {
    this.dID = dID;
    this.dealerName = dealerName;
    this.vehicle = vehicle;
    initialFrame();
  }

  private void initialFrame() {
    frame = new JFrame("Managing Inventory of " + this.dealerName);
    JPanel panel = new JPanel(null);
    frame.setSize(460, 720);
    frame.setLocationRelativeTo(null);
    frame.add(panel);
    addComponents(frame, panel);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  private void addComponents(JFrame frame, JPanel panel) {
    JLabel jl = new JLabel(this.dealerName);
    jl.setFont(new Font("Arial", Font.PLAIN, 18));
    jl.setForeground(Color.BLACK);
    jl.setBounds(0, 10, 480, 30);
    jl.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(jl);
    String[] jLabelTexts = new String[]{"VIN:", "Make:", "Model:", "Year:", "Category:", "Price:",
        "Color:", "Miles:", "Ratings", "Image:"};
    JLabel[] jls = new JLabel[10];
    for (int i = 0; i < jls.length; i++) {
      jls[i] = new JLabel();
      jls[i].setText(jLabelTexts[i]);
      jls[i].setBounds(60, 50 + i * 50, 80, 25);
      jls[i].setFont(new Font("Arial", Font.PLAIN, 15));
      panel.add(jls[i]);
    }
    jl1 = new JLabel("Please Enter A Valid Number In Four Digits For VIN!");
    jl1.setBounds(165, 70, 300, 25);
    jl1.setFont(new Font("Arial", Font.PLAIN, 10));
    jl1.setForeground(frame.getBackground());
    jl2 = new JLabel("Please Enter A Valid Non-negative Number For Price!");
    jl2.setBounds(165, 320, 300, 25);
    jl2.setFont(new Font("Arial", Font.PLAIN, 10));
    jl2.setForeground(frame.getBackground());
    jl3 = new JLabel("Please Enter A Valid Non-negative Number For Mileage!");
    jl3.setBounds(165, 420, 300, 25);
    jl3.setFont(new Font("Arial", Font.PLAIN, 10));
    jl3.setForeground(frame.getBackground());
    panel.add(jl1);
    panel.add(jl2);
    panel.add(jl3);

    JTextField tf1 = new JTextField(10);
    tf1.setBounds(160, 50, 160, 25);
    JTextField tf2 = new JTextField(10);
    tf2.setBounds(160, 300, 160, 25);
    JTextField tf3 = new JTextField(10);
    tf3.setText("0");
    tf3.setEditable(false);
    tf3.setBounds(160, 400, 160, 25);
    JTextField tf4 = new JTextField(10);
    tf4.setBounds(160, 500, 160, 25);
    path = "Please select an image";
    JTextField[] jtfs = new JTextField[]{tf1, tf2, tf3, tf4};
    for (int i = 0; i < jtfs.length; i++) {
      jtfs[i].setFont(new Font("Arial", Font.PLAIN, 15));
      panel.add(jtfs[i]);
    }
    JButton uploadBtn = new JButton("Upload");
    uploadBtn.setPreferredSize(new Dimension(75,25));
    uploadBtn.setBounds(330, 500, 75, 25);
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
          tf4.setText(fc.getSelectedFile().toString());
          path = fc.getSelectedFile().toString();
        }
        else
        {
          //No selection of images
          tf4.setText("No image selected");
        }
      }
    });
//        CheckInput c1 = new CheckInput();
//        c1.setLength(4);
//        tf1.setDocument(c1);
//        CheckInput c2 = new CheckInput();
//        c2.setLength(10);
//        tf2.setDocument(c2);
//        CheckInput c3 = new CheckInput();
//        c3.setLength(10);
//        tf3.setDocument(c3);
    vinPattern = "\\d{4}";
    tf1.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent documentEvent) {
        vinValidateInput(tf1.getText());
      }

      @Override
      public void removeUpdate(DocumentEvent documentEvent) {
        vinValidateInput(tf1.getText());
      }

      @Override
      public void changedUpdate(DocumentEvent documentEvent) {
      }
    });
    pricePattern = "\\d*(\\.)?\\d*$";
    tf2.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent documentEvent) {
        priceValidateInput(tf2.getText());
      }

      @Override
      public void removeUpdate(DocumentEvent documentEvent) {
        priceValidateInput(tf2.getText());
      }

      @Override
      public void changedUpdate(DocumentEvent documentEvent) {
      }
    });
    mileagePattern = "\\d+$";
    tf3.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent documentEvent) {
        mileageValidateInput(tf3.getText());
      }

      @Override
      public void removeUpdate(DocumentEvent documentEvent) {
        mileageValidateInput(tf3.getText());
      }

      @Override
      public void changedUpdate(DocumentEvent documentEvent) {
      }
    });
    tf1.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent e) {
        if (Character.isLetter(e.getKeyChar())) {
          jl1.setText("Please Enter A Valid Number In Four Digits For VIN");
        }
      }

      public void keyPressed(KeyEvent e) {
      }

      public void keyReleased(KeyEvent e) {
      }
    });
    tf2.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent e) {
        if (Character.isLetter(e.getKeyChar())) {
          jl2.setText("Please Enter A Valid Non-negative Number For Price!");
        }
      }

      public void keyPressed(KeyEvent e) {
      }

      public void keyReleased(KeyEvent e) {
      }
    });
    tf3.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent e) {
        if (Character.isLetter(e.getKeyChar())) {
          jl3.setText("Please Enter A Valid Non-negative Number For Mileage!");
        }
      }

      public void keyPressed(KeyEvent e) {
      }

      public void keyReleased(KeyEvent e) {
      }
    });

    JButton btn1 = new JButton("Add");
    btn1.setBounds(60, 550, 120, 40);
    JButton btn2 = new JButton("Back");
    btn2.setBounds(220, 550, 120, 40);
    Dimension preferredSize = new Dimension(120, 40);
    JButton[] jButtons = new JButton[]{btn1, btn2};
    for (int i = 0; i < jButtons.length; i++) {
      jButtons[i].setPreferredSize(preferredSize);
      jButtons[i].setBackground(Color.WHITE);
      jButtons[i].setOpaque(true);
      jButtons[i].setFont(new Font("Arial", Font.PLAIN, 15));
      panel.add(jButtons[i]);
    }
    btn2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new InventoryInformation(dID, dealerName);
        frame.dispose();
      }
    });

    JYearChooser yc = new JYearChooser();
    yc.setBounds(165, 200, 155, 23);
    yc.setEndYear(2020);
    yc.setStartYear(1985);
    yc.setValue(2020);
    yc.setFont(new Font("Arial", Font.PLAIN, 15));
    yc.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(yc);

    JComboBox cmb1 = new JComboBox();
    cmb1.setBounds(160, 350, 160, 25);
    cmb1.setFont(new Font("Arial", Font.PLAIN, 15));
    for (Map.Entry<String, String> entry : colors.entrySet()) {
      int r = Integer.parseInt(entry.getValue().substring(1, 3), 16);
      int g = Integer.parseInt(entry.getValue().substring(3, 5), 16);
      int b = Integer.parseInt(entry.getValue().substring(5, 7), 16);
      Color c = new Color(r, g, b);
      p = new ColorCell(c, entry.getKey());
      cmb1.addItem(p);
    }
    ListCellRenderer renderer = new PanelComboBoxCellRenderer();
    cmb1.setRenderer(renderer);
    JComboBox cmb2 = new JComboBox();
    cmb2.setBounds(160, 250, 160, 25);
    cmb2.addItem("New");
    cmb2.addItem("Used");
    cmb2.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          if(cmb2.getSelectedItem().toString().equals("New")) {
            tf3.setText("0");
            tf3.setEditable(false);
          }else{
            tf3.setText("");
            tf3.setEditable(true);
          }
        }

      }
    });
    cmb2.setFont(new Font("Arial", Font.PLAIN, 15));
    String[] nums = new String[]{String.valueOf(5), String.valueOf(4), String.valueOf(3), String.valueOf(2), String.valueOf(1)};
    JComboBox cmb3 = new JComboBox(nums);
    cmb3.setBounds(160, 450, 160, 25);
    cmb3.setFont(new Font("Arial", Font.PLAIN, 15));
    panel.add(cmb1);
    panel.add(cmb2);
    panel.add(cmb3);
    String[] makes = new String[makeModelVer2s.size()];
    for (int i = 0; i < makeModelVer2s.size(); i++) {
      makes[i] = makeModelVer2s.get(i).getBrand();
    }
    JComboBox make = new JComboBox(makes);
    make.setBounds(160, 100, 160, 25);
    make.setFont(new Font("Arial", Font.PLAIN, 15));
    String makeValue = make.getSelectedItem().toString();
    List<String> models = makeModel(makeValue).getModels();
    JComboBox model = new JComboBox();
    String[] models1 = models.toArray(new String[models.size()]);
    model.setModel(new DefaultComboBoxModel(models1));
    model.setBounds(160, 150, 160, 25);
    model.setFont(new Font("Arial", Font.PLAIN, 15));
    make.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          String makeValue = make.getSelectedItem().toString();
          List<String> models = makeModel(makeValue).getModels();
          String[] models1 = models.toArray(new String[models.size()]);
          model.setModel(new DefaultComboBoxModel(models1));
        }

      }
    });
    panel.add(make);
    panel.add(model);

    btn1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        if (tf1.getText().equals("")) {
          btn1.setEnabled(false);
          jl1.setForeground(Color.RED);
          tf1.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
              if (Character.isDigit(e.getKeyChar())) { jl1.setForeground(frame.getBackground()); }
            }

            public void keyPressed(KeyEvent e) { }

            public void keyReleased(KeyEvent e) { }
          });
          btn1.setEnabled(true);
        }
        if(tf2.getText().equals("")) {
          btn1.setEnabled(false);
          jl2.setForeground(Color.RED);
          tf2.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
              if (Character.isDigit(e.getKeyChar())) { jl2.setForeground(frame.getBackground()); }
            }

            public void keyPressed(KeyEvent e) { }

            public void keyReleased(KeyEvent e) { }
          });
          btn1.setEnabled(true);
        }
        if (tf3.getText().equals("")) {
          btn1.setEnabled(false);
          jl3.setForeground(Color.RED);
          tf3.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
              if (Character.isDigit(e.getKeyChar())) { jl3.setForeground(frame.getBackground()); }
            }

            public void keyPressed(KeyEvent e) { }

            public void keyReleased(KeyEvent e) { }
          });
          btn1.setEnabled(true);
        }
        if(vinValidateInput(tf1.getText()) && priceValidateInput(tf2.getText()) && mileageValidateInput(tf3.getText())) {
          try {
            vehicle.setVin(Integer.parseInt(tf1.getText()));
            vehicle.setDealerId(dID);
            vehicle.setMake(make.getSelectedItem().toString());
            vehicle.setModel(model.getSelectedItem().toString());
            vehicle.setYear(yc.getYear());
            vehicle.setCategory(cmb2.getSelectedItem().toString());
            vehicle.setPrice(Float.parseFloat(tf2.getText()));
            vehicle.setColor(cmb1.getSelectedItem().toString());
            vehicle.setRatings(Integer.parseInt(cmb3.getSelectedItem().toString()));
            vehicle.setMileage(Integer.parseInt(tf3.getText()));
            if (du.validateVin(vehicle) == false) {
              btn1.setEnabled(false);
              JOptionPane.showMessageDialog(null, "The VIN You Entered Already Exists! Please Enter A New VIN");
              btn1.setEnabled(true);
            } else {
              int vin1 = vmi.addVehicle(vehicle).getVin();
              if (vin1 == Integer.parseInt(tf1.getText())) {
                if (!path.equals("Please select an image")) {
                  if (path.endsWith(".jpg") || path.endsWith(".jpeg") ||path.endsWith(".bmp") || path.endsWith(".png")) {
                    du.addImageToAzureBlob(path, Integer.parseInt(tf1.getText()));
                  } else {
                    JOptionPane.showMessageDialog(panel, "Please select a valid image to upload (jpg, jpeg, bmp, png)");
                  }
                }
                JOptionPane.showMessageDialog(panel, "Vehicle " + vehicle.getVin() + " has been updated");
                frame.dispose();
                JOptionPane.showMessageDialog(null, "You have successfully added a car！");
                new AddVehicles(dID, dealerName);
                frame.dispose();
              }
            }
          } catch (SQLException ex) {
            ex.printStackTrace();
          }
        }else {
          JOptionPane.showMessageDialog(null, "Please Check In All Inputs!");
        }

      }
    });
  }

  private MakeModelVer2 makeModel(String makeValue) {
    for (MakeModelVer2 mm : makeModelVer2s) {
      if (mm.getBrand().equals(makeValue)) {
        return mm;
      }


    }
    return null;
  }

  private boolean vinValidateInput(String input) {
    String text = input;
    Pattern r = Pattern.compile(vinPattern);
    Matcher m = r.matcher(text);
    if (m.matches()) {
      jl1.setForeground(frame.getBackground());
      return true;
    } else {
      jl1.setForeground(Color.RED);
      return false;
    }
  }

  private boolean priceValidateInput(String input) {
    String text = input;
    Pattern r = Pattern.compile(pricePattern);
    Matcher m = r.matcher(text);
    if (m.matches()) {
      jl2.setForeground(frame.getBackground());
      return true;
    } else {
      jl2.setForeground(Color.RED);
      return false;
    }
  }

  private boolean mileageValidateInput(String input) {
    String text = input;
    Pattern r = Pattern.compile(mileagePattern);
    Matcher m = r.matcher(text);
    if (m.matches()) {
      jl3.setForeground(frame.getBackground());
      return true;
    } else {
      jl3.setForeground(Color.RED);
      return false;
    }
  }
}








