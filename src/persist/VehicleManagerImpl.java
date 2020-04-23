package persist;

import dto.Vehicle;
import service.IncentiveSearchFilter;
import service.IncentiveSearchFilterElement;
import service.VehicleSearchFilter;
import service.VehicleSearchFilterElement;

import java.util.ArrayList;
import java.util.Collection;

public class VehicleManagerImpl implements VehicleManager {
  ConnectionToSql connect = new ConnectionToSql();

  @Override
  public Collection<Vehicle> getVehiclesBasedOnDealerId(int dealerId) {
    String query = "SELECT * from VehicleTable WHERE DealerId=" + dealerId + " ;";

    /*Call 'executeQuery' method to run the query*/
    ArrayList<ArrayList> result = connect.executeVehicleQuery(query, "SELECT");

    /*Convert to Vehicle object*/
    ArrayList<Vehicle> vehicle = convertToVehicleObject(result);

    return vehicle;
  }

  @Override
  public Collection<Vehicle> getVehicles(VehicleSearchFilter vsf) {
    /*Make , Model , Year , Price are optional fields. If passed, then add to the query*/
    /*DealerId is mandatory passed*/
    String mandatoryFilter = "DealerId = " + vsf.getDealerID() + " ";
    // Initialize a StringBuilder to build query String
    StringBuilder filterString = new StringBuilder("SELECT * FROM VehicleTable WHERE " + mandatoryFilter);
    // Iterate the list of VehicleFilterElement if it is not null, add info extracted from SearchFilterElement to StringBuilder
    if (vsf.getElements() != null) {
      for (VehicleSearchFilterElement vse : vsf.getElements()) {
        filterString.append(" and ").append(vse.getName());
        if (vse.getEnumKey().equals(VehicleSearchFilterElement.VehicleSearchCriterion.PRICE)){
          filterString.append("<").append("'").append(vse.getValue()).append("'");
        } else {
          filterString.append("=").append("'").append(vse.getValue()).append("'");
        }
      }
    }
    filterString.append(";");

    /*Final select query*/
    String finalQuery = filterString.toString();
    System.out.println(finalQuery);
    /*Call 'executeQuery' method to run the query*/
    ArrayList<ArrayList> result = connect.executeVehicleQuery(finalQuery, "SELECT");

    /*Convert to Vehicle object*/
    ArrayList<Vehicle> vehicleResult = convertToVehicleObject(result);

    return vehicleResult;
  }

  @Override
  public Collection<Vehicle> getVehiclesForCase5(IncentiveSearchFilter vsf){
    /*VIN, Make, MaxPrice, MinPrice, New, are optional fields. If passed, then add to the query*/
    /*DealerId is mandatory passed*/
    String mandatoryFilter = "DealerId = " + vsf.getDealerID() + " ";
    // Initialize a StringBuilder to build query String
    StringBuilder filterString = new StringBuilder("SELECT * FROM VehicleTable WHERE " + mandatoryFilter);
    // Iterate the list of VehicleFilterElement if it is not null, add info extracted from SearchFilterElement to StringBuilder
    if (vsf.getElements() != null) {
      for (IncentiveSearchFilterElement vse : vsf.getElements()) {
        filterString.append(" and ").append(vse.getName());
        if (vse.getEnumKey().equals(IncentiveSearchFilterElement.IncentiveSearchCriterion.MAXPrice)){
          filterString.append("<").append("'").append(vse.getValue()).append("'");
        } else if (vse.getEnumKey().equals(IncentiveSearchFilterElement.IncentiveSearchCriterion.MINPrice)) {
          filterString.append(">").append("'").append(vse.getValue()).append("'");
        } else {
          filterString.append("=").append("'").append(vse.getValue()).append("'");
        }
      }
    }
    filterString.append(";");

    /*Final select query*/
    String finalQuery = filterString.toString();
    System.out.println(finalQuery);
    /*Call 'executeQuery' method to run the query*/
    ArrayList<ArrayList> result = connect.executeVehicleQuery(finalQuery, "SELECT");

    /*Convert to Vehicle object*/
    ArrayList<Vehicle> vehicleResult = convertToVehicleObject(result);
    return vehicleResult;
  }

  @Override
  public Vehicle addVehicle(Vehicle vehicle) {
    String query = "INSERT INTO VehicleTable (VIN , DealerId ,Make , Model , Year , " +
            "Category , Price , Color , Miles, IncentiveId , DiscountPrice , Ratings) " +
            "VALUES ('"+vehicle.getVin()+"' , "+vehicle.getDealerId()+" , '"+vehicle.getMake()+"' , '"+vehicle.getModel()+
               "' , "+vehicle.getYear()+" , '"+vehicle.getCategory()+"' , "+vehicle.getPrice()+" , '"+vehicle.getColor()+
               "' , "+vehicle.getMileage()+" , '"+vehicle.getIncentiveId()+
               "' , "+vehicle.getDiscountPrice()+ " , "+vehicle.getRatings()+
               ") ;";

    /*Call 'executeQuery' method to run the query*/
    ArrayList<ArrayList> result = connect.executeVehicleQuery(query, "INSERT");

    return vehicle;
  }

  @Override
  public Vehicle updateVehicle(Vehicle vehicle) {
    String query = "UPDATE VehicleTable SET VIN='" + vehicle.getVin() + "' , DealerId=" + vehicle.getDealerId() +
            " , Make='" + vehicle.getMake() + "' , Model='" + vehicle.getModel() + "' , Year= " + vehicle.getYear() +
            " , Category = '" + vehicle.getCategory() + "' , Price = " + vehicle.getPrice() + " , Color = '" + vehicle.getColor() +
            "' , Miles = " + vehicle.getMileage() +
            " WHERE VehicleId=" + vehicle.getVehicleId() +
            " ;";

    /*Call 'executeQuery' method to run the query*/
    ArrayList<ArrayList> result = connect.executeVehicleQuery(query, "UPDATE");

    return vehicle;
  }

  @Override
  public Vehicle deleteVehicle(Vehicle vehicle) {
    String query = "DELETE FROM VehicleTable WHERE VehicleId = " + vehicle.getVehicleId() + " ;";

    /*Call 'executeQuery' method to run the query*/
    ArrayList<ArrayList> result = connect.executeVehicleQuery(query, "UPDATE");
    return vehicle;
  }

  private ArrayList<Vehicle> convertToVehicleObject(ArrayList<ArrayList> sqlQueryOutput) {
    ArrayList<Vehicle> vehicle = new ArrayList<>();

    for (int i = 0; i < sqlQueryOutput.size(); i++) {
      ArrayList temp = sqlQueryOutput.get(i);

      Vehicle v = new Vehicle();

      v.setVehicleId((Integer) temp.get(0));
      v.setVin((Integer) temp.get(1));
      v.setDealerId((Integer) temp.get(2));
      v.setMake(temp.get(3).toString());
      v.setModel(temp.get(4).toString());
      v.setYear((Integer) temp.get(5));
      v.setCategory(temp.get(6).toString());
      v.setPrice((Float) temp.get(7));
      v.setColor(temp.get(8).toString());
      v.setMileage((Integer) temp.get(9));
      //v.setImage((Image) temp.get(10));
      v.setIncentiveId(temp.get(11).toString());
      v.setDiscountPrice((Float) temp.get(12));
      v.setRatings((Integer)temp.get(13));

      vehicle.add(v);
    }

    return vehicle;
  }
}
