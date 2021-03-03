package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.Multiplayer.Client;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class ServerTest {

    @Before
    public void setup(){
        RoboServer myHost = new RoboServer();
        myHost.runServer();
    }

    @Test
    public void ifServerAndHostAreTheSameThenRespondLAN() throws Exception {
        Client myClient = new Client(RoboServer.getLANIp());
        myClient.sendRequest("Join");
        sleep(5000); // Delay so host can respond
        System.out.println(myClient.getResponse() + "  <-  Should be \"Joined\"");
        assertEquals("Joined", myClient.getResponse());
    }

    @Test
    public void ifClientHaveWrongIpHostDontReceiveMessage() throws Exception {
        Client myClient = new Client("000.000.00.0");
        myClient.sendRequest("Join");
        sleep(1000); // Delay so host can respond
        assertEquals(null, myClient.getResponse());
    }
}
