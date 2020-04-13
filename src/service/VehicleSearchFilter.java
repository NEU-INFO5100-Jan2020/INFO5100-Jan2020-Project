package service;

import java.util.ArrayList;
import java.util.List;

public class VehicleSearchFilter implements SearchFilter {
  /*
  SearchFilter implementation for Vehicle GUI
   */
  int dealerID; // The must have input for Vehicle Search GUI
  List<VehicleSearchFilterElement> elements;

  public VehicleSearchFilter(int dealerID) {
    // Constructor
    elements = new ArrayList<>();
    this.dealerID = dealerID;
  }

  public int getDealerID() {
    return dealerID;
  }

  public void addElement(VehicleSearchFilterElement element) {
    elements.add(element);
  }

  @Override
  public List<VehicleSearchFilterElement> getElements() {
    return null;
  }
}
