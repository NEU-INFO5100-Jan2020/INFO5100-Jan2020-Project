package ui.guiforcase3;

import dto.*;
import persist.IncentivesManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.Collection;
import java.net.URL;


public class CarDetailPanel extends JPanel {
    float discount = 0;
    Collection<Vehicle> vehicleResult;
    public static final int width = 600;
    public static final int height = 600;
    public static final int x = 20;
    public static final int y = 330;
    public static final int dis = 40;
    public static final int dis_info = 120;
    private Image img;
    private String carMake = "BMW";
    private String imgFilename;

    // constructor
    public CarDetailPanel(Collection<Vehicle> vehicleResult){
        this.vehicleResult = vehicleResult;
    }

    // detail panel size
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }

    // PANEL 1 details + image
    public void paint(Graphics g){

        Vehicle v = (Vehicle) vehicleResult.toArray()[0];
        IncentivesManagerImpl incImpl = new IncentivesManagerImpl();
        Collection<Incentives> result = incImpl.checkIncentives(v.getVehicleId());

        if(result.size()>0){
        for (Incentives inc : result){
            discount = inc.getDiscountedPrice();
        }
        }else{
            discount=v.getPrice();
        }


        // Draw Details
        g.setColor(Color.gray);
        g.setFont(new Font("default", Font.ITALIC,16));
        g.drawString("MAKE", x*2,y + dis*2);
        g.drawString("DISCOUNTED PRICE" , x*7, y + dis);
        g.drawString("MODEL", x*2, y + dis*3);
        g.drawString("CATEGORY", x*2, y + dis*4);
        g.drawString("COLOR", x*2, y + dis*5);

        g.drawString("PRICE" , width/2, y + dis*2);
        g.drawString("YEAR", width/2, y + dis*3);
        g.drawString("MILEAGE", width/2, y + dis*4);
        g.drawString("VIN", width/2, y + dis*5);


        g.setColor(Color.darkGray);
        g.setFont(new Font("default", Font.BOLD,14));
        g.drawString(String.valueOf(v.getMake()),x*5 + dis_info, y + dis*2);
        g.drawString(String.format("$%,.2f", discount), x*10 + dis_info, y + dis);
        g.drawString(v.getModel(), x*5 + dis_info, y + dis*3);
        g.drawString(v.getCategory(), x*5 + dis_info, y + dis*4);
        g.drawString(v.getColor(), x*5 + dis_info, y + dis*5);

        g.drawString(String.format("$%,.2f", v.getPrice()) , width/2 + dis_info, y + dis*2);
        g.drawString(String.valueOf(v.getYear()), width/2 + dis_info, y + dis*3);
        g.drawString(String.format("%,d", v.getMileage()), width/2 + dis_info, y + dis*4);
        g.drawString(String.valueOf(v.getVin()), width/2 + dis_info, y + dis*5);

        // Test Image
        carMake = v.getMake();

        imgFilename = "CarImages/" + carMake + ".png";
//        imgFilename = "https://5100finalproject.blob.core.windows.net/vehicleimages/" + make;
        imgFilename = "CarImages/" + carMake + ".png";

        // Try Image
        ImageIcon icon = null;
        URL imgURL = getClass().getClassLoader().getResource(imgFilename);

        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
            img = icon.getImage();
        } else {
            imgFilename = "CarImages/" + carMake + ".jpg";

            imgURL = getClass().getClassLoader().getResource(imgFilename);
            if (imgURL != null){
                icon = new ImageIcon(imgURL);
                img = icon.getImage();
            }else{
                imgFilename = "CarImages/" + carMake + ".jpeg";
                imgURL = getClass().getClassLoader().getResource(imgFilename);
                if (imgURL != null){
                    icon = new ImageIcon(imgURL);
                    img = icon.getImage();
                }else{
                    System.err.println("Couldn't find file: " + imgFilename);
                    g.drawString("Couldn't find the car image: " + carMake,150, 200);
                }
            }
        }

         img = icon.getImage();
        // file extension is not required as long as the expected file(with no extension) exists
//        String uri = "https://5100finalproject.blob.core.windows.net/vehicleimages/1";
//        ImageIcon ii = null;
//        try {
//            ii = new ImageIcon(new URL(uri));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        img = ii.getImage();




        // Draw Image
        g.drawImage(img,x, x,  width-x*2, height*1/2, null);

        // Draw Borders
        g.setColor(Color.darkGray);
        g.draw3DRect(x, y, width-x*2, 220, false);
    }
}
