package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    @Test
    public void gameRulesIsCorrectlyConstructed(){
        RoboClient robo = new RoboClient(null);
        robo.parser("gameRules,4;10,");
        assertEquals(4,robo.getGameRules().getLifeTokens());
        assertEquals(10,robo.getGameRules().getDamageTokens());
    }
}
