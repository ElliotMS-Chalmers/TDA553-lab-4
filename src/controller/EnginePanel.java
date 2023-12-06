package controller;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnginePanel extends JPanel {
	VehicleController controller;

	JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");
	
	public EnginePanel(VehicleController controller) {
        this.controller = controller;

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(200, 200));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(200, 200));
        this.add(stopButton);
        
		startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { controller.startEngine();}
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { controller.stopEngine();}
        });

	}

}
