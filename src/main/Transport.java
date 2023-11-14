import java.awt.*;
import java.util.ArrayList;

public class Transport<T extends Towable> extends Vehicle {
    
    private boolean bedDown = false;
    private ArrayList<T> vehicles = new ArrayList<>();
	private final int capacity; 
    
    public Transport(int capacity) {
		super(
			2, 					// nrDoors
			125,				// enginePower
			Color.red,  		// color
			"Biltransport1337"	// modelName
		);
		this.capacity = capacity;
    }

	public boolean getBedDown() {
		return bedDown;
	}

	public ArrayList<T> getVehicles() {
		return vehicles;
	}

	public int getCapacity() {
		return capacity;
	}

    public void lowerBed(){
        if (currentSpeed == 0);
        bedDown = true;
    }

    public void raiseBed(){
        if (currentSpeed == 0);
        bedDown = false;
    }

    @Override
    public void gas(double amount) {
		if (bedDown) return;
        super.gas(amount);
		for (T vehicle : vehicles){
            vehicle.setCurrentSpeed(currentSpeed);
        }
    }

    @Override
    public void brake(double amount) {	
		super.brake(amount);
		for (T vehicle : vehicles){
            vehicle.setCurrentSpeed(currentSpeed);
        }
    }

    @Override
    public void move() {
		super.move();
        for (T vehicle : vehicles){
            vehicle.setPosition(position);
        }
	}

	private double distanceToVehicle(T vehicle) {
		double[] pos1 = getPosition();
		double[] pos2 = vehicle.getPosition();
		return Math.sqrt(Math.pow(pos2[0] - pos1[0], 2) + Math.pow(pos2[1] - pos1[1], 2)); // Eucledian distance formula
	}

    public void loadVehicle(T vehicle) {
		if (vehicles.size() + 1 <= capacity && bedDown &&  distanceToVehicle(vehicle) <= 5) {
            vehicles.add(vehicle);
        }
    }

    public void unloadVehicles(int unloadCount){
		if (!bedDown) return;
		
		int loadedCount = vehicles.size();
		unloadCount = unloadCount > loadedCount ? loadedCount : unloadCount; 

		int uppperBount = Math.max(0, loadedCount - 1);
		int lowerBound = loadedCount - unloadCount;
		for (int i = uppperBount; i >= lowerBound; i--){
			T vehicle  = vehicles.get(i);
			double[] unloadPosition = { position[0] - i, position[1] - i };
			vehicle.setPosition(unloadPosition);
			vehicles.remove(i);
		}
    }
}
