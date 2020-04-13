package service;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
This file defines the general data transmission protocol between GUIs and our backend search algorithms.
The idea is similar to what we designed before but the implementation and usage will be more elegant.
We will define the mandatory inputs in the specific constructor and use enum to provide all possible optional search
filter
 */


abstract class SearchFilterElement {
  /*
  Base class of SearchFilterElement
   */
  String name;
  String value;

  String getName() {
    return name;
  }

  String getValue() {
    return value;
  }
}

class VehicleSearchFilterElement extends SearchFilterElement {
  /*
  FilterElement for Vehicle Search GUI: case 2, team 5
  Usage: Instantiate the search element with the constructor below. The input should be a Enum object within the options
  and String value as collected from user input if there is valid input of the specific field.
   */
  public VehicleSearchFilterElement(VehicleSearchCriterion key, String value) {
    /*
    Constructor of search element of search vehicle
     */
    this.name = key.key;
    this.value = value;
  }

  public enum VehicleSearchCriterion {
    /*
    Enum objects include all possible optional search filter of Vehicle Search GUI
     */
    MODEL("Model"),
    MAKE("Make"),
    YEAR("Year"),
    PRICE("Price");

    private final String key; // key is the String value of each enum element

    private VehicleSearchCriterion(String key) {
      // private constructor that binds key string to enum element
      this.key = key;
    }

    public String getKey() {
      // getter of key of enum
      return key;
    }
  }
}

class IncentiveSearchFilterElement extends SearchFilterElement {
  /*
  FilterElement for Incentive GUI: case 5, team 3
  @Ekie may implement this class based on my implementation above
   */
  public IncentiveSearchFilterElement(IncentiveSearchCriterion key, String value) {
    this.name = key.key;
    this.value = value;
  }

  public enum IncentiveSearchCriterion {
    /*
    Enum objects include all possible optional search filter of Vehicle Search GUI
     */
    MAXPrice("MaxPrice"),
    MINPrice("MinPrice"),
    NEW("New");

    private final String key; // key is the String value of each enum element

    private IncentiveSearchCriterion(String key) {
      // private constructor that binds key string to enum element
      this.key = key;
    }

    public String getKey() {
      // getter of key of enum
      return key;
    }
  }
}

class DealerSearchFilterElement extends SearchFilterElement {
  /*
  FilterElement implementation for Dealer search GUI: case 1, team 4
  @Lakshya may implement this class based on my implementation above
   */
  public DealerSearchFilterElement(DealerSearchCriterion key, String value) {
    /*
    Constructor of search element of search vehicle
     */
    this.name = key.key;
    this.value = value;
  }

  public enum DealerSearchCriterion {
    /*
    Enum objects include all possible optional search filter of Dealer Search GUI
     */
    DEALERNAME("DealerName"),
    ZIP("Zip");

    private final String key; // key is the String value of each enum element

    private DealerSearchCriterion(String key) {
      // private constructor that binds key string to enum element
      this.key = key;
    }

    public String getKey() {
      // getter of key of enum
      return key;
    }
  }
}


public class SearchFilter {
  /*
  The base class of Search Filters, container of Search Elements.
   */
  List<SearchFilterElement> elements; // Data structure containing Search elements

  public SearchFilter() {
    // Constructor
    elements = new ArrayList<>();
  }

  public void addElement(SearchFilterElement element) {
    // method that add SearchFilterElement to SearchFilter
    this.elements.add(element);
  }

  public List<SearchFilterElement> getElements() {
    return this.elements;
  }
}


class VehicleSearchFilter extends SearchFilter {
  /*
  SearchFilter implementation for Vehicle GUI
   */
  int dealerID; // The must have input for Vehicle Search GUI

  public VehicleSearchFilter(int dealerID) {
    // Constructor
    super(); // Call the inherited constructor which initializes List<SearchElement>
    this.dealerID = dealerID;
  }
}

class IncentiveSearchFilter extends SearchFilter {
  /*
  SearchFilter implementation for Incentive GUI
 */
  int dealerID;

  public IncentiveSearchFilter(int dealerID) {
    super();
    this.dealerID = dealerID;
  }
}

class DealerSearchFilter extends SearchFilter {
  private final String zip = " ";
  /*
    SearchFilter implementation for Dealer Search GUI
   */
  public DealerSearchFilter(String zip, int minradius, int maxradius) {
    super();
  }
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
        arr.add(i, str);
        arr.add(i, "WA" + str);
      }
    }
    Collections.reverse(arr);
    return arr;
  }
}