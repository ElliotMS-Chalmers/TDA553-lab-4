package controller;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
	VehicleController controller;
	GasPanel gasPanel;

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");
	JButton addVehicleButton = new JButton("Add vehicle");
	JButton removeVehicleButton = new JButton("Remove vehicle");
    
	public ControlPanel(VehicleController controller, GasPanel gasPanel) {
        this.controller = controller;
		this.gasPanel = gasPanel;

		setLayout(new GridLayout(2, 5));

        add(gasButton, 0);
        add(turboOnButton, 1);
        add(liftBedButton, 2);
        add(brakeButton, 3);
        add(turboOffButton, 4);
        add(lowerBedButton, 5);
		add(addVehicleButton, 6);
		add(removeVehicleButton, 7);

        setPreferredSize(new Dimension(600, 200));
        setBackground(Color.CYAN);
        
		gasButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { controller.gas(gasPanel.gasAmount); }
		});

		brakeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { controller.brake(gasPanel.gasAmount); }
		});

        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { controller.raiseBed();}
        });

        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { controller.lowerBed();}
        });

        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { controller.setTurboOn();}
        });

        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { controller.setTurboOff();}
        });

        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { controller.addVehicle(); }
        });

        removeVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { controller.removeVehicle(); }
        });
	}

}
