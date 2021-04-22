package inf112.skeleton.app;

import static org.junit.Assert.*;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.player.Robot;
import org.junit.Before;
import org.junit.Test;


public class RobotTest {
    private Robot robot;
    private Vector2 robotPosition;

    @Before
    public void setUp() {
        System.out.println(getClass());
        //robot = new Robot(new Vector2(2, 1),Direction.NORTH, null, T);
    }

    @Test
    public void robotSetPositionIsGetPosition1(){
        robotPosition = new Vector2(1,1);
        robot.setPosition(robotPosition);
        assertEquals(new Vector2(1,1), robot.getPosition());
    }
    @Test
    public void robotSetPositionIsGetPosition2(){
        assertEquals(new Vector2(2,1), robot.getPosition());
    }
    @Test
    public void robotFaceNorthAndGoesOneStepForwardAndThenOneBackward(){
        robot.move(Action.FORWARD);
        assertEquals(new Vector2(2,2), robot.getPosition());

        robot.move(Action.REVERSE);
        assertEquals(new Vector2(2,1), robot.getPosition());
    }
    @Test
    public void robotTurnsLeftThenRightAndLastUturn(){
        robot.move(Action.ROTATE_LEFT);
        assertEquals(Direction.WEST, robot.getFacingDirection());

        robot.move(Action.ROTATE_RIGHT);
        assertEquals(Direction.NORTH, robot.getFacingDirection());

        robot.move(Action.U_TURN);
        assertEquals(Direction.SOUTH, robot.getFacingDirection());

    }
}
