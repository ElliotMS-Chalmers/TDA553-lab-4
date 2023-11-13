import org.junit.Before;

import java.awt.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WorkshopTest {

    private final Workshop<Car> workshop1 = new Workshop<>(5);
    private final Workshop<Saab95> workshop2 = new Workshop<>(3);
    private final Saab95 saab = new Saab95();
    private final Volvo240 volvo = new Volvo240();

    @Before
    public void setUp() {workshop1.carAdd(saab);}


    @Test
    public void testAddCarInNonLimitedWorkshop() {
        workshop1.carAdd(saab);
        workshop1.carAdd(volvo);
        assertEquals(3, workshop1.getCarlist().size());
    }

    @Test
    public void testAddCarinLimitedWorkshopWorking() {
        workshop2.carAdd(saab);
        assertEquals(1, workshop2.getCarlist().size());
    }

    @Test
    public void testGetVehicleFromNonLimitedWorkshop(){
        assertEquals(saab, workshop1.getCar(0));
    }

    @Test
    public void testGetVehicleFromWorkshopWrongIndex() {
        assertNull(workshop1.getCar(3));
    }

}