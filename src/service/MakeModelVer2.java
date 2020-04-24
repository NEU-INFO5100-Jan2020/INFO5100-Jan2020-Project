package service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MakeModelVer2 {
  @SerializedName("brand")
  @Expose
  private String brand;
  @SerializedName("models")
  @Expose
  private List<String> models = null;

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public List<String> getModels() {
    return models;
  }

  public void setModels(List<String> models) {
    this.models = models;
  }
}
