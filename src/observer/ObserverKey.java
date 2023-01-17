package observer;

import controllable.ControllableObject;

public class ObserverKey extends Observer {

    private final int upKey;
    private final int downKey;
    private final int leftKey;
    private final int rightKey;

    public ObserverKey(int upKey, int downKey, int leftKey, int rightKey, ControllableObject controllableObject) {
        super(controllableObject);
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    @Override
    public void update(IObservable observable) {
        if (!(observable instanceof ObservableKeyAdapter)) {
            return;
        }
        ObservableKeyAdapter observableKeyAdapter = (ObservableKeyAdapter) observable;
        int key = observableKeyAdapter.getPressedKey();
        if (key == rightKey) {
            controllableObject.turnRight();
        } else if (key == leftKey) {
            controllableObject.turnLeft();
        } else if (key == upKey) {
            controllableObject.turnUp();
        } else if (key == downKey) {
            controllableObject.turnDown();
        }
    }
}
