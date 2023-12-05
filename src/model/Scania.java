package model;

import java.awt.*;

public class Scania extends Vehicle {
	private double bedAngle = 0.0;
	private final double minBedAngle = 0.0;
	private final double maxBedAngle = 70.0;

	public Scania(){
		super(
			2, 			// nrDoors
			300,		// enginePower
			Color.blue, // color
			"Scania"	// modelName
		);
	};

	public double getBedAngle() {
		return bedAngle;
	}

	public void liftBed(double angle) {
		if (currentSpeed == 0) { 
			bedAngle = Math.min(maxBedAngle, bedAngle + angle);
		}
	}

	public void lowerBed(double angle) {
		if (currentSpeed == 0) {
			bedAngle = Math.max(minBedAngle, bedAngle - angle);
		}
	}

	@Override
    public void gas(double amount) {
		if (bedAngle != 0) return;
		super.gas(amount);
    }
}
