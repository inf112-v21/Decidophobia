package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class Client {

    private String serverAddress;

    private String response;

    public String getResponse() {
        return response;
    }

    public Client(String serverAddress){
        this.serverAddress = serverAddress;
    }


    public void sendRequest(String request) {

        com.esotericsoftware.kryonet.Client client = new com.esotericsoftware.kryonet.Client();
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
                    response = (String) object;
                    System.out.println(response);

                }
            }
        });

    }

    /**
     * Made for running Clientrequest in main-thread, for testing Host-Client-relation over the internet
     * @param args
     */
    public static void main(String[] args){
        Client client = new Client("000.000.000.00");
        client.sendRequest("Join");
    }
}
