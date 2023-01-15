package state;

import utility.PositionImage;

import java.util.LinkedList;

public interface IState {

    IState turnLeft();
    IState turnRight();
    IState turnUp();
    IState turnDown();
    void move();
    LinkedList<PositionImage> getBody();
}
