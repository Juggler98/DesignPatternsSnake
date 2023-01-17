package hittableModels;

import controllable.ControllableObject;
import controllable.Snake;

public class Apple extends Hittable {
    public Apple(String resource) {
        super(resource);
    }

    @Override
    public void action2(ControllableObject c) {
        if (c instanceof Snake) {
            Snake snake = (Snake) c;
            snake.addBodyPart();
        }
        move();
    }

}
