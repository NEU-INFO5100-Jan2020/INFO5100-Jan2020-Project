package service;


import dto.Vehicle;
import persist.ConnectionToSql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DealerUtilities {

  public DealerUtilities(){

  }

  public boolean validateVin(Vehicle vehicle) throws SQLException {
    String sqlValidationQuery = String.format("SELECT VIN FROM VehicleTable WHERE VIN = '%s' AND DealerID = '%s'", vehicle.getVin(), vehicle.getDealerId());
    ConnectionToSql connectionToSql = new ConnectionToSql();
    ResultSet resultSet = connectionToSql.executeValidation(sqlValidationQuery);
    return !resultSet.next();
  }

  public boolean validateDealerID(int DealerID) throws SQLException {
    String sqlValidationQuery = String.format("SELECT DealerID FROM Dealer WHERE DealerID = '%d'", DealerID);
    ConnectionToSql connectionToSql = new ConnectionToSql();
    ResultSet resultSet = connectionToSql.executeValidation(sqlValidationQuery);
    return resultSet.next();
  }
}
