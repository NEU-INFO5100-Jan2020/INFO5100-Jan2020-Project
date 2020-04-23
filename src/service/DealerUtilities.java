package service;


import dto.Vehicle;
import persist.ConnectionToSql;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import persist.ConnectionToAzureBlob;

public class DealerUtilities {

  public DealerUtilities(){

  }

  public boolean validateVin(Vehicle vehicle) throws SQLException {
    String sqlValidationQuery = String.format("SELECT VIN FROM VehicleTable WHERE VIN = '%s' AND DealerID = '%s'", vehicle.getVin(), vehicle.getDealerId());
    ConnectionToSql connectionToSql = new ConnectionToSql();
    try(Connection connection = connectionToSql.connectToDB()) {
      ResultSet resultSet = connectionToSql.executeValidation(sqlValidationQuery);
      return !resultSet.next();
    }
  }

  public boolean validateDealerID(int DealerID) throws SQLException {
    String sqlValidationQuery = String.format("SELECT DealerID FROM Dealer WHERE DealerID = '%d'", DealerID);
    ConnectionToSql connectionToSql = new ConnectionToSql();
    try(Connection connection = connectionToSql.connectToDB()) {
      ResultSet resultSet = connectionToSql.executeValidation(sqlValidationQuery);
      return resultSet.next();
    }
  }

  public String getImageFromAzureBlob(){

    return null;
  }

  public void addImageToAzureBlob(String path, int VIN){
    ConnectionToAzureBlob.initBlob(ConnectionToAzureBlob.connectString);
    File imageFile = new File(path);
//    String extension = "";
//    if (path.contains(".")){
//      extension = path.substring(path.lastIndexOf(".") + 1);
//    }
    String fileName = String.format("%d", VIN);
    ConnectionToAzureBlob.uploadFile(imageFile,fileName);
  }

}
