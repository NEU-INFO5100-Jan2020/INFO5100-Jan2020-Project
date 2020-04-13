package service;


import java.util.ArrayList;
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
    ZIP("Zip"),
    MINRADIUS("MinRadius"),
    MAXRADIUS("MaxRadius");

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


