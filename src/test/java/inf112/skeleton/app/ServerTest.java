package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.packets.GameCards;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import inf112.skeleton.app.cards.Deck;
import inf112.skeleton.app.cards.PlayerCards;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import static inf112.skeleton.app.cards.CardType.*;
import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class ServerTest {

    RoboServer server;

    @Before
    public void setup(){
        server = new RoboServer();
        server.runServer();

    }
    @After
    public void stop(){
        server.stopServer();
    }
    @Test
    public void ifServerAndHostAreTheSameThenRespondLAN() throws Exception {
        RoboClient myClient = new RoboClient();
        myClient.join(RoboServer.getLANIp());
        while(myClient.response == null)
            sleep(1000); // Delay so host can respond
        System.out.println(myClient.response + "  <-  Should be \"joined,0\"");
        String[] strResponse = myClient.response.split(",");
        assertEquals("joined,0", strResponse[0]+","+strResponse[1]);
    }

    @Test
    public void ifClientHaveWrongIpHostDontReceiveMessage() throws Exception {
        RoboClient myClient = new RoboClient();
        myClient.join("000.000.00.0");
        sleep(1000); // Delay so host can respond
        assertEquals(null, myClient.response);
    }
    @Test
    public void serverHasPlayerConnection() throws Exception {
        RoboClient myClient = new RoboClient();
        myClient.join(RoboServer.getLANIp());
        while(myClient.response == null)
            sleep(1000); // Delay so host can respond
        assertEquals(1, server.getPlayerIpToConnect().size());
    }
    @Test
    public void serverRejectsJoinSinceGameStarted() throws Exception {
        RoboClient myClient = new RoboClient();
        server.sendGameInstance("Started");
        sleep(1000); // Delay so host can respond
        myClient.join(RoboServer.getLANIp());
        while(myClient.response == null)
            sleep(1000); // Delay so host can respond
        assertEquals("Rejected",myClient.response);
    }
    @Test
    public void serverNotAddsTwoEqualConnections() throws Exception {
        RoboClient myClient = new RoboClient();
        myClient.join(RoboServer.getLANIp());
        while(myClient.response == null)
            sleep(1000); // Delay so host can respond
        String[] strResponse = myClient.response.split(",");
        System.out.println(myClient.response);
        assertEquals("joined,0", strResponse[0]+","+strResponse[1]);
        myClient.join(RoboServer.getLANIp());
        while(myClient.response.substring(0,2+6+1).equals("joined,0,"))
            sleep(1000); // Delay so host can respond
        assertEquals(1,server.getPlayerIpToConnect().size());
        assertEquals("AlreadyJoined",myClient.response);
    }
    @Test
    public void hostSendsRules() throws InterruptedException {
        RoboClient myClient = new RoboClient();
        myClient.join(RoboServer.getLANIp());
        while(myClient.getGameRules() == null)
            sleep(1000); // Delay so host can respond
        assertEquals("gameRules,3;9,", myClient.getGameRules().toString());
        assertEquals("lobby,0;P1;0;true;false:,", myClient.getLobbyInfo().toString());
    }
    @Test
    public void hostDealsCards() throws InterruptedException {
        RoboClient myClient = new RoboClient();
        myClient.join(RoboServer.getLANIp());
        while(myClient.getGameRules() == null)
            sleep(1000); // Delay so host can respond
        GameCards gameCards = new GameCards(new Deck());
        String cardString = "h;FORWARD_1:999;FORWARD_2:888;FORWARD_3:777;ROTATE_RIGHT:666;" +
                "l;FORWARD_2:333;FORWARD_3:222;ROTATE_RIGHT:111;" +
                "a;ROTATE_LEFT:555;U_TURN:444;";
        gameCards.addPlayerCards(0,cardString);
        myClient.response = null;
        myClient.dealCards(gameCards);
        while(myClient.response == null)
            sleep(1000); // Delay so host can respond
        sleep(3000);
        PlayerCards clientPlayersCards = myClient.getClientCards();
        ParserTest.playerCardEvaluation(clientPlayersCards);
    }
}
