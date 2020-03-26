package project.persist;

import project.dto.Inventory;
import project.dto.Vehicle;

public interface DealerInventroy {
	public void addVehicle(Vehicle vehicle);

	public void deleteVehicle(Vehicle vehicle);

	public void modifyVehicle(String vin, Vehicle vehicle);

	public Inventory getInventoryForDealer(String dealerId);
}
