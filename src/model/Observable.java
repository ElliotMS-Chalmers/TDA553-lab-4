package model;

import java.util.ArrayList;

public class Observable {
    protected boolean change;
    private ArrayList<Observer> observers;

    public void addObserver(Observer observer){
        observers.add(observer);
    }
    protected void notifyObservers(){
        for (Observer o : observers) {
           o.actOnChange(change);
        }
        change = false;
    }
}