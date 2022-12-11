public abstract class MovingObject implements IMovingObject {

    protected Smer smer = Smer.VPRAVO;
    public final int upKey;
    public final int downKey;
    public final int leftKey;
    public final int rightKey;

    public MovingObject(int upKey, int downKey, int leftKey, int rightKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    public void up(int key) {
        if (smer == Smer.DOLE) {
            return;
        }
        if (upKey == key) {
            smer = Smer.HORE;
        }
    }

    public void down(int key) {
        if (smer == Smer.HORE) {
            return;
        }
        if (downKey == key) {
            smer = Smer.DOLE;
        }
    }

    public void left(int key) {
        if (smer == Smer.VPRAVO) {
            return;
        }
        if (leftKey == key) {
            smer = Smer.VLAVO;
        }
    }

    public void right(int key) {
        if (smer == Smer.VLAVO) {
            return;
        }
        if (rightKey == key) {
            smer = Smer.VPRAVO;
        }
    }

    public void changeDirection(int key) {
        if (rightKey == key) {
//            right();
        }
    }

}
