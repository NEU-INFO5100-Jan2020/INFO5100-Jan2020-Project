package service;

import java.util.List;

public class IncentiveSearchFilter implements SearchFilter {
  /*
  SearchFilter implementation for Incentive GUI
 */
  int dealerID;
  List<IncentiveSearchFilterElement> elements;

  public IncentiveSearchFilter(int dealerID) {
    super();
    this.dealerID = dealerID;
  }

  @Override
  public List<? extends SearchFilterElement> getElements() {
    return elements;
  }
}
