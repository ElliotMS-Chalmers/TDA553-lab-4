import org.junit.Before;

import java.awt.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WorkshopTest {

    Workshop<Car> workshop1 = new Workshop<>(5);
    Workshop<Saab95> workshop2 = new Workshop<>(3);
    Saab95 saab = new Saab95();
    Saab95 saab2 = new Saab95();
    Volvo240 volvo = new Volvo240();
    Scania truck = new Scania();

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


}