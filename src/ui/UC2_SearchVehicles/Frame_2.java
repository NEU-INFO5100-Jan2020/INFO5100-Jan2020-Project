package ui.UC2_SearchVehicles;


import dto.Dealer;
import dto.Vehicle;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Frame_2 extends JPanel{

    private Dealer dealer;
    private List<Vehicle> vehicleList;

    public List<Vehicle> getVehicleList(int dealerID, SearchFilterDTO filter) {
        return FrameUtilities.vehicleSearchAndSort(dealerID, filter);
    }
    private TablePagingPanel pagedPanel;
    private JTable jt;
    ArrayList<JLabel> label_list;
    ArrayList<JPanel> panel_list;
    ArrayList<JButton> button_list;


    public Frame_2(Dealer dealer, SearchFilterDTO filter){
        this.vehicleList = getVehicleList(dealer.getDealerId(), filter);

        this.dealer = dealer;
        label_list = new ArrayList<>();
        panel_list = new ArrayList<>();
        button_list = new ArrayList<>();
        createComponents();
        addComponents();

    }

    public void createComponents(){
        createTable();
        createPanel();
    }

    public void addComponents(){
        for(JPanel panel : panel_list){
            this.add(panel);
        }
    }

    public void createPanel(){

        JPanel panel_main = new JPanel();
        panel_main.setBounds(100, 10, 700, 700);

        panel_main.setVisible(true);
        panel_list.add(panel_main);
        for(JLabel lb : label_list){
            panel_main.add(lb);
            lb.setBounds(150,50,300,30);
        }
        for(JButton jb : button_list){
            panel_main.add(jb);
        }
        // hard coded paging size here
        int pagingSize = 10;
        pagedPanel = createPaging(jt, pagingSize, vehicleList);
        panel_main.add(pagedPanel);

    }

    public void createTable(){
        String[] header = {"Make", "Model", "Price", "Vin", "Year"};
        DefaultTableModel vModel = new DefaultTableModel(header, 0) {
            Class[] types = {String.class, String.class, Float.class, Integer.class, Integer.class};
            @Override
            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        for (Vehicle vehicle : vehicleList) {
            vModel.addRow(new Object[]{
                    vehicle.getModel(), vehicle.getMake(), vehicle.getPrice(), vehicle.getVin(), vehicle.getYear()
            });
        }
        jt = new JTable(vModel);
        JTableHeader jtHeader = jt.getTableHeader();
        jtHeader.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        jtHeader.setForeground(Color.BLACK);
        jt.setFont(new Font("Arial", Font.CENTER_BASELINE, 13) );
        jt.setBounds(80, 150, 500, 300);
        jt.setColumnSelectionAllowed(true);
        jt.setCellSelectionEnabled(false);
        jt.setRowSelectionAllowed(true);
        jt.setSelectionMode(0);
        jt.setShowGrid(false);
        jt.setShowHorizontalLines(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jt.setDefaultRenderer(String.class, centerRenderer);
        jt.setDefaultRenderer(Integer.class, centerRenderer);
        jt.setDefaultRenderer(Float.class,centerRenderer);

    }


    private TablePagingPanel createPaging(JTable jt, int size, List<Vehicle> vehicles) {
        return new TablePagingPanel(jt, size, vehicles);
    }

    private void refreshPanel() {
        DefaultTableModel vModel = new DefaultTableModel(new String[] {"Make", "Model", "Price", "Vin", "Year"}, 0) {
            Class[] types = {String.class, String.class, Float.class, Integer.class, Integer.class};

            @Override
            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        for (Vehicle vehicle : vehicleList) {
            vModel.addRow(new Object[]{
                vehicle.getModel(), vehicle.getMake(), vehicle.getPrice(), vehicle.getVin(), vehicle.getYear()
            });
        }

        pagedPanel.refreshTable(vModel, vehicleList);
    }

    public void refreshDataModel(Dealer dealer, SearchFilterDTO searchFilter) {
        vehicleList = getVehicleList(dealer.getDealerId(), searchFilter);
        refreshPanel();
    }
}
