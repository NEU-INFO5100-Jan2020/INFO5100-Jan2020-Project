package com.neu.msis.guiforcase4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationOptions extends JFrame {
    String dID;
    public OperationOptions(String dID) {
        this.dID = dID;
        initialFrame();
    }
    private void initialFrame() {
        JFrame frame = new JFrame("Dealer's Account");
        JPanel panel = new JPanel(null);
        frame.setSize(400, 280);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponents(panel);
    }
    private void addComponents(JPanel panel) {
        JLabel jl = new JLabel("Dealer " + this.dID + "'s Account");
        jl.setFont(new Font("Arial", Font.PLAIN, 16));
        jl.setForeground(Color.BLUE);
        jl.setHorizontalAlignment(JTextField.CENTER);
        jl.setBounds(50, 10, 280, 30);
        panel.add(jl);

        JButton btn2 = new JButton("View Inventory");
        btn2.setBounds(130, 80, 120, 40);
        JButton btn3 = new JButton("Create Incentives");
        btn3.setBounds(130, 160, 120, 40);
        Dimension preferredSize = new Dimension(120, 40);
        btn2.setPreferredSize(preferredSize);
        btn2.setBackground(Color.blue);
        btn2.setOpaque(true);
        btn3.setPreferredSize(preferredSize);
        btn3.setBackground(Color.blue);
        btn3.setOpaque(true);
        panel.add(btn2);
        panel.add(btn3);

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryInformation(dID);
            }
        });
    }
}




