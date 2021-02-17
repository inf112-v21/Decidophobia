package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class Host {
    
    Server server;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
        server.bind(54555, 54777);

        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {

                if (object instanceof String) {
                    String request = (String)object;
                    System.out.println(request);

                    String response = "";
                    response = "Thanks";
                    connection.sendTCP(response);
                }


            }
        });
    }
}
