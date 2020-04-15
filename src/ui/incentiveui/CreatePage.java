package ui.incentiveui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import dto.Incentives;
import persist.IncentivesManagerImpl;
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
    public IncentivesManagerImpl incentivesManagerImpl;
    public IncentiveMainPage incentiveMainPage;
    //public Incentives incentive;
    private static final long serialVersionUID = 1L;

    private JFrame jframe;
    private JPanel mainPanel, rightPanel;

    private JButton searchButton, applyButton;

    private JLabel mainTitle, vehicleIDLabel, selectPriceLabel, newVehicleLabel, makeLabel, welcomeLabel, cautionLabel;
    public JTextField vehicleIDText, minimumInt, maximumInt;
    protected JComboBox makeCombobox;
    protected Checkbox newVehicleButton;
    private String[] makelist = {"Toyota","Buick","Honda","Audi","Jaguar","Kia","Mercedes-Benz"," Land Rover", "Mazda","Volvo", "Ford", "BMW","Jeep","Tesla","Porsche","Acura", "Aston Martin","Chevrolet","Ferrari","Cadillac","Infiniti","Volkswagen","Subaru","Nissan"};
    private Integer minimumPrice, maximumPrice;

    private JLabel rightTitle, titleLabel, valueLabel, descriptionLabel, disclaimerLabel, dateLabel, slashLabel, incenitveTypeLabel;
    private JComboBox incentiveTypeBox;
    public JTextField titleText, valueText;
    public JTextArea descriptionText, disclaimerText;
    public JDateChooser startDateChooser, endDateChooser;

    // IncentiveInput searchInput, applyInput;

    protected String vehicleID, title, description, disclaimer, dealerID;
    protected int maximum, minimum;
    protected Integer value;
    protected boolean isNewVehicle;
    protected Date startDate, endDate;

    // public int[][] priceRangeArray;


    Font botton = new Font("Courier", Font.BOLD, 21);

    public CreatePage() {
    }

    public CreatePage(String dealerID,IncentiveMainPage incentiveMainPage) {
        createComponents(dealerID);
        placeComponents();
        addComponents();
        addListeners();
        // makeVisible();
        jframe.setVisible(true);
        this.incentiveMainPage=incentiveMainPage;


    }

    private void addListeners() {
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                JOptionPane.showMessageDialog(jframe, "Search");
            }
        });

        applyButton.addActionListener((ActionEvent ae) -> performOperationAndTrapException());
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






    // applyButton.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    //// Form form = new
    // Form(titleText.toString(),valueText.toString(),descriptionText.toString(),disclaimerText.toString());
    // System.out.println(title+value+description+disclaimer);
    //// return form;
    // // JOptionPane.showMessageDialog(jframe, applyInput.title + " + " +
    // applyInput.value + " + "
    // // + applyInput.description + " + " + applyInput.disclaimer);
    // }
    // });




    private void createComponents(String dealerID) {
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

    private void createMainComponent(String dealerID) {
        mainTitle = new JLabel("Create Incentives");
        Font mainTitleFont = new Font("Courier", Font.BOLD, 27);
        mainTitle.setFont(mainTitleFont);
        Font mainCommonFont = new Font("Courier", Font.PLAIN, 17);
        vehicleIDLabel = new JLabel("VIN");
        vehicleIDLabel.setFont(mainCommonFont);
//        selectPriceLabel = new JLabel("<html><body><p>Select Price Range for Vehicles</p><body></html>");
        selectPriceLabel = new JLabel("<html><body><p>Price Range</p><body></html>");
        selectPriceLabel.setFont(mainCommonFont);
        newVehicleLabel = new JLabel("New Vehicles");
        newVehicleLabel.setFont(mainCommonFont);
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
        cautionLabel = new JLabel("Enter min and max integers.");
        Font cautionFont = new Font("Courier", Font.PLAIN,5);

        makeCombobox = new JComboBox(makelist);
        makeCombobox.setFont(mainCommonFont);
//        priceComboBox = new JComboBox();
//        priceComboBox.setFont(mainCommonFont);
//        priceComboBox.addItem("25000-35000");
//        priceComboBox.addItem("35000-45000");
//        priceComboBox.addItem("45000-55000");
//        priceComboBox.addItem("55000-65000");
//        priceComboBox.addItem("Above 65000");

        newVehicleButton = new Checkbox();
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
//        mainPanel.add(priceComboBox);
        mainPanel.add(minimumInt);
        mainPanel.add(maximumInt);
        mainPanel.add(newVehicleButton);
        mainPanel.add(newVehicleLabel);
        mainPanel.add(searchButton);
        mainPanel.add(makeLabel);
        mainPanel.add(welcomeLabel);
        mainPanel.add(cautionLabel);
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
        vehicleIDLabel.setBounds(mainLabelX, 150, 150, 40);
        selectPriceLabel.setBounds(mainLabelX, 230, 150, 60);
        newVehicleLabel.setBounds(150, 450, 200, 50);

        vehicleIDText.setBounds(mainTextX, 150, 175, 40);
//        priceComboBox.setBounds(mainTextX, 310, 175, 40);
        minimumInt.setBounds(mainTextX,230,75,40);
        maximumInt.setBounds(mainTextX+100,230,75,40);
        makeLabel.setBounds(mainLabelX,375,150,60);
        makeCombobox.setBounds(mainTextX,375,175,40);
        newVehicleButton.setBounds(100, 450, 50, 50);
        searchButton.setBounds(150, 550, 130, 40);

        welcomeLabel.setBounds(750,10,150,20);
        cautionLabel.setBounds(mainTextX,280,200,20);
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
