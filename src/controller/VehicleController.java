package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Vehicle;
import model.Saab95;
import model.Scania;
import model.TimerListener;

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
}
