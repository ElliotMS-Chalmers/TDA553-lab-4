

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CarTransportTest {
    private final Saab95 saab95 = new Saab95();
    private final Volvo240 volvo240 = new Volvo240();
    private final CarTransport cartransport = new CarTransport();
    private final double delta = 0.0001;

    @Test
    public void testBedInitializedAsUp(){
        assertFalse(cartransport.getBedDown());
    }

    @Test
    public void testCurrentCarsOnbedInitializedAsZero(){
        assertEquals(0, cartransport.getCurrentCarsOnBed().size());
    }

    @Test
    public void testRaiseBed(){
        cartransport.raiseBed();
        assertFalse(cartransport.getBedDown());
    }

    @Test
    public void testLowerBed(){
        cartransport.raiseBed();
        cartransport.lowerBed();
        assertTrue(cartransport.getBedDown());
    }

    @Test
    public void testLoadOneCar(){
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        assertEquals(1, cartransport.getCurrentCarsOnBed().size());
    }

    @Test
    public void testLoadTwoCar(){
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        cartransport.loadCar(volvo240);
        assertEquals(2, cartransport.getCurrentCarsOnBed().size());
    }

    @Test
    public void testloadTwoCarsUnloadOne(){
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        cartransport.loadCar(volvo240);
        cartransport.unloadCar(1);
        assertEquals(1, cartransport.getCurrentCarsOnBed().size());
    }

    @Test
    public void testloadTwoCarsUnloadAll(){
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        cartransport.loadCar(volvo240);
        cartransport.unloadCar(2);
        assertEquals(0, cartransport.getCurrentCarsOnBed().size());
    }

    @Test
    public void testLastCarLoadedIsUnloadedFirst(){
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        cartransport.loadCar(volvo240);
        cartransport.unloadCar(1);
        ArrayList<Car> carOnBoard = cartransport.getCurrentCarsOnBed();
        assertEquals(saab95, carOnBoard.get(0));
    }

    @Test
    public void testUnloadMoreThanOnCarTransport(){
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        cartransport.loadCar(volvo240);
        cartransport.unloadCar(3);
        assertEquals(0, cartransport.getCurrentCarsOnBed().size());
    }

    @Test
    public void testGasWithBedDown(){
        cartransport.lowerBed();
        cartransport.gas(1);
        assertEquals(0, cartransport.getCurrentSpeed(), delta);
    }

    @Test
    public void testGasWithBedUp(){
        cartransport.raiseBed();
        cartransport.gas(1);
        assertEquals(Math.min(0 + cartransport.speedFactor() * 1, cartransport.getEnginePower()), cartransport.getCurrentSpeed(), delta);
    }

    @Test
    public void testBreakWithBedUp(){
        cartransport.raiseBed();
        cartransport.gas(0.5);
        cartransport.brake(0.5);
        assertEquals(0,  cartransport.getCurrentSpeed(), delta);
    }

    @Test
    public void testGasWithLoadedTransport(){
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        cartransport.raiseBed();
        cartransport.gas(1);
        assertEquals(Math.min(0 + cartransport.speedFactor() * 1, cartransport.getEnginePower()), saab95.getCurrentSpeed(), delta);
    }

    @Test
    public void testBreakWithLoadedTransport(){
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        cartransport.raiseBed();
        cartransport.gas(0.5);
        cartransport.brake(0.5);
        assertEquals(0,  saab95.getCurrentSpeed(), delta);
    }

    @Test
    public void testMoveNorth() {
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        cartransport.raiseBed();
        cartransport.gas(0.5);
        cartransport.move();
        assertEquals(cartransport.getCurrentSpeed(), cartransport.getPosition()[1], delta);
        assertEquals(saab95.getCurrentSpeed(), cartransport.getPosition()[1], delta);
    }

    @Test
    public void testMoveWest() {
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        cartransport.raiseBed();
        cartransport.turnLeft();
        cartransport.gas(0.5);
        cartransport.move();
        assertEquals(-cartransport.getCurrentSpeed(), cartransport.getPosition()[0], delta);
        assertEquals(-saab95.getCurrentSpeed(), cartransport.getPosition()[0], delta);
    }

    @Test
    public void testMoveSouth() {
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        cartransport.raiseBed();
        cartransport.turnLeft();
        cartransport.turnLeft();
        cartransport.gas(0.5);
        cartransport.move();
        assertEquals(-cartransport.getCurrentSpeed(), cartransport.getPosition()[1], delta);
        assertEquals(-saab95.getCurrentSpeed(), cartransport.getPosition()[1], delta);
    }

    @Test
    public void testMoveEast() {
        cartransport.lowerBed();
        cartransport.loadCar(saab95);
        cartransport.raiseBed();
        cartransport.turnRight();
        cartransport.gas(0.5);
        cartransport.move();
        assertEquals(cartransport.getCurrentSpeed(), cartransport.getPosition()[0], delta);
        assertEquals(saab95.getCurrentSpeed(), cartransport.getPosition()[0], delta);
    }



    
}
