import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WorkshopTest {

    private final Workshop<Car> workshop = new Workshop<>(1);

    @Test
    public void testRegisterVehicleWithRemainingCapacity() {
        workshop.registerVehicle(new Saab95());
        assertEquals(1, workshop.getVehicles().size());
    }

    @Test
    public void testRegisterVehicleWithoutRemainingCapacity() {
        workshop.registerVehicle(new Saab95());
		workshop.registerVehicle(new Volvo240());
        assertEquals(1, workshop.getVehicles().size());
    }

    @Test
    public void testReleaseVehicle(){
        Volvo240 registeredVehicle = new Volvo240();
		workshop.registerVehicle(registeredVehicle); 
		Car releasedVehicle = workshop.releaseVehicle(0);
		assertEquals(registeredVehicle, releasedVehicle);
    }

    @Test
    public void testReleaseVehicleInvalidIndex() {
        assertNull(workshop.releaseVehicle(3));
    }

}
