package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MakeModelJsonPopulator {

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

