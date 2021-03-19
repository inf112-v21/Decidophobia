package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.MoveCardsPacket;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.cards.Deck;
import inf112.skeleton.app.cards.PlayerCards;

import java.util.Map;

public class Game {

    private static String serverAddress = RoboServer.getLANIp();

    private Deck cardDeck;

    private RoboClient networkClient;

    boolean isHost;

    private int localPlayerNumber;
    public static Map<Integer, PlayerCards> playerMoves;

    PlayerCards yourCards;

    public Game(){
        networkClient = new RoboClient(serverAddress);
        cardDeck = new Deck();
    }

    private void dealCards(){
        PlayerCards cards = new PlayerCards();
        cards.insertCard(cardDeck.pop());
        networkClient.sendRequest(cards);

    }
    public void setLocalPlayerNumber(int playerNr) {
        localPlayerNumber = playerNr;
    }

    public void setMove(MoveCardsPacket moveCardsPacket){
        System.out.println("DO move " + moveCardsPacket);
    }

    public RoboClient getNetworkClient() {
        return networkClient;
    }

    public static void main(String[] args) {
        RoboServer server = new RoboServer();
        server.runServer();
        Game game = new Game();
        game.networkClient.setGameReference(game);
        game.networkClient.join();
        game.dealCards();
    }
}
