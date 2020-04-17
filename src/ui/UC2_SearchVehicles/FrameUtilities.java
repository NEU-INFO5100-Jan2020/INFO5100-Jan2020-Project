package ui.UC2_SearchVehicles;

import dto.Vehicle;

import persist.VehicleManager;
import persist.VehicleManagerImpl;
import service.SearchFilterElement;
import service.VehicleSearchFilter;
import service.VehicleSearchFilterElement;

import java.util.*;

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


    public static Integer[] initStartYearModel() {
        ArrayList<Integer> startYearModel = new ArrayList<>();
        for (int i = 2020; i >= 1990; i--) {
            startYearModel.add(i);
        }
        return startYearModel.toArray(new Integer[startYearModel.size()]);
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
        for (int i = 0; i < 50000; i = i + 5000) {
            priceModel.add("$" + i);
        }
        priceModel.add("No Max Price");
        String[] arrayModel = new String[priceModel.size()];
        return priceModel.toArray(arrayModel);
    }

    public static String[] getMake(ArrayList<MakeDTO> makeDTOS) {
        String[] makeArray = new String[makeDTOS.size()];
        for (int i = 0; i < makeDTOS.size(); i++) {
            makeArray[i] = makeDTOS.get(i).make;
        }
        return makeArray;
    }

    public static Vector<String> getModelOnMake(ArrayList<MakeDTO> makeDTOS, String make) {
        Vector<String> dataMode = new Vector<>();
        for (MakeDTO makeDTO : makeDTOS) {
            if (makeDTO.make.equals(make)) {
                for (String model : makeDTO.models) {
                    dataMode.add(model);
                }
            }
        }
        return dataMode;
    }

    public static ArrayList<MakeDTO> getMakeModelFromDb() {
        MakeModelCbbInitiator mccInit = new MakeModelCbbInitiator();
        Collection<MakeDTO> temp = mccInit.getMakeModel();
        ArrayList<MakeDTO> dtoList = new ArrayList<>();
        for (MakeDTO makeDTO : temp) {
            dtoList.add(makeDTO);
        }
        return dtoList;
    }


    public static List<Vehicle> vehicleSearchAndSort(int dealerID, String make, String model, String year, String maxPrice) {
        VehicleSearchFilter vsf = new VehicleSearchFilter(dealerID);
//        VehicleSearchFilterElement vsfe1 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MODEL, "Infiniti");
        VehicleSearchFilterElement vsfe1 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MODEL, model);
        VehicleSearchFilterElement vsfe2 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MAKE, make);
        VehicleSearchFilterElement vsfe3 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.YEAR, year);
        VehicleSearchFilterElement vsfe4 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.PRICE, maxPrice);

        vsf.addElement(vsfe1);
        vsf.addElement(vsfe2);
        vsf.addElement(vsfe3);
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
