package persist;

import dto.Vehicle;
import service.IncentiveSearchFilter;
import service.VehicleSearchFilter;


import java.awt.*;
import java.util.Collection;

public interface VehicleManager {

    /*Returns a list of Vehicles with its details, based on the specific 'dealerId' passed*/
    // Use case 4 backend
    Collection<Vehicle> getVehiclesBasedOnDealerId(int dealerId);

    Collection<Vehicle> getVehiclesForCase5(IncentiveSearchFilter incentiveSearchFilter);

    /*Returns a list of Vehicles with its details, based on 'vehicleModel' / 'vehicleMake' / 'year' / 'vehiclePrice'. Vehicle object is passed as argument*/
    // Use case 2, 5 backend
    Collection<Vehicle> getVehicles(VehicleSearchFilter vehicleSearchFilter);

    /*Add new entry in 'VehicleTable' with the parameters as listed.
     * Returns true - if vehicle is added successfully
     *        false - if there is an error in operation and vehicle isn't added to the db*/
    // Use case
    Vehicle addVehicle(Vehicle vehicle);

    /*Update 'VehicleTable' table for the provided 'vehicleId' , with the parameters as listed
    * Returns true - if vehicle is updated successfully
    *        false - if there is an error in operation and vehicle isn't added to the db*/
    Vehicle updateVehicle(Vehicle vehicle);

    /*Delete entry from 'VehicleTable' table with given 'vehicleId' */
    Vehicle deleteVehicle(Vehicle vehicle);
}
