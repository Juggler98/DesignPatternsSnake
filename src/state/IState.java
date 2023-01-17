package state;

import utility.PositionImage;

import java.util.List;

public interface IState {

    IState turnLeft();
    IState turnRight();
    IState turnUp();
    IState turnDown();
    void move();
    List<PositionImage> getBody();
}
