import java.util.Collection;

public interface VehicleManager {
    /*Return Vehicle details based on Vehicle Id passed*/
    Collection<Vehicle> getVehicleDetail(int vehicleId);

    /*Returns a list of Vehicles with its details, based on the vehicle ids passed*/
    Collection<Vehicle> getListOfVehiclesBasedOnVehicleIds(int[] vehicleIdList);

    /*Returns a list of Vehicles with its details, based on the specific 'dealerId' passed*/
    Collection<Vehicle> getListOfVehiclesBasedOnDealerId(int dealerId);

    /*Returns a list of Vehicles with its details, based on 'vehicleModel' / 'vehicleMake' / 'year' / 'vehiclePrice' passed*/
    Collection<Vehicle> getVehicleDetails(String vehicleModel, String vehicleMake, String year, String vehiclePrice);

    /*Add new entry in 'VehicleTable' with the parameters as listed.
     * Returns true - if vehicle is added successfully
     *        false - if there is an error in operation and vehicle isn't added to the db*/
    boolean addVehicle(String VIN,
                       String dealerId,
                       String make,
                       String model,
                       int year,
                       String category,
                       int price,
                       String color,
                       int miles );

    /*Update 'VehicleTable' table for the provided 'vehicleId' , with the parameters as listed
    * Returns true - if vehicle is updated successfully
    *        false - if there is an error in operation and vehicle isn't added to the db*/
    boolean updateVehicle(int vehicleId,
                          String VIN,
                          String dealerId,
                          String make,
                          String model,
                          int year,
                          String category,
                          int price,
                          String color,
                          int miles  );


    /*Delete entry from 'VehicleTable' table with given 'vehicleId' */
    boolean deleteVehicle(int vehicleId);
}