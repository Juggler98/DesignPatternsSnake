package hittableModels;

import controllable.ControllableObject;
import controllable.Snake;

public class Spider extends Hittable {

    public Spider(String resource) {
        super(resource);
    }

    @Override
    public void action2(ControllableObject c) {
        if (c.getSize() == 1) {
            c.end();
        } else {
            if (c instanceof Snake) {
                Snake snake = (Snake) c;
                snake.removeBodyPart();
            }
        }
        move();
    }
}
