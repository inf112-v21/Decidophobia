package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.MoveCardsPacket;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.Multiplayer.packets.GameCards;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import inf112.skeleton.app.Multiplayer.packets.PlayerInfo;
import inf112.skeleton.app.board.IBoard;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.Deck;
import inf112.skeleton.app.cards.PlayerCards;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private static String serverAddress = RoboServer.getLANIp();

    private Deck cardDeck;

    private GameRules gameRules;

    private RoboClient networkClient;

    private int localPlayerNumber;

    public static Map<Integer, PlayerCards> playerMoves;

    PlayerCards yourCards;

    private GameCards lastRound;

    public Game(RoboClient networkClient) {
        this.networkClient = networkClient;
        gameRules = networkClient.getGameRules();
        cardDeck = new Deck();
    }

    public void dealCards() {
        if(networkClient.getLobbyInfo().getPlayers().get(localPlayerNumber).getHost()){
            GameCards thisRound = new GameCards(cardDeck);
            HashMap<Integer, PlayerCards> newHands = new HashMap<>();
            for(PlayerInfo pInfo : networkClient.getLobbyInfo().getPlayers()){
                int pNr = pInfo.getPlayerNr();
                PlayerCards pCards = new PlayerCards();
                for(int i = 0; i<9;i++){
                    pCards.dealToHand(cardDeck.pop());
                }
                newHands.put(pNr,pCards);
            }
            thisRound.setAllPlayerHands(newHands);
            networkClient.dealCards(thisRound);
        }
    }
    public void doRound(GameCards playerMoves){
        for(int r = 0; r < 5; r++){
            //move for all players
            //Robots shoot lasers
            //colision hole, wall and flag
            //conveyor belts move blue double and yellow ones
            //colision hole, wall and flag
            //pushers push
            //colision hole, wall and flag
            //Gears rotate 90*

            //deal cards

        }
        lastRound = playerMoves;
    }

    public void setLocalPlayerNumber(int playerNr) {
        localPlayerNumber = playerNr;
    }

    public GameCards getLastRound() {
        return lastRound;
    }
}
