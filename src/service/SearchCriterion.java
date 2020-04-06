package service;
public class SearchCriterion {
  /*
  SearchCriterion class serves as a template of data structure that how parameters GUIs need to pass into our services
   */
  String signature;
  String[] optionalSearchFilters;

  public SearchCriterion(String signature, String[] optionalSearchFilters) {
    /*
    Constructor of SearchCriterion, still takes mandatory parameter and optional arguments
     */
    this.optionalSearchFilters = optionalSearchFilters;
    this.signature = signature;
  }
}
