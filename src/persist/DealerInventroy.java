package persist;

import dto.Inventory;
import dto.Vehicle;

public interface DealerInventroy {
	public void addVehicle(Vehicle vehicle);

	public void deleteVehicle(Vehicle vehicle);

	public void modifyVehicle(String vin, Vehicle vehicle);

	public Inventory getInventoryForDealer(String dealerId);
}
