package ui.guiforcase4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddVehicles extends JFrame {
	String dID;
	public AddVehicles(String dID) {
		this.dID = dID;
		initialFrame();
	}
	private void initialFrame() {
		JFrame frame = new JFrame("Managing Inventory of Dealer " + this.dID);
		JPanel panel = new JPanel(null);
		frame.setSize(400, 480);
		frame.add(panel);
		addComponents(frame, panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void addComponents(JFrame frame, JPanel panel) {
		JLabel jl = new JLabel("Dealer " + this.dID);
		jl.setFont(new Font("Arial", Font.PLAIN, 16));
		jl.setForeground(Color.BLUE);
		jl.setHorizontalAlignment(JTextField.CENTER);
		jl.setBounds(160, 10, 80, 30);
		panel.add(jl);
		String[] jLabelTexts = new String[]{"VehicleId:", "VIN:", "Make:", "Model:", "Year:", "Category:", "Price:",
		"Color:", "Miles:", "Image:"};
	    JLabel[] jls = new JLabel[10];
	    for(int i = 0; i < jls.length; i++) {
	    	jls[i] = new JLabel();
			jls[i].setText(jLabelTexts[i]);
	    	jls[i].setBounds(60,50 + i * 30,80,25);
	    	panel.add(jls[i]);
		}

		JTextField tf1 = new JTextField(10);
		tf1.setBounds(160, 50, 160, 25);
		JTextField tf2 = new JTextField(10);
		tf2.setBounds(160, 80, 160, 25);
		JTextField tf3 = new JTextField(10);
		tf3.setBounds(160, 230, 160, 25);
		JTextField tf4 = new JTextField(10);
		tf4.setBounds(160, 290, 160, 25);
		JTextField tf5 = new JTextField(10);
		tf5.setBounds(160, 320, 160, 25);
		JTextField[] jtfs = new JTextField[]{tf1, tf2, tf3, tf4, tf5};
	    for(int i = 0; i < jtfs.length; i++) {
	    	panel.add(jtfs[i]);
		}

		JButton btn1 = new JButton("Add");
		btn1.setBounds(60, 380, 120, 40);
		JButton btn2 = new JButton("Back");
		btn2.setBounds(220, 380, 120, 40);
		Dimension preferredSize = new Dimension(120, 40);
		JButton[] jButtons = new JButton[]{btn1, btn2};
        for(int i = 0; i < jButtons.length; i++) {
        	jButtons[i].setPreferredSize(preferredSize);
        	jButtons[i].setBackground(Color.blue);
        	jButtons[i].setOpaque(true);
        	panel.add(jButtons[i]);
		}

		JComboBox cmb1 = new JComboBox();
		cmb1.setBounds(160, 260, 160, 25);
		cmb1.addItem("White");
		cmb1.addItem("Red");
		cmb1.addItem("Gray");
		JComboBox cmb2 = new JComboBox();
		cmb2.setBounds(160, 200, 160, 25);
		cmb2.addItem("New");
		cmb2.addItem("Used");
		String[] makes = new String[]{"Audi", "Jaguar", "Kia", "Land Rover", "Mazda", "Volvo", "Ford", "BMW", "Jeep",
				"Tesla", "Porsche", "Acura", "Aston Martin", "Honda", "Chevrolet", "Ferrari", "Cadillac", "Infiniti",
				"Volkswagen", "Subaru", "Nissan", "Mercedes-Benz", "Toyota", "Buick"};
		JComboBox cmb3 = new JComboBox();
		cmb3.setBounds(160, 110, 160, 25);
		for (int i = 0; i < makes.length; i++) {
			cmb3.addItem(makes[i]);
		}
		String[] years = new String[]{"2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010",
				"2009", "2008", "2007",};
		JComboBox cmb4 = new JComboBox();
		cmb4.setBounds(160, 170, 160, 25);
		for (int i = 0; i < years.length; i++) {
			cmb4.addItem(years[i]);
		}
		panel.add(cmb1);
		panel.add(cmb2);
		panel.add(cmb3);
		panel.add(cmb4);

		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new InventoryInformation(dID);
				frame.dispose();
			}
		});
	}

}



