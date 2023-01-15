package state;

import flyweight.MyImage;
import flyweight.MyImageFactory;
import utility.Config;
import utility.PositionImage;

import java.util.LinkedList;

public class UpState extends SnakeState {

    private final static MyImage image = MyImageFactory.getOrAddImage("assets/snake_up.png");
    public UpState(LinkedList<PositionImage> telo) {
        super(telo);
        if (telo != null) {
            telo.get(0).setImage(image);
        }
    }

    @Override
    public IState turnUp() {
        return this;
    }

    @Override
    public IState turnDown() {
        return this;
    }

    @Override
    public void move() {
        super.move();
        telo.get(0).position.y -= Config.rozmerBodu;
    }
}
