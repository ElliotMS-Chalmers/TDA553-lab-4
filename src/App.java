import controller.VehicleController;
import model.Vehicle;
import model.Saab95;
import model.Scania;
import model.Volvo240;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
		ArrayList<Vehicle> vehicles = new ArrayList<>(); 
		vehicles.add(new Volvo240());
		vehicles.add(new Saab95());
		vehicles.add(new Scania());
       
		VehicleController c = new VehicleController();
        VehicleView frame = new VehicleView("vehicleSim 1.0", c);
	
		for (Vehicle v : vehicles) {
			c.addVehicle(v);
			if (v instanceof Volvo240) { frame.addVehicle(v, "src/resources/Volvo240.jpg"); }
			else if (v instanceof Saab95) { frame.addVehicle(v, "src/resources/Saab95.jpg"); }
			else if (v instanceof Scania) { frame.addVehicle(v, "src/resources/Scania.jpg"); }
			v.addObserver(frame);
		}

        frame.addGasListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.gas(frame.gasAmount);}
        });

        frame.addBrakeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.brake(frame.gasAmount);}
        });

        frame.addStartListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.startEngine();}
        });

        frame.addStopListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.stopEngine();}
        });

        frame.addLiftBedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.raiseBed();}
        });

        frame.addLowerBedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.lowerBed();}
        });

        frame.addTurboOnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.setTurboOn();}
        });

        frame.addTurboOffListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.setTurboOff();}
        });

        c.run();
    }
}
