package ui.guiforcase4;

import dto.Vehicle;
import persist.VehicleManagerImpl;
import service.SearchFilter;
import service.SearchFilterElement;
import service.VehicleSearchFilter;
import service.VehicleSearchFilterElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class InventoryInformation extends JFrame {
  int dID;
  //Get data from VehicleManagerImpl
  VehicleManagerImpl vmi = new VehicleManagerImpl();
  DefaultListModel model = new DefaultListModel();

  public InventoryInformation(int dID) {
    this.dID = dID;
    Collection<Vehicle> veh = vmi.getVehiclesBasedOnDealerId(dID);
    for (Vehicle v : veh) {
      model.addElement(v.getVehicleId());
    }
    initialFrame();
  }

  private void initialFrame() {
    JFrame frame = new JFrame("Inventory of Dealer" + this.dID);
    frame.setSize(400, 480);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel(null);
    frame.add(panel);
    addComponents(frame, panel);
    frame.setVisible(true);
  }

  private void addComponents(JFrame frame, JPanel panel) {
    JLabel jl = new JLabel("Inventory of Dealer" + this.dID);
    jl.setFont(new Font("Arial", Font.PLAIN, 20));
    jl.setForeground(Color.BLUE);
    jl.setHorizontalAlignment(JTextField.CENTER);
    jl.setBounds(55, 10, 280, 30);
    panel.add(jl);
    //(Ekie)Show the VehicleList based on DealerID
    JList list_jp_vList = new JList(model);
    list_jp_vList.setBounds(80, 60, 240, 220);
    list_jp_vList.setBackground(Color.LIGHT_GRAY);
    list_jp_vList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list_jp_vList.setFont(new Font("Arial", Font.PLAIN, 15));
    DefaultListCellRenderer renderer = new DefaultListCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);
    list_jp_vList.setCellRenderer(renderer);
    panel.add(list_jp_vList);

    JButton btn1 = new JButton("Modify");
    btn1.setBounds(50, 310, 120, 40);
    JButton btn2 = new JButton("Delete");
    btn2.setBounds(230, 310, 120, 40);
    JButton btn3 = new JButton("Add Vehicles");
    btn3.setBounds(50, 380, 300, 40);
    JButton[] jButtons = new JButton[]{btn1, btn2, btn3};
    Dimension preferredSize = new Dimension(120, 40);
    for (JButton jButton : jButtons) {
      jButton.setPreferredSize(preferredSize);
      jButton.setBackground(Color.blue);
      jButton.setOpaque(true);
      jButton.setFont(new Font("Arial", Font.PLAIN, 15));
      panel.add(jButton);
    }

    btn1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //Get the VehicleID, VIN of selected vehicle
        int vehID = (int) list_jp_vList.getSelectedValue();
        Collection<Vehicle> veh = vmi.getVehiclesBasedOnDealerId(dID);
        Vehicle modifyV = new Vehicle();
        for (Vehicle v : veh) {
          if (v.getVehicleId() == vehID) {
            modifyV = v;
          }
        }
        new ModifyInventory(modifyV);
        frame.dispose();
      }
    });
    //(Ekie)Delete vehicles
    btn2.addActionListener(e -> {
      //Step1: Delete the vehicle from the db
      int vehID = (int) list_jp_vList.getSelectedValue();
      Vehicle deleteV = new Vehicle();
      Collection<Vehicle> veh = vmi.getVehiclesBasedOnDealerId(dID);
      //Use vehicleID to get Vehicle and pass it to deleteVehicle()
      for (Vehicle v : veh) {
        if (v.getVehicleId() == vehID) {
          deleteV = v;
        }
      }
      vmi.deleteVehicle(deleteV);
      //Step2: Delete the vehicle on the screen
      JOptionPane.showMessageDialog(list_jp_vList, "Vehicle " + deleteV.getVehicleId() + " has been deleted");
      int index = list_jp_vList.getSelectedIndex();
      model.remove(index);
      int size = model.getSize();
      if (size == 0) { //No vehicles left, disable delete.
        btn2.setEnabled(false);
      } else { //Select an index.
        if (index == model.getSize()) {
          index--;
        }
        list_jp_vList.setSelectedIndex(index);
        list_jp_vList.ensureIndexIsVisible(index);
      }
    });

    btn3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new AddVehicles(dID);
        frame.dispose();
      }
    });
  }
}
