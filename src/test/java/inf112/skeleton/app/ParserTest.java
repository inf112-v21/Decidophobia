package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import inf112.skeleton.app.Multiplayer.packets.LobbyInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    RoboClient robo;
    @Before
    public void setup(){
        robo = new RoboClient(null);
    }

    @Test
    public void gameRulesIsCorrectlyConstructed1(){
        robo.parser("gameRules,4;10,");
        assertEquals(4,robo.getGameRules().getLifeTokens());
        assertEquals(10,robo.getGameRules().getDamageTokens());
    }
    @Test
    public void gameRulesObjectIsCorrectlyConstructedAndParsed(){
        GameRules rules = new GameRules(4,10);
        robo.parser(rules.toString());
        assertEquals(4,robo.getGameRules().getLifeTokens());
        assertEquals(10,robo.getGameRules().getDamageTokens());
    }
    @Test
    public void lobbyInfoIsCorrectlyConstructed(){
        robo.parser("lobby,1;Isak;1;true;true:2;Alex;2;false;false:,");
        lobbyInfoEvaluation();
    }
    @Test
    public void lobbyObjectIsCorrectlyConstructedAndParsed(){
        LobbyInfo lobby = new LobbyInfo();
        lobby.addPlayer(0);
        lobby.addPlayer(1);
        lobby.getPlayers().get(0).setNickname("Isak");
        lobby.getPlayers().get(1).setNickname("Alex");
        lobby.getPlayers().get(0).setReady(true);
        robo.parser(lobby.toString());
        lobbyInfoEvaluation();

    }

    public void lobbyInfoEvaluation(){
        assertEquals(2,robo.getLobbyInfo().getPlayers().size());
        assertEquals("Isak",robo.getLobbyInfo().getPlayers().get(0).getNickname());
        assertEquals("Alex",robo.getLobbyInfo().getPlayers().get(1).getNickname());
        assertTrue(robo.getLobbyInfo().playerIsReady(0));
        assertFalse(robo.getLobbyInfo().playerIsReady(1));
        assertTrue(robo.getLobbyInfo().getPlayers().get(0).getHost());
        assertFalse(robo.getLobbyInfo().getPlayers().get(1).getHost());
    }
}
