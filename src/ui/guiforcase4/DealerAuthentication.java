package ui.guiforcase4;

import service.DealerUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class DealerAuthentication extends JFrame {
    DealerUtilities du = new DealerUtilities();
    public DealerAuthentication() {
        JFrame jf = new JFrame("Dealer Authentication");
        jf.setSize(320, 160);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        jf.add(panel);
        placeComponents(jf, panel);
        jf.setVisible(true);
    }

    private void placeComponents(JFrame jf, JPanel panel) {
        panel.setLayout(null);
        JLabel userLabel = new JLabel("Dealer Id:");
        JLabel message = new JLabel(" ");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        userLabel.setBounds(40, 20, 80, 25);
        panel.add(userLabel);
        panel.add(message);

        JTextField userText = new JTextField(20);
        userText.setBounds(120, 20, 160, 25);
        userText.setFont(new Font("Arial", Font.PLAIN, 15));
        CheckInput c1 = new CheckInput();
        c1.setLength(10);
        userText.setDocument(c1);
        userText.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if (Character.isLetter(e.getKeyChar())) {
                    JOptionPane.showMessageDialog(null, "Please Enter A Valid Number For Id!");
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        });
        panel.add(userText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(80, 70, 160, 25);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 15));
        loginButton.setBackground(Color.WHITE);
        loginButton.setOpaque(true);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userText.getText().equals("")) {
                    loginButton.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Please Enter A Valid Id!");
                    loginButton.setEnabled(true);
                } else {
                    try {
                        if(du.validateDealerID(Integer.parseInt(userText.getText())) == false) {
                            JOptionPane.showMessageDialog(null, "The Id You Entered Does Not " +
                                "Exist! \nPlease Enter A Valid Id!");
                        } else{
                            int dealerID = Integer.parseInt(userText.getText());
                            new OperationOptions(dealerID);
                            jf.dispose();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });


    }
}
