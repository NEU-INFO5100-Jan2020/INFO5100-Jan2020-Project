package ui.guiforcase5;

import dto.BigDataType;
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
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.toedter.calendar.JDateChooser;

//        import lombok.Data;
//@Data
public class CreatePage {
    private JFrame jframe;
    private JPanel mainPanel, rightPanel;
    private JButton createButton;

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

    private int dealerID;
    private static int[] vehicleIDList;

    Font botton = new Font("Helvetica", Font.BOLD, 21);

    public CreatePage(int dealerID) {
        setDealerID(dealerID);
        createComponents(dealerID);
        placeComponents();
        addComponents();
        addListeners();
        jframe.setLocation(30,370);
        jframe.setVisible(true);


    }
    private void setDealerID(int dealerID) {
        this.dealerID = dealerID;
    }

    private void addListeners() {
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Incentives incentive=new Incentives();
                vehicleIDList = setSearchFilter();
                System.out.println(vehicleIDList.length);
                if (vehicleIDList.length == 0) {
                    JOptionPane.showMessageDialog(jframe, "There is no vehicle meeting your requirements.");
                } else {
                    IncentivesManagerImpl incentivesManagerImpl=new IncentivesManagerImpl();
                    IncentiveMainPage incentiveMainPage=new IncentiveMainPage(dealerID);


                    try {
                        setIncentiveApplyData(incentive);

                        incentivesManagerImpl.addIncentive2(incentive, vehicleIDList);
                    } catch (NullPointerException eEmpty) {
                        JOptionPane.showMessageDialog(jframe, "Please enter All The Details.");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    finally {
//                    CreatePage.dispose();
                        incentiveMainPage.setVisible(true);

                        incentiveMainPage.refreshTableContents();
                    }
                }

            }
        });
    }

    private int[] setSearchFilter() {
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
        return s.getArrayOfVehicleID();

    }


    // apply button
    private void setIncentiveApplyData(Incentives incentive)  {
        try {
            incentive.setDiscountValue(Integer.parseInt(valueText.getText()));
            incentive.setTitle(titleText.getText());
            incentive.setDiscountType(String.valueOf(incentiveTypeBox.getSelectedItem()));
            incentive.setDescription(descriptionText.getText());
            incentive.setDisclaimer(disclaimerText.getText());
            incentive.setStartDate(startDateChooser.getDate());
            incentive.setEndDate(endDateChooser.getDate());
            incentive.setDealerId(dealerID);
            incentive.setIsDeleted(false);
            String filterList = convertFilterListToString();
            incentive.setFilterList(filterList);
            incentive.setVehicleIdList("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(jframe, "Please Enter Value in Integer");
        }
    }
//    private String convertFilterListToString() {
//        String[] arr = new String[6];
//
//        try {
//            arr[0] = vehicleIDText.getText();
//        } catch (NullPointerException e) {
//            arr[0] = "null";
//        }
//        try {
//            arr[1] = minimumInt.getText();
//        } catch (NullPointerException e) {
//            arr[1] = "null";
//        }
//        try {
//            arr[2] = maximumInt.getText();
//        } catch (NullPointerException e) {
//            arr[2] = "null";
//        }
//        arr[3] = Objects.requireNonNull(makeCombobox.getSelectedItem()).toString();
//        if (newVehicleButton.isSelected()) {
//            arr[4] = "true";
//        } else {
//            arr[4] = "false";
//        }
//        if (usedVehicleButton.isSelected()) {
//            arr[5] = "true";
//        } else {
//            arr[5] = "false";
//        }
//        return String.join(" ", arr);
//    }
    private String convertFilterListToString() {
        String[] arr = new String[6];
        if (newVehicleButton.isSelected()) {
            if (vehicleIDText.getText().equals("")) {
                arr[0] = "null";
            }else {
                arr[0] = vehicleIDText.getText();
            }
            arr[1] = "null";
            arr[2] = "null";
            arr[3] = "Default";
            arr[4] = "false";
            arr[5] = "false";
        }
        if (groupRadioButton.isSelected()) {
            arr[0] = "null";
            if (minimumInt.getText().equals("")) {
                arr[1] = "null";
            }else {
                arr[1] = minimumInt.getText();
            }
            if (maximumInt.getText().equals("")) {
                arr[2] = "null";
            }else {
                arr[2] = maximumInt.getText();
            }
            arr[3] = Objects.requireNonNull(makeCombobox.getSelectedItem()).toString();
            if (newVehicleButton.isSelected()) {
                arr[4] = "true";
            } else {
                arr[4] = "false";
            }
            if (usedVehicleButton.isSelected()) {
                arr[5] = "true";
            } else {
                arr[5] = "false";
            }
        }
        return String.join(" ", arr);
    }

    private void createComponents(int dealerID) {
        jframe = new JFrame("Incentive Management");
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
        Font mainTitleFont = new Font("Helvetica", Font.BOLD, 27);
        mainTitle.setFont(mainTitleFont);
        Font mainCommonFont = new Font("Helvetica", Font.PLAIN, 17);
        vehicleIDLabel = new JLabel("VIN");
        vehicleIDLabel.setFont(mainCommonFont);
        selectPriceLabel = new JLabel("<html><body><p>Price Range</p><body></html>");
        selectPriceLabel.setFont(mainCommonFont);

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
        Font cautionFont = new Font("Helvetica", Font.PLAIN,12);
        cautionLabel.setFont(cautionFont);

        makeCombobox = new JComboBox(makelist);
        makeCombobox.setFont(mainCommonFont);

        Font radioFont = new Font("Helvetica",Font.BOLD,20);
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

        createButton = new JButton("Create");
        createButton.setFont(botton);
    }


    private void createRightsComponent() {
        rightTitle = new JLabel(
                "<html><body><p align=\"center\">Add Incentive Details for A Certain Vehicle<br>Or Group of Vehicles</p><body</html>");

        Font rightTitleFont = new Font("Helvetica", Font.BOLD, 17);
        rightTitle.setFont(rightTitleFont);
        Font rightCommonFont = new Font("Helvetica", Font.PLAIN, 15);
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


        incentiveTypeLabel = new JLabel("IncentiveType");
        incentiveTypeLabel.setFont(rightCommonFont);
        incentiveTypeBox = new JComboBox();
        incentiveTypeBox.setFont(rightCommonFont);
        incentiveTypeBox.addItem("Cash Back");
        incentiveTypeBox.addItem("Percentage");
    }

    private void addComponents() {
        mainPanel.setBounds(20, 20, 960, 635);
        jframe.add(mainPanel);
        rightPanel.setBounds(420, 90, 510, 515);
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

        mainPanel.add(makeLabel);
        mainPanel.add(welcomeLabel);
        mainPanel.add(cautionLabel);
        mainPanel.add(usedVehicleButton);
        mainPanel.add(oneRadioButton);
        mainPanel.add(groupRadioButton);
        mainPanel.add(createButton);
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

        rightPanel.add(incentiveTypeLabel);
        rightPanel.add(incentiveTypeBox);
        ButtonGroup group = new ButtonGroup();
        group.add(oneRadioButton);
        group.add(groupRadioButton);
    }

    private void placeComponents() {
        jframe.setSize(1000, 695);
        placeMainComponents();
        placeRightComponents();
    }

    private void placeMainComponents() {
        int mainLabelX = 50;
        int mainTextX = 180;
        mainTitle.setBounds(325, 40, 800, 40);
        welcomeLabel.setBounds(750,10,150,20);

        oneRadioButton.setBounds(20, 130,300,40);
        vehicleIDLabel.setBounds(mainLabelX, 180, 150, 40);
        vehicleIDText.setBounds(mainTextX, 180, 175, 40);

        groupRadioButton.setBounds(20, 250,300,40);
        selectPriceLabel.setBounds(mainLabelX, 300, 150, 40);
        minimumInt.setBounds(mainTextX,300,75,40);
        maximumInt.setBounds(mainTextX+100,300,75,40);
        cautionLabel.setBounds(mainTextX+10,340,150,20);

        makeLabel.setBounds(mainLabelX,370,150,40);
        makeCombobox.setBounds(mainTextX,370,175,40);

        newVehicleButton.setBounds(mainLabelX, 440, 170, 40);
        usedVehicleButton.setBounds(220 ,440,170,40);

        createButton.setBounds(150, 510, 130, 40);



    }

    private void placeRightComponents() {
        int rightLabelX = 25;
        int rightTextX = 150;
        rightTitle.setBounds(65, 25, 500, 40);
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



    }
}
