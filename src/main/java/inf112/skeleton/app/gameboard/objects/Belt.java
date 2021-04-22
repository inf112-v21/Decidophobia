package inf112.skeleton.app.gameboard.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;

public class Belt {

    private final Vector2 position;
    private final Direction direction;

    public Belt(Direction direction, Vector2 position) {
        this.direction = direction;
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }
}
