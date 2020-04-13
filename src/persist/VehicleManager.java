package persist;

import dto.Vehicle;

import java.awt.*;
import java.util.Collection;

public interface VehicleManager {
    /*Return Vehicle details based on Vehicle Id passed*/
    Collection<Vehicle> getVehicleDetail(int vehicleId);

    /*Returns a list of Vehicles with its details, based on the vehicle ids passed*/
    Collection<Vehicle> getListOfVehiclesBasedOnVehicleIds(int[] vehicleIdList);

    /*Returns a list of Vehicles with its details, based on the specific 'dealerId' passed*/
    Collection<Vehicle> getListOfVehiclesBasedOnDealerId(int dealerId , VehicleSearchFilter vehicleSearchFilter);

    /*Returns a list of Vehicles with its details, based on 'vehicleModel' / 'vehicleMake' / 'year' / 'vehiclePrice'. Vehicle object is passeed as argument*/
    Collection<Vehicle> getVehicleDetails(Vehicle vehicle);

    /*Add new entry in 'VehicleTable' with the parameters as listed.
     * Returns true - if vehicle is added successfully
     *        false - if there is an error in operation and vehicle isn't added to the db*/
    boolean addVehicle(Vehicle vehicle);

    /*Update 'VehicleTable' table for the provided 'vehicleId' , with the parameters as listed
    * Returns true - if vehicle is updated successfully
    *        false - if there is an error in operation and vehicle isn't added to the db*/
    boolean updateVehicle(Vehicle vehicle);

    /*Delete entry from 'VehicleTable' table with given 'vehicleId' */
    boolean deleteVehicle(int vehicleId);
}
