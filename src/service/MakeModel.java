package service;

import persist.ConnectionToSql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MakeModel {
  String make;
  Collection<String> models;

  public MakeModel() {

  }

  public MakeModel(String make) {
    this.make = make;
  }

  public MakeModel(String make, Collection<String> models) {
    this.make = make;
    this.models = models;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public Collection<String> getModels() {
    return models;
  }

  public void setModels(Collection<String> models) {
    this.models = models;
  }

  public void addModelToModels(String model) {
    models.add(model);
  }
}


class MakeModelContainer {
  List<MakeModel> makeModels;

  MakeModel getMakeModel(String make) {
    for (MakeModel mm : makeModels) {
      if (mm.make.equals(make)) {
        return mm;
      }
    }
    return null;
  }

  public List<MakeModel> getMakeModels() {
    return makeModels;
  }
}

class MakeModelContainerPopulator {
  ConnectionToSql connect = new ConnectionToSql();

  public Collection<MakeModel> getMakeModels(VehicleSearchFilter vsf) {


    String query = "SELECT DISTINCT Make,Model FROM VehicleTable;";

    System.out.println(query);
    /*Call 'executeQuery' method to run the query*/
    ArrayList<ArrayList> result = connect.executeVehicleQuery(query, "SELECT");

    /*Convert to Vehicle object*/
    ArrayList<MakeModel> makeModelResult = convertToMakeModel(result);

    return makeModelResult;
  }


  private ArrayList<MakeModel> convertToMakeModel(ArrayList<ArrayList> sqlQueryOutput) {
    ArrayList<MakeModel> makeModels = new ArrayList<>();

    for (int i = 0; i < sqlQueryOutput.size(); i++) {
      ArrayList temp = sqlQueryOutput.get(i);

      MakeModelContainer mmc = new MakeModelContainer();
      MakeModel d = mmc.getMakeModel(temp.get(0).toString());
      if (d == null) {
        d = new MakeModel(temp.get(0).toString());
        d.addModelToModels(temp.get(1).toString());
        makeModels.add(d);
      }
      d.addModelToModels(temp.get(1).toString());
    }

    return makeModels;
  }
}

// get make from db
// get models of make from db
// store the make/model relationship within MakeModel
// store it within MakeModelContainer