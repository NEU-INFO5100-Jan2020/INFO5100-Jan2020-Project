package service;

import dto.BigDataType;
import dto.Dealer;
import dto.Vehicle;
import persist.DealerManager;
import persist.DealerManagerImpl;
import persist.VehicleManager;
import persist.VehicleManagerImpl;

import java.util.List;


public interface DataGetter {
  /*
  Interface DataGetter,
   */
  List<? extends BigDataType> get();
}

class IncentiveGetter implements DataGetter {
  IncentiveSearchFilter isf;
  public IncentiveGetter(SearchFilter sf){
    isf = (IncentiveSearchFilter) sf;
  }

  @Override
  public List<dto.Vehicle> get() {
    VehicleManager v = new VehicleManagerImpl(); // Team 1 should have a VehicleManagerImpl which implements VehicleManager
    return (List<Vehicle>) v.getListOfVehiclesBasedOnDealerId(isf);
  }
}

class VehicleGetter implements DataGetter {
  VehicleSearchFilter vsf;

  public VehicleGetter(VehicleSearchFilter vsf) {
    /*
    Constructor of Vehicle getter
     */
    this.vsf = vsf;
  }

  @Override
  public List<dto.Vehicle> get() {
    VehicleManager v = new VehicleManagerImpl(); // Team 1 should have a VehicleManagerImpl which implements VehicleManager
    return (List<Vehicle>) v.getListOfVehiclesBasedOnDealerId(vsf);
  }
}

class DealerGetter implements DataGetter {
  DealerSearchFilter dsf;

  public DealerGetter(DealerSearchFilter dsf) {
    this.dsf = dsf;
  }

  @Override
  public List<Dealer> get() {
    DealerManager d = new DealerManagerImpl();
    return (List<Dealer>) d.getDealerDetails(dsf); // TODO: 4/12/2020 Connect with Poonam to finalize the input of get functions
  }
}

