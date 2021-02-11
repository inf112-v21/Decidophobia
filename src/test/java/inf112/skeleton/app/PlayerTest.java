package inf112.skeleton.app;

import static org.junit.Assert.*;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.player.LocalPlayer;
import org.junit.Test;


public class PlayerTest {
    @Test
    public void playerSetPositionIsGetPosition1(){
        LocalPlayer player = new LocalPlayer();
        Vector2 playerPosition = new Vector2(1,1);
        player.setPosition(playerPosition);
        assertEquals(new Vector2(1,1), player.getPosition());
    }
    @Test
    public void playerSetPositionIsGetPosition2(){
        LocalPlayer player = new LocalPlayer();
        Vector2 playerPosition = new Vector2(2,1);
        player.setPosition(playerPosition);
        assertEquals(new Vector2(2,1), player.getPosition());
    }
    @Test
    public void playerFaceNorthAndGoesOneStepForwardAndThenOneBackward(){
        LocalPlayer player = new LocalPlayer();
        player.setPosition(new Vector2(2,1));
        player.setFacingDirection(Direction.NORTH);
        player.move(Action.FORWARD);
        assertEquals(new Vector2(2,2), player.getPosition());
        player.move(Action.REVERSE);
        assertEquals(new Vector2(2,1), player.getPosition());
    }
    @Test
    public void playerTurnsLeftThenRightAndLastUturn(){
        LocalPlayer player = new LocalPlayer();
        player.setPosition(new Vector2(2,1));
        player.setFacingDirection(Direction.NORTH);
        player.move(Action.ROTATE_LEFT);
        assertEquals(Direction.WEST, player.getFacingDirection());
        player.move(Action.ROTATE_RIGHT);
        assertEquals(Direction.NORTH, player.getFacingDirection());
        player.move(Action.U_TURN);
        assertEquals(Direction.SOUTH, player.getFacingDirection());

    }
}
