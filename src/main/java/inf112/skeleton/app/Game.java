package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Multiplayer.MoveCardsPacket;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.Deck;
import inf112.skeleton.app.cards.PlayerCards;
import inf112.skeleton.app.player.LocalPlayer;

import java.util.HashMap;
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
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");
        cfg.setWindowedMode(Launcher.WIDTH, Launcher.HEIGHT);

        new Lwjgl3Application(new GameGUI(), cfg);

        networkClient = new RoboClient(serverAddress);
        cardDeck = new Deck();
        cardDeck.add(new Cards(CardType.FORWARD_1,1000));
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
