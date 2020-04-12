package service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleSearchFilterTest {

  @Test
  void addElement() {
    VehicleSearchFilter vsf = new VehicleSearchFilter(1);
    VehicleSearchFilterElement vsfe1 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MODEL, "Accord");
    VehicleSearchFilterElement vsfe2 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MAKE, "Honda");
    VehicleSearchFilterElement vsfe3 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.YEAR, "2007");
    VehicleSearchFilterElement vsfe4 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.PRICE, "10000");
    vsf.addElement(vsfe1);
    vsf.addElement(vsfe2);
    vsf.addElement(vsfe3);
    vsf.addElement(vsfe4);
    assert(vsf.elements.contains(vsfe1));
    assert(vsf.elements.contains(vsfe2));
    assert(vsf.elements.contains(vsfe3));
    assert(vsf.elements.contains(vsfe4));
  }

  @Test
  void getElements() {
    VehicleSearchFilter vsf = new VehicleSearchFilter(1);
    VehicleSearchFilterElement vsfe1 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MODEL, "Accord");
    VehicleSearchFilterElement vsfe2 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MAKE, "Honda");
    VehicleSearchFilterElement vsfe3 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.YEAR, "2007");
    VehicleSearchFilterElement vsfe4 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.PRICE, "10000");
    vsf.addElement(vsfe1);
    vsf.addElement(vsfe2);
    vsf.addElement(vsfe3);
    vsf.addElement(vsfe4);
    List<SearchFilterElement>result = vsf.getElements();
    for (SearchFilterElement element: result) {
      System.out.println(element.getName());
      System.out.println(element.getValue());
    }
  }
}