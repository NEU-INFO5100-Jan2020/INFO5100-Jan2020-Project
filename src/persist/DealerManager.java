package persist;
import dto.Dealer;

import java.util.Collection;

public interface DealerManager {

    /*Get a list of all the dealers from the Dealer table*/
    Collection<Dealer> getAListOfAllDealer();

    /*Returns a list of Dealer details, based on the dealerId passed*/
    Collection<Dealer> getDealerDetails(int dealerId);

    /*Returns a list of Dealer details, based on the 'dealerName' / 'zipCode' / 'distanceInMiles' passed*/
    Collection<Dealer> getDealerDetails(String dealerName, String[] zipCode/*, String distanceInMiles*/);

    /*Add a new entry in 'Dealer' table , with the parameters provided as listed
     * Returns true - if dealer is added successfully
     *        false - if there is an error in operation and dealer isn't added to the db*/
    boolean addDealer(String dealerName,
                      String dealerAddress);

    /*Update 'Dealer' table for the provided 'dealerId' , with the parameters as listed
     * Returns true - if dealer is updated successfully
     *        false - if there is an error in operation and dealer isn't updated to the db*/
    boolean updateDealer(int dealerId,
                         String dealerName,
                         String dealerAddress);

    /*Delete entry from 'Dealer' table with given 'dealerId' */
    boolean deleteDealer(int dealerId);
}
