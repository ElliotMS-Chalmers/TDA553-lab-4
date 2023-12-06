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

	private static VehicleController controller = new VehicleController(vehicles);
	private static GasPanel gasPanel = new GasPanel(controller);
	private static ControlPanel controlPanel = new ControlPanel(controller, gasPanel);
	private static EnginePanel enginePanel = new EnginePanel(controller);

	private static BufferedImage DEFAULT_IMAGE = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);	
	private static HashMap<Class<? extends Vehicle>, BufferedImage> imageMap = new HashMap<>(Map.of(
		Volvo240.class, DEFAULT_IMAGE,
		Saab95.class, DEFAULT_IMAGE,
		Scania.class, DEFAULT_IMAGE
	));
	
	private static Frame frame = new Frame("vehicleSim 2.0", vehicles, imageMap); 

	public static void main(String[] args) {
		try { 
			imageMap.put(Volvo240.class, ImageIO.read(new File("src/resources/Volvo240.jpg")));
			imageMap.put(Saab95.class, ImageIO.read(new File("src/resources/Saab95.jpg")));
			imageMap.put(Scania.class, ImageIO.read(new File("src/resources/Scania.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame.add(gasPanel);
		frame.add(controlPanel);
		frame.add(enginePanel);
		
		vehicles.add(new Volvo240());
		vehicles.add(new Saab95());
		vehicles.add(new Scania());

        controller.run();
    }
}
