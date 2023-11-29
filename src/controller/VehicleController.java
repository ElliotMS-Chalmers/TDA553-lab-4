package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.*;
import view.*;

public class VehicleController {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    VehicleView frame;
    // A list of vehicles, modify if needed
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        // Instance of this class
        VehicleController cc = new VehicleController();

        // Start a new view and send a reference of self
        cc.frame = new VehicleView("vehicleSim 1.0", cc);

        cc.addvehicle(new Volvo240(), "src/main/resources/Volvo240.jpg");
        cc.addvehicle(new Saab95(), "src/main/resources/Saab95.jpg");
        cc.addvehicle(new Scania(), "src/main/resources/Scania.jpg");
        
		// Start the timer
        cc.timer.start();
    }

	private void addvehicle(Vehicle vehicle, String path) {
		vehicles.add(vehicle);
		frame.drawPanel.addVehicle(vehicle, path);
	}

    /* Each step the TimerListener moves all the vehicles in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {
                vehicle.move();
                int x = (int) Math.round(vehicle.getPosition()[0]);
                int y = (int) Math.round(vehicle.getPosition()[1]);
                frame.drawPanel.repaint();
				if (y > 800 - 240 - 60 || y < 0) {
					vehicle.stopEngine();
					vehicle.turnLeft();
					vehicle.turnLeft();
					double[] newPos = {x, Math.round(y / 100) * 100};
					vehicle.setPosition(newPos);
				}
            }
        }
    }

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.brake(brake);
        }
    }


    public void stopEngine() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }

    public void startEngine() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    public void raiseBed() {
        for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof Scania) {
				Scania scania = (Scania) vehicle;
				scania.raiseBed(70);
			}
        }
    }

    public void lowerBed() {
        for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof Scania) {
				Scania scania = (Scania) vehicle;
				scania.lowerBed(70);
			}
        }
    }

	public void setTurboOn() {
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof Saab95) {
				Saab95 saab95 = (Saab95) vehicle;
				saab95.setTurboOn();
			}
		}
	}

	public void setTurboOff() {
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof Saab95) {
				Saab95 saab95 = (Saab95) vehicle;
				saab95.setTurboOff();
			}
		}
	}
}
