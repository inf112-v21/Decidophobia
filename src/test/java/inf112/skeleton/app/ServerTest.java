package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.Host;
import inf112.skeleton.app.Multiplayer.OnlineClient;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class ServerTest {

    @Before
    public void setup(){
        Host myHost = new Host();
        myHost.runServer();
    }

    @Test
    public void ifServerAndHostAreTheSameThenRespondLAN() throws Exception {
        OnlineClient myClient = new OnlineClient(Host.getLANIp());
        myClient.sendRequest("Here is the request");
        sleep(1000); // Delay so host can respond
        System.out.println(myClient.getResponse() + "  <-  Should be \"Thanks\"");
        assertEquals("Thanks", myClient.getResponse());
    }

    @Test
    public void ifClientHaveWrongIpHostDontReceiveMessage() throws Exception {
        OnlineClient myClient = new OnlineClient("000.000.00.0");
        myClient.sendRequest("Here is the request");
        sleep(1000); // Delay so host can respond
        assertEquals(null, myClient.getResponse());
    }
}
