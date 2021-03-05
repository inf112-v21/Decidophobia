package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.MoveCardsPacket;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.Deck;
import inf112.skeleton.app.cards.PlayerCards;

import java.util.Map;

public class Game {

    private static String serverAddress = RoboServer.getLANIp();

    private Deck cardDeck;

    private RoboClient networkClient;

    boolean isHost;

    public static Map<Integer, PlayerCards> playerMoves;

    PlayerCards yourCards;

    public Game(){
        networkClient = new RoboClient(serverAddress);
        cardDeck = new Deck();
        cardDeck.add(new Cards(CardType.FORWARD_1,1000));
    }

    private void dealCards(){
        PlayerCards cards = new PlayerCards();
        cards.insertCard(cardDeck.pop());
        networkClient.sendRequest(cards);

    }

    public void setMove(MoveCardsPacket moveCardsPacket){
        System.out.println("DO move " + moveCardsPacket);
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
