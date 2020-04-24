package ui.guiforcase4;

import ui.guiforcase5.IncentiveMainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationOptions extends JFrame {
    int dID;
    public OperationOptions(int dID) {
        this.dID = dID;
        initialFrame();
    }

    private void initialFrame() {
        JFrame frame = new JFrame("Dealer's Account");
        JPanel panel = new JPanel(null);
        frame.setSize(400, 280);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addComponents(frame, panel);
    }

    private void addComponents(JFrame frame, JPanel panel) {
        JLabel jl = new JLabel("Dealer " + this.dID + "'s Account");
        jl.setFont(new Font("Arial", Font.PLAIN, 18));
        jl.setForeground(Color.BLACK);
        jl.setHorizontalAlignment(JTextField.CENTER);
        jl.setBounds(50, 20, 280, 30);
        panel.add(jl);
        JButton btn1 = new JButton("log out");
        btn1.setBounds(325,5,70,20);
        btn1.setFont(new Font("Arial", Font.PLAIN, 15));
        JButton btn2 = new JButton("View Inventory");
        btn2.setBounds(110, 80, 160, 40);
        JButton btn3 = new JButton("Manage Incentives");
        btn3.setBounds(110, 160, 160, 40);
        Dimension preferredSize = new Dimension(120, 40);
        btn2.setPreferredSize(preferredSize);
        btn2.setBackground(Color.WHITE);
        btn2.setOpaque(true);
        btn2.setFont(new Font("Arial", Font.PLAIN, 15));
        btn3.setPreferredSize(preferredSize);
        btn3.setBackground(Color.WHITE);
        btn3.setFont(new Font("Arial", Font.PLAIN, 15));
        btn3.setOpaque(true);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Log Out？",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    new DealerAuthentication();
                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryInformation(dID);
                frame.dispose();
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IncentiveMainPage(dID).setVisible(true);
                frame.dispose();
            }
        });
    }
}




