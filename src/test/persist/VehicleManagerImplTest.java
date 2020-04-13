package persist;

import org.junit.jupiter.api.Test;
import service.VehicleSearchFilter;
import service.VehicleSearchFilterElement;

import static org.junit.jupiter.api.Assertions.*;

class VehicleManagerImplTest {
  VehicleManagerImpl vmi;
  void initialize(){
    vmi = new VehicleManagerImpl();
  }
  @Test
  void testInitialize() {
    initialize();

  }

  @Test
  void getVehicles() {
    initialize();
    VehicleSearchFilter vsf = new VehicleSearchFilter(1);
    VehicleSearchFilterElement vsfe1 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MODEL, "Accord");
    VehicleSearchFilterElement vsfe2 = new VehicleSearchFilterElement(VehicleSearchFilterElement.VehicleSearchCriterion.MAKE, "Honda");

    vsf.addElement(vsfe1);
    vsf.addElement(vsfe2);
    vmi.getVehicles(vsf);

  }

  @Test
  void addVehicle() {
  }

  @Test
  void updateVehicle() {
  }

  @Test
  void deleteVehicle() {
  }
}