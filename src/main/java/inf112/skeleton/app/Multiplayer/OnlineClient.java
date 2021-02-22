package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class OnlineClient{

    String serverAddress;

    public OnlineClient(String serverAddress){
        this.serverAddress = serverAddress;
    }


    public void send(String request) {

        Client client = new Client();
        client.start();
        try {
            System.out.println(serverAddress);
            client.connect(5000, serverAddress, 54555, 54777); //ip-addresse til server
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.sendTCP(request);

        client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof String) {
                    String response = (String)object;

                }
            }
        });
    }
}
