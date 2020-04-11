package persist;

import dto.Dealer;

import java.util.ArrayList;
import java.util.Collection;

public class DealerManagerImpl implements DealerManager {
    ConnectionToSql connect = new ConnectionToSql();

    @Override
    public Collection<Dealer> getAListOfAllDealer() {
        String query = "SELECT * from Dealer;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "Dealer", "SELECT");

        /*Convert to Dealer object*/
        ArrayList<Dealer> dealerResult = convertToDealerObject(result);

        return dealerResult;
    }

    @Override
    public Collection<Dealer> getDealerDetails(int dealerId) {
        String query = "SELECT * from Dealer WHERE DealerId='"+dealerId+"' ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "Dealer", "SELECT");

        /*Convert to Dealer object*/
        ArrayList<Dealer> dealerResult = convertToDealerObject(result);

        return dealerResult;
    }

    @Override
    public Collection<Dealer> getDealerDetails(String dealerName, String[] zipCode/*, String distanceInMiles*/) {
        String zipCodeString = "";

        for(int i=0;i<zipCode.length;i++){
            if(i == 0){
                zipCodeString += "'"+zipCode[i]+"'";
                continue;
            }
            zipCodeString += " , '"+zipCode[i]+"'";
        }

        String query = "SELECT * from Dealer " +
                "WHERE DealerName = '"+dealerName+"' and ZipCode IN ("+zipCodeString+") ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "Dealer", "SELECT");

        /*Convert to Dealer object*/
        ArrayList<Dealer> dealerResult = convertToDealerObject(result);

        return dealerResult;
    }

    @Override
    public boolean addDealer(String dealerName, String dealerAddress) {
        String query = "INSERT INTO Dealer (DealerName , DealerAddress) VALUES ('"+dealerName+"' , '"+dealerAddress+"' ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "Dealer", "INSERT");

        if(result != null)
            return true;

        return false;
    }

    @Override
    public boolean updateDealer(int dealerId, String dealerName, String dealerAddress) {
        String query = "UPDATE Dealer SET DealerName = '"+dealerName+"'" +
                " , DealerAddress='"+dealerAddress+"' WHERE DealerId='"+dealerId+"';";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "Dealer", "UPDATE");

        if(result != null)
            return true;

        return false;
    }

    @Override
    public boolean deleteDealer(int dealerId) {
        String query = "DELETE FROM Dealer WHERE DealerId ='"+dealerId+"' ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "Dealer", "UPDATE");

        if(result != null)
            return true;

        return false;
    }

    private ArrayList<Dealer> convertToDealerObject(ArrayList<ArrayList> sqlQueryOutput) {
        ArrayList<Dealer> dealerResult = new ArrayList<>();

        for(int i=0;i<sqlQueryOutput.size();i++){
            ArrayList temp = sqlQueryOutput.get(i);

            Dealer d = new Dealer();
            d.setDealerId((Integer)temp.get(0));
            d.setDealerName(temp.get(1).toString());
            d.setDealerAddress(temp.get(2).toString());
            d.setPhoneNumber(temp.get(3).toString());
            d.setZipCode(temp.get(4).toString());
            d.setCity(temp.get(5).toString());
            d.setCountry(temp.get(6).toString());

            dealerResult.add(d);
        }

        return dealerResult;
    }
}