package model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TimerListener implements ActionListener{
	
    private final ArrayList<Vehicle> vehicles;


    public TimerListener(ArrayList<Vehicle> vehicles){
        this.vehicles = vehicles;
    }

	public void actionPerformed(ActionEvent e) {
		for (Vehicle vehicle : vehicles) {
			vehicle.move();
            int x = (int) Math.round(vehicle.getPosition()[0]);
            int y = (int) Math.round(vehicle.getPosition()[1]);
			if (y > 800 - 240 - 60 || y < 0) {
				vehicle.stopEngine();
				vehicle.turnLeft();
				vehicle.turnLeft();
				double[] newPos = {x, Math.round(y / 100) * 100};
				vehicle.setPosition(newPos);
			}
        }
    }
}
