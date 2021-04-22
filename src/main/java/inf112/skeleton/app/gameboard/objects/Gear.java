package inf112.skeleton.app.gameboard.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;

public class Gear {

    public final RotateByAction rotate;
    public final Vector2 position;

    public Gear(RotateByAction rotate, Vector2 position) {
        this.rotate = rotate;
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public RotateByAction getRotate() {
        return rotate;
    }
}
