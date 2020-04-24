package ui.guiforcase4;

import dto.Dealer;
import persist.DealerManagerImpl;
import service.DealerUtilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DealerAuthentication extends JFrame {
    DealerUtilities du = new DealerUtilities();
    DealerManagerImpl dml = new DealerManagerImpl();
    JFrame jf;
    JLabel message;
    JTextField userText;
    JButton loginButton;
    private String dealerIdPattern;
    public DealerAuthentication() {
        jf = new JFrame("Dealer Authentication");
        jf.setSize(400, 160);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        jf.add(panel);
        placeComponents(panel);
        jf.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        JLabel userLabel = new JLabel("Dealer Id:");
        message = new JLabel("Please Enter A Valid Number Id!");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        userLabel.setBounds(40, 20, 80, 25);
        message.setFont(new Font("Arial", Font.PLAIN, 10));
        message.setBounds(125,40,280,25);
        message.setForeground(jf.getBackground());
        panel.add(userLabel);
        panel.add(message);

        userText = new JTextField(20);
        userText.setBounds(120, 20, 160, 25);
        userText.setFont(new Font("Arial", Font.PLAIN, 15));
        dealerIdPattern = "\\d+$";
        userText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                dealerIdInput(userText.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                dealerIdInput(userText.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
            }
        });
        panel.add(userText);

        loginButton = new JButton("login");
        loginButton.setBounds(80, 80, 160, 25);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 15));
        loginButton.setBackground(Color.WHITE);
        loginButton.setOpaque(true);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userText.getText().equals("")) {
                    message.setForeground(Color.RED);
                    userText.addKeyListener(new KeyListener() {
                        public void keyTyped(KeyEvent e) {
                            if (Character.isDigit(e.getKeyChar())) {
                                message.setForeground(jf.getBackground());
                            }
                        }

                        public void keyPressed(KeyEvent e) {
                        }

                        public void keyReleased(KeyEvent e) {
                        }
        });
                } else {
                    try {
                        if (du.validateDealerID(Integer.parseInt(userText.getText())) == false) {
                            JOptionPane.showMessageDialog(null, "The Id You Entered Does Not " +
                                "Exist! \nPlease Enter A Valid Id!");
                        } else {
                            Dealer dealer = dml.getDealer(Integer.parseInt(userText.getText()));
                            String dealerName = dealer.getDealerName();
                            int dealerID = Integer.parseInt(userText.getText());
                            new OperationOptions(dealerID, dealerName);
                            jf.dispose();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });

    }
    private boolean dealerIdInput(String input) {
        String text = input;
        Pattern r = Pattern.compile(dealerIdPattern);
        Matcher m = r.matcher(text);
        if (m.matches()) {
            message.setForeground(jf.getBackground());
            loginButton.setEnabled(true);
            return true;
        } else {
            message.setForeground(Color.RED);
            loginButton.setEnabled(false);
            return false;
        }
    }
}
