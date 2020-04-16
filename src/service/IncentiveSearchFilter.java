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
  public List<IncentiveSearchFilterElement> getElements() {
    return elements;
  }

  public void addElement(IncentiveSearchFilterElement isfe) {
    elements.add(isfe);
  }

  public int getDealerID() {
    return dealerID;
  }
}
