package movingModels;

import appPackage.App;
import command.PauseCommand;
import flyweight.MyImage;
import staticModels.Jedlo;
import utility.Config;
import utility.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Trieda moving.Hadik vytvori hadika prefarbením štvorca na červeno. moving.Hadik sa môže pohybovať dolava, doprava, hore, dole.
 * Tak isto sa môže zväčšovať, alebo pri reštartovaní hry vytvoriť na novo.
 *
 * @author (Adam Beliansky)
 * @version (a version number or a date)
 * #C432B3
 */
public class Hadik extends MovingObject {

    private final MyImage[] head = new MyImage[4];
    private MyImage body;
    public final ArrayList<Position> telo = new ArrayList<>();
    private int initialLength = 2;
    private static final Random random = new Random();


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
        body = App.getOrAddImage("src/resources/square.png");

        head[0] = App.getOrAddImage("src/resources/snake_up.png");
        head[1] = App.getOrAddImage("src/resources/snake_down.png");
        head[2] = App.getOrAddImage("src/resources/snake_left.png");
        head[3] = App.getOrAddImage("src/resources/snake_right.png");

        int y = random.nextInt(Config.pocetPixelov) * Config.rozmerBodu;
        for (int i = initialLength; i >= 0; i--) {
            telo.add(new Position(Config.rozmerBodu * 4 + i * Config.rozmerBodu, y));
        }
    }

    public Image getBody() {
        return body.image;
    }

    public Image getHead() {
        Image image = null;
        switch (smer) {
            case HORE:
                image = head[0].image;
                break;
            case DOLE:
                image = head[1].image;
                break;
            case VLAVO:
                image = head[2].image;
                break;
            case VPRAVO:
                image = head[3].image;
                break;
        }
        return image;
    }

    /**
     * Prida clanok.
     */
    public void pridajClanok() {
        this.telo.add(new Position(telo.get(telo.size() - 1).x, telo.get(telo.size() - 1).y));
        App.getInstance().pauseCommand.execute();
//        App.getInstance().pause();
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