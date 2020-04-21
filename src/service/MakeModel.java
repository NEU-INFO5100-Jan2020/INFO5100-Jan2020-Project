package service;

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


