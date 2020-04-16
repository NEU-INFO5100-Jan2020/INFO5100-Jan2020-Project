package ui.UseCase1ListOfDealer.Validator;

import java.util.regex.Pattern;

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

    public static boolean isAValidZipCode(String zip) {
        return Pattern.matches(regex, zip);
    }

}