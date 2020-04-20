package service;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MakeModelFromJson {

  public static List<MakeModelVer2> populateMakeModel() {
    Gson gson = new Gson();

    String jsonPath = "Documents/car-models.json";
    try {
      BufferedReader br = new BufferedReader(new FileReader(jsonPath));
      Type MakeModelListType = new TypeToken<ArrayList<MakeModelVer2>>(){}.getType();
      List<MakeModelVer2> makeModelVer2s = gson.fromJson(br, MakeModelListType);
      return makeModelVer2s;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}

class MakeModelVer2 {

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