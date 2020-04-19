package ui.UC2_SearchVehicles;

import dto.Vehicle;

import persist.VehicleManager;
import persist.VehicleManagerImpl;
import service.*;

import java.util.*;

public class FrameUtilities {

    String make;
    int price;
    int VIN;

    private static MakeModelContainer mmc;
    private static MakeModelContainerPopulator mmcp = new MakeModelContainerPopulator();

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


    public static String[] initStartYearModel() {
        ArrayList<String> startYearModel = new ArrayList<>();
        startYearModel.add("All Year");
        for (int i = 2020; i >= 1990; i--) {
            startYearModel.add(String.valueOf(i));
        }
        return startYearModel.toArray(new String[startYearModel.size()]);
    }

    public static Integer[] initEndYearModel(int startYear) {
        ArrayList<Integer> endYearModel = new ArrayList<>();
        for (int i = 2020; i >= startYear; i--) {
            endYearModel.add(i);
        }
        return endYearModel.toArray(new Integer[endYearModel.size()]);
    }

    public static String[] initPriceModel() {
        ArrayList<String> priceModel = new ArrayList<>();
        priceModel.add("No Max Price");
        for (int i = 50000; i >= 0; i = i - 5000) {
            priceModel.add("$" + i);
        }

        String[] arrayModel = new String[priceModel.size()];
        return priceModel.toArray(arrayModel);
    }

    public static String[] getMake(ArrayList<MakeModel> makeDTOS) {
        String[] makeArray = new String[makeDTOS.size() + 1];
        makeArray[0] = "All Make";
        for (int i = 1; i < makeDTOS.size(); i++) {
            makeArray[i] = makeDTOS.get(i).getMake();
        }
        return makeArray;
    }

    public static Vector<String> getModelOnMake(ArrayList<MakeModel> makeDTOS, String make) {
        Vector<String> dataMode = new Vector<>();
        for (MakeModel makeDTO : makeDTOS) {
            if (makeDTO.getMake().equals(make)) {
                for (String model : makeDTO.getModels()) {
                    dataMode.add(model);
                }
            }
        }
        return dataMode;
    }

    public static Collection<MakeModel> getMakeModelFromDb() {
        mmc = mmcp.getMakeModels();
        return mmc.getMakeModels();

    }


    public static List<Vehicle> vehicleSearchAndSort(int dealerID, String make, String model, String year, String maxPrice) {
        VehicleSearchFilter vsf = new VehicleSearchFilter(dealerID);
        // VehicleSearchFilterElement vsfe1 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MODEL, "Infiniti");
        if (!make.isEmpty() && make != null) {
            VehicleSearchFilterElement vsfe2 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MAKE, make);
            vsf.addElement(vsfe2);
        }
        if (!model.isEmpty() && model != null) {
            VehicleSearchFilterElement vsfe1 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MODEL, model);
            vsf.addElement(vsfe1);
        }
        if (!year.isEmpty() && year != null) {
            VehicleSearchFilterElement vsfe3 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.YEAR, year);
            vsf.addElement(vsfe3);
        }

        VehicleSearchFilterElement vsfe4 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.PRICE, maxPrice);
        vsf.addElement(vsfe4);

        VehicleManager vhcManager = new VehicleManagerImpl();
        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) vhcManager.getVehicles(vsf);

        return vehicleList;
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

}
