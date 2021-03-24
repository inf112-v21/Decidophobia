package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.MoveCardsPacket;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.Multiplayer.packets.GameCards;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import inf112.skeleton.app.board.IBoard;
import inf112.skeleton.app.cards.Deck;
import inf112.skeleton.app.cards.PlayerCards;

import java.util.Map;

public class Game {

    private static String serverAddress = RoboServer.getLANIp();

    private Deck cardDeck;
    private GameRules gameRules;

    private RoboClient networkClient;

    private int localPlayerNumber;

    public static Map<Integer, PlayerCards> playerMoves;

    PlayerCards yourCards;

    public Game(RoboClient networkClient) {
        this.networkClient = networkClient;
        gameRules = networkClient.getGameRules();
        cardDeck = new Deck();
    }

    private void dealCards() {


    }

    public void setLocalPlayerNumber(int playerNr) {
        localPlayerNumber = playerNr;
    }

    public void setMove(GameCards roundMoves){
        System.out.println("Do move " + roundMoves);
    }
}
