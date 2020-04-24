package persist;

import dto.*;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionToSql {
    private static final String hostName = "is-swang01.ischool.uw.edu";
    private static final String dbName = "Jan2020_Info5100";
    private static final String user = "INFO6210";
    private static final String password = "NEUHusky!";
    private Connection connection = null;
    private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String linkForConnection = "jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=true;"
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

   public Connection connectToDB() {
        try {
            Class.forName(jdbcDriver);
            String url = String.format(linkForConnection, hostName, dbName, user, password);

            /*Establish connection to the DB*/
            connection = DriverManager.getConnection(url);
            String schema = connection.getSchema();
            System.out.println("Successful connection - Schema: " + schema);
            return connection;
        } catch (Exception e) {
            System.out.println("Exception :" + e.getMessage());
            return connection;
        }
    }

    public void disconnectFromDB() {
        try {
            /*Disconnect from DB*/
            connection.close();
        } catch (Exception e) {
            System.out.println("Exception :" + e.getMessage());
            return;
        }
    }

    public ArrayList<ArrayList> executeVehicleQuery(String queryToExecute, String queryType) {
        ArrayList<ArrayList> result = new ArrayList<ArrayList>();

        /*Connect to database*/
        connectToDB();

        try {
            if("INSERT".equalsIgnoreCase(queryType)){
                /*Execute the query*/
                Statement statement = connection.createStatement();
                statement.executeUpdate(queryToExecute);
            }
            else{
            /*Execute the query*/
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(queryToExecute)) {

                while (resultSet.next()) {
                    ArrayList temp = new ArrayList();
                    temp.add(resultSet.getInt(1)); //VehicleId
                    temp.add(resultSet.getInt(2)); //VIN
                    temp.add(resultSet.getInt(3)); //DealerId
                    temp.add(resultSet.getString(4)); //Make
                    temp.add(resultSet.getString(5)); //Model
                    temp.add(resultSet.getInt(6)); //Year
                    temp.add(resultSet.getString(7)); //Category
                    temp.add(resultSet.getFloat(8)); //Price
                    temp.add(resultSet.getString(9)); //Color
                    temp.add(resultSet.getInt(10)); //Mileage
                    temp.add(resultSet.getObject(11)); //Image
                    temp.add(resultSet.getString(12)); //IncentiveId
                    temp.add(resultSet.getFloat(13)); //DiscountPrice
                    temp.add(resultSet.getInt(14)); //Ratings

                    //Adding each row to the result
                    result.add(temp);
                }
            }
          }
        } catch (Exception e) {
            System.out.println("Exception :" + e.getMessage());
            return null;
        }

        /*Disconnect from DB*/
        disconnectFromDB();

        /*Return result*/
        return result;
    }

    public ArrayList<ArrayList> executeDealerQuery(String queryToExecute, String queryType) {

        ArrayList<ArrayList> result = new ArrayList<ArrayList>();

        /*Connect to database*/
        connectToDB();

        try {
            if("INSERT".equalsIgnoreCase(queryType)){
                /*Execute the query*/
                Statement statement = connection.createStatement();
                statement.executeUpdate(queryToExecute);
            }
            else{
            /*Execute the query*/
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(queryToExecute)) {

                while (resultSet.next()) {
                    ArrayList temp = new ArrayList();

                    temp.add(resultSet.getInt(1)); //DealerId
                    temp.add(resultSet.getString(2)); //DealerName
                    temp.add(resultSet.getString(3)); //DealerAddress
                    temp.add(resultSet.getString(4)); //PhoneNumber
                    temp.add(resultSet.getString(5)); //ZipCode
                    temp.add(resultSet.getString(6)); //City
                    temp.add(resultSet.getString(7)); //Country

                    //Adding each row to the result
                    result.add(temp);
                }
            }
          }
        } catch (Exception e) {
            System.out.println("Exception :" + e.getMessage());
            return null;
        }

        /*Disconnect from DB*/
        disconnectFromDB();

        /*Return result*/
        return result;
    }

    public ArrayList<ArrayList> executeIncentivesQuery(String queryToExecute, String queryType) {

        ArrayList<ArrayList> result = new ArrayList<ArrayList>();

        /*Connect to database*/
        connectToDB();

        try {
            if("INSERT".equalsIgnoreCase(queryType)){
                /*Execute the query*/
                Statement statement = connection.createStatement();
                statement.executeUpdate(queryToExecute);
            }
            else{
            /*Execute the query*/
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(queryToExecute)) {

                while (resultSet.next()) {
                    ArrayList temp = new ArrayList();

                    temp.add(resultSet.getInt(1)); //IncentiveId
                    temp.add(resultSet.getString(2)); //Title
                    temp.add(resultSet.getString(3)); //Description
                    temp.add(resultSet.getString(4)); //Disclaimer
                    temp.add(resultSet.getDate(5)); //StartDate
                    temp.add(resultSet.getDate(6)); //EndDate
                    temp.add(resultSet.getInt(7)); //DiscountValue
                    temp.add(resultSet.getString(8)); //DiscountType
                    temp.add(resultSet.getInt(9)); //DealerId
                    temp.add(resultSet.getBoolean(10)); //IsDeleted
                    temp.add(resultSet.getString(11)); //FilterList
                    temp.add(resultSet.getString(12)); //VehicleIdList

                    //Adding each row to the result
                    result.add(temp);
                }
            }
          }
        } catch (Exception e) {
            System.out.println("Exception :" + e.getMessage());
            return null;
        }

        /*Disconnect from DB*/
        disconnectFromDB();

        /*Return result*/
        return result;
    }

    public ResultSet executeValidation(String sqlQuery){
       connectToDB();

       try{
         Statement stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
         return stmt.executeQuery(sqlQuery);
       } catch (SQLException e) {
         System.out.println(e.getMessage());
       } catch (Exception e) {
         System.out.println("Unknown exception");
         System.out.println(e.getMessage());
       }

       return null;
    }
}
