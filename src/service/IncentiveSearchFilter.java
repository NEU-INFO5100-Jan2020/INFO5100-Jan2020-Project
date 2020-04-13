package service;

import java.util.ArrayList;
import java.util.List;

public class IncentiveSearchFilter implements SearchFilter {
  /*
  SearchFilter implementation for Incentive GUI
 */
  int dealerID;
  List<IncentiveSearchFilterElement> elements;

  public IncentiveSearchFilter(int dealerID) {
    this.dealerID = dealerID;
    elements = new ArrayList<>();
  }

  @Override
  public List<? extends SearchFilterElement> getElements() {
    return elements;
  }
}
