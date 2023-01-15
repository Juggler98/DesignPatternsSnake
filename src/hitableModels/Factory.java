package hitableModels;

import utility.Config;
import utility.Position;

public class Factory {

    private Factory() {
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
            obstacle.add(new Position(position.x * Config.rozmerBodu, position.y * Config.rozmerBodu), "assets/square.png");
        }
        obstacle.add(new Position(6 * Config.rozmerBodu, 5 * Config.rozmerBodu), "assets/square.png");
        obstacle.add(new Position(7 * Config.rozmerBodu, 5 * Config.rozmerBodu), "assets/square.png");
        obstacle.add(new Position(5 * Config.rozmerBodu, 6 * Config.rozmerBodu), "assets/square.png");
        obstacle.add(new Position(6 * Config.rozmerBodu, 6 * Config.rozmerBodu), "assets/square.png");
        obstacle.add(new Position(7 * Config.rozmerBodu, 6 * Config.rozmerBodu), "assets/square.png");
        return obstacle;
    }


}
