package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class Host extends Thread {

    String firstMessage ="";

    public String getFirstMessage() {
        return firstMessage;
    }

    @Override
    public void run() {

        Server server = new Server();
        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof String) {
                    String request = (String) object;
                    firstMessage = request;
                    System.out.println(request);

                    connection.sendTCP("Thanks");
                }
            }
        });

        super.run();
    }
}
