package observer;

import controllable.ControllableObject;
import observer.IObserver;

public abstract class Observer implements IObserver {
    protected final ControllableObject controllableObject;
    public Observer(ControllableObject controllableObject) {
        this.controllableObject = controllableObject;
    }


}
