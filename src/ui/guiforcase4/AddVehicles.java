package ui.guiforcase4;

import dto.Vehicle;
import persist.ExtractSingleColumnFromDB;
import persist.VehicleManagerImpl;
import service.MakeModel;
import service.MakeModelContainer;
import service.MakeModelContainerPopulator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;

public class AddVehicles extends JFrame {
    int dID;
    ExtractSingleColumnFromDB connect = new ExtractSingleColumnFromDB();
    MakeModelContainer mmc;
    MakeModelContainerPopulator mmcp = new MakeModelContainerPopulator();
    ArrayList<MakeModel> makeModels;
    Vehicle vehicle = new Vehicle();
    VehicleManagerImpl vmi = new VehicleManagerImpl();

    public AddVehicles(int dID) {
        this.dID = dID;
        this.vehicle = vehicle;
        mmc = mmcp.getMakeModels();
        initialFrame();
    }

    private void initialFrame() {
        JFrame frame = new JFrame("Managing Inventory of Dealer " + this.dID);
        JPanel panel = new JPanel(null);
        frame.setSize(400, 480);
        frame.add(panel);
        addComponents(frame, panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addComponents(JFrame frame, JPanel panel) {
        JLabel jl = new JLabel("Dealer " + this.dID);
        jl.setFont(new Font("Arial", Font.PLAIN, 18));
        jl.setForeground(java.awt.Color.BLUE);
        jl.setHorizontalAlignment(JTextField.CENTER);
        jl.setBounds(160, 10, 80, 30);
        panel.add(jl);
        String[] jLabelTexts = new String[]{"VehicleId:", "VIN:", "Make:", "Model:", "Year:", "Category:", "Price:",
                "Color:", "Miles:", "Image:"};
        JLabel[] jls = new JLabel[10];
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
        tf2.setBounds(160, 80, 160, 25);
        JTextField tf3 = new JTextField(10);
        tf3.setBounds(160, 230, 160, 25);
        JTextField tf4 = new JTextField(10);
        tf4.setBounds(160, 290, 160, 25);
        JTextField tf5 = new JTextField(10);
        tf5.setBounds(160, 320, 160, 25);
        JTextField[] jtfs = new JTextField[]{tf1, tf2, tf3, tf4, tf5};
        for (int i = 0; i < jtfs.length; i++) {
            jtfs[i].setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(jtfs[i]);
        }

        JButton btn1 = new JButton("Add");
        btn1.setBounds(60, 380, 120, 40);
        JButton btn2 = new JButton("Back");
        btn2.setBounds(220, 380, 120, 40);
        Dimension preferredSize = new Dimension(120, 40);
        JButton[] jButtons = new JButton[]{btn1, btn2};
        for (int i = 0; i < jButtons.length; i++) {
            jButtons[i].setPreferredSize(preferredSize);
            jButtons[i].setBackground(java.awt.Color.blue);
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

        JComboBox cmb1 = new JComboBox(removeDuplicates(getColorsFromDatabase()));
        cmb1.setBounds(160, 260, 160, 25);
        JComboBox cmb2 = new JComboBox();
        cmb2.setBounds(160, 200, 160, 25);
        cmb2.addItem("New");
        cmb2.addItem("Used");
        JComboBox cmb3 = new JComboBox(removeDuplicates(getYearsFromDatabase()));
        cmb3.setBounds(160, 170, 160, 25);

        panel.add(cmb1);
        panel.add(cmb2);
        panel.add(cmb3);

        JComboBox make = new JComboBox(removeDuplicates(getMakesFromDatabase()));
        make.setBounds(160, 110, 160, 25);
        String makeValue = make.getSelectedItem().toString();

        JComboBox model = new JComboBox();
        Collection<String> models = makeModel(makeValue).getModels();
        String[] models1 = models.toArray(new String[models.size()]);
        model.setModel(new DefaultComboBoxModel(models1));
        model.setBounds(160, 140, 160, 25);
        make.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String makeValue = make.getSelectedItem().toString();
                    Collection<String> models = makeModel(makeValue).getModels();
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
                vehicle.setVin(Integer.parseInt(tf2.getText()));
                vehicle.setDealerId(dID);
                vehicle.setMake(make.getSelectedItem().toString());
                vehicle.setModel(model.getSelectedItem().toString());
                vehicle.setYear(Integer.parseInt(cmb3.getSelectedItem().toString()));
                vehicle.setCategory(cmb2.getSelectedItem().toString());
                vehicle.setPrice(Float.parseFloat(tf3.getText()));
                vehicle.setColor(cmb1.getSelectedItem().toString());
                vehicle.setMileage(Integer.parseInt(tf4.getText()));
                vmi.addVehicle(vehicle);
            }
        });


    }
    private MakeModel makeModel(String makeValue) {
        makeModels =(ArrayList<MakeModel>) (mmc.getMakeModels());
        for (MakeModel mm : makeModels) {
            if (mm.getMake().equals(makeValue)) {
                return mm;
            }
        }
        return null;
    }

    public String[] getYearsFromDatabase() {
        ArrayList<Vehicle>  vehicles = connect.executeVehicleQuery();
        String[] years = new String[vehicles.size()];
        for (int i = 0; i < vehicles.size(); i++) {
            years[i] = vehicles.get(i).getYear()+"";
        }
        return years;
    }
    public String[] getColorsFromDatabase() {
        ArrayList<Vehicle> vehicles = connect.executeVehicleQuery();
        String[] color = new String[vehicles.size()];
        for (int i = 0; i < vehicles.size(); i++) {
            color[i] = vehicles.get(i).getColor();
        }
        return color;
    }
    public String[] getMakesFromDatabase() {
        ArrayList<Vehicle> vehicles = connect.executeVehicleQuery();
        String[] makes = new String[vehicles.size()];
        for (int i = 0; i < vehicles.size(); i++) {
            makes[i] = vehicles.get(i).getMake();
        }
        return makes;
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








