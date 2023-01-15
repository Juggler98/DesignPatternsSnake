package hittableModels;

import controllable.ControllableObject;
import controllable.Hadik;

public class Apple extends Hittable {
    public Apple(String resource) {
        super(resource);
    }

    @Override
    public void action2(ControllableObject c) {
        if (c instanceof Hadik) {
            Hadik hadik = (Hadik) c;
            hadik.pridajClanok();
        }
        move();
    }

}
