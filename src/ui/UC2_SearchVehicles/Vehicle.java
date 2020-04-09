package ui.UC2_SearchVehicles;

public class Vehicle {
    String make;
    int price;
    int VIN;
    public Vehicle(String make, int price, int VIN) {
        this.make = make;
        this.price = price;
        this.VIN = VIN;
    }

    @Override
    public String toString() {
        return "Vehicle: " +
                "Make : " + make +
                "price : " + price
                 ;
    }

    public static Vehicle[] createTestVehicles() {
        Vehicle[] vGroups = new Vehicle[20];
        for (int i = 0; i < vGroups.length; i++) {
            vGroups[i] = new Vehicle("Make1", 1000+i*100, i*100323+i);
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
}
