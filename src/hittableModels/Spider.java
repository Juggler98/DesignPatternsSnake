package hittableModels;

import controllable.ControllableObject;
import controllable.Hadik;

public class Spider extends Hittable {

    public Spider(String resource) {
        super(resource);
    }

    @Override
    public void action2(ControllableObject c) {
        if (c.getSize() == 1) {
            c.end();
        } else {
            if (c instanceof Hadik) {
                Hadik hadik = (Hadik) c;
                hadik.odoberClanok();
            }
        }
        move();
    }
}
