package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.Host;
import inf112.skeleton.app.Multiplayer.OnlineClient;
import org.junit.Test;
import java.net.InetAddress;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class ServerTest {

    @Test
    public void ifServerAndHostAreTheSameThenRespond(){
        try {
            Host myHost = new Host();
            myHost.start();
            OnlineClient myClient = new OnlineClient(InetAddress.getLocalHost().getHostAddress());
            myClient.send("Here is the request");
            sleep(1000); // Delay so host has recieved message
            System.out.println(myHost.getFirstMessage() + "  ...  Printed");
            assertEquals("Here is the request", myHost.getFirstMessage());


        } catch (Exception e){
            System.out.println(e);
            assertEquals(false,true);
        }

    }
}
