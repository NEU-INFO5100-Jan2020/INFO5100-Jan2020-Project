package service;
import java.util.List;
/*
This file defines the general data transmission protocol between GUIs and our backend search algorithms.
The idea is similar to what we designed before but the implementation and usage will be more elegant.
We will define the mandatory inputs in the specific constructor and use enum to provide all possible optional search
filter
 */


public interface SearchFilter {
  /*
  The base class of Search Filters, container of Search Elements.
   */

  public List<? extends SearchFilterElement> getElements();
}


