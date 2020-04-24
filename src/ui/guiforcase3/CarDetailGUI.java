package ui.guiforcase3;

import dto.*;
import service.*;
import persist.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class CarDetailGUI extends JPanel {
    Collection<Vehicle> vehicleResult;
    int rating;

    public JPanel panelCarDetails(Collection<Vehicle> vehicleResult){
        JPanel panel = new CarDetailPanel(vehicleResult);
        panel.setBackground(Color.GRAY);
        panel.getPreferredSize();

        return panel;
    }

    public JPanel panelBackButton(JFrame frame){
        JPanel panel = new JPanel();
        JButton button = new JButton("Back");
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
            }
        });

        return panel;
    }

    public JPanel panelDiscountDetails(JFrame frame){
        JPanel panel = new JPanel();
        JButton button = new JButton("Click here For more Discount Details");
        panel.add(button);
        button.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent ae) {
            	//frame.dispose();
            	Vehicle v = (Vehicle) vehicleResult.toArray()[0];
            	IncentiveScreen incs=new IncentiveScreen(v.getVehicleId());   	
            	
            }
            });          	
       return panel;    
	}

    public JPanel panelStarRating(JFrame frame) {
        JPanel panel = new JPanel();

        for(Vehicle v :vehicleResult){
            rating = v.getRatings();
        }

        StarRater starRater = new StarRater(5, rating, 0);
        starRater.addStarListener(
                new StarRater.StarListener() {
                    public void handleSelection(int selection) {
                        System.out.println(selection);
                    }
                });

        panel.setOpaque(false);
        panel.add(starRater);

        return panel;
    }


    public CarDetailGUI(Collection<Vehicle> vehicleResult){
        this.vehicleResult = vehicleResult;

        JFrame frame= new JFrame("Car Details Page");

        JPanel panelCarDetails = panelCarDetails(vehicleResult);
        JPanel panelBackButton = panelBackButton(frame);
        JPanel panelDiscountDetails = panelDiscountDetails(frame);
        JPanel ratingPanel = panelStarRating(frame);

        frame.getContentPane().add(panelBackButton, BorderLayout.PAGE_END);
        frame.getContentPane().add(panelCarDetails, BorderLayout.PAGE_START);
        frame.getContentPane().add(panelDiscountDetails, BorderLayout.LINE_END);
        frame.getContentPane().add(ratingPanel, BorderLayout.LINE_START);

        frame.setSize(CarDetailPanel.width, CarDetailPanel.height+120);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
