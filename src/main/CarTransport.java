import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends Car {
    
    private boolean bedDown;
    private ArrayList<Car> currentCarsOnBed;
    
    public CarTransport() {
		super(
			2, 			// nrDoors
			125,		// enginePower
			Color.red,  // color
			"Biltransport1337",	// modelName
			7495				// weight
		);
        currentCarsOnBed = new ArrayList<Car>();
	    bedDown = false;
    }

    public boolean getBedDown() {
        return bedDown;
    }

    public ArrayList<Car> getCurrentCarsOnBed(){ return currentCarsOnBed; }

    public void lowerBed(){
        if (currentSpeed == 0) {
            bedDown = true;
        }
    }

    public void raiseBed(){
        if (currentSpeed == 0) {
            bedDown = false;
        }
    }

    @Override
    public void gas(double amount) {
		if (bedDown) return;
        if (amount > 1 || amount < 0) return;
		incrementSpeed(amount);
    }

    @Override
    public void brake(double amount) {
		if (amount > 1 || amount < 0) return;
        decrementSpeed(amount);
    }

    private void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        for (Car car : currentCarsOnBed){
            car.setCurrentSpeed(currentSpeed);
        }
    }

    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        for (Car car : currentCarsOnBed){
            car.setCurrentSpeed(currentSpeed);
        }
    }

    @Override
    public void move() {
		super.move();
        for (Car car : currentCarsOnBed){
            car.move();
        }
	}


    public void loadCar(Car car){
        double[] carPosition = car.getPosition();
        if (car instanceof CarTransport) return;
        if (bedDown) {
            if (carPosition[0] > position[0]-5 && carPosition[0] < position[0]+5 && carPosition[1] > position[1]-5 && carPosition[1] < position[1]+5){
                currentCarsOnBed.add(car);
            }
        }
    }

    public void unloadCar(int numberOfCarsToUnload){
        int unloadAmount = numberOfCarsToUnload;
        int numberOfCarsOnBed = currentCarsOnBed.size();
        if (numberOfCarsToUnload > numberOfCarsOnBed){
            unloadAmount = numberOfCarsOnBed;}
        if (bedDown){
            for (int i = 0; i < unloadAmount; i++){
                Car car  = currentCarsOnBed.get(currentCarsOnBed.size() - 1);
                double[] currentPosition = {position[0]-i, position[1]-i};
                car.setPosition(currentPosition);
                currentCarsOnBed.remove(currentCarsOnBed.size() - 1);
            }
        }
    }
}
