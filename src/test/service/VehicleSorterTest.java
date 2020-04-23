package service;

import dto.BigDataType;
import dto.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleSorterTest {
  VehicleSearchFilter vsf;
  DealerSearchFilter dsf;
  IncentiveSearchFilter isf;
  SortFilter dummy;
  Search s;

  @BeforeEach
  void initialize() {
    vsf = new VehicleSearchFilter(1);
    //dsf = new DealerSearchFilter("WA98109", 1, 2);
    isf = new IncentiveSearchFilter(19);
    SortFilter dummy = new SortFilter();
  }

  @Test
  void sort() {
    s = new Search(vsf, dummy);
    s.doSearch();
    for (BigDataType v : s.getResults()) {
      System.out.println(((Vehicle) v).getVehicleId());
    }
  }
}