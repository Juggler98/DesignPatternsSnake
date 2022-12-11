package observer;

public interface Observable {

    void attach(IObserver observer);
    void detach(IObserver observer);
    void notifyObservers();
}
