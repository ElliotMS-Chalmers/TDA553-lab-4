package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.ArrayList;

import model.Vehicle;
import model.Observer;


public class Frame extends JFrame implements Observer {
    public Frame(
		String title,
		ArrayList<Vehicle> vehicles,
		HashMap<Class<? extends Vehicle>, BufferedImage> imageMap)
	{
        setTitle(title);
        setPreferredSize(new Dimension(1100, 800));
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(new DrawPanel(vehicles, imageMap));
        pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	@Override
	public void update() {
		repaint();
	}
}
