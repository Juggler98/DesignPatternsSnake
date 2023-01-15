package hittableModels;

import controllable.ControllableObject;
import flyweight.MyImageFactory;
import movingModels.IMovingObject;
import utility.Config;
import utility.Position;
import utility.PositionImage;

import java.util.LinkedList;
import java.util.Random;

public abstract class Hittable implements IServant, IMovingObject {

    private final static Random random = new Random();
    protected LinkedList<PositionImage> body = new LinkedList<>();

    public Hittable(String resource) {
        add(new Position(0, 0), resource);
        move();
    }

    public Hittable() {

    }

    public void add(Position p, String path) {
        body.add(new PositionImage(p, MyImageFactory.getOrAddImage(path)));
    }

    public LinkedList<PositionImage> getBody() {
        return body;
    }

    public abstract void action2(ControllableObject c);
    @Override
    public void action(ControllableObject c) {
        for (PositionImage p : body) {
            if (p.position.equals(c.getHeadPosition())) {
                action2(c);
                return;
            }
        }
    }

    @Override
    public void move() {
        for (PositionImage p : body) {
            p.position.x = random.nextInt(Config.pocetPixelov) * Config.rozmerBodu;
            p.position.y = random.nextInt(Config.pocetPixelov) * Config.rozmerBodu;
        }
    }
}
