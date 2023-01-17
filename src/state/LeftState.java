package state;

import flyweight.MyImage;
import flyweight.MyImageFactory;
import utility.Config;
import flyweight.PositionImage;

import java.util.List;

public class LeftState extends SnakeState {

    private final static MyImage image = MyImageFactory.getOrAddImage("assets/snake_left.png");
    public LeftState(List<PositionImage> telo) {
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
        telo.get(0).position.incX(-Config.rozmerBodu);
    }
}
