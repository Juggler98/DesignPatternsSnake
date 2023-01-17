package hittableModels;

import utility.Config;
import utility.Position;

public class HittableFactory {

    private HittableFactory() {
    }

    public static Apple createApple() {
        return new Apple("assets/apple.png");
    }

    public static Spider createSpider() {
        return new Spider("assets/spider.png");
    }

    public static Obstacle createObstacle(Position[] positions) {
        Obstacle obstacle = new Obstacle();
        for (Position position : positions) {
            obstacle.add(new Position(position.getX() * Config.rozmerBodu, position.getY() * Config.rozmerBodu), "assets/square.png");
        }
        return obstacle;
    }


}
