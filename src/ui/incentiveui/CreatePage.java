package ui.incentiveui;
import ui.incentiveui.JTextFieldHintListener;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;


//        import lombok.Data;

import java.text.DateFormat;
import java.util.Date;

// import javax.swing.JCalendar;
//@Data
class CreatePage extends JFrame {
    /**
     * ng
     */
    private static final long serialVersionUID = 1L;

    private JFrame jframe;
    private JPanel mainPanel, rightPanel;

    private JButton searchButton, applyButton;

    private JLabel mainTitle, vehicleIDLabel, selectPriceLabel, newVehicleLabel, makeLabel, welcomeLabel, cautionLabel;
    private JTextField vehicleIDText, minimumInt, maximumInt;
    private JComboBox makeCombobox;
    private JRadioButton newVehicleButton;
    private String[] makelist = {"Default", "Toyota","Buick","Honda","Audi","Jaguar","Kia","Mercedes-Benz"," Land Rover", "Mazda","Volvo", "Ford", "BMW","Jeep","Tesla","Porsche","Acura", "Aston Martin","Chevrolet","Ferrari","Cadillac","Infiniti","Volkswagen","Subaru","Nissan"};
    private Integer minimumPrice, maximumPrice;

    private JLabel rightTitle, titleLabel, valueLabel, descriptionLabel, disclaimerLabel, dateLabel, slashLabel, incenitveTypeLabel;
    private JComboBox incentiveTypeBox;
    private JTextField titleText, valueText;
    private JTextArea descriptionText, disclaimerText;
    private JDateChooser startDateChooser, endDateChooser;

    // IncentiveInput searchInput, applyInput;

    private String vehicleID, title, description, disclaimer, dealerID, incentiveType;
    private int maximum, minimum;
    private String value;
    private boolean isNewVehicle;
    private String startDate, endDate;

    // public int[][] priceRangeArray;

    Font botton = new Font("Courier", Font.BOLD, 21);

    public CreatePage() {
    }

    public CreatePage(String dealerID) {
        createComponents(dealerID);
        placeComponents();
        addComponents();
        addListeners();
        // makeVisible();
        jframe.setVisible(true);

    }

    private void addListeners() {
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jframe, "Search");
            }
        });

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                title = titleText.getText();
                incentiveType = incentiveTypeBox.getSelectedItem().toString();
                value = valueText.getText();
                description = descriptionText.getText();
                disclaimer = disclaimerText.getText();
                startDate = DateFormat.getDateInstance().format(startDateChooser.getDate());
                endDate = DateFormat.getDateInstance().format(endDateChooser.getDate());
                saveApplicationData(title,incentiveType,value,description,disclaimer,startDate,endDate);
            }
        });

    }

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
        startDate = DateFormat.getDateInstance().format(startDateChooser.getDate());
    }

    private void setEndDate() {
        endDate = DateFormat.getDateInstance().format(endDateChooser.getDate());
    }

    private void setTitle() {
        title = titleText.getText();
        // return title;
    }

    private void setIncentiveType() {
        incentiveType = incentiveTypeBox.getSelectedItem().toString();
    }

    private void setValue() {
        value = valueText.getText();
    }

    private void setDescription() {
        description = descriptionText.getText();
    }

    private void setDisclaimer() {
        disclaimer = disclaimerText.getText();
    }

    private void createComponents(String dealerID) {
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

    private void createMainComponent(String dealerID) {
        mainTitle = new JLabel("Create Incentives");
        Font mainTitleFont = new Font("Courier", Font.BOLD, 27);
        mainTitle.setFont(mainTitleFont);
        Font mainCommonFont = new Font("Courier", Font.PLAIN, 17);
        vehicleIDLabel = new JLabel("VIN");
        vehicleIDLabel.setFont(mainCommonFont);
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
        minimumInt.addFocusListener(new JTextFieldHintListener("Minimum", minimumInt));
        maximumInt = new JTextField(7);
        maximumInt.addFocusListener(new JTextFieldHintListener("Maximum", maximumInt));
        welcomeLabel = new JLabel("Welcome, " + dealerID);
        welcomeLabel.setFont(mainCommonFont);
        cautionLabel = new JLabel("Enter min and max integers.");
        Font cautionFont = new Font("Courier", Font.PLAIN,5);
        makeCombobox = new JComboBox(makelist);
        makeCombobox.setFont(mainCommonFont);
        newVehicleButton = new JRadioButton();
    }


    private void createRightsComponent() {
        rightTitle = new JLabel("<html><body><p align=\"center\">Add Incentive Details for A Certain Vehicle<br>Or Group of Vehicles</p><body</html>");

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
        minimumInt.setBounds(mainTextX,230,75,40);
        maximumInt.setBounds(mainTextX+100,230,75,40);
        makeLabel.setBounds(mainLabelX,375,150,60);
        makeCombobox.setBounds(mainTextX,375,175,40);
        newVehicleButton.setBounds(100, 450, 50, 50);
        searchButton.setBounds(150, 550, 130, 40);

        welcomeLabel.setBounds(750,10,200,20);
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

    private void saveApplicationData(String titleText, String incentiveType,String valueText, String descriptionTextring, String disclaimerText, String startDate, String endDate) {
        System.out.println("This Is Incentive Details for New Created One.");
        System.out.println(titleText+ " + "+ incentiveType+" + "+valueText + " + "+descriptionTextring+" + "+ disclaimerText+ " + " + startDate+" + "+endDate );
    }

//    public class CreateIncentives {
////        CreatePage incentive = new CreatePage();
//        public void SaveApplicationData(String titleText, String incentiveType,String valueText, String descriptionTextring, String disclaimerText, String startDate, String endDate) {
//            System.out.println(titleText+ " + "+ incentiveType+" + "+valueText + " + "+descriptionTextring+" + "+ disclaimerText+ " + " + startDate+" + "+endDate );
////            return 0;
//        }
//    }
}


