package service;

public abstract class SearchFilterElement {
  /*
  Base class of SearchFilterElement
   */
  String name;
  String value;

  String getName() {
    return name;
  }

  String getValue() {
    return value;
  }
}
