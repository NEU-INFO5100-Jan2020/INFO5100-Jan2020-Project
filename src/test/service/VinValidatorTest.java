package service;

import dto.Vehicle;
import org.junit.jupiter.api.Test;
import persist.VehicleManager;
import persist.VehicleManagerImpl;

import java.sql.SQLException;
import java.util.ArrayList;

class VinValidatorTest {

  @Test
  void validateVin() throws SQLException {
    VinValidator vinValidator = new VinValidator();
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

    assert vinValidator.validateVin(v);
    assert !vinValidator.validateVin(v2);
    assert vinValidator.validateVin(v3);
    assert !vinValidator.validateVin(v4);
  }
}