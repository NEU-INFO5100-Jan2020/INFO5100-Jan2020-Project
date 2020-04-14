package ui.guiforcase4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryInformation extends JFrame {
    int dID;

    public InventoryInformation(int dID) {
        this.dID = dID;
        initialFrame();
    }

    private void initialFrame() {
        JFrame frame = new JFrame("Inventory of Dealer" + this.dID);
        frame.setSize(400, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(null);
        frame.add(panel);
        addComponents(frame, panel);
        frame.setVisible(true);
    }

    private void addComponents(JFrame frame, JPanel panel) {
        JLabel jl = new JLabel("Inventory of Dealer" + this.dID);
        jl.setFont(new Font("Arial", Font.PLAIN, 20));
        jl.setForeground(Color.BLUE);
        jl.setHorizontalAlignment(JTextField.CENTER);
        jl.setBounds(55, 10, 280, 30);
        panel.add(jl);

        String[] items= new String[]{"V1", "V2", "V3", "V4"};
        JList list_jp_vList = new JList<>(items);
        list_jp_vList.setBounds(80,60,240,220);
        list_jp_vList.setBackground(Color.LIGHT_GRAY);
        list_jp_vList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_jp_vList.setFont(new Font("Arial", Font.PLAIN, 15));
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        list_jp_vList.setCellRenderer(renderer);
        panel.add(list_jp_vList);

        JButton btn1 = new JButton("Modify");
        btn1.setBounds(50, 310, 120, 40);
        JButton btn2 = new JButton("Delete");
        btn2.setBounds(230, 310, 120, 40);
        JButton btn3 = new JButton("Add Vehicles");
        btn3.setBounds(50, 380, 300, 40);
        JButton[] jButtons = new JButton[]{btn1, btn2, btn3};
        Dimension preferredSize = new Dimension(120, 40);
        for (JButton jButton : jButtons) {
            jButton.setPreferredSize(preferredSize);
            jButton.setBackground(Color.blue);
            jButton.setOpaque(true);
            jButton.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(jButton);
        }

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifyInventory(dID);
                frame.dispose();
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddVehicles(dID);
                frame.dispose();
            }
        });
    }
}
