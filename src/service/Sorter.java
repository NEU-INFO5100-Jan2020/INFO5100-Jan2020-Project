package service;
import java.util.ArrayList;
import java.util.Collection;

public interface Sorter {
  ArrayList<? extends BigDataType> sort(Collection<? extends BigDataType> in);
}

class DealerSorter implements Sorter {

  @Override
  public ArrayList<? extends BigDataType> sort(Collection<? extends BigDataType> in) {
    return (ArrayList<? extends BigDataType>) in;
  }
}

class VehicleSorter implements Sorter {

  @Override
  public ArrayList<? extends BigDataType> sort(Collection<? extends BigDataType> in) {
    return (ArrayList<? extends BigDataType>) in;
  }
}

class IncentiveSorter implements Sorter {
  @Override
  public ArrayList<? extends BigDataType> sort(Collection<? extends BigDataType> in) {
    return (ArrayList<? extends BigDataType>) in;
  }
}