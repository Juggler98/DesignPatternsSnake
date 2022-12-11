package staticModels;

import movingModels.Hadik;
import utility.Config;
import utility.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Trieda staticModels.Jedlo vytvori a zobrazi na platne jedlo na nahodnej polohe. Teda objekt ktory sa hadik snazi "zjest".
 *
 * @author (Adam Beliansky)
 * @version (a version number or a date)
 */
public class Jedlo {
    private Position position;
    private Image image;
    private final static Random random = new Random();


    /**
     * Vytvori prve jedlo a nastavi pociatocne hodnoty.
     */
    public Jedlo(String resource) {
        ImageIcon image = new ImageIcon(resource);
        this.image = image.getImage();

        int x = random.nextInt(Config.rozmerPlatna / Config.rozmerBodu) * Config.rozmerBodu;
        int y = random.nextInt(Config.rozmerPlatna / Config.rozmerBodu) * Config.rozmerBodu;
        this.position = new Position(x, y);
    }

    /**
     * Sryje jedlo.
     */
    public void skryJedlo() {
//        this.hraciePole.skry(this.x, this.y);
    }

    public Position getPosition() {
        return position;
    }

    public Image getImage() {
        return image;
    }

    /**
     * Zmeni polohu jedla na nahodnu. Skontroluje ci sa nevygenerovalo na hadikovy.
     */
    public void zmenPoziciu() {
        position.x = random.nextInt(Config.rozmerPlatna / Config.rozmerBodu) * Config.rozmerBodu;
        position.y = random.nextInt(Config.rozmerPlatna / Config.rozmerBodu) * Config.rozmerBodu;
    }

    public void action(Hadik hadik) {
        hadik.action(this);
    }
}
