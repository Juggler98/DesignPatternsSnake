package abstractFactory;

import utility.Position;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class LongProduct extends AbstractProduct {

    private LinkedList<Position> body = new LinkedList<>();

    public LongProduct(String resource) {
        super(resource);
    }
}
