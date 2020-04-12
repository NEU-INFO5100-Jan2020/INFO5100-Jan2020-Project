package com.neu.msis.guiforcase4;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DealerAuthentication extends JFrame {

    public DealerAuthentication() {
        JFrame jf = new JFrame("Dealer Authentication");
        jf.setSize(320, 160);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        jf.add(panel);
        placeComponents(jf, panel);
        jf.setVisible(true);
    }

    private void placeComponents(JFrame jf, JPanel panel) {
        panel.setLayout(null);
        JLabel userLabel = new JLabel("Dealer Id:");
        JLabel message = new JLabel();
        message.setText("Please Enter A Valid User Id!");
        message.setForeground(Color.RED);
        userLabel.setBounds(10, 20, 80, 25);
        message.setBounds(60, 100, 200, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(50, 70, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dealerID = userText.getText();
                if(dealerID.equals("") ) {
                    panel.add(message);
                }else {
                    new OperationOptions(dealerID);
                    jf.dispose();
                }
            }
        });

    }
}
