package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.Game;
import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.PlayerCards;

import java.io.IOException;
import java.util.LinkedList;

public class RoboClient {

    private String serverAddress;

    public String response;

    private Client client;

    private Game game;

    public String getResponse() {
        return response;
    }

    public RoboClient(String serverAddress){
        this.serverAddress = serverAddress;
        client = new Client();
        client.getKryo().register(PlayerCards.class);
        client.getKryo().register(Cards.class);
        client.getKryo().register(CardType.class);
        client.getKryo().register(LinkedList.class);
        client.getKryo().register(MoveCardsPacket.class);
        client.start();
    }
    public void join(){
        sendRequest("Join");
    }

    public void sendRequest(Object request) {
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
                else if (object instanceof MoveCardsPacket) {
                    MoveCardsPacket moveCards = (MoveCardsPacket) object;
                    System.out.println("Responded: " + moveCards.playerCards.getCard(0));
                    if(!(game == null)){
                        game.setMove(moveCards);
                    }
                }
            }
        });

    }
    public void setGameReference(Game game){
        this.game = game;
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
