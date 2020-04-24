package ui.guiforcase5;

import service.IncentiveSearchFilterElement;
import service.SortFilter;
import service.Search;
import dto.Incentives;
import persist.IncentivesManagerImpl;
import service.IncentiveSearchFilter;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;

//        import lombok.Data;
//@Data
public class CreatePageWithSearchButton extends JFrame {

    private static final long serialVersionUID = 1L;

    private JFrame jframe;
    private JPanel mainPanel, rightPanel;
    private JButton searchButton, applyButton;

    private JLabel mainTitle, vehicleIDLabel, selectPriceLabel, makeLabel, welcomeLabel, cautionLabel;
    private JTextField vehicleIDText, minimumInt, maximumInt;
    private JComboBox makeCombobox;
    private JRadioButton oneRadioButton, groupRadioButton;
    private JCheckBox newVehicleButton, usedVehicleButton;
    private String[] makelist = {"Default", "Toyota","Buick","Honda","Audi","Jaguar","Kia","Mercedes-Benz","Land Rover", "Mazda","Volvo", "Ford", "BMW","Jeep","Tesla","Porsche","Acura", "Aston Martin","Chevrolet","Ferrari","Cadillac","Infiniti","Volkswagen","Subaru","Nissan"};


    private JLabel rightTitle, titleLabel, valueLabel, descriptionLabel, disclaimerLabel, dateLabel, slashLabel, incentiveTypeLabel;
    private JComboBox incentiveTypeBox;
    private JTextField titleText, valueText;
    private JTextArea descriptionText, disclaimerText;
    private JDateChooser startDateChooser, endDateChooser;

    private String dealerName;

    private int dealerID;
    private static int[] vid;

    Font botton = new Font("Courier", Font.BOLD, 21);

    public CreatePageWithSearchButton(int dealerID, String dealerName) {
        setDealerID(dealerID, dealerName);
        createComponents(dealerID);
        placeComponents();
        addComponents();
        addListeners();
        jframe.setVisible(true);


    }
    private void setDealerID(int dealerID, String dealerName) {
        this.dealerID = dealerID;
        this.dealerName = dealerName;
    }

    private void addListeners() {
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                searchButtonActionPerformed(e);
                setSearchFilter();
                for (int i : vid) {
                    System.out.println("This is #"+i);
                }
            }
        });

//        applyButton.addActionListener((ActionEvent ae) -> performOperationAndTrapException());
        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Incentives incentive=new Incentives();
                setIncentiveApplyData(incentive);
                IncentivesManagerImpl incentivesManagerImpl=new IncentivesManagerImpl();
                IncentiveMainPage incentiveMainPage=new IncentiveMainPage(dealerID, dealerName);

                try {
                    setSearchFilter();
                    incentivesManagerImpl.addIncentive2(incentive, vid);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                finally {
                    dispose();
                    incentiveMainPage.setVisible(true);
                    JOptionPane.showMessageDialog(jframe, "Applied.");
                    incentiveMainPage.refreshTableContents();
                }

            }
        });
    }
    // search button
    private void setSearchFilter() {
        IncentiveSearchFilter isf = new IncentiveSearchFilter(dealerID);
        SortFilter dummy = new SortFilter();

        if (oneRadioButton.isSelected()) {
            // Search input
            IncentiveSearchFilterElement vinNum = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.VIN, vehicleIDText.getText());
            isf.addElement(vinNum);
        }
        if (groupRadioButton.isSelected()) {
            int min;
            try {
                min = Integer.parseInt(minimumInt.getText());
            } catch (NumberFormatException e) {
                min = Integer.MIN_VALUE;
            }
            int max;
            try {
                max = Integer.parseInt(maximumInt.getText());
            } catch (NumberFormatException e) {
                max = Integer.MAX_VALUE;
            }

            if (max < min || max < 0) {
                JOptionPane.showMessageDialog(jframe, "Range is invalid.");
            } else {
                IncentiveSearchFilterElement minimum = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MINPrice, Integer.toString(min));
                isf.addElement(minimum);
                IncentiveSearchFilterElement maximum = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MAXPrice, Integer.toString(max));
                isf.addElement(maximum);
            }
            if (! makeCombobox.getSelectedItem().toString().equals("Default")) {
                IncentiveSearchFilterElement make = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MAKE, makeCombobox.getSelectedItem().toString());
                isf.addElement(make);
            }
            IncentiveSearchFilterElement isNew;
            if (newVehicleButton.isSelected() && !usedVehicleButton.isSelected()) {
                isNew = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.NEW, "New");
                isf.addElement(isNew);
            }
            if (usedVehicleButton.isSelected() && !newVehicleButton.isSelected()) {
                isNew = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.NEW, "Used");
                isf.addElement(isNew);
            }
        }

        Search s = new Search(isf, dummy);
        s.doSearch();
//        vid = s.getArrayOfVehicleID();
    }
    private void searchButtonActionPerformed(ActionEvent evt) {
            setSearchFilter();
            for (int i : vid) {
                System.out.println("This is #"+i);
            }

    }


    // apply button
    private void setIncentiveApplyData(Incentives incenitve) {
        incenitve.setTitle(titleText.getText());
        incenitve.setDiscountType(String.valueOf(incentiveTypeBox.getSelectedItem()));
        incenitve.setDiscountValue(Integer.parseInt(valueText.getText()));
        incenitve.setDescription(descriptionText.getText());
        incenitve.setDisclaimer(disclaimerText.getText());
        incenitve.setStartDate(startDateChooser.getDate());
        incenitve.setEndDate(endDateChooser.getDate());
        incenitve.setDealerId(dealerID);
        incenitve.setIsDeleted(false);
        incenitve.setFilterList(null);
        incenitve.setVehicleIdList(null);
    }

    private void createComponents(int dealerID) {
        jframe = new JFrame("Incentives GUI");
        jframe.setLayout(null);

        Border blackLine = BorderFactory.createLineBorder(Color.black, 3);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBorder(blackLine);

        rightPanel = new JPanel(null);
        rightPanel.setBorder(blackLine);

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
        selectPriceLabel = new JLabel("<html><body><p>Price Range</p><body></html>");
        selectPriceLabel.setFont(mainCommonFont);
        searchButton = new JButton("Search");
        searchButton.setFont(botton);
        vehicleIDText = new JTextField(17);
        makeLabel = new JLabel("Make");
        makeLabel.setFont(mainCommonFont);
        minimumInt = new JTextField(7);
//        maximumInt.addFocusListener(new JTextFieldHintListener("minimum",minimumInt));
        maximumInt = new JTextField(7);
//        maximumInt.addFocusListener(new JTextFieldHintListener( "maximum", maximumInt));
        welcomeLabel = new JLabel("Welcome, " + dealerID);
        welcomeLabel.setFont(mainCommonFont);
        cautionLabel = new JLabel("Enter in integers.");
        Font cautionFont = new Font("Courier", Font.PLAIN,12);
        cautionLabel.setFont(cautionFont);

        makeCombobox = new JComboBox(makelist);
        makeCombobox.setFont(mainCommonFont);

        Font radioFont = new Font("Courier",Font.BOLD,20);
        oneRadioButton = new JRadioButton("Create for one vehicle");
        oneRadioButton.setFont(radioFont);
        oneRadioButton.setSelected(true);
        groupRadioButton = new JRadioButton("Or a group of vehicles");
        groupRadioButton.setFont(radioFont);
        groupRadioButton.setSelected(false);

        newVehicleButton = new JCheckBox("New Vehicles", false);
        newVehicleButton.setFont(mainCommonFont);
        usedVehicleButton = new JCheckBox("Used Vehicles", false);
        usedVehicleButton.setFont(mainCommonFont);
    }


    private void createRightsComponent() {
        rightTitle = new JLabel(
                "<html><body><p align=\"center\">Add Incentive Details for A Certain Vehicle<br>Or Group of Vehicles</p><body</html>");

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

        applyButton = new JButton("Create");
        applyButton.setFont(botton);
        incentiveTypeLabel = new JLabel("IncentiveType");
        incentiveTypeLabel.setFont(rightCommonFont);
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
        mainPanel.add(oneRadioButton);
        mainPanel.add(groupRadioButton);
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
        rightPanel.add(incentiveTypeLabel);
        rightPanel.add(incentiveTypeBox);
        ButtonGroup group = new ButtonGroup();
        group.add(oneRadioButton);
        group.add(groupRadioButton);
    }

    private void placeComponents() {
        jframe.setSize(1000, 800);
        placeMainComponents();
        placeRightComponents();
    }

    private void placeMainComponents() {
        int mainLabelX = 50;
        int mainTextX = 180;
        mainTitle.setBounds(325, 40, 800, 20);

        oneRadioButton.setBounds(20, 110,300,40);
        vehicleIDLabel.setBounds(mainLabelX, 160, 150, 40);
        vehicleIDText.setBounds(mainTextX, 160, 175, 40);

        groupRadioButton.setBounds(20, 230,300,40);
        selectPriceLabel.setBounds(mainLabelX, 280, 150, 40);
        minimumInt.setBounds(mainTextX,280,75,40);
        maximumInt.setBounds(mainTextX+100,280,75,40);
        makeLabel.setBounds(mainLabelX,375,150,60);
        makeCombobox.setBounds(mainTextX,375,175,40);
        newVehicleButton.setBounds(mainLabelX, 450, 170, 50);
        usedVehicleButton.setBounds(220 ,450,170,50);

        searchButton.setBounds(150, 550, 130, 40);
        welcomeLabel.setBounds(750,10,150,20);
        cautionLabel.setBounds(mainTextX+10,320,150,20);
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
        incentiveTypeLabel.setBounds(rightLabelX,155,130,30);

        titleText.setBounds(rightTextX, 100, 150, 30);
        valueText.setBounds(rightTextX, 215, 150, 30);
        descriptionText.setBounds(rightTextX, 285, 250, 50);
        disclaimerText.setBounds(rightTextX, 380, 250, 50);
        incentiveTypeBox.setBounds(rightTextX,155,150,30);

        slashLabel.setBounds(340, 450, 10, 40);
        startDateChooser.setBounds(210,450, 125,40);
        endDateChooser.setBounds(355, 450, 125,40);

        applyButton.setBounds(215, 535, 100, 40);

    }

}
