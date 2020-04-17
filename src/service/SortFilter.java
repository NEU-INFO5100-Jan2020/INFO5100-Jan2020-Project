package service;

import java.util.ArrayList;
import java.util.Collection;

enum SortKey {
// TODO: 4/10/2020
}


enum SortOrder {
  ASC, DESC
}

public class SortFilter {
  Collection<SortFilterElement> sortFilterElements = new ArrayList<>();
}

class SortFilterElement {
  SortKey key;
  SortOrder order;
  SortFilterElement(SortKey key, SortOrder order){
    this.key = key;
    this.order = order;
  }
}
