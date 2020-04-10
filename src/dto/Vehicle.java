package dto;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Vehicle implements VehicleManager {
    ConnectionToSql connect = new ConnectionToSql();
    public int vehicleId;
    public int vin ;
    public int dealerId;
    public String make;
    public String model;
    public int year;
    public String category;
    public float price;
    public String color;
    public int mileage;
    public int incentiveId;
    public Image image;
    public float discountPrice;

    @Override
    public Collection<Vehicle> getVehicleDetail(int vehicleId) {
        String query = "SELECT * from VehicleTable WHERE VehicleId='"+vehicleId+"' ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "VehicleTable", "SELECT");

        /*Convert to Vehicle object*/
        ArrayList<Vehicle> vehicleResult = convertToVehicleObject(result);

        return vehicleResult;
    }

    @Override
    public Collection<Vehicle> getListOfVehiclesBasedOnVehicleIds(int[] vehicleIdList) {
        return null;
    }

    @Override
    public Collection<Vehicle> getListOfVehiclesBasedOnDealerId(int dealerId) {
        String query = "SELECT * from VehicleTable WHERE DealerId='"+dealerId+"' ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "VehicleTable", "SELECT");

        /*Convert to Vehicle object*/
        ArrayList<Vehicle> vehicleResult = convertToVehicleObject(result);

        return vehicleResult;
    }

    @Override
    public Collection<Vehicle> getVehicleDetails(int dealerId, String vehicleModel, String vehicleMake, String year, String vehiclePrice) {
        
        /*Make , Model , Year , Price are optional fields. If passed, then add to the query*/
        /*DealerId is mandatory passed*/
        String queryString = "DealerId = '"+dealerId+"'";

        if(vehicleModel!= "" ){
            queryString += " and Model='"+vehicleModel+"'";
        }
        if(vehicleMake != ""){
            queryString += " and Make='"+vehicleMake+"'";
        }
        if(year != ""){
            queryString += " and Year='"+year+"'";
        }
        if(vehiclePrice != ""){
            queryString += " and Price='"+vehiclePrice+"'";
        }

        /*Final select query*/
        String query = "SELECT * from VehicleTable WHERE "+queryString+" ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "VehicleTable", "SELECT");

        /*Convert to Vehicle object*/
        ArrayList<Vehicle> vehicleResult = convertToVehicleObject(result);

        return vehicleResult;
    }

    @Override
    public boolean addVehicle(String VIN, String dealerId, String make, String model, int year,
                              String category, int price, String color, int miles,
                              Image image, int incentiveId, float discountPrice) {
       String query = "INSERT INTO Dealer (VIN , DealerId ,Make , Model , Year , " +
               "Category , Price , Color , Miles , Image , IncentiveId , DiscountPrice) " +
               "VALUES ('"+VIN+"' , '"+dealerId+"' , '"+make+"' , '"+model+"' , '"+year+"' , " +
               "'"+category+"' , '"+price+"' , '"+color+"' , '"+miles+"' , '"+image+"' , '"+incentiveId+"' , '"+discountPrice+"') ;";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "VehicleTable", "INSERT");

        if(result != null)
            return true;

        return false;
    }

    @Override
    public boolean updateVehicle(int vehicleId, String VIN, String dealerId, String make, String model, int year,
                                 String category, int price, String color, int miles , Image image, int incentiveId , float discountPrice) {
        String query = "UPDATE VehicleTable SET VIN='"+VIN+"' , DealerId='"+dealerId+"' , Make='"+make+"' , " +
                "Model='"+model+"' , Year='"+year+"' , Category = '"+category+"' , " +
                "Price = '"+price+"' , Color = '"+color+"' , Miles = '"+miles+"' , " +
                "Image = '"+image+"' , IncentiveId= '"+incentiveId+"' , DiscountPrice = '"+discountPrice+"' "+
                "WHERE VehicleId='"+vehicleId+"';";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "VehicleTable", "UPDATE");

        if(result != null)
            return true;

        return false;
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        String query = "DELETE FROM VehicleTable WHERE VehicleId ='"+vehicleId+"';";

        /*Call 'executeQuery' method to run the query*/
        ArrayList<ArrayList> result = connect.executeQuery(query, "VehicleTable", "UPDATE");

        if(result != null)
            return true;

        return false;
    }

    private ArrayList<Vehicle> convertToVehicleObject(ArrayList<ArrayList> sqlQueryOutput){
        ArrayList<Vehicle> vehicleResult = new ArrayList<>();

        for(int i=0;i<sqlQueryOutput.size();i++){
            ArrayList temp = sqlQueryOutput.get(i);

            Vehicle v = new Vehicle();
            v.vehicleId = (Integer)temp.get(0);
            v.vin = (Integer)temp.get(1);
            v.dealerId = (Integer)temp.get(2);
            v.make = temp.get(3).toString();
            v.model = temp.get(4).toString();
            v.year = (Integer)temp.get(5);
            v.category = temp.get(6).toString();
            v.price = (Float)temp.get(7);
            v.color = temp.get(8).toString();
            v.mileage = (Integer)temp.get(9);
            //v.image = (Image) temp.get(10);
            v.incentiveId = (Integer) temp.get(11);
            v.discountPrice = (Float) temp.get(12);

            vehicleResult.add(v);
        }

        return vehicleResult;
    }
}
