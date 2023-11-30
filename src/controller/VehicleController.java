package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.*;
import view.*;

public class VehicleController {
    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());

    VehicleView frame;
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        VehicleController cc = new VehicleController();

        cc.frame = new VehicleView("vehicleSim 1.0", cc);

        cc.addvehicle(new Volvo240(), "src/resources/Volvo240.jpg");
        cc.addvehicle(new Saab95(), "src/resources/Saab95.jpg");
        cc.addvehicle(new Scania(), "src/resources/Scania.jpg");
        
        cc.timer.start();
    }

	private void addvehicle(Vehicle vehicle, String path) {
		vehicles.add(vehicle);
		frame.drawPanel.addVehicle(vehicle, path);
	}

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
