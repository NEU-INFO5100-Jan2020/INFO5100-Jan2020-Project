package ui.incentiveui;

import service.IncentiveSearchFilterElement;
import service.SortFilter;
import service.Search;
import dto.Incentives;
import persist.IncentivesManagerImpl;
import dto.Vehicle;
import dto.BigDataType;
import ui.incentiveui.JTextFieldHintListener;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import dto.Incentives;
import persist.IncentivesManager;
import service.IncentiveSearchFilter;
import ui.incentiveui.IncentiveMainPage;

//        import lombok.Data;

import java.util.Collection;
import java.util.Date;

// import javax.swing.JCalendar;
//@Data
public class CreatePage extends JFrame {
    /**
     * ng
     */
    private IncentivesManagerImpl incentivesManagerImpl;
    private IncentiveMainPage incentiveMainPage;
    //public Incentives incentive;
    private static final long serialVersionUID = 1L;

    private JFrame jframe;
    private JPanel mainPanel, rightPanel;
    private JButton searchButton, applyButton;

    private JLabel mainTitle, vehicleIDLabel, selectPriceLabel, makeLabel, welcomeLabel, cautionLabel;
    private JTextField vehicleIDText, minimumInt, maximumInt;
    private JComboBox makeCombobox;
    private JRadioButton vinRadioButton, priceRangeRadioButton;
    private JCheckBox newVehicleButton, usedVehicleButton;
    private String[] makelist = {"Default", "Toyota","Buick","Honda","Audi","Jaguar","Kia","Mercedes-Benz"," Land Rover", "Mazda","Volvo", "Ford", "BMW","Jeep","Tesla","Porsche","Acura", "Aston Martin","Chevrolet","Ferrari","Cadillac","Infiniti","Volkswagen","Subaru","Nissan"};


    private JLabel rightTitle, titleLabel, valueLabel, descriptionLabel, disclaimerLabel, dateLabel, slashLabel, incenitveTypeLabel;
    private JComboBox incentiveTypeBox;
    private JTextField titleText, valueText;
    private JTextArea descriptionText, disclaimerText;
    private JDateChooser startDateChooser, endDateChooser;

    IncentivesManagerImpl impl = new IncentivesManagerImpl();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // Search input
    private IncentiveSearchFilterElement vinNum, maximum, minimum, make, isNew;
    private int min, max;


    // apply input
    private String vehicleID, title, description, disclaimer, incentiveType;
    private int value;
    private Date startDate, endDate;

    private int dealerID;
//    private Collection<Vehicle extends BigDataType> vehicleList;



    Font botton = new Font("Courier", Font.BOLD, 21);

    public CreatePage() {
    }

    public CreatePage(int dealerID,IncentiveMainPage incentiveMainPage) {
        setDealerID(dealerID);
        createComponents(dealerID);
        placeComponents();
        addComponents();
        addListeners();
        // makeVisible();
        jframe.setVisible(true);
        this.incentiveMainPage=incentiveMainPage;


    }
    private void setDealerID(int dealerID) {
        this.dealerID = dealerID;
    }

    private void addListeners() {
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(jframe, "Search");
                searchButtonActionPerformed(e);
            }
        });

//        applyButton.addActionListener((ActionEvent ae) -> performOperationAndTrapException());
        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

//                title = titleText.getText();
//                incentiveType = incentiveTypeBox.getSelectedItem().toString();
//                value = Integer.parseInt(valueText.getText());
//                description = descriptionText.getText();
//                disclaimer = disclaimerText.getText();

//                String startDate = sdf.format(startDateChooser.getDate());
//                String endDate = sdf.format(endDateChooser.getDate());
                //startDate = startDateChooser.getDate();
                //endDate=endDateChooser.getDate();
//                System.out.println(startDate);

                //endDate = DateFormat.getDateInstance().format(endDateChooser.getDate());
                setIncentiveApplyData();
                saveApplicationData(dealerID,title,incentiveType,value,description,disclaimer,startDate,endDate);
            }
        });
    }
    // search button
    private void searchButtonActionPerformed(ActionEvent evt) {

        IncentiveSearchFilter isf = new IncentiveSearchFilter(dealerID);
        SortFilter dummy = new SortFilter();

        if (vinRadioButton.isSelected() && vehicleIDText.getText()!= null) {
            vinNum = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.VIN, vehicleIDText.getText());
            isf.addElement(vinNum);
        }
        if (priceRangeRadioButton.isSelected()) {
            min = Integer.parseInt(minimumInt.getText());
            max = Integer.parseInt(maximumInt.getText());
            if (min <= max && max >0) {
                if (minimumInt.getText()!=null) {
                    minimum = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MINPrice, minimumInt.getText());
                    isf.addElement(minimum);
                }
                if (maximumInt.getText()!=null) {
                    maximum = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MAXPrice, maximumInt.getText());
                    isf.addElement(maximum);
                }
            }
        }
        if (! makeCombobox.getSelectedItem().toString().equals("Default")) {
            make = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MAKE, makeCombobox.getSelectedItem().toString());
            isf.addElement(make);
        }
        if (newVehicleButton.isSelected() && !usedVehicleButton.isSelected()) {
            isNew = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.NEW, "New");
            isf.addElement(isNew);
        }
        if (usedVehicleButton.isSelected() && !newVehicleButton.isSelected()) {
            isNew = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.NEW, "Used");
            isf.addElement(isNew);
        }
        Search s = new Search(isf, dummy);
        s.doSearch();
//        vehicleList = new Collection<Vehicle>();
//        vehicleList = s.getResults();
        System.out.println(s.getFactory());
    }


    // apply button
    private void setIncentiveApplyData() {
        setTitle();
        setIncentiveType();
        setValue();
        setDescription();
        setDisclaimer();
        setStartDate();
        setEndDate();
    }

    private void setStartDate() {
//        startDate= sdf.format(startDateChooser.getDate());
        startDate = startDateChooser.getDate();

    }

    private void setEndDate() {
//        endDate=sdf.format(endDateChooser.getDate());
        endDate = endDateChooser.getDate();

    }

    private void setTitle() {
        title = titleText.getText();
        // return title;
    }

    private void setIncentiveType() {
        incentiveType = incentiveTypeBox.getSelectedItem().toString();
    }

    private void setValue() {
        value = Integer.parseInt(valueText.getText());
    }

    private void setDescription() {
        description = descriptionText.getText();
    }

    private void setDisclaimer() {
        disclaimer = disclaimerText.getText();
    }

    private void saveApplicationData(int dealerID, String titleText, String incentiveType,int valueText, String descriptionTextring, String disclaimerText, Date startDate2, Date endDate2) {
        System.out.println("This Is Incentive Details for New Created One.");
        Incentives incentive = new Incentives();
        incentive.setDealerId(dealerID);
        incentive.setTitle(titleText);
        incentive.setDiscountType(incentiveType);
        incentive.setDiscountValue(valueText);
        incentive.setDescription(descriptionTextring);
        incentive.setDisclaimer(disclaimerText);
        incentive.setStartDate(startDate2);
        incentive.setEndDate(endDate2);

        impl.addIncentive(incentive);


        System.out.println(incentive.getDealerId()+ " + "+incentive.getTitle()+ " + "+ incentive.getDiscountType()+" + "+incentive.getDiscountValue() + " + "+incentive.getDescription()+" + "+ incentive.getDisclaimer()+ " + " + incentive.getStartDate()+" + "+incentive.getEndDate() );
    }

    private void performOperationAndTrapException() {
        IncentivesManagerImpl incentivesManagerImpl =new IncentivesManagerImpl();
        Incentives incentive=new Incentives();
        incentive.setEndDate(endDateChooser.getDate());
        incentive.setStartDate(startDateChooser.getDate());
        incentive.setDiscountValue(Integer.parseInt(valueText.getText()));
        incentive.setTitle(titleText.getText());
        incentive.setDiscountType(incentiveTypeBox.getSelectedItem().toString());
        incentive.setDescription((descriptionText.getText()));

        Collection<Incentives> incentivelist= incentivesManagerImpl.getListOfIncentives();
        System.out.println(incentivelist.size());

        incentive.setDealerId(5);
        incentive.setFilterList("");
        incentive.setVehicleIdList("");
        incentive.setDisclaimer(disclaimerText.getText());
        //IncentivesMangerimpl incentivesMangerimpl=new IncentivesMangerimpl();

        incentivesManagerImpl.addIncentive(incentive);


        System.out.println(incentivelist.size());
        //IncentiveMainPage incentiveMainPage = new IncentiveMainPage();


        //incentiveMainPage.setVisible(true);
        //incentiveMainPage.refreshTableContents();




        JOptionPane.showMessageDialog(jframe, "Apply");
        jframe.setVisible(false);

    }










    private void createComponents(int dealerID) {
        jframe = new JFrame("Incentives GUI");
        jframe.setLayout(null);
        // jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);

        Border blackline = BorderFactory.createLineBorder(Color.black, 3);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBorder(blackline);

        rightPanel = new JPanel(null);
        rightPanel.setBorder(blackline);

        createMainComponent(dealerID);
        createRightsComponent();
    }

    private void createMainComponent(int dealerID) {
        mainTitle = new JLabel("Create Incentives");
        Font mainTitleFont = new Font("Courier", Font.BOLD, 27);
        mainTitle.setFont(mainTitleFont);
        Font mainCommonFont = new Font("Courier", Font.PLAIN, 17);
        vehicleIDLabel = new JLabel("VIN");
        vehicleIDLabel.setFont(mainCommonFont);
//        selectPriceLabel = new JLabel("<html><body><p>Select Price Range for Vehicles</p><body></html>");
        selectPriceLabel = new JLabel("<html><body><p>Price Range</p><body></html>");
        selectPriceLabel.setFont(mainCommonFont);
//        newVehicleLabel = new JLabel("New Vehicles");
//        newVehicleLabel.setFont(mainCommonFont);
        searchButton = new JButton("Search");
        searchButton.setFont(botton);
        vehicleIDText = new JTextField(17);
        makeLabel = new JLabel("Make");
        makeLabel.setFont(mainCommonFont);
        minimumInt = new JTextField(7);
//        maximumInt.addFocusListener(new JTextFieldHintListener(minimumInt, "min"));
        maximumInt = new JTextField(7);
//        maximumInt.addFocusListener(new JTextFieldHintListener(maximumInt, "Max"));
        welcomeLabel = new JLabel("Welcome, " + dealerID);
        welcomeLabel.setFont(mainCommonFont);
        cautionLabel = new JLabel("Enter in integers.");
        Font cautionFont = new Font("Courier", Font.PLAIN,12);
        cautionLabel.setFont(cautionFont);

        makeCombobox = new JComboBox(makelist);
        makeCombobox.setFont(mainCommonFont);

        vinRadioButton = new JRadioButton();
        vinRadioButton.setSelected(false);
        priceRangeRadioButton = new JRadioButton();
        priceRangeRadioButton.setSelected(false);

        newVehicleButton = new JCheckBox("New Vehicles", false);
        newVehicleButton.setFont(mainCommonFont);
        usedVehicleButton = new JCheckBox("Used Vehicles", false);
        usedVehicleButton.setFont(mainCommonFont);
    }


    private void createRightsComponent() {
        rightTitle = new JLabel(
                "<html><body><p align=\"center\">Add Incentive Details for A Certain Vehicle<br>Or Group of Vehicles</p><body</html>");
        // <html><body><p>Add Incentive Details for A Certain Vehicle Or Group of
        // Vehicles</p><body></html>
        // <html><body><p align=\"center\">Add Incentive Details for A Certain
        // Vehicle<br>Or Group of Vehicles<\p><\body<\html>
        Font rightTitleFont = new Font("Courier", Font.BOLD, 17);
        rightTitle.setFont(rightTitleFont);
        Font rightCommonFont = new Font("Courier", Font.PLAIN, 15);
        titleLabel = new JLabel("Title");
        titleLabel.setFont(rightCommonFont);
        valueLabel = new JLabel("Value");
        valueLabel.setFont(rightCommonFont);
        descriptionLabel = new JLabel("Description");
        descriptionLabel.setFont(rightCommonFont);
        disclaimerLabel = new JLabel("Disclaimer");
        disclaimerLabel.setFont(rightCommonFont);
        dateLabel = new JLabel("StartDate - EndDate");
        dateLabel.setFont(rightCommonFont);
        titleText = new JTextField(17);
        valueText = new JTextField(17);
        descriptionText = new JTextArea(1, 6);
        disclaimerText = new JTextArea(1, 6);

        // date
        slashLabel = new JLabel("-");
        startDateChooser = new JDateChooser();
        endDateChooser = new JDateChooser();

        applyButton = new JButton("Apply");
        applyButton.setFont(botton);
        incenitveTypeLabel = new JLabel("IncentiveType");
        incenitveTypeLabel.setFont(rightCommonFont);
        incentiveTypeBox = new JComboBox();
        incentiveTypeBox.setFont(rightCommonFont);
        incentiveTypeBox.addItem("Cash Back");
        incentiveTypeBox.addItem("Percentage");
    }

    private void addComponents() {
        mainPanel.setBounds(20, 20, 960, 740);
        jframe.add(mainPanel);
        rightPanel.setBounds(420, 90, 510, 620);
        mainPanel.add(rightPanel, null);
        addMainPanel();
        addRightPanel();
    }

    private void addMainPanel() {
        mainPanel.add(mainTitle);
        mainPanel.add(vehicleIDLabel);
        mainPanel.add(vehicleIDText);
        mainPanel.add(selectPriceLabel);
        mainPanel.add(makeCombobox);
        mainPanel.add(minimumInt);
        mainPanel.add(maximumInt);
        mainPanel.add(newVehicleButton);
        mainPanel.add(searchButton);
        mainPanel.add(makeLabel);
        mainPanel.add(welcomeLabel);
        mainPanel.add(cautionLabel);
        mainPanel.add(usedVehicleButton);
        mainPanel.add(vinRadioButton);
        mainPanel.add(priceRangeRadioButton);
    }

    private void addRightPanel() {
        rightPanel.add(rightTitle);
        rightPanel.add(titleLabel);
        rightPanel.add(titleText);
        rightPanel.add(valueLabel);
        rightPanel.add(valueText);
        rightPanel.add(descriptionLabel);
        rightPanel.add(descriptionText);
        rightPanel.add(disclaimerLabel);
        rightPanel.add(disclaimerText);
        rightPanel.add(dateLabel);
        rightPanel.add(slashLabel);
        rightPanel.add(startDateChooser);
        rightPanel.add(endDateChooser);
        rightPanel.add(applyButton);
        rightPanel.add(incenitveTypeLabel);
        rightPanel.add(incentiveTypeBox);
        ButtonGroup group = new ButtonGroup();
        group.add(vinRadioButton);
        group.add(priceRangeRadioButton);
    }

    private void placeComponents() {
        jframe.setSize(1000, 800);
        placeMainComponents();
        placeRightComponents();
    }

    private void placeMainComponents() {
        int mainLabelX = 40;
        int mainTextX = 180;
        mainTitle.setBounds(325, 40, 800, 20);
        vinRadioButton.setBounds(mainLabelX, 150,20,40);
        vehicleIDLabel.setBounds(mainLabelX+35, 150, 150, 40);
        priceRangeRadioButton.setBounds(mainLabelX, 230,20,40);
        selectPriceLabel.setBounds(mainLabelX+35, 230, 150, 40);


        vehicleIDText.setBounds(mainTextX+30, 150, 175, 40);

        minimumInt.setBounds(mainTextX+30,230,75,40);
        maximumInt.setBounds(mainTextX+130,230,75,40);
        makeLabel.setBounds(mainLabelX,375,150,60);
        makeCombobox.setBounds(mainTextX,375,175,40);
        newVehicleButton.setBounds(mainLabelX, 450, 150, 50);
        usedVehicleButton.setBounds(200 ,450,200,50);
        searchButton.setBounds(150, 550, 130, 40);

        welcomeLabel.setBounds(750,10,150,20);
        cautionLabel.setBounds(mainTextX+30,280,150,20);
    }

    private void placeRightComponents() {
        int rightLabelX = 25;
        int rightTextX = 150;
        rightTitle.setBounds(35, 25, 500, 40);
        titleLabel.setBounds(rightLabelX, 100, 130, 30);
        valueLabel.setBounds(rightLabelX, 215, 130, 30);
        descriptionLabel.setBounds(rightLabelX, 285, 130, 50);
        disclaimerLabel.setBounds(rightLabelX, 370, 130, 50);
        dateLabel.setBounds(rightLabelX, 450, 175, 40);
        incenitveTypeLabel.setBounds(rightLabelX,155,130,30);

        titleText.setBounds(rightTextX, 100, 130, 30);
        valueText.setBounds(rightTextX, 215, 130, 30);
        descriptionText.setBounds(rightTextX, 285, 250, 50);
        disclaimerText.setBounds(rightTextX, 380, 250, 50);
        incentiveTypeBox.setBounds(rightTextX,155,130,30);

        slashLabel.setBounds(340, 450, 10, 40);
        startDateChooser.setBounds(210,450, 125,40);
        endDateChooser.setBounds(355, 450, 125,40);

        applyButton.setBounds(215, 535, 100, 40);

    }

}
