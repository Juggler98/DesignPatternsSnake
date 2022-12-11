package utility;

public class Position {

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x;
    public int y;

    public boolean equals(Position p) {
        return x == p.x && y == p.y;
    }
}
