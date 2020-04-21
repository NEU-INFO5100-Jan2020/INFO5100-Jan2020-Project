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
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addComponents(panel);
    }

    private void addComponents(JPanel panel) {
        JLabel jl = new JLabel("Dealer " + this.dID + "'s Account");
        jl.setFont(new Font("Arial", Font.PLAIN, 18));
        jl.setForeground(Color.BLUE);
        jl.setHorizontalAlignment(JTextField.CENTER);
        jl.setBounds(50, 10, 280, 30);
        panel.add(jl);

        JButton btn2 = new JButton("View Inventory");
        btn2.setBounds(110, 80, 160, 40);
        JButton btn3 = new JButton("Manage Incentives");
        btn3.setBounds(110, 160, 160, 40);
        Dimension preferredSize = new Dimension(120, 40);
        btn2.setPreferredSize(preferredSize);
        btn2.setBackground(Color.blue);
        btn2.setOpaque(true);
        btn2.setFont(new Font("Arial", Font.PLAIN, 15));
        btn3.setPreferredSize(preferredSize);
        btn3.setBackground(Color.blue);
        btn3.setFont(new Font("Arial", Font.PLAIN, 15));
        btn3.setOpaque(true);
        panel.add(btn2);
        panel.add(btn3);

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryInformation(dID);
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IncentiveMainPage(dID).setVisible(true);
            }
        });
    }
}




