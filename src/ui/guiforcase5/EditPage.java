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



    private JLabel mainTitle, vehicleIDLabel, makeLabel, welcomeLabel;
    private JCheckBox newVehicleButton, usedVehicleButton;
    private JButton applyButton;

    private JLabel rightTitle, titleLabel, valueLabel, descriptionLabel, disclaimerLabel, dateLabel, slashLabel, incenitveTypeLabel;
    private JComboBox incentiveTypeBox;
    private JTextField titleText, valueText;
    private JTextArea descriptionText, disclaimerText;
    private JDateChooser startDateChooser, endDateChooser;

    private JLabel vinLabel, minLabel, maxLabel, makeMakeLabel, lowLabel, highLabel;
    private int dealerID;
    private String dealerName;
    public Incentives incentives;

    private IncentiveMainPage parentPage;

    Font button = new Font("Helvetica", Font.BOLD, 21);



    protected EditPage(int dealerID, String dealerName, Incentives incentives, IncentiveMainPage parentPage) {
        this.parentPage = parentPage;
        this.dealerID=dealerID;
        this.dealerName = dealerName;
        this.incentives=incentives;
        createComponents(dealerID);
        placeComponents();
        addComponents();
        addListeners();
        displayFilterList();
        jframe.setLocation(1200,0);
        jframe.setVisible(true);

    }

    private void displayFilterList(){
        String filterList = this.incentives.getFilterList();
        String[] filterArray = filterList.split(" ");
        if (! filterArray[0].equals("null")) vinLabel.setText(filterArray[0]);
        if (! filterArray[1].equals("null")) minLabel.setText(filterArray[1]);
        if (! filterArray[2].equals("null")) maxLabel.setText(filterArray[2]);
        if (! filterArray[3].equals("Default")) makeMakeLabel.setText(filterArray[3]);
        newVehicleButton.setSelected(filterArray[4].equals("true"));
        usedVehicleButton.setSelected(filterArray[5].equals("true"));
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
                parentPage.refreshTableContents();
                jframe.dispose();
            }
        });
    }

    private void createComponents(int dealerID) {
        jframe = new JFrame("Incentive Management");
        jframe.setLayout(null);

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
        Font mainTitleFont = new Font("Helvetica", Font.BOLD, 27);
        mainTitle.setFont(mainTitleFont);
        Font mainCommonFont = new Font("Helvetica", Font.PLAIN, 17);
        Font mainBoldFont = new Font("Helvetica", Font.BOLD,17);
        vehicleIDLabel = new JLabel("VIN: ");
        vehicleIDLabel.setFont(mainBoldFont);
        vinLabel = new JLabel("");
        vinLabel.setFont(mainCommonFont);
        lowLabel = new JLabel("Min:");
        lowLabel.setFont(mainBoldFont);
        highLabel = new JLabel("Max:");
        highLabel.setFont(mainBoldFont);
        minLabel = new JLabel("");
        minLabel.setFont(mainCommonFont);
        maxLabel = new JLabel("");
        maxLabel.setFont(mainCommonFont);

        makeLabel = new JLabel("Make: ");
        makeMakeLabel = new JLabel("");
        makeLabel.setFont(mainBoldFont);
        makeMakeLabel.setFont(mainCommonFont);

        welcomeLabel = new JLabel("Welcome, " + dealerName);
        welcomeLabel.setFont(mainCommonFont);

        newVehicleButton = new JCheckBox("New Vehicles");
        newVehicleButton.setFont(mainBoldFont);
        newVehicleButton.setEnabled(false);
        usedVehicleButton = new JCheckBox("Used Vehicles");
        usedVehicleButton.setFont(mainBoldFont);
        usedVehicleButton.setEnabled(false);
    }


    private void createRightsComponent() {
        rightTitle = new JLabel(
                "<html><body><p align=\"center\">Edit Incentive Details for A Certain Vehicle<br>Or Group of Vehicles</p><body</html>");

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
        titleText = new JTextField(37);
        titleText.setText(incentives.getTitle());
        valueText = new JTextField(37);
        valueText.setText(String.valueOf(incentives.getDiscountValue()));
        descriptionText = new JTextArea(1, 6);
        disclaimerText = new JTextArea(1, 6);

        slashLabel = new JLabel("-");
        startDateChooser = new JDateChooser();
        startDateChooser.setDate(incentives.getStartDate());
        endDateChooser = new JDateChooser();
        endDateChooser.setDate(incentives.getEndDate());

        applyButton = new JButton("Edit");
        applyButton.setFont(button);
        incenitveTypeLabel = new JLabel("IncentiveType");
        incenitveTypeLabel.setFont(rightCommonFont);
        incentiveTypeBox = new JComboBox();
        incentiveTypeBox.setFont(rightCommonFont);
        incentiveTypeBox.addItem("Cash Back");
        incentiveTypeBox.addItem("Percentage");
    }

    private void addComponents() {
        mainPanel.setBounds(20, 20, 550, 660);
        jframe.add(mainPanel);
        rightPanel.setBounds(20, 200, 510, 440);
        mainPanel.add(rightPanel, null);
        addMainPanel();
        addRightPanel();
    }

    private void addMainPanel() {
        mainPanel.add(mainTitle);
        mainPanel.add(vehicleIDLabel);
        mainPanel.add(lowLabel);
        mainPanel.add(highLabel);
        mainPanel.add(usedVehicleButton);
        mainPanel.add(newVehicleButton);

        mainPanel.add(makeLabel);
        mainPanel.add(welcomeLabel);
        mainPanel.add(vinLabel);
        mainPanel.add(minLabel);
        mainPanel.add(maxLabel);
        mainPanel.add(makeMakeLabel);
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
        jframe.setSize(590, 720);
        placeMainComponents();
        placeRightComponents();
    }

    private void placeMainComponents() {
        int mainLabelX = 40;
        int filterX = 100;
        mainTitle.setBounds(150, 40, 250, 30);
        welcomeLabel.setBounds(300,10,250,20);

        vehicleIDLabel.setBounds(mainLabelX, 85, 60, 20);
        vinLabel.setBounds(filterX,85,150,20);

        lowLabel.setBounds(mainLabelX,110,60,20);
        minLabel.setBounds(filterX,110,100,20);
        highLabel.setBounds(200,110,60,20);
        maxLabel.setBounds(260,110,150,20);

        makeLabel.setBounds(mainLabelX,135,60,20);
        makeMakeLabel.setBounds(filterX,135,150,20);
        newVehicleButton.setBounds(mainLabelX, 160, 150, 20);
        usedVehicleButton.setBounds(200,160,200,20);
    }

    private void placeRightComponents() {
        int rightLabelX = 25;
        int rightTextX = 150;
        rightTitle.setBounds(65, 25, 500, 40);

        titleLabel.setBounds(rightLabelX, 80, 130, 30);
        incenitveTypeLabel.setBounds(rightLabelX,120,130,30);
        valueLabel.setBounds(rightLabelX, 160, 130, 30);
        descriptionLabel.setBounds(rightLabelX, 200, 130, 50);
        disclaimerLabel.setBounds(rightLabelX, 260, 130, 50);
        dateLabel.setBounds(rightLabelX, 320, 175, 40);


        titleText.setBounds(rightTextX, 80, 150, 30);
        incentiveTypeBox.setBounds(rightTextX,120,150,30);
        valueText.setBounds(rightTextX, 160, 150, 30);
        descriptionText.setBounds(rightTextX, 200, 250, 50);
        descriptionText.setText(incentives.getDescription());
        disclaimerText.setBounds(rightTextX, 260, 250, 50);
        disclaimerText.setText(incentives.getDisclaimer());


        slashLabel.setBounds(340, 320, 10, 40);
        startDateChooser.setBounds(210,320, 125,40);
        endDateChooser.setBounds(355, 320, 125,40);

        applyButton.setBounds(215, 370, 100, 40);
    }

    public void disposePage() {
        jframe.dispose();
    }
}
