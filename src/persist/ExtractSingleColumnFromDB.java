package persist;

import dto.Vehicle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ExtractSingleColumnFromDB {
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
    public ArrayList<Vehicle> executeVehicleQuery() {
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        Vehicle v = null;
        /*Connect to database*/
        connectToDB();
        try {
            /*Execute the query*/
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT DISTINCT Year, Make, Color FROM VehicleTable" +
                         " ORDER BY Year DESC" )) {

                while (resultSet.next()) {
                    v = new Vehicle();
                    v.setYear(resultSet.getInt("Year"));
                    v.setColor(resultSet.getString("Color"));
                    v.setMake(resultSet.getString("Make"));
                    result.add(v);
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
    public ArrayList<Vehicle> executeVehicleQuery1(String makeValue) {
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        Vehicle v = null;
        /*Connect to database*/
        connectToDB();
        try {
            /*Execute the query*/
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT DISTINCT Model FROM VehicleTable WHERE " +
                         "Make =" + makeValue)) {

                while (resultSet.next()) {
                    v = new Vehicle();
                    v.setModel(resultSet.getString("Model"));
                    result.add(v);
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

}
