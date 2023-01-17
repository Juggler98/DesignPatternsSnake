package hittableModels;

import controllable.ControllableObject;
import flyweight.MyImageFactory;
import movingModels.IMovingObject;
import utility.Config;
import utility.Position;
import flyweight.PositionImage;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Hittable implements IServant, IMovingObject {

    private final static Random random = new Random();
    protected List<PositionImage> body = new LinkedList<>();

    public Hittable(String resource) {
        add(new Position(0, 0), resource);
        move();
    }

    public Hittable() {

    }

    public void add(Position p, String path) {
        body.add(new PositionImage(p, MyImageFactory.getOrAddImage(path)));
    }

    public List<PositionImage> getBody() {
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
            p.position.setX( random.nextInt(Config.pocetPixelov) * Config.rozmerBodu);
            p.position.setY(random.nextInt(Config.pocetPixelov) * Config.rozmerBodu);
        }
    }
}
