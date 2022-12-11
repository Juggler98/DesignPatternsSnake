package abstractFactory;

import movingModels.IMovingObject;

public class MovingApple extends ShortProduct implements IMovingObject {

    public MovingApple(String resource) {
        super(resource);
    }

    @Override
    public void move() {

    }
}
