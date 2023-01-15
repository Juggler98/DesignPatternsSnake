package hitableModels;

import controllable.ControllableObject;
import controllable.Hadik;

public class Apple extends Food {
    public Apple(String resource) {
        super(resource);
    }

    @Override
    public void action(ControllableObject c) {
        if (c.getHeadPosition().equals(position)) {
            if (c instanceof Hadik) {
                Hadik hadik = (Hadik) c;
                hadik.pridajClanok();
            }
            move();
        }
    }

}
