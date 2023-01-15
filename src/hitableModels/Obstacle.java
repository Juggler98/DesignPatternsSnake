package hitableModels;

import controllable.ControllableObject;
import controllable.Hadik;
import flyweight.MyImageFactory;
import utility.Position;
import utility.PositionImage;

import java.util.LinkedList;

public class Obstacle implements IServant {

    protected LinkedList<PositionImage> body = new LinkedList<>();

    public Obstacle() {

    }

    public void add(Position p, String path) {
        body.add(new PositionImage(p, MyImageFactory.getOrAddImage(path)));
    }

    public LinkedList<PositionImage> getBody() {
        return body;
    }

    @Override
    public void action(ControllableObject c) {
        for (PositionImage p : body) {
            if (p.position.equals(c.getHeadPosition())) {
                c.end();
            }
        }

    }
}
