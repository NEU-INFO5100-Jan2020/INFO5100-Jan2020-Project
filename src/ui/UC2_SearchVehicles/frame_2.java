package ui.UC2_SearchVehicles;


import javax.swing.*;
import java.util.ArrayList;

public class frame_2 extends JFrame{
    private String dealerName;
    private JScrollPane jsp;
    private JTable jt;
    ArrayList<JLabel> label_list;
    ArrayList<JPanel> panel_list;
    ArrayList<JButton> button_list;


    public frame_2(String name){
        dealerName = name;
        label_list = new ArrayList<>();
        panel_list = new ArrayList<>();
        button_list = new ArrayList<>();
        createComponents();
        addComponents();
    }

    public void createComponents(){
        createFrame();
        createLabel();
        crateButton();
        createTable();
        createJsp();
        createPanel();
    }

    private void crateButton() {
        JButton btn_jp_viewDetail = new JButton("View Detail");
        btn_jp_viewDetail.setBounds(400 ,500, 130, 30);
        button_list.add(btn_jp_viewDetail);
    }

    private void createJsp() {
        jsp = new JScrollPane(jt);
    }

    public void addComponents(){
        for(JPanel panel : panel_list){
            getContentPane().add(panel);
        }
    }
    public void createFrame(){
        JFrame jf = new JFrame("Search Results");
        jf.setBounds(100, 100, 600, 700);
        jf.setVisible(false);
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
        panel_main.add(jsp);
    }
    public void createLabel(){

        JLabel lbl_header = new JLabel("List of Vehicles of Dealer " + dealerName);
        lbl_header.setBounds(300,50,300,30);
        label_list.add(lbl_header);

    }

    public void createTable(){
        String[] header = {"Make", "Price", "Vin"};
        VehicleTester[] vehicles = VehicleTester.createTestVehicles();
        String[][] jt_data = new String[vehicles.length][header.length];
        int row = 0;
        for (VehicleTester vehicle : vehicles) {
            jt_data[row++] = new String[]{vehicle.make, String.valueOf(vehicle.price), String.valueOf(vehicle.VIN)};

        }
        jt = new JTable(jt_data, header){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        jt.getTableHeader().setReorderingAllowed(false);

    }
}
