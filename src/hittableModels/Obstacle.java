package hittableModels;

import controllable.ControllableObject;
import utility.PositionImage;

import java.util.LinkedList;

public class Obstacle extends Hittable {

    public Obstacle(String resource) {
        super(resource);
    }

    public Obstacle() {

    }

    @Override
    public void action2(ControllableObject c) {
        c.end();
    }

}
