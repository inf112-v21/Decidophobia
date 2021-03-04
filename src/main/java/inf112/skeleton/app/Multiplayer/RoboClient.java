package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.PlayerCards;

import java.io.IOException;
import java.util.LinkedList;

public class RoboClient {

    private String serverAddress;

    private String response;

    private Client client;


    public String getResponse() {
        return response;
    }

    public RoboClient(String serverAddress){
        this.serverAddress = serverAddress;
        client = new Client();
        client.getKryo().register(PlayerCards.class);
        client.getKryo().register(Cards.class);
        client.getKryo().register(LinkedList.class);
        client.getKryo().register(MoveCardsPacket.class);
    }


    public void sendRequest(Object request) {

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
                }
            }
        });

    }

    /**
     * Made for running Clientrequest in main-thread, for testing Host-Client-relation over the internet
     * @param args
     */
    public static void main(String[] args){
        RoboClient client = new RoboClient("localhost");
        client.sendRequest(new MoveCardsPacket(1,new PlayerCards()));
    }
}
