package ui.guiforcase4;

import com.toedter.calendar.JYearChooser;
import dto.Vehicle;
import persist.ExtractSingleColumnFromDB;
import persist.VehicleManagerImpl;
import service.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddVehicles extends JFrame {
    int dID;
    ExtractSingleColumnFromDB connect = new ExtractSingleColumnFromDB();
    Vehicle vehicle = new Vehicle();
    VehicleManagerImpl vmi = new VehicleManagerImpl();
    DealerUtilities du = new DealerUtilities();
    List<MakeModelVer2> makeModelVer2s = MakeModelJsonPopulator.populateMakeModel();
    public AddVehicles(int dID) {
        this.dID = dID;
        this.vehicle = vehicle;

        initialFrame();
    }

    private void initialFrame() {
        JFrame frame = new JFrame("Managing Inventory of Dealer " + this.dID);
        JPanel panel = new JPanel(null);
        frame.setSize(400, 480);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        addComponents(frame, panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addComponents(JFrame frame, JPanel panel) {
        JLabel jl = new JLabel("Dealer " + this.dID);
        jl.setFont(new Font("Arial", Font.PLAIN, 18));
        jl.setForeground(Color.BLACK);
        jl.setHorizontalAlignment(JTextField.CENTER);
        jl.setBounds(160, 10, 80, 30);
        panel.add(jl);
        String[] jLabelTexts = new String[]{"VIN:", "Make:", "Model:", "Year:", "Category:", "Price:",
                "Color:", "Miles:", "Image:"};
        JLabel[] jls = new JLabel[9];
        for (int i = 0; i < jls.length; i++) {
            jls[i] = new JLabel();
            jls[i].setText(jLabelTexts[i]);
            jls[i].setBounds(60, 50 + i * 30, 80, 25);
            jls[i].setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(jls[i]);
        }

        JTextField tf1 = new JTextField(10);
        tf1.setBounds(160, 50, 160, 25);
        JTextField tf2 = new JTextField(10);
        tf2.setBounds(160, 200, 160, 25);
        JTextField tf3 = new JTextField(10);
        tf3.setBounds(160, 260, 160, 25);
        JTextField tf4 = new JTextField(10);
        tf4.setBounds(160, 290, 160, 25);
        JTextField[] jtfs = new JTextField[]{tf1, tf2, tf3, tf4};
        for (int i = 0; i < jtfs.length; i++) {
            jtfs[i].setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(jtfs[i]);
        }
        CheckInput c1 = new CheckInput();
        c1.setLength(4);
        tf1.setDocument(c1);
        CheckInput c2 = new CheckInput();
        c2.setLength(10);
        tf2.setDocument(c2);
        CheckInput c3 = new CheckInput();
        c3.setLength(10);
        tf3.setDocument(c3);
//        if(tf1.getText().length() == 4) {
//            tf1.addKeyListener(new KeyListener() {
//            public void keyTyped(KeyEvent e) {
//                if (e.getKeyChar() = true) {
//                    JOptionPane.showMessageDialog(null, "Please Enter A Number In Four Digits");
//                }
//            }
//            public void keyPressed(KeyEvent e) {
//            }
//            public void keyReleased(KeyEvent e) {
//            }
//        });
//        }
        tf1.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if (Character.isLetter(e.getKeyChar())) {
                    JOptionPane.showMessageDialog(null, "Please Enter A Number In Four Digits");
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
                    JOptionPane.showMessageDialog(null, "Please Enter A Valid Number For Price!");
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
                    JOptionPane.showMessageDialog(null, "Please Enter A Valid Number For Mileage!");
                }
            }
                public void keyPressed (KeyEvent e){
                }
                public void keyReleased (KeyEvent e){
                }
        });

        JButton btn1 = new JButton("Add");
        btn1.setBounds(60, 380, 120, 40);
        JButton btn2 = new JButton("Back");
        btn2.setBounds(220, 380, 120, 40);
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
                new InventoryInformation(dID);
                frame.dispose();
            }
        });

        JYearChooser yc = new JYearChooser();
        yc.setBounds(165, 140, 155, 23);
        yc.setEndYear(2020);
        yc.setStartYear(1985);
        yc.setValue(2020);
        yc.setFont(new Font("Arial", Font.PLAIN, 15));
        yc.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(yc);

        JComboBox cmb1 = new JComboBox(removeDuplicates(getColorsFromDatabase()));
        cmb1.setBounds(160, 230, 160, 25);
        cmb1.setFont(new Font("Arial", Font.PLAIN, 15));
        JComboBox cmb2 = new JComboBox();
        cmb2.setBounds(160, 170, 160, 25);
        cmb2.addItem("New");
        cmb2.addItem("Used");
        cmb2.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(cmb1);
        panel.add(cmb2);
//        if(cmb1.getSelectedItem().equals("New")) {
//            tf3.setText("0");
//            tf3.
//
//        }

        String[] makes = new String[makeModelVer2s.size()];
        for(int i = 0; i < makeModelVer2s.size(); i++) {
            makes[i] = makeModelVer2s.get(i).getBrand();
        }
        JComboBox make = new JComboBox(makes);
        make.setBounds(160, 80, 160, 25);
        make.setFont(new Font("Arial", Font.PLAIN, 15));
        String makeValue = make.getSelectedItem().toString();
        List<String> models = makeModel(makeValue).getModels();
        JComboBox model = new JComboBox();
        String[] models1 = models.toArray(new String[models.size()]);
        model.setModel(new DefaultComboBoxModel(models1));
        model.setBounds(160, 110, 160, 25);
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

                if (tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("")) {
                    btn1.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Please  Fill In All Of" +
                        " VIN, Make, Model, Year, Category, Price, Color, Mileage To Add The Vehicle!");
                    btn1.setEnabled(true);
                } else if (!tf1.getText().equals("") && tf1.getText().length() < 4) {
                    btn1.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Please Enter A Number In Four Digits For VIN!");
                    btn1.setEnabled(true);
                } else {
                    try {
                        vehicle.setVin(Integer.parseInt(tf1.getText()));
                        vehicle.setDealerId(dID);
                        vehicle.setMake(make.getSelectedItem().toString());
                        vehicle.setModel(model.getSelectedItem().toString());
                        vehicle.setYear(yc.getYear());
                        vehicle.setCategory(cmb2.getSelectedItem().toString());
                        vehicle.setPrice(Float.parseFloat(tf2.getText()));
                        vehicle.setColor(cmb1.getSelectedItem().toString());
                        vehicle.setMileage(Integer.parseInt(tf3.getText()));
                        if (du.validateVin(vehicle) == false) {
                            btn1.setEnabled(false);
                            JOptionPane.showMessageDialog(null, "The VIN You Entered Already " +
                                "Exists! \nPlease Enter A New VIN");
                            btn1.setEnabled(true);
                        } else {
                            vmi.addVehicle(vehicle);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
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

    public String[] getColorsFromDatabase() {
        ArrayList<Vehicle> vehicles = connect.executeVehicleQuery();
        String[] color = new String[vehicles.size()];
        for (int i = 0; i < vehicles.size(); i++) {
            color[i] = vehicles.get(i).getColor();
        }
        return color;
    }

    public String[] removeDuplicates(String[] array) {
        ArrayList<String> result = new ArrayList<>();
        boolean flag;
        for (int i = 0; i < array.length; i++) {
            flag = false;
            for (int j = 0; j < result.size(); j++) {
                if (array[i].equals(result.get(j))) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                result.add(array[i]);
            }
        }
        String[] arrayResult = (String[]) result.toArray(new String[result.size()]);
        return arrayResult;
    }

}








