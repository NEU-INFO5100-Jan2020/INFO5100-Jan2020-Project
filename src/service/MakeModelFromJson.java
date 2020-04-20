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

  public void populateMakeModel() {
    Gson gson = new Gson();

    String jsonPath = "Documents/car-models.json";
    try {
      BufferedReader br = new BufferedReader(new FileReader(jsonPath));
      Type MakeModelListType = new TypeToken<ArrayList<MakeModelVer2>>(){}.getType();
      List<MakeModelVer2> makeModelVer2s = gson.fromJson(br, MakeModelListType);

      for (MakeModelVer2 makeModelVer2: makeModelVer2s) {
        System.out.println(makeModelVer2.getBrand());
        System.out.println(makeModelVer2.getModels());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
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