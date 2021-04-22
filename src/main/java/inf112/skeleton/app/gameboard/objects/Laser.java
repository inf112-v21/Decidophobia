package inf112.skeleton.app.gameboard.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;

public class Laser {

    private final Vector2 startPosition;
    private final Direction direction;

    public Laser(int x, int y, Direction direction) {
        this.startPosition = new Vector2(x, y);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Vector2 getStartPosition() {
        return startPosition;
    }
}
