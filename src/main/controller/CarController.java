package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.*;
import view.CarView;

public class CarController {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        cc.addCar(new Volvo240(), "src/main/resources/Volvo240.jpg");
        cc.addCar(new Saab95(), "src/main/resources/Saab95.jpg");
        cc.addCar(new Scania(), "src/main/resources/Scania.jpg");
        
		// Start the timer
        cc.timer.start();
    }

	private void addCar(Vehicle vehicle, String path) {
		cars.add(vehicle);
		frame.drawPanel.addVehicle(vehicle, path);
	}

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move();
                int x = (int) Math.round(car.getPosition()[0]);
                int y = (int) Math.round(car.getPosition()[1]);
                frame.drawPanel.repaint();
				if (y > 800 - 240 - 60 || y < 0) {
					car.stopEngine();
					car.turnLeft();
					car.turnLeft();
					double[] newPos = {x, Math.round(y / 100) * 100};
					car.setPosition(newPos);
				}
            }
        }
    }

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }


    public void stopEngine() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    public void startEngine() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    public void raiseBed() {
        for (Vehicle car : cars) {
			if (car instanceof Scania) {
				Scania scania = (Scania) car;
				scania.raiseBed(70);
			}
        }
    }

    public void lowerBed() {
        for (Vehicle car : cars) {
			if (car instanceof Scania) {
				Scania scania = (Scania) car;
				scania.lowerBed(70);
			}
        }
    }

	public void setTurboOn() {
		for (Vehicle car : cars) {
			if (car instanceof Saab95) {
				Saab95 saab95 = (Saab95) car;
				saab95.setTurboOn();
			}
		}
	}

	public void setTurboOff() {
		for (Vehicle car : cars) {
			if (car instanceof Saab95) {
				Saab95 saab95 = (Saab95) car;
				saab95.setTurboOff();
			}
		}
	}
}
