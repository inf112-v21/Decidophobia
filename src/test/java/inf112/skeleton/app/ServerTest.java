package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.Multiplayer.RoboClient;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

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
        RoboClient myClient = new RoboClient(RoboServer.getLANIp());
        myClient.sendRequest("Join");
        sleep(1000); // Delay so host can respond
        System.out.println(myClient.getResponse() + "  <-  Should be \"Joined\"");
        assertEquals("Joined", myClient.getResponse());
    }

    @Test
    public void ifClientHaveWrongIpHostDontReceiveMessage() throws Exception {
        RoboClient myClient = new RoboClient("000.000.00.0");
        myClient.sendRequest("Join");
        sleep(1000); // Delay so host can respond
        assertEquals(null, myClient.getResponse());
    }
    @Test
    public void serverHasPlayerConnection() throws Exception {
        RoboClient myClient = new RoboClient(RoboServer.getLANIp());
        myClient.sendRequest("Join");
        sleep(1000); // Delay so host can respond
        assertEquals(1, server.getPlayerIpToConnect().size());
    }
    @Test
    public void serverRejectsJoinSinceGameStarted() throws Exception {
        RoboClient myClient = new RoboClient(RoboServer.getLANIp());
        server.sendGameInstance("Started");
        myClient.sendRequest("Join");
        sleep(1000); // Delay so host can respond
        assertEquals("Rejected",myClient.getResponse());
    }
    @Test
    public void serverNotAddsTwoEqualConnections() throws Exception {
        RoboClient myClient = new RoboClient(RoboServer.getLANIp());
        myClient.sendRequest("Join");
        sleep(1000);
        myClient.sendRequest("Join");
        sleep(1000); // Delay so host can respond
        assertEquals(1,server.getPlayerIpToConnect().size());
        assertEquals("AlreadyJoined",myClient.getResponse());

    }
}
