package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

import model.Vehicle;

// This panel represent the animated part of the view with the car images.
public class DrawPanel extends JPanel {

	ArrayList<VehicleImage> vehicleImages = new ArrayList<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		for (int i = 0; i < vehicleImages.size(); i++) {
			VehicleImage vehicleImage = vehicleImages.get(i); 
			g.drawImage(
				vehicleImage.image, 
				(int)vehicleImage.vehicle.getPosition()[0] + 100*i, 
				(int)vehicleImage.vehicle.getPosition()[1],
				null
			);
		}
    }

	public void addVehicle(Vehicle vehicle, String path) {
		vehicleImages.add(new VehicleImage(vehicle, path));
	}
}

final class VehicleImage {
	public Vehicle vehicle;
	public BufferedImage image;

	VehicleImage(Vehicle v, String path) {
		vehicle = v;
		try { image = ImageIO.read(new File(path)); } 
		catch (IOException ex) { ex.printStackTrace(); }
	}
}
