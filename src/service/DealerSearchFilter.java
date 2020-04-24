package service;

import dto.Dealer;
import org.json.JSONArray;
import org.json.JSONObject;
import persist.DealerManager;
import persist.DealerManagerImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DealerSearchFilter implements SearchFilter {
  List<DealerSearchFilterElement> elements;

  public DealerSearchFilter() {
    elements = new ArrayList<>();
  }
  public DealerSearchFilter(String dealerName, String zipCode, int minRadius, int maxRadius) {
    elements = new ArrayList<>();
  }

  public Collection<Dealer> dealerZipSearch(String dealerName, String zipCode, int minRadius, int maxRadius) throws Exception {
    String url = "https://api.zip-codes.com/ZipCodesAPI.svc/1.0/FindZipCodesInRadius?zipcode=" + zipCode + "&minimumradius=" + minRadius + "&maximumradius=" + maxRadius + "&key=1FCKOZBYVBO2673VS3BT";
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuilder response = new StringBuilder();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    response.replace(0, 1, " ");
    JSONObject myResponse = new JSONObject(response.toString());
    if (myResponse.has("Error")){
      return null;
    }
    JSONArray DataList = myResponse.getJSONArray("DataList");
    ArrayList<String> arr = new ArrayList<>();
    for (int index = 0; index < DataList.length(); index++) {
      JSONObject obj1 = DataList.getJSONObject(index);
      String str = obj1.getString("Code");
      for (int i = 0; i < 1; i++) {
        arr.add(i, "WA" + str);
      }
    }
    Collections.reverse(arr);
    DealerManagerImpl dmi = new DealerManagerImpl();
    return dmi.getDealerDetails("" + dealerName, arr);
  }

  @Override
  public List<? extends SearchFilterElement> getElements() {
    return this.elements;
  }

  public void addElement(DealerSearchFilterElement dsfe) {
    elements.add(dsfe);
  }
}

