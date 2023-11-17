import org.junit.Test;

import static org.junit.Assert.assertEquals;

import model.Volvo240;

public class Volvo240Test {
	private final Volvo240 volvo240 = new Volvo240();
	private final double delta = 0.0001;

	@Test
	public void testTrimFactorInitializedAsOnePointTwentyfive(){
		assertEquals(volvo240.getTrimFactor(), 1.25, delta);
	}

	@Test
	public void testSpeedFactor(){
		assertEquals(volvo240.getEnginePower() * 0.01 * volvo240.getTrimFactor(), volvo240.speedFactor(), delta);
	}
}
