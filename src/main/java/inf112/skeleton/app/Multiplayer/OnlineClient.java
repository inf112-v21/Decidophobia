package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class OnlineClient {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
        client.connect(5000, "192.168.10.179", 54555, 54777);

        String request = "";
        request = "Here is the request";
        client.sendTCP(request);

        client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof String) {
                    String response = (String)object;
                    System.out.println(response);
                }
            }
        });
    }

}
