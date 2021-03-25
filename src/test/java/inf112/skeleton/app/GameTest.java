package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.cards.PlayerCards;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GameTest {
    RoboServer server;

    RoboClient client;

    Game roboRally;

    @Before
    public void setup() throws InterruptedException {
        server = new RoboServer();
        server.runServer();
        Thread.sleep(1000);
        client = new RoboClient(RoboServer.getLANIp());
        roboRally = new Game(client);
        client.setGameReference(roboRally);
        client.join();
        while(client.getGameRules()==null && client.getLobbyInfo()==null){
            Thread.sleep(1000);
        }
    }
    @After
    public void end(){
        server.stopServer();
    }

    @Test
    public void gameProcedure() throws InterruptedException {
        roboRally.dealCards();
        while(client.getClientCards()==null){
            Thread.sleep(1000);
        }
        client.getClientCards().setActiveCard(0,0);
        client.getClientCards().setActiveCard(0,1);
        client.getClientCards().setActiveCard(0,2);
        client.getClientCards().setActiveCard(0,3);
        client.getClientCards().setActiveCard(0,4);
        client.sendMoves(client.getClientCards());

        while(roboRally.getLastRound()==null){
            Thread.sleep(1000);
        }

        PlayerCards pCards = roboRally.getLastRound().getAllPlayerHands().get(client.getClientPlayerNr());

        assertNotNull(pCards.getActiveCard(0));
        assertNotNull(pCards.getActiveCard(1));
        assertNotNull(pCards.getActiveCard(2));
        assertNotNull(pCards.getActiveCard(3));
        assertNotNull(pCards.getActiveCard(4));
    }
}
