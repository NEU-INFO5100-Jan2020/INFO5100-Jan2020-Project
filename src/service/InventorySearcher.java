package project.service;

public interface InventorySearcher {
	public Collection<Vehicle> searchInventory(String dealerId, VehicleSearchCriterion vsc);
	
	public Collection<Vehicle> sortInventory, String dealerId, VehicleSearchCriterion vsc, VehicleSortCriterion a);
	
	public VehicleFilters getVehicleFilters(String dealerID);
}
