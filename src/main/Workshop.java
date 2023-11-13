import java.util.ArrayList;
public class Workshop<T> {
    private int capacity;
    private int totalVehicles = 0;
    private ArrayList<T> vehicles = new ArrayList<>();
    
	public Workshop(int capacity) {
        this.capacity = capacity;
    }

    public void registerVehicle(T vehicle) {
        if (totalVehicles + 1 <= capacity) {
            totalVehicles += 1;
            vehicles.add(vehicle);
        }
    }

    public ArrayList<T> getVehicles() {
        return vehicles;
    }

    public T releaseVehicle(int index) {
        if (index < 0 || index > totalVehicles) return null;
		T car = vehicles.get(index);
		vehicles.remove(index);
		return car;
    }

}
