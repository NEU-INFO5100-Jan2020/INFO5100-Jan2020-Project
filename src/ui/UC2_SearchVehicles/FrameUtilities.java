package ui.UC2_SearchVehicles;

//import dto.*;

import dto.Vehicle;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class FrameUtilities {
    String make;
    int price;
    int VIN;
    public FrameUtilities(String make, int price, int VIN) {
        this.make = make;
        this.price = price;
        this.VIN = VIN;
    }

    @Override
    public String toString() {
        return "Make : " + make +
                "price : " + price
                 ;
    }

    public static List<Vehicle> createTestVehicles() {
        List<Vehicle> vGroups = new ArrayList<>();
        Random r = new Random(1);
        for (int i = 0; i < 20; i++) {
            Vehicle v = new Vehicle();
            v.setMake(createMake()[r.nextInt(3)]);
            v.setPrice(1000+i*100);
            v.setVin(i*100323+i);
            v.setDiscountPrice(v.getPrice() - 1000);
            v.setModel(v.getMake());
            v.setYear(1000+ i * 10);
            v.setCategory("test Category");
            v.setColor("Black");
            v.setIncentiveId("33");
            vGroups.add(v);
        }
        return vGroups;
    }

    public static String[] createMake() {
        return new String[] {"Toyota", "Nissan", "Ford"};
    }

    public static String[] createModel (String make) {
        if (make == null)
            return new String[] {"NULL"};
        if (make.equals("Toyota")){
            return new String[] {"Camry", "Corolla"};
        }
        else if (make.equals("Nissan")) {
            return new String[] {"Altima", "GT-R", "Rogue"};
        }
        if (make.equals("Ford")){
            return new String[] {"Focus"};
        }
        else
            return new String[]{"No Model"};

    }

    public static Vehicle createVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setColor("Black");
        vehicle.setCategory("Category");
        vehicle.setDiscountPrice(8000);
        vehicle.setPrice(10000);
        vehicle.setMake("Ford");
        vehicle.setModel("Focus");
        vehicle.setMileage(11333);
        vehicle.setVehicleId(9999);
        vehicle.setDealerId(999);
        vehicle.setRatings(555);
        vehicle.setVin(12312312);
        vehicle.setYear(2020);

        return vehicle;
    }

    public static Integer[] initStartYearModel() {
        ArrayList<Integer> startYearModel = new ArrayList<>();
        for (int i = 1990; i <= 2020; i++) {
            startYearModel.add(i);
        }
        Integer[] arrayModel = new Integer[startYearModel.size()];
        return startYearModel.toArray(arrayModel);
    }

    public static Integer[] initEndYearModel(int startYear) {
        ArrayList<Integer> endYearModel = new ArrayList<>();
        for (int i = 2020; i >= startYear; i--) {
            endYearModel.add(i);
        }
        Integer[] arrayModel = new Integer[endYearModel.size()];
        return endYearModel.toArray(arrayModel);
    }

    public static String[] initPriceModel() {
        ArrayList<String> priceModel = new ArrayList<>();
        for (int i = 0; i < 50000; i = i + 5000) {
            priceModel.add("$" + i);
        }
        priceModel.add("No Max Price");
        String[] arrayModel = new String[priceModel.size()];
        return priceModel.toArray(arrayModel);
    }

//    public static Collection<Vehicle> callDB(int dealerID) {
//        Vehicle vhDB = new Vehicle();
//        Collection<Vehicle> vehicles = vhDB.getListOfVehiclesBasedOnDealerId(dealerID);
//        return vehicles;
//    }

}
