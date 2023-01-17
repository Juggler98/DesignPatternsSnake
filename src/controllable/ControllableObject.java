package controllable;

import appPackage.App;
import movingModels.IMovingObject;
import state.IState;
import flyweight.Position;

public abstract class ControllableObject implements IMovingObject {

    private IState state;

    public ControllableObject(IState state) {
        this.state = state;
    }

    public void turnLeft() {
        state = state.turnLeft();
    }

    public void turnRight() {
        state = state.turnRight();
    }

    public void turnUp() {
        state = state.turnUp();
    }

    public void turnDown() {
        state = state.turnDown();
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
