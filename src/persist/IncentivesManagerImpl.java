package persist;

import dto.Incentives;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class IncentivesManagerImpl implements IncentivesManager {
    ConnectionToSql connect = new ConnectionToSql();

    @Override
    public Collection<Incentives> getListOfIncentives() {
        String query = "SELECT * FROM Incentives";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeIncentivesQuery(query, "SELECT");

        /*Convert to Incentives object*/
        ArrayList<Incentives> incentivesResult = convertToIncentivesObject(result);

        return incentivesResult;
    }

    @Override
    public Collection<Incentives> getIncentiveDetails(int[] incentiveIdArray) {
        return null;
    }

    /**
     * This method is used for saving data to incentive table.
     */

    @Override
	public boolean addIncentive(Incentives incentives) {
		Connection connection = connect.connectToDB();
		Statement stmt;
		ResultSet rs = null;
		try {
			String query = "INSERT INTO Incentives (Title , Description , Disclaimer , "
					+ "StartDate , EndDate , DiscountValue , DiscountType,IsDeleted,FilterList,VehicleIdList) "
					+ "VALUES ('" + incentives.getTitle() + "' , '" + incentives.getDescription() + "' , '"
					+ incentives.getDisclaimer() + "' , " + "'" + incentives.getStartDate() + "' , '"
					+ incentives.getEndDate() + "' , " + incentives.getDiscountValue() + " , '"
					+ incentives.getDiscountType() + "' , '" + incentives.getDiscountType() + "','"
					+ incentives.getIsDeleted() + "'," + "'" + incentives.getFilterList() + "','"
					+ incentives.getVehicleIdList() + "')";
			if (connection != null) {
				stmt = connection.createStatement();
				stmt.execute(query, stmt.RETURN_GENERATED_KEYS);
				rs = stmt.getGeneratedKeys();
				if (rs != null && rs.next()) {
					int incentiveId = rs.getInt(1);
					int[] arr = { 25, 40 };
					applyIncentive(incentiveId, arr, connection);
				}
			}
		} catch (SQLException e) {
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	public void addIncentive2(Incentives incentives) throws SQLException {
		ResultSet resultSet=null;
		Statement statement=null;
		Date startDate = DateToSqlDatetime.JavaStartDateToSqlDate(incentives);
		Date endDate = DateToSqlDatetime.JavaEndDateToSqlDate(incentives);


		String query = "INSERT INTO Incentives (Title , Description , Disclaimer , "
				+ "StartDate , EndDate , DiscountValue , DiscountType,DealerId,IsDeleted,FilterList,VehicleIdList) "
				+ "VALUES ('" + incentives.getTitle() + "' , '" + incentives.getDescription() + "' , '"
				+ incentives.getDisclaimer() + "' , " + "'" + startDate + "' , '"
				+ endDate + "' , " + incentives.getDiscountValue() + " , '"
				+ incentives.getDiscountType() + "' , '" + incentives.getDealerId() + "','"
				+ incentives.getIsDeleted() + "'," + "'" + incentives.getFilterList() + "','"
				+ incentives.getVehicleIdList() + "')";
		try{
			statement=connection.createStatement();
			statement.executeUpdate(query,statement.RETURN_GENERATED_KEYS);
			resultSet=statement.getGeneratedKeys();
			if(resultSet.next()){
				int incentiveId=resultSet.getInt(1);
			}

		}catch (SQLException e){
			e.printStackTrace();
		}

	}

    /**
     * @author SwatiBhojwani
     * @param incentiveId:This id is used for updating vehicleTable with incentivesId
     * @param vehicleIdsArray:list of vehicleIds used for updating incentives
     * @param conn:Existing DB Connection
     * @return :True or False on the basis of query execution
     */
    public boolean applyIncentive(int incentiveId, int[] vehicleIdsArray,Connection conn) {
    	Statement stmt;
		String query;
		String vehicleIdsArr = vehicleIdsArray.toString().replaceAll("(^\\[|\\]$)", "");
		try {
		    query="UPDATE VehicleTable SET INCENTIVEID ="+incentiveId+" WHERE VehicleId IN ("+vehicleIdsArr+")";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return true;
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
    public boolean updateIncentive(Incentives incentives) {
        String query = "UPDATE Incentives SET Title='"+incentives.getTitle()+"' , Description ='"+incentives.getDescription()+
                "' , Disclaimer='"+incentives.getDisclaimer()+"' , StartDate='"+incentives.getStartDate()+"' , EndDate='"+incentives.getEndDate()+
                "' , DiscountValue = "+incentives.getDiscountValue()+" , DiscountType = '"+incentives.getDiscountType()+"'" +
                " WHERE IncentiveId = "+incentives.getIncentiveId()+" ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeIncentivesQuery(query, "UPDATE");

        if(result != null)
            return true;

        return false;
    }

	public void updateIncentive2(Incentives incentives){
		Date startDate = DateToSqlDatetime.JavaStartDateToSqlDate(incentives);
		Date endDate = DateToSqlDatetime.JavaEndDateToSqlDate(incentives);

		String sql ="UPDATE Incentives SET Title=incentives.getTitle(), Description=incentives.getDescription()," +
				"DiscountValue=incentives.getDiscountValue()," +
				" StartDate=startDate,EndDate=endDate,DiscountType=incentives.getDiscountType()," +
				"Disclaimer=incentives.getDisclaimer()  WHERE  IncentiveId =incentives.getIncentiveId();";
		String query = "UPDATE Incentives SET Title='"+incentives.getTitle()+"' , Description ='"+incentives.getDescription()+
				"' , Disclaimer='"+incentives.getDisclaimer()+"' , StartDate='"+startDate+"' , EndDate='"+endDate+
				"' , DiscountValue = "+incentives.getDiscountValue()+" , DiscountType = '"+incentives.getDiscountType()+"'" +
				" WHERE IncentiveId = "+incentives.getIncentiveId()+" ;";


		try {


			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


    /*
     * When we wanted to delete the incentive we are updating that particular incentiveId with isDeleted Flag=true
     */
    @Override
    public boolean deleteIncentive(int incentivesId) {
    	Statement stmt = null;
		String deleteQuery;
		Connection connection = null;
		try {
			connection = connect.connectToDB();
			deleteQuery = "Update Incentives SET isDeleted=1 WHERE IncentiveId ='" + incentivesId + "' ;";
			stmt = connection.createStatement();
			stmt.executeUpdate(deleteQuery);
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
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
            in.setDealerId((Integer) temp.get(8));
            in.setIsDeleted(Boolean.parseBoolean(temp.get(9).toString()));
            in.setFilterList(temp.get(10).toString());
            in.setVehicleIdList(temp.get(11).toString());

            incentivesResult.add(in);
        }

        return incentivesResult;
    }
}
