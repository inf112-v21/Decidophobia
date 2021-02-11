package inf112.skeleton.app.player;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Action;
import inf112.skeleton.app.Direction;

import static inf112.skeleton.app.DirectionTools.directionToVector;
import static inf112.skeleton.app.DirectionTools.rotationToDirection;

public class LocalPlayer implements IPlayer{
    private Vector2 playerPosition;
    private Direction facingDirection;

    //public LocalPlayer(){}
    @Override
    public void setPosition(Vector2 position) {
        this.playerPosition = position;
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(playerPosition.x,playerPosition.y);
    }

    public void setFacingDirection(Direction facingDir) { this.facingDirection = facingDir; }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void move(Action move) {
        if(move == Action.FORWARD)
            playerPosition.add(directionToVector(facingDirection));
        else if(move == Action.REVERSE)
            playerPosition.sub(directionToVector(facingDirection));
        if(move == Action.ROTATE_LEFT)
            facingDirection=(rotationToDirection(facingDirection,false));
        else if(move == Action.ROTATE_RIGHT)
            facingDirection=(rotationToDirection(facingDirection,true));
        else if(move == Action.U_TURN) {
            facingDirection = (rotationToDirection(facingDirection, true));
            facingDirection = (rotationToDirection(facingDirection, true));
        }
    }
}
