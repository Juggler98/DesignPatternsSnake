package observer;

import java.awt.event.MouseEvent;

public class ObservableMouseListener extends Observable implements java.awt.event.MouseListener {

    private int x;
    private int y;

    private static ObservableMouseListener instance;
    private ObservableMouseListener() {

    }
    public static ObservableMouseListener getInstance() {
        if (instance == null) {
            instance = new ObservableMouseListener();
        }
        return instance;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        super.notifyObservers();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
