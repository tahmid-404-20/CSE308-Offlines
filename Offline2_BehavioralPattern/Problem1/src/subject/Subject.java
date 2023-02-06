package subject;

import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<Observer> observers;

    public Subject() {
        observers = new ArrayList<>();
    }

    public abstract boolean addObserver(Observer o);
    public abstract void removeObserver(Observer o);
    protected abstract void notifyObservers();
}
