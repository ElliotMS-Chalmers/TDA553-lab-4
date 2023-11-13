import java.util.ArrayList;

public class Workshop<T> {

    private int maxNrCars;
    private int totalCars = 0;
    private ArrayList<T> cars = new ArrayList<T>();
    public Workshop(int amount) {
        maxNrCars = amount;
    }

    public void add(T car) {
        if ((totalCars + 1) <= maxNrCars) {
            totalCars += 1;
            cars.add(car);
        }
    }


    private T getVehicle(int index) {
        if (index >= 0 && index < totalCars) {
            return cars.get(index);
        } else {
            return null;
        }
    }

    public T getCar(int index) {
        T car = getVehicle(index);
        cars.remove(index);
        return car;
    }

}