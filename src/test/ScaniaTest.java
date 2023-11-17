import org.junit.Test;

import static org.junit.Assert.assertEquals;

import model.Scania;

public class ScaniaTest {
	private final Scania scania = new Scania();
	private final double delta = 0.0001;

	@Test
	public void testBedAngleInitializedAsZero() {
		assertEquals(0, scania.getBedAngle(), delta);
	}

	@Test 
	public void testRaiseBedAngleBy45Deg() {
		scania.raiseBed(45.0);
		assertEquals(45.0, scania.getBedAngle(), delta);
	}

	@Test
	public void testRaiseBedAngleAboveMaxShouldSetBedAngleTo70() {
		scania.raiseBed(100);
		assertEquals(70, scania.getBedAngle(), delta);
	}

	@Test 
	public void testLowerBedAngleBy45AfterRaiseBy45ShouldEqualZero() {
		scania.raiseBed(45);
		scania.lowerBed(45);
		assertEquals(0, scania.getBedAngle(), delta);
	}

	@Test
	public void testLowerBedAngleBelowMinAngleShouldSetBedAngleTo0() {
		scania.lowerBed(100);
		assertEquals(0, scania.getBedAngle(), delta);
	}

	@Test 
	public void testGasWithBedAngleSetToNonZeroShouldDoNothing() {
		scania.raiseBed(45);
		scania.gas(0.5);
		assertEquals(0, scania.getPosition()[1], delta);
	}

	@Test
	public void testGasWithBedAngleSetToZeroShouldWorkNormally() {
		scania.gas(0.5);
		double expectedSpeed =  scania.speedFactor() * 0.5;
		assertEquals(expectedSpeed, scania.getCurrentSpeed(), delta);
	}
}
