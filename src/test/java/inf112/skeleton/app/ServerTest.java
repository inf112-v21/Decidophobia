package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.Host;
import inf112.skeleton.app.Multiplayer.OnlineClient;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.net.InetAddress;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class ServerTest {

    @Test
    public void ifServerAndHostAreTheSameThenRespond() throws Exception {
        Host myHost = new Host();
        myHost.start();
        OnlineClient myClient = new OnlineClient(InetAddress.getLocalHost().getHostAddress());
        myClient.sendRequest("Here is the request");
        sleep(1000); // Delay so host can respond
        System.out.println(myClient.getResponse() + "  <-  Should be \"Thanks\"");
        assertEquals("Thanks", myClient.getResponse());

    }
    @Test
    public void ifClientHaveWrongIpHostDontReceiveMessage() throws Exception {
        Host myHost = new Host();
        myHost.start();
        OnlineClient myClient = new OnlineClient("000.000.00.0");
        myClient.sendRequest("Here is the request");
        sleep(1000); // Delay so host can respond
        assertEquals(null, myClient.getResponse());
    }
}
