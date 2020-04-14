package service;

public class VehicleSearchFilterElement extends SearchFilterElement {
  /*
  FilterElement for Vehicle Search GUI: case 2, team 5
  Usage: Instantiate the search element with the constructor below. The input should be a Enum object within the options
  and String value as collected from user input if there is valid input of the specific field.
   */
  public VehicleSearchFilterElement(VehicleSearchCriterion key, String value) {
    /*
    Constructor of search element of search vehicle
     */
    this.name = key.getKey();
    this.value = value;
  }

  public String getKey() {
    // getter of key of enum
    return this.name;
  }

  public String getValue() {
    return this.value;
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
      return key;
    }
  }
}
