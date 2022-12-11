package abstractFactory;

import utility.Position;

public class ShortProduct extends AbstractProduct {

    private final Position position = new Position(0, 0);
    public ShortProduct(String resource) {
        super(resource);
    }


}
