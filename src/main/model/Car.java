package model;

import java.awt.*;

public abstract class Car extends Vehicle implements Transportable {

	public Car(int nrDoors, double enginePower, Color color, String modelName) {
		super(nrDoors, enginePower, color, modelName);
	}

	public void setCurrentSpeed(double amount){
		currentSpeed = amount;
	}
}
