package observer;

import appPackage.App;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ObservableKeyAdapter extends Observable implements KeyListener {
    private int pressedKey;
    private static ObservableKeyAdapter instance;
    private ObservableKeyAdapter() {

    }
    public static ObservableKeyAdapter getInstance() {
        if (instance == null) {
            instance = new ObservableKeyAdapter();
        }
        return instance;
    }

    public void keyPressed(KeyEvent e) {
        pressedKey = e.getKeyCode();
        super.notifyObservers();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public int getPressedKey() {
        return pressedKey;
    }
}
