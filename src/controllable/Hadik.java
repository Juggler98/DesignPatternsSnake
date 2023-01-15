package controllable;

import state.RightState;
import state.SnakeState;
import utility.Position;

/**
 * Trieda moving.Hadik vytvori hadika prefarbením štvorca na červeno. moving.Hadik sa môže pohybovať dolava, doprava, hore, dole.
 * Tak isto sa môže zväčšovať, alebo pri reštartovaní hry vytvoriť na novo.
 *
 * @author (Adam Beliansky)
 * @version (a version number or a date)
 * #C432B3
 */
public class Hadik extends ControllableObject {
    private int initialLength = 2;

    /**
     * Vytvori hadika
     */
    public Hadik(int[] keys) {
        super(keys[0], keys[1], keys[2], keys[3], new RightState(null));
        init();
    }

    public Hadik(int[] keys, int initialLength) {
        super(keys[0], keys[1], keys[2], keys[3], new RightState(null));
        if (initialLength > 0) {
            this.initialLength = initialLength;
        }
        init();
    }

    private void init() {
        for (int i = initialLength; i > 0; i--) {
            state().pridajClanok();
        }
    }

    /**
     * Prida clanok.
     */
    public void pridajClanok() {
        state().pridajClanok();
    }

    public void odoberClanok() {
        state().odoberClanok();
    }

    public Position getHeadPosition() {
        return state().getBody().get(0).position;
    }

    public void move() {
        state().move();
    }

    public SnakeState state() {
        return (SnakeState) getState();
    }

    /**
     * Vrati velkost hadika
     */
    public int getSize() {
        return state().getBody().size();
    }

}