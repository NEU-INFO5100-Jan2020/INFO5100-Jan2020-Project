package ui.UseCase1ListOfDealer.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class Validation {
    private static final int maxDealerNameChars=100;
    private static  String regex = "^?[0-9]{5}(?:-[0-9]{4})?$";
//    ^[0-9]{5}(?:-[0-9]{4})?$#¢$
   // "^(?:[A-Za-z]{2})?[0-9]{5}(?:-[0-9]{4})?$"; (Example: WA98111)

    public static boolean isAValidDealerName(String name) {
        if(name.length()>maxDealerNameChars) {
            return false;
        }
        return true;
    };
    //Comment out this validation method as it only do format validation
    /*public static boolean isAValidZipCode(String zipCode) {
    	return Pattern.matches(regex, zipCode);
    }
    */

    public static boolean isAValidZipCode(String zipCode) throws Exception {
    	if (!Pattern.matches(regex, zipCode)) {
    		return false;
    	}       
        String url = "https://api.zip-codes.com/ZipCodesAPI.svc/1.0/QuickGetZipCodeDetails/"+ zipCode +"?&key=Q4OGENOO4JCXT4QTD965";
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
        //SubString(3) to get rid of the random chars in the returing json file
        JSONObject myResponse = new JSONObject(response.toString().trim().substring(3));        
        if (myResponse.toString().equals("{}")) return false;      
            return true;
        }

}