package flyweight;

public class Position  {

    private int x;
    private int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void incX(int x) {
        this.x += x;
    }

    public int getY() {
        return y;
    }

    public void incY(int y) {
        this.y += y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Position p) {
        return x == p.x && y == p.y;
    }
}
