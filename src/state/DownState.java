package state;

import flyweight.MyImage;
import flyweight.MyImageFactory;
import utility.Config;
import utility.PositionImage;

import java.util.List;

public class DownState extends SnakeState{

    private final static MyImage image = MyImageFactory.getOrAddImage("assets/snake_down.png");
    public DownState(List<PositionImage> telo) {
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
        telo.get(0).position.incY(Config.rozmerBodu);
    }
}
