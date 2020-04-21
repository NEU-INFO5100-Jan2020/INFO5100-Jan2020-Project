package ui.guiforcase4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DealerAuthentication extends JFrame {

    public DealerAuthentication() {
        JFrame jf = new JFrame("Dealer Authentication");
        jf.setSize(320, 160);
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
        message.setFont(new Font("Arial", Font.PLAIN, 15));
        message.setForeground(Color.RED);
        userLabel.setBounds(10, 20, 80, 25);
        message.setBounds(60, 100, 200, 25);
        panel.add(userLabel);
        panel.add(message);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
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
        loginButton.setBounds(50, 70, 100, 25);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userText.getText().equals("")) {
                    loginButton.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Please Enter A Valid Id!");
                    loginButton.setEnabled(true);
                }
                else{
                    int dealerID = Integer.parseInt(userText.getText());
                    new OperationOptions(dealerID);
                    jf.dispose();
                }

            }
        });


    }
}
