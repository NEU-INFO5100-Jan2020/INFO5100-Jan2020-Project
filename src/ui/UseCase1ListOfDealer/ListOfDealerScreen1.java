
/* Use Case I Screen */
package ui.UseCase1ListOfDealer;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import static ui.UseCase1ListOfDealer.Constants.Constant.*;

import ui.UC2_SearchVehicles.Frame_1;
import ui.UseCase1ListOfDealer.Validator.Validation;
import persist.*;
import dto.*;
import service.DealerSearchFilter;


public class ListOfDealerScreen1 {
    private JFrame frame;
    private JPanel panelLeft, panelRight;
    private JTextField textFieldDealerName;
    private JTextField textFieldZipCode;
    private JComboBox<String> comboBox;
    private JButton btnSearch;
    private ArrayList<Dealer> dealerList=new ArrayList<>();


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListOfDealerScreen1 window = new ListOfDealerScreen1();
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ListOfDealerScreen1() {
        initializeFrameAndPanel();
        initializeDealerName();
        initializeZipCode();
        initializeMileRange();
        initializeSearchButton();
    }

    // method for the complete frame
    private void initializeFrameAndPanel() {
        frame = new JFrame();
        frame.setTitle("Automotive Dealers Website");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);
        frame.getContentPane().setLayout(new BorderLayout());
        initializeLeftPanel();
        initializeRightPanel();
    }
    // method for left panel containing the form
    private void initializeLeftPanel() {
        panelLeft= new JPanel();
        panelLeft.setBackground(new Color(0, 30, 54));
        panelLeft.setLayout(null);
        panelLeft.setPreferredSize(LeftPanel);
        frame.getContentPane().add(panelLeft,BorderLayout.WEST);
    }

    //method for right panel containg thr table
    private void initializeRightPanel() {
        panelRight= new JPanel();
        panelRight.setBackground(new Color(222, 249, 250));
        panelRight.setPreferredSize(RightPanel);
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        frame.getContentPane().add(panelRight,BorderLayout.CENTER);
    }

    //method to handel dealerName
    private void initializeDealerName() {
        JLabel lblName = new JLabel("Enter Dealers Name: ");
        lblName.setBounds(10, 50, 200, 14);
        lblName.setForeground(White);
        panelLeft.add(lblName);

        textFieldDealerName = new JTextField();
        textFieldDealerName.setBounds(10, 80, 200, 20);
        textFieldDealerName.setColumns(10);
        panelLeft.add(textFieldDealerName);
        highlightDealerName();
        dealerNameValidation();
    }

    //method to validate dealer name (length cannot be more than 100)
    private void dealerNameValidation(){
        textFieldDealerName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                //this will be called on tab i.e when the field looses focus
                String dealerName=textFieldDealerName.getText();
                if(!dealerName.isEmpty()&&!Validation.isAValidDealerName(dealerName)) {
                    JOptionPane.showMessageDialog(frame,
                            "This is a invalid dealer name, Please enter again");
                }
            }

        });
    }

    // method to highlight if dealername textfield id empty
    private void highlightDealerName(){
        Border defaultBorder = textFieldDealerName.getBorder();

        textFieldDealerName.setBorder(BorderFactory.createLineBorder(Blue, 3));

        textFieldDealerName.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }

            private void maybeHighlight()
            {
                if (textFieldDealerName.getText().trim().length() != 0)
                {
                    textFieldDealerName.setBorder(defaultBorder);
                }
                else
                {
                    textFieldDealerName.setBorder(BorderFactory.createLineBorder(Blue, 3));
                }
            }
        });
    }

    //method to handel zipcode
    private void initializeZipCode() {
        JLabel lblPhone = new JLabel("Enter Zipcode (Search Dealers Near You): ");
        lblPhone.setBounds(10, 150, 270, 14);
        lblPhone.setForeground(Color.WHITE);
        panelLeft.add(lblPhone);

        textFieldZipCode = new JTextField();
        textFieldZipCode.setBounds(10, 180, 200, 20);
        textFieldZipCode.setColumns(10);
        panelLeft.add(textFieldZipCode);
        highlightZipCode();
        validateZipCode();
    }

    // method to highlight zipcode textfield if empty
    private void highlightZipCode(){
        Border defaultBorder2 = textFieldZipCode.getBorder();
        textFieldZipCode.setBorder(BorderFactory.createLineBorder(Blue, 3));
        textFieldZipCode.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                maybeHighlight();
            }

            private void maybeHighlight()
            {
                if (textFieldZipCode.getText().trim().length() != 0)
                {
                    textFieldZipCode.setBorder(defaultBorder2);
                }
                else
                {
                    textFieldZipCode.setBorder(BorderFactory.createLineBorder(Blue, 3));
                }
            }
        });
    }

    //validation for Zip code (length 5 or 7(WA98109) )
    private void validateZipCode(){
        textFieldZipCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                //this will be called on tab i.e when the field looses focus
                String zipCode=textFieldZipCode.getText();
                if(!zipCode.isEmpty()&&!Validation.isAValidZipCode(zipCode)) {
                    JOptionPane.showMessageDialog(frame,
                            "This is a invalid zip code, Please enter again");
                }
            }

        });
    }

    //method to handle range in miles
    private void initializeMileRange() {
        JLabel lblDistanceInMiles = new JLabel("Select the distance range in miles: ");
        lblDistanceInMiles.setBounds(10, 250, 300, 14);
        lblDistanceInMiles.setForeground(White);
        panelLeft.add(lblDistanceInMiles);

        comboBox = new JComboBox<String>();
        comboBox.addItem("5");
        comboBox.addItem("10");
        comboBox.addItem("15");
        comboBox.addItem("20");
        comboBox.addItem("25");

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        comboBox.setBounds(10, 280, 200, 20);
        panelLeft.add(comboBox);
    }

    // method to handel search button
    private void initializeSearchButton() {
        btnSearch = new JButton("Search");
        btnSearch.setBounds(45, 370, 180, 40);
        panelLeft.add(btnSearch);

        //Action Listener for SearchButton
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if(textFieldDealerName.getText().isEmpty()||(textFieldZipCode.getText().isEmpty())){
                    JOptionPane.showMessageDialog(frame, "All fields are Mandatory!"); // if any textfield is empty
                } else if(!Validation.isAValidZipCode(textFieldZipCode.getText())){
                    JOptionPane.showMessageDialog(frame, "This is a invalid zip code, Please enter again"); // if zipcode is invalid
                } else {
                    //call method to get list of dealers
                    frame.getContentPane().remove(panelRight);
                    initializeRightPanel();
                    panelRight.revalidate();
                    panelRight.repaint();
                    System.out.println(textFieldDealerName.getText() + " "+ textFieldZipCode.getText() + " "
                            + 0+ " "+ Integer.parseInt(comboBox.getSelectedItem().toString()));

                    try {
                        initialiseAndCreateTable();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    //CreationOfTable
    private void initialiseAndCreateTable() throws Exception {
        String[] columns = {"ID","Dealer Name", "Dealer Address", "Phone Number","ZipCod"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

       DealerSearchFilter dsf= new DealerSearchFilter();
       ArrayList<String> zipcodes = dsf.zipCodeRadius(textFieldZipCode.getText(),0,Integer.parseInt(comboBox.getSelectedItem().toString()));

       for(String zip: zipcodes){
           System.out.print(zip + " ");
       }
        DealerManagerImpl imp = new DealerManagerImpl();
        dealerList= (ArrayList<Dealer>) imp.getDealerDetails(textFieldDealerName.getText(), zipcodes );
 if(dealerList.size()>0){

//       dealerList = createList();

        for( Dealer detail : dealerList)
        {
            Vector<String> row = new Vector<>();
            row.add(detail.getDealerId()+ " ");
            row.add(detail.getDealerName());
            row.add(detail.getDealerAddress());
            row.add(detail.getPhoneNumber());
            row.add(detail.getZipCode());
            model.addRow(row);
        }

        JTable table = new JTable(model){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                //this is to have alternative color in the table row
                Component returnComp = super.prepareRenderer(renderer, row, column);
                Color darkShade = new Color(0, 52, 94);
                Color lightShade = new Color(0, 75, 134);
                if (!returnComp.getBackground().equals(getSelectionBackground())){
                    Color bg = (row % 2 == 0 ? darkShade : lightShade);
                    returnComp .setBackground(bg);
                    bg = null;
                }
                return returnComp;
            }
        };

        table.setPreferredSize(new Dimension(700,500));
        table.setForeground(White);
        table.setBackground(new Color(222, 249, 250));
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        table.setRowHeight(table.getRowHeight() + 20); // set row height
        table.setDefaultEditor(Object.class, null); // to stop the editing of table cell on double click of mouse

        // to make text Center Align
        DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
        rendar.setHorizontalAlignment(JLabel.CENTER);
        for(int x=0;x<table.getColumnCount();x++){
            table.getColumnModel().getColumn(x).setCellRenderer( rendar );
        }

        //Table Header
        JTableHeader header = table.getTableHeader();
        header.setFont(Header);
        header.setBackground(new Color(0, 30, 54));
        header.setForeground(Color.WHITE);
        //set header size
        table.getTableHeader().setPreferredSize(
                new Dimension(700,table.getRowHeight() )
        );
        // set header text to Center Align
        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // set different width for different columns
        setJTableColumnsWidth(table, 700, 10, 30, 30, 20,10);
        JScrollPane jScrollPane = new JScrollPane(table);
        panelRight.add(jScrollPane);

        // entry point for usecase 2 by click on a particular table row
        table.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent me) {
                int row = table.rowAtPoint(me.getPoint());
                if(row!=-1) {
                    // Stephen added
                    // new Frame_1(selected dealer);

                    //temprory screen for usecase 2 has to be replaced
                    //change made to pass the complete Dealer object instead of just the dealer id.
//                    NewTextFrame nf= new NewTextFrame(dealerList.get(row));
                }
            }
        });
        System.out.println(table.getHeight());}
 else{
     JLabel lblNoDataFound= new JLabel("No Record Available with Dealer Name " + textFieldDealerName.getText().toUpperCase() +
             " within " + (comboBox.getSelectedItem().toString()) + " Miles of ZipCode "+ textFieldZipCode.getText());

     lblNoDataFound.setForeground(Blue);
     lblNoDataFound.setFont(new Font("Arial", Font.PLAIN, 15));
     lblNoDataFound.setAlignmentX(JLabel.CENTER_ALIGNMENT);
     lblNoDataFound.setPreferredSize(new Dimension(700, 500));
     panelRight.add(lblNoDataFound);
     panelRight.setEnabled(true);
 }
    }

    //created dummy arraylist for testing
//    private ArrayList<Dealer> createList(){
//        ArrayList<Dealer> list1 = new ArrayList<>();
//
//        Dealer d1 = new Dealer(0,"Benz0","Address1","425-5","98121","seattle","us");
//        Dealer d2 = new Dealer("1","Mazada1","Address2","4256","98121","seattle","us");
//        Dealer d3 = new Dealer("2","Tesla2","Address3","425-0","98121","seattle","us");
//        Dealer d4 = new Dealer("3","Mazada3","Address2","4256","98121","seattle","us");
//        Dealer d5 = new Dealer("4","Tesla4","Address3","425-0","98121","seattle","us");
//        Dealer d6 = new Dealer("5","Mazada5","Address2","4256","98121","seattle","us");
//
//        if(textFieldZipCode.getText().equals("98109")){
//            list1 = new ArrayList<>();
//            list1.add(d1);
//        }else if(textFieldZipCode.getText().equals("98108")) {
//            list1 = new ArrayList<>();
//            list1.add(d4);
//        }else if(textFieldZipCode.getText().equals("98105")){
//            list1 = new ArrayList<>();
//            list1.add(d5);
//            list1.add(d6);
//        } else{
//            list1 = new ArrayList<>();
//            list1.add(d2);
//            list1.add(d3);
//        }
//        return list1;
//    }

    // method to set the column width dynamically
    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
                                             double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int)
                    (tablePreferredWidth * (percentages[i] / total)));
        }
    }
}

