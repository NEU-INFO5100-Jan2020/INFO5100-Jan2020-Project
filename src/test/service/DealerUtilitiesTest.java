package service;

import dto.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persist.VehicleManager;
import persist.VehicleManagerImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DealerUtilitiesTest {
  DealerUtilities dealerUtilities;
  @BeforeEach
  void init() {
    dealerUtilities = new DealerUtilities();
  }
  @Test
  void validateVin() throws SQLException {
    VehicleManager vm = new VehicleManagerImpl();
    ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vm.getVehiclesBasedOnDealerId(1);
    Vehicle v = vehicles.get(0);
    Vehicle v2 = new Vehicle();
    Vehicle v3 = new Vehicle();
    Vehicle v4 = new Vehicle();
    v2.setVin(10000);
    v2.setDealerId(0);
    v3.setVin(1008);
    v3.setDealerId(20);
    v4.setVin(1008);
    v4.setDealerId(2);

    assert !dealerUtilities.validateVin(v);
    assert dealerUtilities.validateVin(v2);
    assert !dealerUtilities.validateVin(v3);
    assert dealerUtilities.validateVin(v4);
  }

  @Test
  void validateDealerID() throws SQLException {
    assert dealerUtilities.validateDealerID(1);
    assert dealerUtilities.validateDealerID(2);
    assert dealerUtilities.validateDealerID(3);
    assert dealerUtilities.validateDealerID(39);
    assert dealerUtilities.validateDealerID(38);
    assert dealerUtilities.validateDealerID(37);
    assert !dealerUtilities.validateDealerID(100);
    assert !dealerUtilities.validateDealerID(-100);
    assert !dealerUtilities.validateDealerID(-217979873);

  }

  @Test
  void testAddImageToAzureBlob(){
    String testPath = "src\\main\\resources\\CarImages\\1.jpeg";
    dealerUtilities.addImageToAzureBlob(testPath , 10000);
  }

//  @Test
//  void testGetImageFromAzure(){
//    for (int i = 1; i <= 9; i++){
//      dealerUtilities.getImageFromAzureBlob(i);
//    }
//  }
}