package hitableModels;

import controllable.ControllableObject;
import controllable.Hadik;

public class Spider extends Food {

    public Spider(String resource) {
        super(resource);
    }

    @Override
    public void action(ControllableObject c) {
        if (c.getHeadPosition().equals(position)) {
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
}
