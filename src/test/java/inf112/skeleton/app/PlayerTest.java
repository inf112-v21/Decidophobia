package inf112.skeleton.app;

import static org.junit.Assert.*;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.player.LocalPlayer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


public class PlayerTest {
    private LocalPlayer player;
    private Vector2 playerPosition;

    @Before
    public void setUp() throws Exception {
        player = new LocalPlayer();
        playerPosition = new Vector2(2, 1);
        player.setPosition(playerPosition);
        player.setFacingDirection(Direction.NORTH);
    }

    @Test
    public void playerSetPositionIsGetPosition1(){
        playerPosition = new Vector2(1,1);
        player.setPosition(playerPosition);
        assertEquals(new Vector2(1,1), player.getPosition());
    }
    @Test
    public void playerSetPositionIsGetPosition2(){
        assertEquals(new Vector2(2,1), player.getPosition());
    }
    @Test
    public void playerFaceNorthAndGoesOneStepForwardAndThenOneBackward(){
        player.move(Action.FORWARD);
        assertEquals(new Vector2(2,2), player.getPosition());

        player.move(Action.REVERSE);
        assertEquals(new Vector2(2,1), player.getPosition());
    }
    @Test
    public void playerTurnsLeftThenRightAndLastUturn(){
        player.move(Action.ROTATE_LEFT);
        assertEquals(Direction.WEST, player.getFacingDirection());

        player.move(Action.ROTATE_RIGHT);
        assertEquals(Direction.NORTH, player.getFacingDirection());

        player.move(Action.U_TURN);
        assertEquals(Direction.SOUTH, player.getFacingDirection());

    }
}
