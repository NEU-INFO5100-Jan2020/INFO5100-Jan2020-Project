package service;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ColorJsonPopulator {
  public static Map<String,String> populateColorContainer() {
    Gson gson = new Gson();
    String jsonPath = "Documents/color.json";
    try {
      BufferedReader br = new BufferedReader(new FileReader(jsonPath));
      Map<String,String> map = gson.fromJson(br, Map.class);
      return map;
    } catch (IOException e){
      e.printStackTrace();
    }
    return null;
  }

}


class Color {

  @SerializedName("color")
  @Expose
  private String color;
  @SerializedName("colorCode")
  @Expose
  private String colorCode;

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getColorCode() {
    return colorCode;
  }

  public void setColorCode(String colorCode) {
    this.colorCode = colorCode;
  }

}