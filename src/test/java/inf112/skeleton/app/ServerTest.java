package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
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
        myClient.join();
        while(myClient.response == null)
            sleep(1000); // Delay so host can respond
        System.out.println(myClient.response + "  <-  Should be \"joined,0\"");
        String[] strResponse = myClient.response.split(",");
        assertEquals("joined,0", strResponse[0]+","+strResponse[1]);
    }

    @Test
    public void ifClientHaveWrongIpHostDontReceiveMessage() throws Exception {
        RoboClient myClient = new RoboClient("000.000.00.0");
        myClient.join();
        sleep(1000); // Delay so host can respond
        assertEquals(null, myClient.response);
    }
    @Test
    public void serverHasPlayerConnection() throws Exception {
        RoboClient myClient = new RoboClient(RoboServer.getLANIp());
        myClient.join();
        while(myClient.response == null)
            sleep(1000); // Delay so host can respond
        assertEquals(1, server.getPlayerIpToConnect().size());
    }
    @Test
    public void serverRejectsJoinSinceGameStarted() throws Exception {
        RoboClient myClient = new RoboClient(RoboServer.getLANIp());
        server.sendGameInstance("Started");
        sleep(1000); // Delay so host can respond
        myClient.join();
        while(myClient.response == null)
            sleep(1000); // Delay so host can respond
        assertEquals("Rejected",myClient.response);
    }
    @Test
    public void serverNotAddsTwoEqualConnections() throws Exception {
        RoboClient myClient = new RoboClient(RoboServer.getLANIp());
        myClient.join();
        while(myClient.response == null)
            sleep(1000); // Delay so host can respond
        String[] strResponse = myClient.response.split(",");
        assertEquals("joined,0", strResponse[0]+","+strResponse[1]);
        myClient.join();
        while(myClient.response.substring(0,2+6+1).equals("joined,0,"))
            sleep(1000); // Delay so host can respond
        assertEquals(1,server.getPlayerIpToConnect().size());
        assertEquals("AlreadyJoined",myClient.response);
    }
    @Test
    public void hostSendsRules() throws InterruptedException {
        RoboClient myClient = new RoboClient(RoboServer.getLANIp());
        myClient.join();
        while(myClient.getGameRules() == null)
            sleep(1000); // Delay so host can respond
        assertEquals("gameRules,3;9,", myClient.getGameRules().toString());
        assertEquals("lobby,0;P1;0;true;false:,", myClient.getLobbyInfo().toString());
    }
}
