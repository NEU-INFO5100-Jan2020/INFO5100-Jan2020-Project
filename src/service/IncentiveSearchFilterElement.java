package service;

public class IncentiveSearchFilterElement extends SearchFilterElement {
  /*
  FilterElement for Incentive GUI: case 5, team 3
  @Ekie may implement this class based on my implementation above
   */
  IncentiveSearchCriterion enumKey;
  public IncentiveSearchFilterElement(IncentiveSearchCriterion key, String value) {
    this.name = key.getKey();
    this.value = value;
    this.enumKey = key;
  }

  public enum IncentiveSearchCriterion {
    /*
    Enum objects include all possible optional search filter of Vehicle Search GUI
     */
    VIN("Vin"),
    MAKE("Make"),
    MAXPrice("Price"),
    MINPrice("Price"),
    NEW("Category");

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

  @Override
  public String getValue() {
    return super.getValue();
  }

  @Override
  public String getName() {
    return super.getName();
  }

  public IncentiveSearchCriterion getEnumKey() {
    return enumKey;
  }
}
