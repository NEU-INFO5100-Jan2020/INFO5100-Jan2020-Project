package service;

import dto.BigDataType;
import dto.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {
  VehicleSearchFilter vsf;
  DealerSearchFilter dsf;
  IncentiveSearchFilter isf;
  SortFilter dummy;
  Search s;

  void initialize() {
    vsf = new VehicleSearchFilter(1);
    dsf = new DealerSearchFilter("WA98109", 1, 2);
    isf = new IncentiveSearchFilter(1);
    SortFilter dummy = new SortFilter();
  }

  @Test
  void doSearch() {
    initialize();
    s = new Search(vsf, dummy);
    s.doSearch();
    for (BigDataType v: s.getResults()){
      Vehicle vehicle = (Vehicle) v;
      System.out.println(vehicle.getVehicleId());
      System.out.println(vehicle.getVin());
    }
  }

  @Test
  void IncentiveSearch() {
    initialize();
    IncentiveSearchFilterElement isfe1 = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.NEW, "Used");
    IncentiveSearchFilterElement isfe2 = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MAXPrice, "10000");
//    IncentiveSearchFilterElement isfe3 = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.NEW, "New");

    isf.addElement(isfe1);
    isf.addElement(isfe2);
//    isf.addElement(isfe3);
    s = new Search(isf, dummy);
    s.doSearch();
    for (BigDataType v: s.getResults()){
      Vehicle vehicle = (Vehicle) v;
      System.out.println(vehicle.getVehicleId());
      System.out.println(vehicle.getVin());
    }
  }

  @Test
  void testConstructor() {
    SearchFilter vsf = new VehicleSearchFilter(1);
    SearchFilter dsf = new DealerSearchFilter("WA98109", 1, 2);
    SearchFilter isf = new IncentiveSearchFilter(1);
    SortFilter dummySortFilter = new SortFilter();
    Search s = new Search(vsf, dummySortFilter);
    Search s1 = new Search(dsf, dummySortFilter);
    Search s2 = new Search(isf, dummySortFilter);
    System.out.println(s.getFactory());
    System.out.println(s.getResults());
    System.out.println(s.getSerf());
    System.out.println(s.getSorf());
    System.out.println(s1.getFactory());
    System.out.println(s1.getResults());
    System.out.println(s1.getSerf());
    System.out.println(s1.getSorf());
    System.out.println(s2.getFactory());
    System.out.println(s2.getResults());
    System.out.println(s2.getSerf());
    System.out.println(s2.getSorf());
  }

  @Test
  void getArrayOfVID(){
    initialize();
    IncentiveSearchFilterElement isfe1 = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.NEW, "New");
    IncentiveSearchFilterElement isfe2 = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MAXPrice, "50000");
    isf.addElement(isfe1);
    isf.addElement(isfe2);
    s = new Search(isf, dummy);
    s.doSearch();
    for (int vid: s.getArrayOfVehicleID()){
      System.out.println(vid);
    }
  }
}