package ui.guiforcase3;

import dto.*;
import service.*;
import persist.*;
import java.awt.event.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DummyFirstPage {
    int carID = 3;
    String buttonName = "BMW";

    private JFrame carListPage = new JFrame("Car List Page(Dummy)");

    public DummyFirstPage() {
        // Get Car Detail
        VehicleManagerImpl vehicle = new VehicleManagerImpl();
        Collection<Vehicle> vehicleResult = vehicle.getVehiclesBasedOnDealerId(carID);

        Vehicle v = (Vehicle) vehicleResult.toArray()[0];
        buttonName = v.getMake() + " " + v.getModel();

        JButton button1 = new JButton(buttonName);
        JButton button2 = new JButton("Audi A4");

        final Container con = carListPage.getContentPane();
        con.setLayout(new FlowLayout());
        con.add(button1);
        con.add(button2);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                carListPage.dispose();
                new CarDetailGUI(vehicleResult);
            }
        });
        carListPage.setSize(CarDetailPanel.width, CarDetailPanel.height+20);
        carListPage.setVisible(true);
        carListPage.setLocationRelativeTo(null);
        carListPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        carListPage.setResizable(false);
    }

    public static void main( String args[] ) {
        new DummyFirstPage();
    }
}
