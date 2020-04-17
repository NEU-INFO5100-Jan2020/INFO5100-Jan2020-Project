package persist;

import dto.Incentives;

import java.util.Collection;
import java.util.Date;
//import java.sql.Date;

public interface IncentivesManager {
    /*Returns list of all available incentives*/
    Collection<Incentives> getListOfIncentives();

    /*Return incentive details based on the array of incentiveIds*/
    Collection<Incentives> getIncentiveDetails(int[] incentiveIdArray);

    /*Add a new entry in 'Incentives' table with the parameters as listed.
     * Returns true - if incentive is added successfully
     *        false - if there is an error in operation and incentive isn't added to the db*/
    boolean addIncentive(Incentives incentives);

    /*Update the 'Incentives' table for given 'incentivesId' in  with the parameters as listed.
     * Returns true - if incentive is updated successfully
     *        false - if there is an error in operation and incentive isn't updated to the db*/
    boolean updateIncentive(Incentives incentives);

    /*Delete entry from 'Incentives' table with given 'incentivesId' */
    boolean deleteIncentive(int incentivesId);

    /*Apply given incentive with 'incentiveId' to the list of provided array of 'vehicleId'
     Returns true - if incentive is applied successfully
            false - if there is an error in operation and incentive isn't applied*/
    boolean applyIncentive(int incentiveId, int[] vehicleIdsArray);

    /*De-Apply given incentive with 'incentiveId' to the list of provided array of 'vehicleId'
      Returns true - if incentive is de-applied successfully
             false - if there is an error in operation and incentive isn't de-applied*/
    boolean deApplyIncentive(int incentiveId, int[] vehicleIdsArray);
    
    /*Check incentives for a specific vehicleId*/    
    Collection<Incentives> checkIncentives(int VehicleId);
}
