package service;
import dto.BigDataType;
import dto.Dealer;
import dto.Vehicle;

import java.util.ArrayList;
import java.util.Collection;

public interface Sorter {
  ArrayList<? extends BigDataType> sort(Collection<? extends BigDataType> in);
}

class DealerSorter implements Sorter {

  @Override
  public ArrayList<? extends BigDataType> sort(Collection<? extends BigDataType> in) {
    return (ArrayList<Dealer>) in;
  }
}

class VehicleSorter implements Sorter {

  @Override
  public ArrayList<? extends BigDataType> sort(Collection<? extends BigDataType> in) {
     ((ArrayList<Vehicle> )in).sort(Vehicle::compareTo);

    return (ArrayList<Vehicle>) in;
  }
}

class IncentiveSorter implements Sorter {
  @Override
  public ArrayList<? extends BigDataType> sort(Collection<? extends BigDataType> in) {
    return (ArrayList<Vehicle>) in;
  }
}