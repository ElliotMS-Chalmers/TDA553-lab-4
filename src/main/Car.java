import java.awt.*;

public abstract class Car extends Vehicle implements Towable {

	public Car(int nrDoors, double enginePower, Color color, String modelName) {
		super(nrDoors, enginePower, color, modelName);
	}

	public void setPosition(double[] amount) {
		position = amount;
	}

	public void setCurrentSpeed(double amount){
		currentSpeed = amount;
	}
}
