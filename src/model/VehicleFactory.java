package model;

import java.util.ArrayList;
import java.util.Random;


public class VehicleFactory {

    private static final Random rand = new Random();
    public static Vehicle createRandom() {
        switch(rand.nextInt(3)) {
            case 2:
                return createSaab();
            case 1:
                return createScania();
            default:
                return createVolvo();
        }
    }

    public static Volvo240 createVolvo() {
        return (new Volvo240());
    }

    public static Saab95 createSaab() {
        return (new Saab95());
    }

    public static Scania createScania() {
        return (new Scania());
    }
}
