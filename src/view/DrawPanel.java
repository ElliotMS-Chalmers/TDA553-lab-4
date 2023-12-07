package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import model.Vehicle;

// This panel represent the animated part of the view with the car images.
public class DrawPanel extends JPanel {

	private final ArrayList<Vehicle> vehicles;
	private final HashMap<Class<? extends Vehicle>, BufferedImage> imageMap;

    public DrawPanel(ArrayList<Vehicle> vehicles, HashMap<Class<? extends Vehicle>, BufferedImage> imageMap) {
        this.vehicles = vehicles;
		this.imageMap = imageMap;

		this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(1100, 600));
        this.setBackground(Color.green);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		for (int i = 0; i < vehicles.size(); i++) {
			Vehicle v = vehicles.get(i);
			BufferedImage image = imageMap.get(v.getClass()); 
			double[] pos = v.getPosition();
			g.drawImage(
				image, 
				(int)pos[0] + image.getWidth()*i, 
				(int)pos[1],
				null
			);
		}
    }
}

