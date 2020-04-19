package ui.UC2_SearchVehicles;


import dto.Dealer;
import dto.Vehicle;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Frame_2 extends JFrame{

    private List<Vehicle> vehicleList;

    public List<Vehicle> getVehicleList(int dealerID, String make, String model, String year, String maxPrice) {
        if (vehicleList == null) {
            vehicleList = FrameUtilities.vehicleSearchAndSort(dealerID, make, model, year,maxPrice);
        }
        return vehicleList;
    }
    private String dealerName;
    private JScrollPane jsp;
    private JTable jt;
    ArrayList<JLabel> label_list;
    ArrayList<JPanel> panel_list;
    ArrayList<JButton> button_list;


    public Frame_2(Dealer dealer, String make, String model, String year, String maxPrice){
        getVehicleList(dealer.getDealerId(), make, model, year,maxPrice);

        dealerName = dealer.getDealerName();
        label_list = new ArrayList<>();
        panel_list = new ArrayList<>();
        button_list = new ArrayList<>();
        createComponents();
        addComponents();

        vehicleList = FrameUtilities.createTestVehicles();
    }

    public void createComponents(){
//        createFrame();
        createLabel();
        createTable();
        createPanel();
    }

    public void addComponents(){
        for(JPanel panel : panel_list){
            getContentPane().add(panel);
        }
    }

    public void createPanel(){

        JPanel panel_main = new JPanel();
        panel_main.setBounds(80, 80, 600, 400);
        panel_main.setVisible(true);
        panel_list.add(panel_main);
        for(JLabel lb : label_list){
            panel_main.add(lb);
        }
        for(JButton jb : button_list){
            panel_main.add(jb);
        }
        // hard coded paging size here
        int pagingSize = 5;
        JPanel tempPanel = createPaging(jt, pagingSize, vehicleList);
        panel_main.add(tempPanel);



    }
    public void createLabel(){

        JLabel lbl_header = new JLabel("Vehicles of " + dealerName);
        lbl_header.setBounds(300,50,300,30);
        lbl_header.setFont(new Font("Arial", Font.BOLD, 15));
        label_list.add(lbl_header);

    }

    public void createTable(){
        String[] header = {"Make", "Model", "Price", "Vin", "Year"};
        //vehicleList = FrameUtilities.createTestVehicles();

        String[][] jt_data = new String[vehicleList.size()][header.length];
        int row = 0;
        for (Vehicle vehicle : vehicleList) {
            jt_data[row++] = new String[]{vehicle.getMake(),
                    String.valueOf(vehicle.getModel()),
                    String.valueOf(vehicle.getPrice()),
                    String.valueOf(vehicle.getVin()),
                    String.valueOf(vehicle.getYear())};
        }
        jt = new JTable(jt_data, header){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        JTableHeader jtHeader = jt.getTableHeader();
        jtHeader.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        jtHeader.setForeground(Color.BLACK);
        jt.setFont(new Font("Arial", Font.CENTER_BASELINE, 13) );
        jt.setBounds(80, 80, 600, 400);
        jt.setColumnSelectionAllowed(true);
        jt.setCellSelectionEnabled(false);
        jt.setRowSelectionAllowed(true);
        jt.setSelectionMode(0);
        jt.setShowGrid(false);
        jt.setShowHorizontalLines(false);

    }

    private JPanel createPaging(JTable jt, int size, List<Vehicle> vehicles) {
        return new TablePagingPanel(jt, size, vehicles);
    }

}
