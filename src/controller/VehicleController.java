package controller;

import javax.swing.*;
import java.util.ArrayList;

import model.*;

public class VehicleController {
    private final int tickRate = 50;
    private Timer timer;
    private final ArrayList<Vehicle> vehicles; 

	public VehicleController(ArrayList<Vehicle> vehicles, TimerListener timerListener) {
		this.vehicles = vehicles;
        this.timer = new Timer(tickRate, timerListener);
	}

    public void run() {
        timer.start();
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
				scania.liftBed(70);
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

	public void addVehicle() {
		int size = vehicles.size();
		if (size == 10) return;
		Vehicle vehicle = new Volvo240();
		vehicles.add(vehicle);
		// TODO vehicle.addObserver(observer)
	}

	public void removeVehicle() {
		int size = vehicles.size();
		if (size <= 0) return;
		Vehicle v = vehicles.remove(size-1);
		v.notifyObservers();
	}
}
