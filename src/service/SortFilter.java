package service;

import java.util.ArrayList;
import java.util.Collection;

enum SortKey {

}


enum SortOrder {
  ASC, DESC
}

public class SortFilter {
  Collection<SortFilterElement> sortFilterElements = new ArrayList<>();
}

class SortFilterElement {
  SortKey key;
  SortOrder filter;
  SortFilterElement(SortKey key, SortOrder filter){
    this.key = key;
    this.filter = filter;
  }
}