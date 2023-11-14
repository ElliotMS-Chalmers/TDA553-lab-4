import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransportTest {
    private final Saab95 saab95 = new Saab95();
    private final Volvo240 volvo240 = new Volvo240();
    private final Transport<Car> transport = new Transport<>(2);
    private final double delta = 0.0001;

    @Test
    public void testBedInitializedAsUp(){
        assertFalse(transport.getBedDown());
    }

    @Test
    public void testCurrentVehiclesOnbedInitializedAsZero(){
        assertEquals(0, transport.getVehicles().size());
    }

    @Test
    public void testCapacityInitializedAsTwo() {
        assertEquals(2, transport.getCapacity());
    }

    @Test
    public void testRaiseBed(){
        transport.raiseBed();
        assertFalse(transport.getBedDown());
    }

    @Test
    public void testLowerBed(){
        transport.raiseBed();
        transport.lowerBed();
        assertTrue(transport.getBedDown());
    }

    @Test
    public void testLoadOneVehicle(){
        transport.lowerBed();
        transport.loadVehicle(saab95);
        assertEquals(1, transport.getVehicles().size());
    }

    @Test
    public void testLoadTwoVehicle(){
        transport.lowerBed();
        transport.loadVehicle(saab95);
        transport.loadVehicle(volvo240);
        assertEquals(2, transport.getVehicles().size());
    }

    public void testLoadVehiclesAboveCapacity() {
        transport.loadVehicle(saab95);
        transport.loadVehicle(volvo240);
        transport.loadVehicle(new Saab95());
        assertEquals(2, transport.getVehicles().size());
    }

    @Test
    public void testloadTwoVehiclesUnloadOne(){
        transport.lowerBed();
        transport.loadVehicle(saab95);
        transport.loadVehicle(volvo240);
        transport.unloadVehicles(1);
        assertEquals(1, transport.getVehicles().size());
    }

    @Test
    public void testloadTwoVehiclesUnloadAll(){
        transport.lowerBed();
        transport.loadVehicle(saab95);
        transport.loadVehicle(volvo240);
        transport.unloadVehicles(2);
        assertEquals(0, transport.getVehicles().size());
    }

    @Test
    public void testLastVehicleLoadedIsUnloadedFirst(){
        transport.lowerBed();
        transport.loadVehicle(saab95);
        transport.loadVehicle(volvo240);
        transport.unloadVehicles(1);
        assertFalse(transport.getVehicles().contains(volvo240));
    }

    @Test
    public void testUnloadMoreThanOntransport(){
        transport.lowerBed();
        transport.loadVehicle(saab95);
        transport.loadVehicle(volvo240);
        transport.unloadVehicles(3);
        assertEquals(0, transport.getVehicles().size());
    }

    @Test
    public void testGasWithBedDown(){
        transport.lowerBed();
        transport.gas(1);
        assertEquals(0, transport.getCurrentSpeed(), delta);
    }

    @Test
    public void testGasWithBedUp(){
        transport.raiseBed();
        transport.gas(1);
        assertEquals(Math.min(0 + transport.speedFactor() * 1, transport.getEnginePower()), transport.getCurrentSpeed(), delta);
    }

    @Test
    public void testBreakWithBedUp(){
        transport.raiseBed();
        transport.gas(0.5);
        transport.brake(0.5);
        assertEquals(0, transport.getCurrentSpeed(), delta);
    }

    @Test
    public void testGasWithLoadedTransport(){
        transport.lowerBed();
        transport.loadVehicle(saab95);
        transport.raiseBed();
        transport.gas(1);
        assertEquals(Math.min(0 + transport.speedFactor() * 1, transport.getEnginePower()), saab95.getCurrentSpeed(), delta);
    }

    @Test
    public void testBreakWithLoadedTransport(){
        transport.lowerBed();
        transport.loadVehicle(saab95);
        transport.raiseBed();
        transport.gas(0.5);
        transport.brake(0.5);
        assertEquals(0,  saab95.getCurrentSpeed(), delta);
    }

    @Test
    public void testMoveNorth() {
        transport.lowerBed();
        transport.loadVehicle(saab95);
        transport.raiseBed();
        transport.gas(0.5);
        transport.move();
        assertEquals(transport.getCurrentSpeed(), transport.getPosition()[1], delta);
        assertEquals(saab95.getCurrentSpeed(), transport.getPosition()[1], delta);
    }

    @Test
    public void testMoveWest() {
        transport.lowerBed();
        transport.loadVehicle(saab95);
        transport.raiseBed();
        transport.turnLeft();
        transport.gas(0.5);
        transport.move();
        assertEquals(-transport.getCurrentSpeed(), transport.getPosition()[0], delta);
        assertEquals(-saab95.getCurrentSpeed(), transport.getPosition()[0], delta);
    }

    @Test
    public void testMoveSouth() {
        transport.lowerBed();
        transport.loadVehicle(saab95);
        transport.raiseBed();
        transport.turnLeft();
        transport.turnLeft();
        transport.gas(0.5);
        transport.move();
        assertEquals(-transport.getCurrentSpeed(), transport.getPosition()[1], delta);
        assertEquals(-saab95.getCurrentSpeed(), transport.getPosition()[1], delta);
    }

    @Test
    public void testMoveEast() {
        transport.lowerBed();
        transport.loadVehicle(saab95);
        transport.raiseBed();
        transport.turnRight();
        transport.gas(0.5);
        transport.move();
        assertEquals(transport.getCurrentSpeed(), transport.getPosition()[0], delta);
        assertEquals(saab95.getCurrentSpeed(), transport.getPosition()[0], delta);
    }
}
