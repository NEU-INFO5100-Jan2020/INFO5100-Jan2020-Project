package service;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealerSearchFilter implements SearchFilter {
  List<DealerSearchFilterElement> elements;
  public DealerSearchFilter(String zip, int minradius, int maxradius) {
    elements = new ArrayList<>();
  }
    private final String zip = " ";
    public ArrayList<String> zipCodeRadius(String zip, int minradius, int maxradius) throws Exception{
      String url = "https://api.zip-codes.com/ZipCodesAPI.svc/1.0/FindZipCodesInRadius?zipcode=" + zip + "&minimumradius=" + minradius + "&maximumradius=" + maxradius + "&key=F14RQVG8YDILP7E79JIP";
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("User-Agent", "Mozilla/5.0");
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      response.replace(0, 1, " ");
      JSONObject myResponse = new JSONObject(response.toString());
      JSONArray DataList = myResponse.getJSONArray("DataList");
      ArrayList<String> arr = new ArrayList<>();
      for (int index = 1; index < DataList.length(); index++) {
        JSONObject obj1 = DataList.getJSONObject(index);
        String str = obj1.getString("Code");
        for (int i = 0; i < 1; i++) {
          arr.add(i, "WA" + str);
        }
      }
      Collections.reverse(arr);
      return arr;
}

  @Override
  public List<? extends SearchFilterElement> getElements() {
    return this.elements;
  }
}

