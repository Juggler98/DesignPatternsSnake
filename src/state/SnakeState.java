package state;

import flyweight.MyImage;
import flyweight.MyImageFactory;
import utility.Config;
import flyweight.Position;
import flyweight.PositionImage;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SnakeState implements IState {

    protected List<PositionImage> telo;
    private final static MyImage body = MyImageFactory.getOrAddImage("assets/square.png");

    public SnakeState(List<PositionImage> telo) {
        if (telo == null) {
            telo = new LinkedList<>();
            Random random = new Random();
            int y = random.nextInt(Config.pocetPixelov) * Config.rozmerBodu;
            telo.add(new PositionImage(new Position(Config.rozmerBodu * 4, y), RightState.image));
        }
        this.telo = telo;
    }

    public void pridajClanok() {
        telo.add(new PositionImage(new Position(telo.get(telo.size() - 1).position.getX(), telo.get(telo.size() - 1).position.getY()), body));
    }

    public void odoberClanok() {
        telo.remove(telo.size() - 1);
    }

    @Override
    public IState turnLeft() {
        return new LeftState(telo);
    }

    @Override
    public IState turnRight() {
        return new RightState(telo);
    }

    @Override
    public IState turnUp() {
        return new UpState(telo);
    }

    @Override
    public IState turnDown() {
        return new DownState(telo);
    }

    @Override
    public void move() {
        for (int z = telo.size() - 1; z > 0; z--) {
            telo.get(z).position.setX(telo.get(z - 1).position.getX());
            telo.get(z).position.setY(telo.get(z - 1).position.getY());
        }
    }

    public List<PositionImage> getBody() {
        return telo;
    }
}
