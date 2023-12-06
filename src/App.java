import controller.*;
import model.*;
import view.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class App {
	private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>() {
		@Override
		public boolean add(Vehicle v) {
			v.addObserver(frame);
			return super.add(v);
		}
	}; 
    
	private static TimerListener timerListener = new TimerListener(vehicles);
	private static VehicleController controller = new VehicleController(vehicles, timerListener);
	private static GasPanel gasPanel = new GasPanel(controller);
	private static ControlPanel controlPanel = new ControlPanel(controller, gasPanel);
	private static EnginePanel enginePanel = new EnginePanel(controller);

	private static BufferedImage VOLVO_IMAGE;	
	private static BufferedImage SAAB_IMAGE;
	private static BufferedImage SCANIA_IMAGE;	
	private static HashMap<Class<? extends Vehicle>, BufferedImage> imageMap;

	private static Frame frame;

	public static void main(String[] args) {
		try { 
			VOLVO_IMAGE = ImageIO.read(new File("src/resources/Volvo240.jpg"));
			SAAB_IMAGE = ImageIO.read(new File("src/resources/Saab95.jpg"));
			SCANIA_IMAGE = ImageIO.read(new File("src/resources/Scania.jpg"));
		} catch (IOException e) {
			throw new RuntimeException("Failed to load vehicle images: " + e.getMessage(), e);
		}

		imageMap = new HashMap<>(Map.of(
			Volvo240.class, VOLVO_IMAGE,
			Saab95.class, SAAB_IMAGE,
			Scania.class, SCANIA_IMAGE
		));

		frame = new Frame("vehicleSim 1.0", vehicles, imageMap); 
		frame.add(controlPanel);
		frame.add(gasPanel);
		frame.add(enginePanel);
		
		vehicles.add(new Volvo240());
		vehicles.add(new Saab95());
		vehicles.add(new Scania());

		for (Vehicle v : vehicles) {
			v.addObserver(frame);
		}

        controller.run();
    }
}
