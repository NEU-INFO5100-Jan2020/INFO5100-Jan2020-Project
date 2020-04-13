package service;

public class IncentiveSearchFilter extends SearchFilter {
  /*
  SearchFilter implementation for Incentive GUI
 */
  int dealerID;

  public IncentiveSearchFilter(int dealerID) {
    super();
    this.dealerID = dealerID;
  }
}
