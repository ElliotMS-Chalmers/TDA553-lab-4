import java.util.ArrayList;
public class Workshop<T> {

    private int maxNrCars;
    private int totalCars = 0;
    private ArrayList<T> cars = new ArrayList<T>();
    public Workshop(int amount) {
        maxNrCars = amount;
    }

    public void carAdd(T car) {
        if ((totalCars + 1) <= maxNrCars) {
            totalCars += 1;
            cars.add(car);
        }
    }

    public ArrayList<T> getCarlist() {
        return cars;
    }


    private T getVehicle(int index) {
        return cars.get(index);
    }

    public T getCar(int index) {
        if (index >= 0 && index < totalCars) {
            T car = getVehicle(index);
            cars.remove(index);
            return car;
        } else {
            return null;
        }
    }

}
