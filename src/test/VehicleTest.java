import org.junit.Before;

import java.awt.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VehicleTest {
	
	private Vehicle vehicle;
	private final double delta = 0.00001; 

	private class DummyVehicle extends Vehicle {
		public DummyVehicle() {
			super(2, 100, Color.blue, "Dummy");
		}
	}

	@Before
	public void setUp() {
		vehicle = new DummyVehicle();
	}

	@Test
	public void testNumberOfDoorsInitializedAsTwo() {
		assertEquals(2, vehicle.getNrDoors()); 
	}

	@Test 
	public void testEnginePowerInitializedAs100() {
		assertEquals(100, vehicle.getEnginePower(), delta); 
	}

	@Test
	public void testColorInitializedAsBlue() {
		assertEquals(Color.blue, vehicle.getColor()); 
	}

	@Test
	public void testSetColorToBlack() {
		vehicle.setColor(Color.black);
		assertEquals(Color.black, vehicle.getColor());
	}

	@Test
	public void testModelNameInitializedAsDummy() {
		assertEquals("Dummy", vehicle.getModelName()); 
	}

	@Test 
	public void testPositionInitializedAsZero() {
		assertEquals(0, vehicle.getPosition()[0], delta);
		assertEquals(0, vehicle.getPosition()[1], delta);
	}

	@Test
	public void testDirectionInitializedAsNorth() {
		assertEquals(Vehicle.Direction.NORTH, vehicle.getDirection());
	}

	@Test 
	public void testCurrentSpeedInitializedAsZero() {
		assertEquals(0, vehicle.getCurrentSpeed(), delta);
	}

	@Test
	public void testTurnLeftIsCorrectDirection() {
		Vehicle.Direction[] expectedDirections = new Vehicle.Direction[] {
			Vehicle.Direction.NORTH,
			Vehicle.Direction.WEST,
			Vehicle.Direction.SOUTH,
			Vehicle.Direction.EAST,
			Vehicle.Direction.NORTH,
			Vehicle.Direction.WEST,
		};
		for (int i = 0; i < 6; i++) {
			assertEquals(expectedDirections[i], vehicle.getDirection());
			vehicle.turnLeft();
		}
	}

	@Test
	public void testTurnRightIsCorrectDirection() {
		Vehicle.Direction[] expectedDirections = new Vehicle.Direction[] {
			Vehicle.Direction.NORTH,
			Vehicle.Direction.EAST,
			Vehicle.Direction.SOUTH,
			Vehicle.Direction.WEST,
			Vehicle.Direction.NORTH,
			Vehicle.Direction.EAST,
		};
		for (int i = 0; i < 6; i++) {
			assertEquals(expectedDirections[i], vehicle.getDirection());
			vehicle.turnRight();
		}
	}

	@Test
	public void testMoveNorth() {
		vehicle.gas(0.5);
		vehicle.move();
		assertEquals(vehicle.getCurrentSpeed(), vehicle.getPosition()[1], delta);
	}

	@Test
	public void testMoveWest() {
		vehicle.turnLeft();
		vehicle.gas(0.5);
		vehicle.move();
		assertEquals(-vehicle.getCurrentSpeed(), vehicle.getPosition()[0], delta);
	}

	@Test
	public void testMoveSouth() {
		vehicle.turnLeft();
		vehicle.turnLeft();
		vehicle.gas(0.5);
		vehicle.move();
		assertEquals(-vehicle.getCurrentSpeed(), vehicle.getPosition()[1], delta);
	}

	@Test
	public void testMoveEast() {
		vehicle.turnRight();
		vehicle.gas(0.5);
		vehicle.move();
		assertEquals(vehicle.getCurrentSpeed(), vehicle.getPosition()[0], delta);
	}

	@Test
	public void testStartEngineSetsSpeedToPointOne() {
		vehicle.startEngine();
		assertEquals(0.1, vehicle.getCurrentSpeed(), delta);
	}

	@Test
	public void testStopEngineSetsSpeedToZero() {
		vehicle.startEngine();
		vehicle.stopEngine();
		assertEquals(0, vehicle.getCurrentSpeed(), delta);
	}

	@Test 
	public void testSpeedFactorReturnsEnginePowerTimesPointZeroOne() {
		double enginepower = vehicle.getEnginePower();
		double expectedSpeedFactor = enginepower * 0.01;
		assertEquals(expectedSpeedFactor, vehicle.speedFactor(), delta);
	}

	@Test
	public void testGasAmountLessThanZeroReturnsEarly() {
		double speed = vehicle.getCurrentSpeed();
		vehicle.gas(-1);
		assertEquals(speed, vehicle.getCurrentSpeed(), delta);
	}

	@Test
	public void testGasAmountGreaterThanOneReturnsEarly() {
		double speed = vehicle.getCurrentSpeed();
		vehicle.gas(2);
		assertEquals(speed, vehicle.getCurrentSpeed(), delta);
	}

	@Test
	public void testBrakeAmountLessThanZeroReturnsEarly() {
		double speed = vehicle.getCurrentSpeed();
		vehicle.gas(-1);
		assertEquals(speed, vehicle.getCurrentSpeed(), delta);
	}

	@Test
	public void testBrakeAmountGreaterThanOneReturnsEarly() {
		double speed = vehicle.getCurrentSpeed();
		vehicle.gas(2);
		assertEquals(speed, vehicle.getCurrentSpeed(), delta);
	}

	@Test
	public void testGasPointFiveShouldReturnSpeedfactorTimesPointFive() {
		vehicle.gas(0.5);
		double expectedSpeed =  vehicle.speedFactor() * 0.5;
		assertEquals(expectedSpeed, vehicle.getCurrentSpeed(), delta);
	}

	@Test
	public void testBrakePointFiveAfterGasPointFiveShouldReturnZero() {
		vehicle.gas(0.5);
		vehicle.brake(0.5);
		assertEquals(0, vehicle.getCurrentSpeed(), delta);
	}
}


