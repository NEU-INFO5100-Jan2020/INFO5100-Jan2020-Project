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

    public static Collection<MakeModel> getMakeModelFromDb(int dealerID) {
        mmc = mmcp.getMakeModels(dealerID);
        return mmc.getMakeModels();

    }


    public static List<Vehicle> vehicleSearchAndSort(int dealerID, SearchFilterDTO filter) {
        VehicleSearchFilter vsf = new VehicleSearchFilter(dealerID);
        // VehicleSearchFilterElement vsfe1 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MODEL, "Infiniti");
        if (!filter.getMake().isEmpty() && filter.getMake() != null) {
            VehicleSearchFilterElement vsfe2 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MAKE, filter.getMake());
            vsf.addElement(vsfe2);
        }
        if (!filter.getModel().isEmpty() && filter.getModel() != null) {
            VehicleSearchFilterElement vsfe1 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MODEL, filter.getModel());
            vsf.addElement(vsfe1);
        }
        if (!filter.getYear().isEmpty() && filter.getYear() != null) {
            VehicleSearchFilterElement vsfe3 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.YEAR, filter.getYear());
            vsf.addElement(vsfe3);
        }

        if (!filter.getMaxPrice().isEmpty() && filter.getMaxPrice() != null) {
            VehicleSearchFilterElement vsfe4 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.PRICE, filter.getMaxPrice());
            vsf.addElement(vsfe4);
        }

        VehicleManager vhcManager = new VehicleManagerImpl();
        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) vhcManager.getVehicles(vsf);

        return vehicleList;
    }

}
