package ui.UC2_SearchVehicles;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class CheckVehicles extends JFrame {
    ArrayList<JLabel> lblList;
    JLabel lbl_headline, lbl_make, lbl_module, lbl_year, lbl_price;

    ArrayList<JTextField> txtList;
    JTextField txt_year, txt_price;

    ArrayList<JComboBox<String>> cbbList;
    JComboBox cbb_make, cbb_module;

    JPanel jp;
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
    }

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
        getContentPane().add(jp);

    }

    private void InitialComponents() {
        InitFrame();
        InitPanel();
        InitLabels();
        InitTextFields();
        InitComboBox();
    }

    private void InitPanel() {
        jp = new JPanel();
        jp.setBounds(350, 100, 400, 400);
        jp.setBorder(BorderFactory.createLineBorder(Color.black));
        jp.setBackground(Color.lightGray);
        jp.setLayout(null);
        InitPanelComponent();

    }

    private void InitPanelComponent() {
        lbl_jp_hl = new JLabel("List of Vehicles of Dealer " + dealerName);
        lbl_jp_hl.setBounds(80,0,300,30);
        jp.add(lbl_jp_hl);

        JList list_jp_vList = new JList<>();
        // list_jp_vList.setBounds(110, 50, 170, 200);
        list_jp_vList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultListModel<Vehicle> lm = new DefaultListModel();
        // For Testing
        Vehicle[] vgroup = Vehicle.createTestVehicles();
        for (Vehicle vehicle : vgroup) {
            lm.addElement(vehicle);
        }
        list_jp_vList.setModel(lm);
        JScrollPane js = new JScrollPane(list_jp_vList);
        js.setBounds(100,50,240,200);
        jp.add(js);



        btn_jp_viewDetail = new JButton("View Detail");
        btn_jp_viewDetail.setBounds(130,320, 130, 30);
        jp.add(btn_jp_viewDetail);
        btn_jp_viewDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list_jp_vList.getSelectedIndex();
                if (index >= 0) {
                    int myVin = lm.getElementAt(index).VIN;
                    System.out.println("index : " + index + " value : " +list_jp_vList.getSelectedValue() + " Vin :" + myVin);
                } else {
                    System.out.println("Please Select a Vehicle");
                }

            }
        });

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
        cbb_make.setModel(new DefaultComboBoxModel(Vehicle.createMake()));
        cbb_module.setModel(new DefaultComboBoxModel(Vehicle.createModel(cbb_make.getSelectedItem().toString())));

        cbb_make.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("User Select" + cbb_make.getSelectedItem());
                }
                cbb_module.setModel(new DefaultComboBoxModel(Vehicle.createModel(cbb_make.getSelectedItem().toString())));
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
        lblList.add(lbl_year);

        lbl_price = new JLabel("Price", JLabel.RIGHT);
        lbl_price.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_price.setBounds(lbl_make.getX(), lbl_year.getY() + yInternal, 50,20);
        lblList.add(lbl_price);
    }

    private void InitFrame() {
        setBounds(00, 00, 800, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("5100 Final Project UserCase 2");

        getContentPane().setLayout(null);

    }

    public static void main(String[] args) {
        int dearID = 15;
        new CheckVehicles(dearID);
    }

}


