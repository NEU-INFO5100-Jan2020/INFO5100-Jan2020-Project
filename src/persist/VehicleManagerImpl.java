package persist;

import dto.Vehicle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class VehicleManagerImpl implements VehicleManager {
    ConnectionToSql connect = new ConnectionToSql();

    @Override
    public Collection<Vehicle> getVehicleDetail(int vehicleId) {
        String query = "SELECT * from VehicleTable WHERE VehicleId="+vehicleId+" ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeVehicleQuery(query,  "SELECT");

        /*Convert to Vehicle object*/
        ArrayList<Vehicle> vehicle = convertToVehicleObject(result);

        return vehicle;
    }

    @Override
    public Collection<Vehicle> getListOfVehiclesBasedOnVehicleIds(int[] vehicleIdList) {
        return null;
    }

    @Override
    public Collection<Vehicle> getListOfVehiclesBasedOnDealerId(int dealerId) {
        String query = "SELECT * from VehicleTable WHERE DealerId="+dealerId+" ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeVehicleQuery(query,  "SELECT");

        /*Convert to Vehicle object*/
        ArrayList<Vehicle> vehicle = convertToVehicleObject(result);

        return vehicle;
    }

    @Override
    public Collection<Vehicle> getVehicleDetails(int dealerId, String vehicleModel, String vehicleMake, String year, String vehiclePrice) {

        /*Make , Model , Year , Price are optional fields. If passed, then add to the query*/
        /*DealerId is mandatory passed*/
        String queryString = "DealerId = "+dealerId+" ";

        if(vehicleModel!= "" ){
            queryString += " and Model='"+vehicleModel+"'";
        }
        if(vehicleMake != ""){
            queryString += " and Make='"+vehicleMake+"'";
        }
        if(year != ""){
            queryString += " and Year= "+year+" ";
        }
        if(vehiclePrice != ""){
            queryString += " and Price= "+vehiclePrice+" ";
        }

        /*Final select query*/
        String query = "SELECT * from VehicleTable WHERE "+queryString+" ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeVehicleQuery(query,  "SELECT");

        /*Convert to Vehicle object*/
        ArrayList<Vehicle> vehicle = convertToVehicleObject(result);

        return vehicle;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
       String query = "INSERT INTO Dealer (VIN , DealerId ,Make , Model , Year , " +
               "Category , Price , Color , Miles , Image , IncentiveId , DiscountPrice) " +
               "VALUES ('"+vehicle.getVin()+"' , "+vehicle.getDealerId()+" , '"+vehicle.getMake()+"' , '"+vehicle.getModel()+
               "' , "+vehicle.getYear()+" , '"+vehicle.getCategory()+"' , "+vehicle.getPrice()+" , '"+vehicle.getColor()+
               "' , "+vehicle.getMileage()+" , '"+vehicle.getImage()+"' , "+vehicle.getIncentiveId()+" , "+vehicle.getDiscountPrice()+
               ") ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeVehicleQuery(query,  "INSERT");

        if(result != null)
            return true;

        return false;
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        String query = "UPDATE VehicleTable SET VIN='"+vehicle.getVin()+"' , DealerId="+vehicle.getDealerId()+
                " , Make='"+vehicle.getMake()+"' , Model='"+vehicle.getModel()+"' , Year= "+vehicle.getYear()+
                " , Category = '"+vehicle.getCategory()+"' , Price = "+vehicle.getPrice()+" , Color = '"+vehicle.getColor()+
                "' , Miles = "+vehicle.getMileage()+" , Image = '"+vehicle.getImage()+"' , IncentiveId= "+vehicle.getIncentiveId()+
                " , DiscountPrice = "+vehicle.getDiscountPrice()+" WHERE VehicleId="+vehicle.getVehicleId()+
                " ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeVehicleQuery(query, "UPDATE");

        if(result != null)
            return true;

        return false;
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        String query = "DELETE FROM VehicleTable WHERE VehicleId = "+vehicleId+" ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeVehicleQuery(query, "UPDATE");

        if(result != null)
            return true;

        return false;
    }

    private ArrayList<Vehicle> convertToVehicleObject(ArrayList<ArrayList> sqlQueryOutput){
        ArrayList<Vehicle> vehicle = new ArrayList<>();

        for(int i=0;i<sqlQueryOutput.size();i++){
            ArrayList temp = sqlQueryOutput.get(i);

            Vehicle v = new Vehicle();

            v.setVehicleId( (Integer)temp.get(0));
            v.setVin((Integer)temp.get(1));
            v.setDealerId((Integer)temp.get(2));
            v.setMake(temp.get(3).toString());
            v.setModel(temp.get(4).toString());
            v.setYear((Integer)temp.get(5));
            v.setCategory(temp.get(6).toString());
            v.setPrice((Float)temp.get(7));
            v.setColor(temp.get(8).toString());
            v.setMileage((Integer)temp.get(9));
            v.setImage((Image) temp.get(10));
            v.setIncentiveId((Integer) temp.get(11));
            v.setDiscountPrice((Float) temp.get(12));

            vehicle.add(v);
        }

        return vehicle;
    }
}
