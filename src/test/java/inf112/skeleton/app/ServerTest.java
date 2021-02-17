package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.Host;
import inf112.skeleton.app.Multiplayer.OnlineClient;
import org.junit.Test;
import java.net.InetAddress;

import static org.junit.Assert.assertEquals;

public class ServerTest {

    @Test
    public void ifServerAndHostAreTheSameThenRespond(){
        try {
            Host myHost = new Host();
            myHost.start();
            OnlineClient myClient = new OnlineClient(InetAddress.getLocalHost().toString());
            myClient.start();
        } catch (Exception e){
            assertEquals(false,true);
        }

    }
}
