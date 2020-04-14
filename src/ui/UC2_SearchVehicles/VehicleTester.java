package ui.UC2_SearchVehicles;

//import dto.*;

import java.util.Collection;
import java.util.Random;

public class VehicleTester {
    String make;
    int price;
    int VIN;
    public VehicleTester(String make, int price, int VIN) {
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

    public static VehicleTester[] createTestVehicles() {
        VehicleTester[] vGroups = new VehicleTester[20];
        Random r = new Random(1);
        for (int i = 0; i < vGroups.length; i++) {
            vGroups[i] = new VehicleTester(createMake()[r.nextInt(3)], 1000+i*100, i*100323+i);
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

//    public static Collection<Vehicle> callDB(int dealerID) {
//        Vehicle vhDB = new Vehicle();
//        Collection<Vehicle> vehicles = vhDB.getListOfVehiclesBasedOnDealerId(dealerID);
//        return vehicles;
//    }

}
