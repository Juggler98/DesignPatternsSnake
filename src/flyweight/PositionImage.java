package flyweight;

import utility.Position;

public class PositionImage {

    public final Position position;
    private MyImage image;

    public PositionImage(Position position, MyImage image) {
        this.position = position;
        this.image = image;
    }

    public void setImage(MyImage image) {
        this.image = image;
    }

    public MyImage getImage() {
        return image;
    }
}
