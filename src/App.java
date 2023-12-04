import controller.VehicleController;
import model.Saab95;
import model.Scania;
import model.Volvo240;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        VehicleController c = new VehicleController();

        VehicleView frame = new VehicleView("vehicleSim 1.0", c);

        Volvo240 volvo = new Volvo240();
        Saab95 saab = new Saab95();
        Scania scania = new Scania();

        c.addvehicle(volvo);
        c.addvehicle(saab);
        c.addvehicle(scania);
        frame.addVehicle(volvo, "src/resources/Volvo240.jpg");
        frame.addVehicle(saab, "src/resources/Saab95.jpg");
        frame.addVehicle(scania, "src/resources/Scania.jpg");

        frame.addGasListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.gas(frame.gasAmount);}
        });

        frame.addBrakeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.brake(frame.gasAmount);}
        });

        frame.addStartListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.startEngine();}
        });

        frame.addStopListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.stopEngine();}
        });

        frame.addLiftBedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.raiseBed();}
        });

        frame.addLowerBedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.lowerBed();}
        });

        frame.addTurboOnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.setTurboOn();}
        });

        frame.addTurboOffListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { c.setTurboOff();}
        });

        c.run()

    }
}
