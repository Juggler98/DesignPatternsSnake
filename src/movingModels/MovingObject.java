package movingModels;

import observer.IObserver;
import observer.ObservableKeyAdapter;
import observer.Observable;
import utility.Smer;

public abstract class MovingObject implements IMovingObject, IObserver {

    protected Smer smer = Smer.VPRAVO;
    private final int upKey;
    private final int downKey;
    private final int leftKey;
    private final int rightKey;

    public MovingObject(int upKey, int downKey, int leftKey, int rightKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    private void up() {
        if (smer == Smer.DOLE) {
            return;
        }
        smer = Smer.HORE;
    }

    private void down() {
        if (smer == Smer.HORE) {
            return;
        }
        smer = Smer.DOLE;
    }

    private void left() {
        if (smer == Smer.VPRAVO) {
            return;
        }
        smer = Smer.VLAVO;
    }

    private void right() {
        if (smer == Smer.VLAVO) {
            return;
        }
        smer = Smer.VPRAVO;
    }

    @Override
    public void update(Observable observable) {
        ObservableKeyAdapter observableKeyAdapter = (ObservableKeyAdapter) observable;
        int key = observableKeyAdapter.getPressedKey();
        if (key == rightKey) {
            right();
        } else if (key == leftKey) {
            left();
        } else if (key == upKey) {
            up();
        } else if (key == downKey) {
            down();
        }
    }

}
