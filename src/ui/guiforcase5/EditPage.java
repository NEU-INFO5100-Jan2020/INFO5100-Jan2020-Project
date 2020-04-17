package ui.guiforcase5;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;
import dto.Incentives;
import persist.IncentivesManagerImpl;
//        import lombok.Data;

//@Data
class EditPage extends JFrame {
    /**
     * ng
     */
    private static final long serialVersionUID = 1L;
    public IncentiveMainPage incentiveMainPage;
    private JFrame jframe;
    private JPanel mainPanel, rightPanel;



    private JLabel mainTitle, vehicleIDLabel, selectPriceLabel,  makeLabel, welcomeLabel;
    private JCheckBox newVehicleButton, usedVehicleButton;
    private JButton applyButton;

    private JLabel rightTitle, titleLabel, valueLabel, descriptionLabel, disclaimerLabel, dateLabel, slashLabel, incenitveTypeLabel;
    private JComboBox incentiveTypeBox;
    private JTextField titleText, valueText;
    private JTextArea descriptionText, disclaimerText;
    private JDateChooser startDateChooser, endDateChooser;

    // IncentiveInput searchInput, applyInput;

    private String vehicleID, title, description, disclaimer, incentiveType;
    private int maximum, minimum;
    private String value;
    private boolean isNewVehicle;
    private String startDate, endDate;
    int rowIndex;
    private static int dealerID;
    public Incentives incentives;
    // public int[][] priceRangeArray;

    Font botton = new Font("Courier", Font.BOLD, 21);

    public EditPage(int dealerID, int rowIndex, ActionListener actionListener) {

    }

    public EditPage(int dealerID, Incentives incentives) {
        this.dealerID=dealerID;

        this.incentives=incentives;
        createComponents(dealerID);
        placeComponents();
        addComponents();
        addListeners();
        jframe.setLocation(1200,20);
        jframe.setVisible(true);

    }

    private void addListeners() {


        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IncentivesManagerImpl incentivesManagerImpl =new IncentivesManagerImpl();
                incentives.setTitle(titleText.getText());
                incentives.setDiscountType(String.valueOf(incentiveTypeBox.getSelectedItem()));
                incentives.setDiscountValue(Integer.parseInt(valueText.getText()));
                incentives.setDescription(descriptionText.getText());
                incentives.setDisclaimer(disclaimerText.getText());
                incentives.setStartDate(startDateChooser.getDate());
                incentives.setEndDate(endDateChooser.getDate());
                incentivesManagerImpl.updateIncentive2(incentives);
                IncentiveMainPage incentiveMainPage=new IncentiveMainPage(dealerID);
                incentiveMainPage.setVisible(true);

            }
        });
    }

    private void createComponents(int dealerID) {
        jframe = new JFrame("Incentive Management");
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
        mainTitle = new JLabel("Edit Incentives");
        Font mainTitleFont = new Font("Courier", Font.BOLD, 27);
        mainTitle.setFont(mainTitleFont);
        Font mainCommonFont = new Font("Courier", Font.PLAIN, 17);
        vehicleIDLabel = new JLabel("VIN: ");
        vehicleIDLabel.setFont(mainCommonFont);
//        selectPriceLabel = new JLabel("<html><body><p>Select Price Range for Vehicles</p><body></html>");
        selectPriceLabel = new JLabel("<html><body><p>Price Range: </p><body></html>");
        selectPriceLabel.setFont(mainCommonFont);


//        vehicleIDText = new JTextField(17);
        makeLabel = new JLabel("Make: ");
        makeLabel.setFont(mainCommonFont);
//        minimumInt = new JTextField(7);
//        maximumInt.addFocusListener(new JTextFieldHintListener(minimumInt, "min"));
//        maximumInt = new JTextField(7);
//        maximumInt.addFocusListener(new JTextFieldHintListener(maximumInt, "Max"));
        welcomeLabel = new JLabel("Welcome, " + dealerID);
        welcomeLabel.setFont(mainCommonFont);

        Font cautionFont = new Font("Courier", Font.PLAIN,5);


        newVehicleButton = new JCheckBox("New Vehicles");
        newVehicleButton.setFont(mainCommonFont);
        usedVehicleButton = new JCheckBox("Used Vehicles");
        usedVehicleButton.setFont(mainCommonFont);
    }


    private void createRightsComponent() {
        rightTitle = new JLabel(
                "<html><body><p align=\"center\">Edit Incentive Details for A Certain Vehicle<br>Or Group of Vehicles</p><body</html>");

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
        titleText.setText(incentives.getTitle());
        valueText = new JTextField(17);
        valueText.setText(String.valueOf(incentives.getDiscountValue()));
        descriptionText = new JTextArea(1, 6);
        disclaimerText = new JTextArea(1, 6);

        // date
        slashLabel = new JLabel("-");
        startDateChooser = new JDateChooser();
        startDateChooser.setDate(incentives.getStartDate());
        endDateChooser = new JDateChooser();
        endDateChooser.setDate(incentives.getEndDate());

        applyButton = new JButton("Edit");
        applyButton.setFont(botton);
        incenitveTypeLabel = new JLabel("IncentiveType");
        incenitveTypeLabel.setFont(rightCommonFont);
        incentiveTypeBox = new JComboBox();
        incentiveTypeBox.setFont(rightCommonFont);
        incentiveTypeBox.addItem("Cash Back");
        incentiveTypeBox.addItem("Percentage");
    }

    private void addComponents() {
        mainPanel.setBounds(20, 20, 550, 840);
        jframe.add(mainPanel);
        rightPanel.setBounds(20, 200, 510, 620);
        mainPanel.add(rightPanel, null);
        addMainPanel();
        addRightPanel();
    }

    private void addMainPanel() {
        mainPanel.add(mainTitle);
        mainPanel.add(vehicleIDLabel);
        mainPanel.add(selectPriceLabel);
        mainPanel.add(usedVehicleButton);
        mainPanel.add(newVehicleButton);

        mainPanel.add(makeLabel);
        mainPanel.add(welcomeLabel);
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
        jframe.setSize(590, 900);
        placeMainComponents();
        placeRightComponents();
    }

    private void placeMainComponents() {
        int mainLabelX = 40;
        int mainTextX = 180;
        mainTitle.setBounds(150, 40, 250, 20);
        welcomeLabel.setBounds(300,10,200,20);
        vehicleIDLabel.setBounds(mainLabelX, 100, 150, 20);
        selectPriceLabel.setBounds(mainLabelX, 120, 150, 20);
        makeLabel.setBounds(mainLabelX,140,150,20);
        newVehicleButton.setBounds(mainLabelX, 160, 150, 20);
        usedVehicleButton.setBounds(200,160,200,20);
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

        titleText.setBounds(rightTextX, 100, 150, 30);
        valueText.setBounds(rightTextX, 215, 150, 30);
        descriptionText.setBounds(rightTextX, 285, 250, 50);
        descriptionText.setText(incentives.getDescription());
        disclaimerText.setBounds(rightTextX, 380, 250, 50);
        disclaimerText.setText(incentives.getDisclaimer());
        incentiveTypeBox.setBounds(rightTextX,155,150,30);

        slashLabel.setBounds(340, 450, 10, 40);
        startDateChooser.setBounds(210,450, 125,40);
        endDateChooser.setBounds(355, 450, 125,40);

        applyButton.setBounds(215, 535, 100, 40);

    }



}
