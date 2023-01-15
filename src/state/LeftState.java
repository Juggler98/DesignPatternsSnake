package state;

import flyweight.MyImage;
import flyweight.MyImageFactory;
import utility.Config;
import utility.PositionImage;

import java.util.LinkedList;

public class LeftState extends SnakeState {

    private final static MyImage image = MyImageFactory.getOrAddImage("assets/snake_left.png");
    public LeftState(LinkedList<PositionImage> telo) {
        super(telo);
        if (telo != null) {
            telo.get(0).setImage(image);
        }
    }

    @Override
    public IState turnLeft() {
        return this;
    }

    @Override
    public IState turnRight() {
        return this;
    }

    @Override
    public void move() {
        super.move();
        telo.get(0).position.x -= Config.rozmerBodu;
    }
}
