package service;

public class VehicleSearchFilter extends SearchFilter {
  /*
  SearchFilter implementation for Vehicle GUI
   */
  int dealerID; // The must have input for Vehicle Search GUI

  public VehicleSearchFilter(int dealerID) {
    // Constructor
    super(); // Call the inherited constructor which initializes List<SearchElement>
    this.dealerID = dealerID;
  }
}
