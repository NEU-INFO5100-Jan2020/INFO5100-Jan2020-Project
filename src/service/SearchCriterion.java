package service;
public class SearchCriterion {
  /*
  SearchCriterion class serves as a template of data structure that how parameters all GUIs need to pass into our services
   */
  String[] mandatoryInput;
  String[] optionalSearchFilters;

  public SearchCriterion(String[] mandatoryInput, String[] optionalSearchFilters) {
    /*
    Constructor of SearchCriterion, still takes mandatory and optional parameters
     */
    this.optionalSearchFilters = optionalSearchFilters;
    this.mandatoryInput = mandatoryInput;
  }
}
