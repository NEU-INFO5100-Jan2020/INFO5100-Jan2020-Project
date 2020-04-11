package service;
import java.util.List;

import dto.Vehicle;
import persist.VehicleManager;


public interface DataGetter {
  /*
  Interface DataGetter,
   */
  List<? extends BigDataType> get();
}

class IncentiveGetter implements DataGetter{

  @Override
  public List<?extends BigDataType> get(VehicleSearchFilter sf) {
    return null;
  }
}

class VehicleGetter implements DataGetter{
  VehicleSearchFilter vsf;
  @Override
  public List<dto.Vehicle> get() {
    VehicleManager v = new VehicleManagerImpl(); // Team 1 should have a VehicleManagerImpl which implements VehicleManager
    List<Vehicle> vehicles =  v.getListOfVehiclesBasedOnDealerId(vsf); // They should change the input to sf object and use vsf.id as id and vsf.elements as Optional filters
    return vehicles;
  }

  public VehicleGetter(VehicleSearchFilter vsf){
    /*
    Constructor of Vehicle getter
     */
    this.vsf = vsf;
  }
}

class DealerGetter implements DataGetter{


  @Override
  public List<Vehicle> get(SearchFilter sf) {
    return null;
  }
}