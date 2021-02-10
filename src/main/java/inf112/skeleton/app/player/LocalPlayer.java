package inf112.skeleton.app.player;

import com.badlogic.gdx.math.Vector2;

public class LocalPlayer implements IPlayer{
    Vector2 playerPosition;
    public LocalPlayer(){}
    @Override
    public void setPosition(Vector2 position) {
        this.playerPosition = position;
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(playerPosition.x,playerPosition.y);
    }
}
