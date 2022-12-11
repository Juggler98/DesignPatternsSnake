package movingModels;

import staticModels.Jedlo;
import utility.Config;
import utility.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Trieda moving.Hadik vytvori hadika prefarbením štvorca na červeno. moving.Hadik sa môže pohybovať dolava, doprava, hore, dole.
 * Tak isto sa môže zväčšovať, alebo pri reštartovaní hry vytvoriť na novo.
 *
 * @author (Adam Beliansky)
 * @version (a version number or a date)
 * #C432B3
 */
public class Hadik extends MovingObject {

    private final Image[] head = new Image[4];
    private Image body;
    public final ArrayList<Position> telo = new ArrayList<>();
    private int initialLength = 2;


    /**
     * Vytvori hadika
     */
    public Hadik(int[] keys) {
        super(keys[0], keys[1], keys[2], keys[3]);
        init();
    }

    public Hadik(int[] keys, int initialLength) {
        super(keys[0], keys[1], keys[2], keys[3]);
        if (initialLength > 0) {
            this.initialLength = initialLength;
        }
        init();
    }

    private void init() {

        ImageIcon leftRight = new ImageIcon("src/resources/square.png");
        body = leftRight.getImage();

        ImageIcon up = new ImageIcon("src/resources/snake_up.png");
        ImageIcon down = new ImageIcon("src/resources/snake_down.png");
        ImageIcon left = new ImageIcon("src/resources/snake_left.png");
        ImageIcon right = new ImageIcon("src/resources/snake_right.png");
        head[0] = up.getImage();
        head[1] = down.getImage();
        head[2] = left.getImage();
        head[3] = right.getImage();

        for (int i = initialLength; i >= 0; i--) {
            telo.add(new Position(Config.rozmerBodu * 4 + i * Config.rozmerBodu, Config.rozmerBodu * 4));
        }
    }

    public Image getBody() {
        return body;
    }

    public Image getHead() {
        Image image = null;
        switch (smer) {
            case HORE:
                image = head[0];
                break;
            case DOLE:
                image = head[1];
                break;
            case VLAVO:
                image = head[2];
                break;
            case VPRAVO:
                image = head[3];
                break;
        }
        return image;
    }

    /**
     * Prida clanok.
     */
    public void pridajClanok() {
        this.telo.add(new Position(telo.get(telo.size() - 1).x, telo.get(telo.size() - 1).y));
    }

    public void move() {
        for (int z = telo.size() - 1; z > 0; z--) {
            telo.get(z).x = telo.get(z - 1).x;
            telo.get(z).y = telo.get(z - 1).y;
        }
        switch (smer) {
            case DOLE:
                telo.get(0).y += Config.rozmerBodu;
                break;
            case HORE:
                telo.get(0).y -= Config.rozmerBodu;
                break;
            case VLAVO:
                telo.get(0).x -= Config.rozmerBodu;
                break;
            case VPRAVO:
                telo.get(0).x += Config.rozmerBodu;
                break;
        }
    }

    public void action(Jedlo jedlo) {

    }

    /**
     * Vrati velkost hadika
     */
    public int getVelkost() {
        return this.telo.size();
    }

//    public position.Smer smer() {
//        return smer;
//    }

    /**
     * Vyvori noveho hadika.
     */
    public void novyHadik() {
        this.telo.clear();
        init();
    }
}