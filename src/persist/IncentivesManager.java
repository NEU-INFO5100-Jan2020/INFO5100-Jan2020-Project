package persist;

import dto.Incentive;

import java.util.Collection;
import java.util.Date;
//import java.sql.Date;

public interface IncentivesManager {
    /*Returns list of all available incentives*/
    Collection<Incentive> getListOfIncentives();

    /*Return incentive details based on the array of incentiveIds*/
    Collection<Incentive> getIncentiveDetails(int[] incentiveIdArray);

    /*Add a new entry in 'Incentives' table with the parameters as listed.
     * Returns true - if incentive is added successfully
     *        false - if there is an error in operation and incentive isn't added to the db*/
    boolean addIncentive(String title,
                          String description,
                          String disclaimer,
                          Date startDate,
                          Date endDate,
                          int discountValue,
                          String discountType);

    /*Apply given incentive with 'incentiveId' to the list of provided array of 'vehicleId'
    Returns true - if incentive is applied successfully
           false - if there is an error in operation and incentive isn't applied*/
    boolean applyIncentive(int incentiveId, int[] vehicleIdsArray);

    /*Update the 'Incentives' table for given 'incentivesId' in  with the parameters as listed.
     * Returns true - if incentive is updated successfully
     *        false - if there is an error in operation and incentive isn't updated to the db*/
    boolean updateIncentive(int incentivesId,
                          String title,
                          String description,
                          String disclaimer,
                          Date startDate,
                          Date endDate,
                          int discountValue,
                          String discountType);

    /*Delete entry from 'Incentives' table with given 'incentivesId' */
    boolean deleteIncentive(int incentivesId);
}
