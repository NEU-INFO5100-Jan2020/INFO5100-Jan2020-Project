package service;

import dto.Vehicle;
import persist.ConnectionToSql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VinValidator {
  public boolean validateVin(Vehicle vehicle) throws SQLException {
    String sqlValidationQuery = String.format("SELECT VIN FROM VehicleTable WHERE VIN = '%s' AND DealerID = '%s'", vehicle.getVin(), vehicle.getDealerId());
    ConnectionToSql connectionToSql = new ConnectionToSql();
    ResultSet resultSet = connectionToSql.executeVinValidationQuery(sqlValidationQuery);
    return resultSet.next();
  }
}
