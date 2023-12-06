import controller.VehicleController;
import model.Vehicle;
import model.Saab95;
import model.Scania;
import model.Volvo240;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class App {

    public static void main(String[] args) {
		BufferedImage VOLVO_IMAGE;	
		BufferedImage SAAB_IMAGE;
		BufferedImage SCANIA_IMAGE;	

		try { 
			VOLVO_IMAGE = ImageIO.read(new File("src/resources/Volvo240.jpg"));
			SAAB_IMAGE = ImageIO.read(new File("src/resources/Saab95.jpg"));
			SCANIA_IMAGE = ImageIO.read(new File("src/resources/Scania.jpg"));
		} catch (IOException e) {
			throw new RuntimeException("Failed to load vehicle images: " + e.getMessage(), e);
		}

		HashMap<Class<? extends Vehicle>, BufferedImage> imageMap = new HashMap<>();
		imageMap.put(Volvo240.class, VOLVO_IMAGE);
		imageMap.put(Saab95.class, SAAB_IMAGE);
		imageMap.put(Scania.class, SCANIA_IMAGE);

		ArrayList<Vehicle> vehicles = new ArrayList<>(); 
		vehicles.add(new Volvo240());
		vehicles.add(new Saab95());
		vehicles.add(new Scania());
       
		VehicleController c = new VehicleController(vehicles);
		VehicleView frame = new VehicleView("vehicleSim 1.0", vehicles, imageMap);
	
		for (Vehicle v : vehicles) {
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

        frame.addVehicleListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
				int size = vehicles.size();
				if (size == 10) return;
				Vehicle vehicle = new Volvo240();
				vehicles.add(vehicle);
				vehicle.addObserver(frame);
			}
        });

        frame.removeVehicleListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
				int size = vehicles.size();
				if (size <= 0) return;
				Vehicle v = vehicles.remove(size-1);
				v.notifyObservers();
			}
        });

        c.run();
    }
}
