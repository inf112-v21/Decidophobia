package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import inf112.skeleton.app.Multiplayer.packets.LobbyInfo;
import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.PlayerCards;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.system.CallbackI;

import static inf112.skeleton.app.cards.CardType.*;
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
    @Test
    public void joinRequestParsesLobbyAndGameRules(){
        robo.parser("joined,1,gameRules,4;10,lobby,0;Isak;0;true;true:0;Alex;0;false;false:,");
        lobbyInfoEvaluation();
        assertEquals(4,robo.getGameRules().getLifeTokens());
        assertEquals(10,robo.getGameRules().getDamageTokens());
        assertEquals(1,robo.getClientPlayerNr());
    }
    @Test
    public void playerCardsIsParsedCorrectly(){
        String parseText = "h;FORWARD_1:999;FORWARD_2:888;FORWARD_3:777;ROTATE_RIGHT:666;" +
                "l;FORWARD_2:333;FORWARD_3:222;ROTATE_RIGHT:111;" +
                "a;ROTATE_LEFT:555;U_TURN:444;";

        PlayerCards nrOnesCards = new PlayerCards(parseText);

        playerCardEvaluation(nrOnesCards);
    }
    @Test
    public void playerCardsObjectIsWrittenAndParsedCorrectly(){
        PlayerCards cards = new PlayerCards();
        cards.insertCard(new Cards(FORWARD_1,999));
        cards.insertCard(new Cards(FORWARD_2,888));
        cards.insertCard(new Cards(FORWARD_3,777));
        cards.insertCard(new Cards(ROTATE_RIGHT,666));
        cards.insertCard(new Cards(ROTATE_LEFT,555));
        cards.insertCard(new Cards(U_TURN,444));
        cards.insertCard(new Cards(FORWARD_2,333));
        cards.insertCard(new Cards(FORWARD_3,222));
        cards.insertCard(new Cards(ROTATE_RIGHT,111));
        cards.setActiveCard(6,0);
        cards.setActiveCard(6,1);
        cards.setActiveCard(6,2);
        cards.setActiveCard(4,3);
        cards.setActiveCard(4,4);
        cards.lockCard(0);
        cards.lockCard(1);
        cards.lockCard(2);

        PlayerCards sameCards = new PlayerCards(cards.toString());
        playerCardEvaluation(sameCards);
    }

    public static void playerCardEvaluation(PlayerCards cards){
        // card from player hand
        assertEquals(888,cards.getCardsInHand().get(1).getPriority());
        assertEquals(FORWARD_2,cards.getCardsInHand().get(1).getType());
        // card from active cards that is locked
        assertEquals(333,cards.getActiveCards().get(0).getPriority());
        assertEquals(FORWARD_2,cards.getActiveCards().get(0).getType());
        // card from active cards that is no locked
        assertEquals(555,cards.getActiveCards().get(3).getPriority());
        assertEquals(ROTATE_LEFT,cards.getActiveCards().get(3).getType());
        // card from locked cards
        assertEquals(222,cards.getLockedCards().get(1).getPriority());
        assertEquals(FORWARD_3,cards.getLockedCards().get(1).getType());
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
