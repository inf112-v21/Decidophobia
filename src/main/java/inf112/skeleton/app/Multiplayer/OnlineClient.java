package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class OnlineClient extends Thread{

    String serverAddress;

    public OnlineClient(String serverAddress){
        this.serverAddress = serverAddress;
    }
    @Override
    public void run() {
        super.run();
        Client client = new Client();
        client.start();
        try {
            client.connect(5000, serverAddress, 54555, 54777); //ip-addresse til server
        } catch (IOException e) {
            e.printStackTrace();
        }

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
