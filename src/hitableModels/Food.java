package hitableModels;

import flyweight.MyImage;
import flyweight.MyImageFactory;
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
public abstract class Food implements IMovingObject, IServant {

    private final static Random random = new Random();

    protected final Position position = new Position(0, 0);
    private final MyImage myImage;

    public Food(String resource) {
        myImage = MyImageFactory.getOrAddImage(resource);
        move();
    }

    public Position getPosition() {
        return position;
    }

    public MyImage getMyImage() {
        return myImage;
    }

    @Override
    public void move() {
        position.x = random.nextInt(Config.pocetPixelov) * Config.rozmerBodu;
        position.y = random.nextInt(Config.pocetPixelov) * Config.rozmerBodu;
    }


}
