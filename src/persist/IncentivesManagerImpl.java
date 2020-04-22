package persist;

import dto.Incentives;
import ui.guiforcase5.DateToSqlDatetime;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.sql.PreparedStatement;

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

	public void addIncentive2(Incentives incentives, int[] vid) throws SQLException {
    	Connection connection=connect.connectToDB();
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
				// apply
				applyIncentive(incentiveId, vid, connection);
			}

		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(connection!=null)
				connection.close();
		}

	}

    /**
     * @author SwatiBhojwani
     * This method is used for mapping single incentive id  to multiple vehicle Id's
     * @param incentiveId:This id is used for inserting in VehicleIncentivesMap 
     * @param vehicleIdsArray:list of vehicleIds used for updating incentives
     * @param conn:Existing DB Connection
     * @return :True or False on the basis of query execution
     */
    public boolean applyIncentive(int incentiveId, int[] vehicleIdsArray,Connection conn) {
    	Statement stmt = null;
	String query = null;
		try {
			stmt = conn.createStatement();
			for(int i =0; i<=vehicleIdsArray.length-1;i++) {
			    query = "INSERT INTO VehicleIncentivesMap (VehicleId , IncentiveId ) "
				    + "VALUES (" + vehicleIdsArray[i] + " , " + incentiveId + ")";
				    stmt.addBatch(query);
			}
			stmt.executeBatch();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
    	Connection connection=connect.connectToDB();
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
	@Override
	//CheckIncentives of a specific VehicleId
	public Collection<Incentives> checkIncentives(int VehicleId) {
		ArrayList<ArrayList> result = new ArrayList<ArrayList>();
		Float discountedPrice = null;
		try {
			ConnectionToSql connect = new ConnectionToSql();
			Connection connection = connect.connectToDB();
			String query="SELECT ROW_NUMBER() OVER(ORDER BY inc.IncentiveId ASC) AS Row,inc.Title,inc.Description,inc.Disclaimer,inc.StartDate,"
					+ "inc.EndDate,inc.DiscountValue,inc.DiscountType,inc.DealerId,inc.IsDeleted,inc.FilterList,inc.VehicleIdList,veh.price"
					+ "  FROM Incentives inc,"
					+ "	 VehicleIncentivesMap map,VehicleTable veh "
					+ "	 where map.IncentiveId = inc.IncentiveId"
					+ "	 and veh.VehicleId=map.VehicleId"
					+ "	 and inc.EndDate>=GETDATE()" 
					+"   and inc.Isdeleted=0"
					+ "	 and map.VehicleId= ?";

			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, VehicleId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArrayList temp = new ArrayList();
				temp.add(rs.getInt("Row")); // Srno
				temp.add(rs.getString("Title")); // Title
				temp.add(rs.getString("Description")); // Description
				temp.add(rs.getString("Disclaimer")); // Disclaimer
				temp.add(rs.getDate("StartDate")); // StartDate
				temp.add(rs.getDate("EndDate")); // EndDate
				temp.add(rs.getInt("DiscountValue")); // DiscountValue
				temp.add(rs.getString("DiscountType")); // DiscountType
				temp.add(rs.getInt("DealerId")); // DealerId
				temp.add(rs.getBoolean("IsDeleted")); // IsDeleted
				temp.add(rs.getString("FilterList")); // FilterList
				temp.add(rs.getString("VehicleIdList")); // VehicleIdList
				if(discountedPrice==null)
					discountedPrice = calculateDiscountedPrice(rs.getFloat("price"),rs.getFloat("price"),rs.getInt("DiscountValue"),rs.getString("DiscountType"));
				else
					discountedPrice = calculateDiscountedPrice(rs.getFloat("price"),discountedPrice,rs.getInt("DiscountValue"),rs.getString("DiscountType"));

				temp.add(discountedPrice); // Price
				result.add(temp);
			}
			/* Convert to Incentives object */
			ArrayList<Incentives> incentivesResult = convToIncObjForShowingDisPrice(result);
			return incentivesResult;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private float calculateDiscountedPrice( float originalPrice, float discountedPrice, int discountValue, String discountType) {

//    	float discountedPrice = 0;
		float temp = 0;
		if(discountType.toUpperCase().startsWith("CASH")) {
			discountedPrice=discountedPrice-discountValue;
		}else {
			temp=(originalPrice*discountValue)/100;
			discountedPrice=discountedPrice-temp;
		}
		return discountedPrice;
		
	}
	
private ArrayList<Incentives> convToIncObjForShowingDisPrice(ArrayList<ArrayList> sqlQueryOutput) {
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
            in.setDiscountedPrice(Float.parseFloat((temp.get(12).toString())));
            incentivesResult.add(in);
        }

        return incentivesResult;
    }
}
