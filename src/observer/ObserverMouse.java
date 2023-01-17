package observer;

import controllable.ControllableObject;
import utility.Config;

public class ObserverMouse extends Observer {

    public ObserverMouse(ControllableObject controllableObject) {
        super(controllableObject);
    }

    @Override
    public void update(IObservable observable) {
        ObservableMouseListener observableMouseListener = (ObservableMouseListener) observable;
        int x = observableMouseListener.getX();
        int y = observableMouseListener.getY();
        int offset = 100;
        if (x > Config.rozmerPlatna - offset) {
            controllableObject.turnRight();
        } else if (x < offset) {
            controllableObject.turnLeft();
        } else if (y < offset) {
            controllableObject.turnUp();
        } else if (y > Config.rozmerPlatna - offset) {
            controllableObject.turnDown();
        }
    }
}
