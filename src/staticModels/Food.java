package staticModels;

import appPackage.App;
import flyweight.MyImage;
import movingModels.IMovingObject;
import utility.Config;
import utility.Position;

import java.util.Random;

/**
 * Trieda staticModels.Jedlo vytvori a zobrazi na platne jedlo na nahodnej polohe. Teda objekt ktory sa hadik snazi "zjest".
 *
 * @author (Adam Beliansky)
 * @version (a version number or a date)
 */
public abstract class Food implements IMovingObject {

    private final Position position = new Position(0, 0);
    private MyImage myImage;
    private final static Random random = new Random();


    public Food(String resource) {
        myImage = App.getOrAddImage(resource);
        move();
    }

    public MyImage getMyImage() {
        return myImage;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void move() {
        position.x = random.nextInt(Config.pocetPixelov) * Config.rozmerBodu;
        position.y = random.nextInt(Config.pocetPixelov) * Config.rozmerBodu;
    }
}
