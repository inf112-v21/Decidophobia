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

import java.util.*;

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
            //colission with wall needs to happen in every move

            //move for all players
            List<Integer> playerPriority = getHighestPriorityOfNewCard(r,playerMoves);
            
            for(Integer pNr : playerPriority){
                doMove(pNr, playerMoves.getAllPlayerHands().get(pNr).getActiveCard(r));
            }

            //Robots shoot lasers
            //colision hole and flag
            //conveyor belts move blue double and yellow ones
            //colision hole and flag
            //pushers push
            //colision hole and flag
            //Gears rotate 90*



        }
        lastRound = playerMoves;
        dealCards();
    }
    private List<Integer> getHighestPriorityOfNewCard(int turn, GameCards playerMoves){
        Map<Integer,PlayerCards> playerHands = playerMoves.getAllPlayerHands();
        List<Integer> playerPriority = new ArrayList<>();

        for(int p = 0; p < playerHands.keySet().size(); p++){
            int highestPriory = 0;
            int highestPNr = -1;
            for(Integer pNr : playerHands.keySet()){
                if(!playerPriority.contains(pNr) && playerHands.get(pNr).getActiveCard(turn).getPriority()>highestPriory)
                    highestPNr = pNr;
            }
            playerPriority.add(highestPNr);
        }
        return playerPriority;
    }
    private void doMove(Integer pNr, Cards activeCard) {
    }

    public void setLocalPlayerNumber(int playerNr) {
        localPlayerNumber = playerNr;
    }

    public GameCards getLastRound() {
        return lastRound;
    }
}
