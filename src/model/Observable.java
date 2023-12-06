package model;

import java.util.ArrayList;

abstract class Observable {
    private ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
		notifyObservers();
    }

    protected void notifyObservers(){
        for (Observer o : observers) {
           o.update();
        }
    }
}
