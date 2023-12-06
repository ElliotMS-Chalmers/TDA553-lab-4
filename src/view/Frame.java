package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.ArrayList;

import model.Vehicle;
import model.Observer;


public class Frame extends JFrame implements Observer {
    private static final int X = 1100;
    private static final int Y = 800;

    public DrawPanel drawPanel;

    public Frame(
		String title,
		ArrayList<Vehicle> vehicles,
		HashMap<Class<? extends Vehicle>,
		BufferedImage> imageMap)
	{
		drawPanel = new DrawPanel(vehicles, imageMap);
        initComponents(title);
    }

    private void initComponents(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);

        this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	@Override
	public void update() {
		this.repaint();
	}
}
