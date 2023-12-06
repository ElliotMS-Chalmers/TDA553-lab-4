package controller;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class GasPanel extends JPanel {
	VehicleController controller;
    
	JSpinner gasSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    public int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");
	
	public GasPanel(VehicleController controller) {
        this.controller = controller;

		gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        setLayout(new BorderLayout());
        add(gasLabel, BorderLayout.PAGE_START);
        add(gasSpinner, BorderLayout.PAGE_END);
	}
}
