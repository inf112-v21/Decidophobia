package inf112.skeleton.app;

import static org.junit.Assert.*;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.player.LocalPlayer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


public class PlayerTest {
    LocalPlayer player;
    Vector2 playerPosition;


    @BeforeEach
    public void setup(){
        player = new LocalPlayer();
        playerPosition = new Vector2(2,1);
    }

    @Test
    public void playerSetPositionIsGetPosition1(){
        playerPosition = new Vector2(1,1);
        player.setPosition(playerPosition);
        assertEquals(new Vector2(1,1), player.getPosition());
    }
    @Test
    public void playerSetPositionIsGetPosition2(){
        player.setPosition(playerPosition);
        assertEquals(new Vector2(2,1), player.getPosition());
    }
    @Test
    public void playerFaceNorthAndGoesOneStepForwardAndThenOneBackward(){
        player.setFacingDirection(Direction.NORTH);

        player.move(Action.FORWARD);
        assertEquals(new Vector2(2,2), player.getPosition());

        player.move(Action.REVERSE);
        assertEquals(new Vector2(2,1), player.getPosition());
    }
    @Test
    public void playerTurnsLeftThenRightAndLastUturn(){
        player.setFacingDirection(Direction.NORTH);

        player.move(Action.ROTATE_LEFT);
        assertEquals(Direction.WEST, player.getFacingDirection());

        player.move(Action.ROTATE_RIGHT);
        assertEquals(Direction.NORTH, player.getFacingDirection());

        player.move(Action.U_TURN);
        assertEquals(Direction.SOUTH, player.getFacingDirection());

    }
}
