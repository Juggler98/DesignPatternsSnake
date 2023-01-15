package controllable;

import appPackage.App;
import movingModels.IMovingObject;
import observer.IObserver;
import observer.ObservableKeyAdapter;
import observer.Observable;
import state.IState;
import utility.Position;

public abstract class ControllableObject implements IMovingObject, IObserver {

    private final int upKey;
    private final int downKey;
    private final int leftKey;
    private final int rightKey;
    private IState state;

    public ControllableObject(int upKey, int downKey, int leftKey, int rightKey, IState state) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.state = state;
    }

    @Override
    public void update(Observable observable) {
        ObservableKeyAdapter observableKeyAdapter = (ObservableKeyAdapter) observable;
        int key = observableKeyAdapter.getPressedKey();
        if (key == rightKey) {
            state = state.turnRight();
        } else if (key == leftKey) {
            state = state.turnLeft();
        } else if (key == upKey) {
            state = state.turnUp();
        } else if (key == downKey) {
            state = state.turnDown();
        }
    }

    public void end() {
        App.getInstance().removeControllable(this);
    }

    public abstract Position getHeadPosition();

    public abstract int getSize();

    public IState getState() {
        return state;
    }
}
