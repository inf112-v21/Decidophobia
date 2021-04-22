package inf112.skeleton.app.gameboard.objects;

import com.badlogic.gdx.math.Vector2;

public class Flag {

    private final int flagnumber;
    private final Vector2 position;

    public Flag(int flagnumber, int x, int y) {
        this.flagnumber = flagnumber;
        this.position = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getFlagnumber() {
        return flagnumber;
    }
}
