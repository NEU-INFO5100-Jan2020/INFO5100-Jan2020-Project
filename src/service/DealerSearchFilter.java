package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealerSearchFilter implements SearchFilter {
  private final int minradius = 0;
  private final int maxradius = 100;
  private final String zip = " ";
  List<DealerSearchFilterElement> elements;

  /*
    SearchFilter implementation for Dealer Search GUI
   */
  public DealerSearchFilter(String zip, int minradius, int maxradius) {
    elements = new ArrayList<>();
  }


//  public ArrayList<String> zipCodeRadius(String zip, int minradius, int maxradius) {
//    String url = "https://api.zip-codes.com/ZipCodesAPI.svc/1.0/FindZipCodesInRadius?zipcode=" + zip + "&minimumradius=" + minradius + "&maximumradius=" + maxradius + "&key=CKJ5LCW9PZAFNVNA8WFN";
//    URL obj = new URL(url);
//    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//    con.setRequestMethod("GET");
//    con.setRequestProperty("User-Agent", "Mozilla/5.0");
//    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//    String inputLine;
//    StringBuffer response = new StringBuffer();
//    while ((inputLine = in.readLine()) != null) {
//      response.append(inputLine);
//    }
//    in.close();
//    //Uncomment below code if you wish to see the raw response from the API:
//    //System.out.println(response.toString());
//    //Replace the start of the JSON o/p with blank because JSON should start with {
//    response.replace(0, 3, " ");
//    //Read JSON response and print
//    JSONObject myResponse = new JSONObject(response.toString());
//    /*Within the JSON response from the API, there is a JSONArray which has different keys and their corresponding values
//     * example: "City" is a key and the value would be Bellevue*/
//    JSONArray DataList = myResponse.getJSONArray("DataList");
//    //Uncomment below code to see the length of response from the API call for a particular zip code
//    //Iterating through the indexes of the Array but skipping element 0 as it is the entered zip code
//    ArrayList<String> arr = new ArrayList<>();
//    for (int index = 1; index < DataList.length(); index++) {
//      JSONObject obj1 = DataList.getJSONObject(index);
//
//      /*The result below can be String Array, List or Collection based on preference for your GUI output*/
//      //System.out.println(obj1.getString("City") + " " + obj1.getString("Code") + " " + obj1.getString("County") + " COUNTY" + " " + obj1.getDouble("Distance"));
//      String str = obj1.getString("Code");
//      for (int i = 0; i < 1; i++) {
//        arr.add(i, str);
//      }
//    }
//    Collections.reverse(arr);
//    //System.out.println(arr);
//    return arr;
//  }

  @Override
  public List<? extends SearchFilterElement> getElements() {
    return this.elements;
  }
}
