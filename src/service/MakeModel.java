package service;

import persist.ConnectionToSql;

import java.util.ArrayList;
import java.util.Collection;

public class MakeModel {
  String make;
  Collection<String> models;

  public MakeModel() {

  }

  public MakeModel(String make) {
    this.make = make;
    models = new ArrayList<>();
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
  Collection<MakeModel> makeModels;

  MakeModel getMakeModel(String make) {
    for (MakeModel mm : makeModels) {
      if (mm.make.equals(make)) {
        return mm;
      }
    }
    return null;
  }

  public Collection<MakeModel> getMakeModels() {
    return makeModels;
  }
  public MakeModelContainer(){
    makeModels = new ArrayList<>();
  }
  public void addMakeModel(MakeModel mm) {
    makeModels.add(mm);
  }
}

class MakeModelContainerPopulator {
  ConnectionToSql connect = new ConnectionToSql();

  public MakeModelContainer getMakeModels() {


    String query = "SELECT * FROM VehicleTable;";

    System.out.println(query);

    ArrayList<ArrayList> result = connect.executeVehicleQuery(query, "SELECT");

    return convertToMakeModelContainer(result);
  }


  private MakeModelContainer convertToMakeModelContainer(ArrayList<ArrayList> sqlQueryOutput) {
    MakeModelContainer mmc = new MakeModelContainer();
    for (int i = 0; i < sqlQueryOutput.size(); i++) {
      ArrayList temp = sqlQueryOutput.get(i);


      MakeModel d = mmc.getMakeModel(temp.get(3).toString());
      if (d == null) {
        d = new MakeModel(temp.get(3).toString());
        d.addModelToModels(temp.get(4).toString());
        mmc.addMakeModel(d);
      }
      if (!d.getModels().contains(temp.get(4))) {
        d.addModelToModels(temp.get(4).toString());
      }
    }
    return mmc;
  }
}

// get make from db
// get models of make from db
// store the make/model relationship within MakeModel
// store it within MakeModelContainer