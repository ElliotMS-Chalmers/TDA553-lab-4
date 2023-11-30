package model;

import java.awt.*;

public class Saab95 extends Vehicle {

    private boolean turboOn;
    
    public Saab95() {
		super(
			2, 			// nrDoors
			125,		// enginePower
			Color.red,  // color
			"Saab95"	// modelName
		);
	    turboOn = false;
    }
    public boolean getTurboOn(){
		return turboOn;
	}

    public void setTurboOn() {
	    turboOn = true;
    }

    public void setTurboOff() {
	    turboOn = false;
    }
    
    public double speedFactor() {
        double turbo = turboOn ? 1.3 : 1;
        return enginePower * 0.01 * turbo;
    }
}
