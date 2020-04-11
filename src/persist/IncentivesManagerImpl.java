package persist;

import dto.Incentives;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class IncentivesManagerImpl implements IncentivesManager {
    ConnectionToSql connect = new ConnectionToSql();

    @Override
    public Collection<Incentives> getListOfIncentives() {
        String query = "SELECT * FROM Incentives";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "Incentives", "SELECT");

        /*Convert to Incentives object*/
        ArrayList<Incentives> incentivesResult = convertToIncentivesObject(result);

        return incentivesResult;
    }

    @Override
    public Collection<Incentives> getIncentiveDetails(int[] incentiveIdArray) {
        return null;
    }

    @Override
    public boolean addIncentive(String title, String description, String disclaimer, Date startDate, Date endDate, int discountValue, String discountType) {
        String query = "INSERT INTO Incentives (Title , Description , Disclaimer , " +
                "StartDate , EndDate , DiscountValue , DiscountType) " +
                "VALUES ('"+title+"' , '"+description+"' , '"+disclaimer+"' , " +
                "'"+startDate+"' , '"+endDate+"' , '"+discountValue+"' , '"+discountType+"') ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "Incentives", "INSERT");

        if(result != null)
            return true;

        return false;
    }

    @Override
    public boolean applyIncentive(int incentiveId, int[] vehicleIdsArray) {
        return false;
    }

    @Override
    public boolean deApplyIncentive(int incentiveId, int[] vehicleIdsArray) {
        return false;
    }

    @Override
    public boolean updateIncentive(int incentivesId, String title, String description, String disclaimer, Date startDate, Date endDate, int discountValue, String discountType) {
        String query = "UPDATE Incentives SET Title='"+title+"' , Description ='"+description+"' , Disclaimer='"+disclaimer+"' ," +
                " StartDate='"+startDate+"' , EndDate='"+endDate+"' , DiscountValue = '"+discountValue+"', DiscountType = '"+discountType+"'" +
                " WHERE IncentiveId = '"+incentivesId+"' ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "Incentives", "UPDATE");

        if(result != null)
            return true;

        return false;
    }

    @Override
    public boolean deleteIncentive(int incentivesId) {
        String query = "DELETE FROM Incentives WHERE IncentiveId ='"+incentivesId+"' ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "Incentives", "UPDATE");

        if(result != null)
            return true;

        return false;
    }

    private ArrayList<Incentives> convertToIncentivesObject(ArrayList<ArrayList> sqlQueryOutput) {
        ArrayList<Incentives> incentivesResult = new ArrayList<>();

        for(int i=0;i<sqlQueryOutput.size();i++){
            ArrayList temp = sqlQueryOutput.get(i);

            Incentives in = new Incentives();
            in.setIncentiveId((Integer)temp.get(0));
            in.setTitle(temp.get(1).toString());
            in.setDescription(temp.get(2).toString());
            in.setDisclaimer(temp.get(3).toString());
            in.setStartDate((Date)temp.get(4));
            in.setEndDate((Date)temp.get(5));
            in.setDiscountValue((Integer)temp.get(6));
            in.setDiscountType(temp.get(7).toString());

            incentivesResult.add(in);
        }

        return incentivesResult;
    }
}