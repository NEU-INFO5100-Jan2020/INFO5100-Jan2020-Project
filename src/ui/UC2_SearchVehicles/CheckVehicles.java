package ui.UC2_SearchVehicles;


//import dto.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class CheckVehicles extends JFrame {

//    ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    ArrayList<JLabel> lblList;
    JLabel lbl_headline, lbl_make, lbl_module, lbl_year, lbl_gif, lbl_price, lbl_Err_Year, lbl_Err_Price;
    ImageIcon icon;
    JTable jt;

    ArrayList<JTextField> txtList;
    JTextField txt_year, txt_price;

    ArrayList<JComboBox<String>> cbbList;
    JComboBox cbb_make, cbb_module;

    ArrayList<JButton> jbList;

    ArrayList<ImageIcon> imageList;


    JFrame jf,frame2;
//    JPanel jp;
    JLabel lbl_jp_hl;
    JButton btn_jp_viewDetail;

    private String dealerName = "default dealer";

    private int DearID;

    final static int yInternal = 100;
    final static int xInterval = 100;

    public CheckVehicles(int dearID) {
        this.DearID = dearID;
        InitialComponents();
        AddComponents();
        setVisible(true);
    }// querty database and fill ArrayList<Vehicles> with data;
    // page has 5 element, vehiclsList 5 elementlf

    private void AddComponents() {
        for (JLabel jLabel : lblList) {
            getContentPane().add(jLabel);
        }

        for (JComboBox<String> jComboBox : cbbList) {
            getContentPane().add(jComboBox);
        }

        for (JTextField jTextField : txtList) {
            getContentPane().add(jTextField);
        }

        for(JButton jb : jbList){
            getContentPane().add(jb);
        }


//        getContentPane().add(jp);

    }
    private void InitialComponents() {

        InitFrame();
//        InitFrame2();
        InitLabels();
        InitTextFields();
        InitComboBox();
        InitButtons();
        InitImageIcon();
    }
    private void InitImageIcon(){
        ImageIcon icon = new ImageIcon("src/ui/UC2_SearchVehicles/Dealer.png");
        lbl_gif = new JLabel(icon);
        lbl_gif.setBounds(350, 100, 400, 400);
        getContentPane().add(lbl_gif);
    }
    private void InitButtons(){
        jbList = new ArrayList<JButton>();
        JButton search = new JButton("Search");
        search.setBounds(lbl_price.getX() + xInterval * 1 / 2, lbl_price.getY() + yInternal * 2 / 3, 100, 20);
        search.setBackground(Color.orange);
//      search.setOpaque(true);
        search.setFont(new Font("Arial", Font.PLAIN, 15));
        search.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean priceValid = false, yearValid = false;
                String yearInput = txt_year.getText();
                String priceInput = txt_price.getText();

                if (isValidPrice(priceInput) ) {
                    getContentPane().remove(lbl_Err_Price);
                    priceValid = true;
                } else {
                    getContentPane().add(lbl_Err_Price);
                }

                if (isValidYear(yearInput)) {
                    getContentPane().remove(lbl_Err_Year);
                    yearValid = true;
                } else {
                    getContentPane().add(lbl_Err_Year);
                }

                if (priceValid && yearValid) {
//                    frame2.setVisible(true);
                    lbl_gif.setVisible(true);
                    getContentPane().remove(lbl_Err_Year);
                    getContentPane().remove(lbl_Err_Price);
                    frame_2 f2 = new frame_2("default");
                    f2.setBounds(100, 100, 600, 700);
                    f2.setVisible(true);
                }
                else{
//                    frame2.setVisible(false);
                    repaint();
                    lbl_gif.setVisible(true);
                }
//                repaint();
            }

            private boolean isValidPrice(String priceInput) {
                if (priceInput.isEmpty()) {
                    return true;
                }
                char[] priceArray = priceInput.toCharArray();
                for (char c : priceArray) {
                    if (!Character.isDigit(c)) {
                        return false;
                    }
                }
                return true;

            }

            private boolean isValidYear(String yearInput) {
                if (yearInput.isEmpty()) {
                    return true;
                }
                if (yearInput.length() != 4) {
                    return false;
                }
                for (char c : yearInput.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        return false;
                    }
                }
                int year = Integer.parseInt(yearInput);
                if ( year < 1950 || year > 2021 ) {
                    return false;
                }
                return true;
            }
        });
        jbList.add(search);
    }
    private void InitFrame2() {
        frame2 = new JFrame();
        frame2.setBounds(350, 100, 800, 900);
//        frame2.setBorder(BorderFactory.createLineBorder(Color.black));
        frame2.setBackground(Color.orange);
        frame2.setLayout(null);
        InitFrame2Component();
        frame2.setVisible(false);
    }


    private void InitFrame2Component() {
        lbl_jp_hl = new JLabel("List of Vehicles of Dealer " + dealerName);
        lbl_jp_hl.setBounds(300,50,300,30);
        frame2.add(lbl_jp_hl);
        //For Testing
//      DefaultListModel<VehicleTester> lm = new DefaultListModel();
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
//            lm.addElement(vehicle);
//        jt.setBounds(80, 80, 600, 400);
//        jt.setColumnSelectionAllowed(true);
        JPanel f2_panel = new JPanel();
        f2_panel.add(new JScrollPane(jt));
        f2_panel.setBounds(80, 80, 600, 400);
        f2_panel.setVisible(true);
        frame2.add(f2_panel);
//        frame2.add(jt);
//        JList list_jp_vList = new JList<>();
//        list_jp_vList.setCellRenderer(new Render());
//        list_jp_vList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        DefaultListModel<Vehicle> lm = new DefaultListModel();
//        Collection<Vehicle> vehicles = VehicleTester.callDB(10);
//        list_jp_vList.setModel(lm);
//        JScrollPane js = new JScrollPane(list_jp_vList);
//        js.setBounds(100,50,240,200);
//        js.setLocation(100, 50);
//        frame2.add(js);
        btn_jp_viewDetail = new JButton("View Detail");
        btn_jp_viewDetail.setBounds(400 ,500, 130, 30);
        frame2.add(btn_jp_viewDetail);
//        btn_jp_viewDetail.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int index = list_jp_vList.getSelectedIndex();
//                if (index >= 0) {
//                    int myVin = lm.getElementAt(index).VIN;
//                    System.out.println("index : " + index + " value : " +list_jp_vList.getSelectedValue() + " Vin :" + myVin);
//                } else {
//                    System.out.println("Please Select a Vehicle");
//                }
//
//            }
//        });
    }

    private void InitComboBox() {
        cbbList = new ArrayList<>();

        cbb_make = new JComboBox();
        cbb_make.setBounds(lbl_make.getX() + xInterval, lbl_make.getY(), 100, 20);
        cbbList.add(cbb_make);



        cbb_module = new JComboBox();
        cbb_module.setBounds(cbb_make.getX(), lbl_module.getY(), 100, 20);
        cbbList.add(cbb_module);


        // For Testing
        cbb_make.setModel(new DefaultComboBoxModel(VehicleTester.createMake()));
        cbb_module.setModel(new DefaultComboBoxModel(VehicleTester.createModel(cbb_make.getSelectedItem().toString())));
        cbb_make.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("User Select" + cbb_make.getSelectedItem());
                }
                cbb_module.setModel(new DefaultComboBoxModel(VehicleTester.createModel(cbb_make.getSelectedItem().toString())));
            }
        });
    }

    private void InitTextFields() {
        txtList = new ArrayList<>();

        txt_year = new JTextField();
        txt_year.setBounds(lbl_year.getX() + xInterval, lbl_year.getY(), 100, 20);
        txtList.add(txt_year);

        txt_price = new JTextField();
        txt_price.setBounds(txt_year.getX(), lbl_price.getY(), 100, 20);
        txtList.add(txt_price);
    }

    private void InitLabels() {
        lblList = new ArrayList<>();

        lbl_headline = new JLabel("Dealer " + dealerName);
        lbl_headline.setFont(new Font("B", Font.BOLD, 20));
        lbl_headline.setBounds(300, 30, 300, 20);
        lblList.add(lbl_headline);

        lbl_make = new JLabel("Make", JLabel.RIGHT);
        lbl_make.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_make.setBounds(100, 100, 50,20);
        lblList.add(lbl_make);

        lbl_module = new JLabel("Module", 4);
        lbl_module.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_module.setBounds(lbl_make.getX(), lbl_make.getY() + yInternal, 50,20);
        lblList.add(lbl_module);

        lbl_year = new JLabel("Year", 4);
        lbl_year.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_year.setBounds(lbl_make.getX(), lbl_module.getY() + yInternal, 50,20);
        lbl_year.setBackground(Color.red);
        lblList.add(lbl_year);

        lbl_Err_Year = new JLabel("Invalid Year !");
        lbl_Err_Year.setFont(new Font("Arial", Font.BOLD, 12));
        lbl_Err_Year.setForeground(Color.red);
        lbl_Err_Year.setBounds(lbl_year.getX() +xInterval, lbl_year.getY() + 20, 170,20);
        // lblList.add(lbl_Err_Year);

        lbl_price = new JLabel("Price", JLabel.RIGHT);
        lbl_price.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_price.setBounds(lbl_make.getX(), lbl_year.getY() + yInternal, 50,20);
        lblList.add(lbl_price);


        lbl_Err_Price = new JLabel("Invalid Price !");
        lbl_Err_Price.setFont(new Font("Arial", Font.BOLD, 12));
        lbl_Err_Price.setForeground(Color.red);
        lbl_Err_Price.setBounds(lbl_price.getX() +xInterval, lbl_price.getY() + 20, 170,20);
        // lblList.add(lbl_Err_Price);
    }

    private void InitFrame() {
        setBounds(00, 00, 800, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("5100 Final Project UserCase 2");

        getContentPane().setLayout(null);
        jf =this;
    }

    public static void main(String[] args) {
        int dearID = 15;
        new CheckVehicles(dearID);
    }

}


