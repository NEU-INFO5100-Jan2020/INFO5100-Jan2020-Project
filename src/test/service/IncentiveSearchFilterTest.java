package service;

import org.junit.jupiter.api.Test;

import java.util.List;


class IncentiveSearchFilterTest {
  @Test
  void addElement() {
    IncentiveSearchFilter isf = new IncentiveSearchFilter(1);
    IncentiveSearchFilterElement isfe1 = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MAXPrice, "50000");
    IncentiveSearchFilterElement isfe2 = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MINPrice, "40000");
    IncentiveSearchFilterElement isfe3 = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.NEW, "New");
    isf.addElement(isfe1);
    isf.addElement(isfe2);
    isf.addElement(isfe3);
    assert (isf.elements.contains(isfe1));
    assert (isf.elements.contains(isfe2));
    assert (isf.elements.contains(isfe3));
  }

  @Test
  void getElements() {
    IncentiveSearchFilter isf = new IncentiveSearchFilter(1);
    IncentiveSearchFilterElement isfe1 = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MAXPrice, "50000");
    IncentiveSearchFilterElement isfe2 = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.MINPrice, "40000");
    IncentiveSearchFilterElement isfe3 = new IncentiveSearchFilterElement(IncentiveSearchFilterElement.IncentiveSearchCriterion.NEW, "Yes");
    isf.addElement(isfe1);
    isf.addElement(isfe2);
    isf.addElement(isfe3);
    List<IncentiveSearchFilterElement> result = isf.getElements();
    for (IncentiveSearchFilterElement element : result) {
      System.out.println(element.getName());
      System.out.println(element.getValue());
    }
  }
}