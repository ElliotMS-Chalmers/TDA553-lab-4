package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

import jdk.internal.org.jline.reader.Buffer;

import model.Vehicle;
// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel {

	BufferedImage[] images;
	// To keep track of a singel cars position
    Point volvoPoint = new Point();
		
    // TODO: Make this genereal for all cars
    public void moveit(int x, int y) {
		volvoPoint.x = x;
        volvoPoint.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            BufferedImage volvoImage = ImageIO.read(new File("src/main/resources/Volvo240.jpg"));
			BufferedImage saabImage = ImageIO.read(new File("src/main/resources/Saab95.jpg"));
			BufferedImage scaniaImage = ImageIO.read(new File("src/main/resources/Scania.jpg"));
        	images = new BufferedImage[] {volvoImage, saabImage, scaniaImage};
		} catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		for (int i = 0; i < images.length; i++) {
        	BufferedImage image = images[i]; 
			g.drawImage(image, volvoPoint.x + 100 * i, volvoPoint.y, null);
		}
    }
}

