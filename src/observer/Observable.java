package observer;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable implements IObservable {

    private final List<IObserver> observers = new LinkedList<>();

    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(this);
        }
    }
}
