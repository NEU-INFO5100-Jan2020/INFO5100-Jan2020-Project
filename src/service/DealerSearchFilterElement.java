package service;

public class DealerSearchFilterElement extends SearchFilterElement {
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

    DealerSearchCriterion(String key) {
      // private constructor that binds key string to enum element
      this.key = key;
    }

    public String getKey() {
      // getter of key of enum
      return key;
    }
  }
}
