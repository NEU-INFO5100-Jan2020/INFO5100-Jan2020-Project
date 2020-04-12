package com.neu.msis.guiforcase4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ModifyInventory extends JFrame {
	String dID;
	private Map<Integer, Vector<String>> map = new HashMap<Integer, Vector<String>>();
	public ModifyInventory(String dID) {
		this.dID = dID;
		initialFrame();
	}
	private void initialFrame() {
		JFrame frame = new JFrame("Managing Inventory of DealerI " + this.dID);
		JPanel panel = new JPanel(null);
		frame.setSize(400,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
	    addComponents(frame, panel);
	    frame.setVisible(true);
	}
	private void addComponents(JFrame frame, JPanel panel) {
	    JLabel jl = new JLabel("Dealer" + this.dID);
	    jl.setFont(new Font("Arial", Font.PLAIN,16));
	    jl.setForeground(Color.BLUE);
	    jl.setHorizontalAlignment(JTextField.CENTER);
	    jl.setBounds(160,10,80,30);
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

	    JButton btn1=new JButton("Modify");
        JButton btn2=new JButton("Back");
        Dimension preferredSize=new Dimension(120, 40);
        JButton[] jButtons = new JButton[]{btn1, btn2};
        for(int i = 0; i < jButtons.length; i++) {
        	jButtons[i].setPreferredSize(preferredSize);
        	jButtons[i].setBounds(60 + i * 160, 380, 120, 40);
        	jButtons[i].setBackground(Color.blue);
        	jButtons[i].setOpaque(true);
        	panel.add(jButtons[i]);
		}
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryInformation(dID);
                frame.dispose();
            }
        });

		JComboBox cmb1 = new JComboBox();
		cmb1.setBounds(160, 260, 160, 25);
		cmb1.addItem("White");
		cmb1.addItem("Red");
		cmb1.addItem("Gray");
		JComboBox cmb2 = new JComboBox();
		cmb2.setBounds(160, 200, 160, 25);
		cmb2.addItem("New");
		cmb2.addItem("Used");
		String[] years = new String[]{"2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010",
				"2009", "2008", "2007",};
		JComboBox cmb3 = new JComboBox();
		cmb3.setBounds(160, 170, 160, 25);
		for (int i = 0; i < years.length; i++) {
			cmb3.addItem(years[i]);
		}
		panel.add(cmb1);
		panel.add(cmb2);
		panel.add(cmb3);

		JComboBox make = new JComboBox();
		make.setBounds(160, 110, 160, 25);
		JComboBox model = new JComboBox();
		model.setBounds(160, 140, 160, 25);
		makeModel(make, model);
		panel.add(make);
		panel.add(model);
	}
	private void makeModel(JComboBox make, JComboBox model) {
		String[] makes = new String[]{"Audi", "Jaguar", "Kia", "Land Rover", "Mazda", "Volvo", "Ford", "BMW", "Jeep",
				"Tesla", "Porsche", "Acura", "Aston Martin", "Honda", "Chevrolet", "Ferrari", "Cadillac", "Infiniti",
				"Volkswagen", "Subaru", "Nissan", "Mercedes-Benz", "Toyota", "Buick"};
		Vector<String> makeItems = new Vector<String>();
		for (int i = 0; i < makes.length; i++) {
			makeItems.add(makes[i]);
		}
		//models for Audi
		Vector<String> vector0 = new Vector<String>();
        vector0.add("A4");
        vector0.add("A5");
        map.put(0, vector0);
        //models for Jaguar
		Vector<String> vector1 = new Vector<String>();
        vector1.add("FPace");
        map.put(1, vector1);
		//models for Kia
		Vector<String> vector2 = new Vector<String>();
		vector0.add("EX");
        vector0.add("Sedona");
        map.put(2, vector2);
		//models for Land Rover
		Vector<String> vector3 = new Vector<String>();
		vector0.add("Evoque");
        vector0.add("V8");
        map.put(3, vector3);
		//models for Mazda
		Vector<String> vector4 = new Vector<String>();
		vector0.add("Mazda3");
        vector0.add("CX9");
        map.put(4, vector4);
		//models for Volvo
		Vector<String> vector5 = new Vector<String>();
		vector0.add("XC40");
        vector0.add("S90");
        map.put(5, vector5);
        //models for Ford
		Vector<String> vector6 = new Vector<String>();
		vector0.add("F150");
        vector0.add("Fiesta");
        map.put(6, vector6);
		//models for BMW
		Vector<String> vector7 = new Vector<String>();
		vector0.add("X6");
        map.put(7, vector7);
		//models for Jeep
		Vector<String> vector8 = new Vector<String>();
		vector0.add("Patriot");
        map.put(8, vector8);
		//models for Tesla
		Vector<String> vector9 = new Vector<String>();
		vector0.add("Model S");
        map.put(9, vector9);
		//models for Porsche
		Vector<String> vector10 = new Vector<String>();
		vector0.add("Cayenne");
        map.put(10, vector10);
		//models for Acura
		Vector<String> vector11 = new Vector<String>();
		vector0.add("TLX");
        vector0.add("ILX");
        map.put(11, vector11);
		//models for Aston Martin
		Vector<String> vector12 = new Vector<String>();
        vector0.add("DB9");
        map.put(12, vector12);
		//models for Honda
		Vector<String> vector13 = new Vector<String>();
		vector0.add("Civic LX");
        map.put(13, vector13);
		//models for Chevrolet
		Vector<String> vector14 = new Vector<String>();
		vector0.add("Trax");
        vector0.add("Express");
        map.put(14, vector14);
		//models for Ferrari
		Vector<String> vector15 = new Vector<String>();
		vector0.add("Berlinetta");
        vector0.add("California");
        map.put(15, vector15);
		//models for Cadillac
		Vector<String> vector16 = new Vector<String>();
		vector0.add("ATS");
        map.put(16, vector16);
		//models for Infiniti
		Vector<String> vector17 = new Vector<String>();
		vector0.add("Q40");
        vector0.add("G35");
        map.put(17, vector17);
		//models for Volkswagen
		Vector<String> vector18 = new Vector<String>();
		vector0.add("Jetta");
        vector0.add("eGolf");
        map.put(18, vector18);
		//models for Subaru
		Vector<String> vector19 = new Vector<String>();
		vector0.add("Impreza");
        vector0.add("Ascent");
        map.put(19, vector19);
		//models for Nissan
		Vector<String> vector20 = new Vector<String>();
		vector0.add("Armada");
        vector0.add("Versa");
        map.put(20, vector20);
		//models for Mercedes-Benz
		Vector<String> vector21 = new Vector<String>();
		vector0.add("GLC300");
        map.put(21, vector21);
		//models for Toyota
		Vector<String> vector22 = new Vector<String>();
		vector0.add("Camry");
        vector0.add("Corolla");
        vector0.add("Highlander");
        map.put(22, vector22);
		//models for Buick
		Vector<String> vector23 = new Vector<String>();
		vector0.add("LaCrosse");
        map.put(23, vector23);
		}


	}


