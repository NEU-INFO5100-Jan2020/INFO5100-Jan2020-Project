package ui.UseCase1ListOfDealer;

import javax.swing.*;
import java.awt.*;
import dto.Dealer;

class NewTextFrame extends JFrame
{
    private JFrame frame;
    private JTextField textFieldDealerName;
    public NewTextFrame(dto.Dealer dealer) {
        frame = new JFrame();
        frame.setTitle("Automotive Dealers Website");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Enter Dealers Name: ");
        lblName.setBounds(10, 50, 200, 14);
        lblName.setForeground(Color.BLACK);
        frame.add(lblName);

        textFieldDealerName = new JTextField();
        textFieldDealerName.setBounds(10, 80, 200, 20);
        textFieldDealerName.setColumns(10);
        textFieldDealerName.setText(dealer.getDealerName());
        frame.add(textFieldDealerName);

         frame.setVisible(true);
         frame.setSize(1000,500);


    }
}
