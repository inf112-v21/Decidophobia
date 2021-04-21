package inf112.skeleton.app;

import inf112.skeleton.app.GUI.screen.GameScreen;
import inf112.skeleton.app.GUI.stages.Game.CardStage;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.Multiplayer.packets.GameCards;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import inf112.skeleton.app.Multiplayer.packets.PlayerInfo;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.Deck;
import inf112.skeleton.app.cards.PlayerCards;

import java.util.*;

public class GameLogic {

    private static String serverAddress = RoboServer.getLANIp();

    private Deck cardDeck;

    private GameRules gameRules;

    public RoboClient networkClient;

    public GameScreen gameGUI;

    private int localPlayerNumber;

    public Map<Integer, PlayerCards> playerMoves;

    PlayerCards yourCards;

    private GameCards lastRound;

    public GameLogic(RoboClient networkClient) {
        this.networkClient = networkClient;
        gameRules = networkClient.getGameRules();
        cardDeck = new Deck();
    }

    public void dealCards() {
        if(networkClient.getLobbyInfo().getPlayers().get(localPlayerNumber).getHost()){
            GameCards thisRound = new GameCards(cardDeck);
            HashMap<Integer, PlayerCards> newHands = new HashMap<>();
            for(PlayerInfo pInfo : networkClient.getLobbyInfo().getPlayers().values()){
                int pNr = pInfo.getPlayerNr();
                PlayerCards pCards = new PlayerCards();
                for(int i = 0; i<9;i++){
                    Cards card = cardDeck.pop();
                    if(cardDeck.size()==0)
                        cardDeck = new Deck();
                    pCards.dealToHand(card);
                }
                newHands.put(pNr,pCards);
            }
            thisRound.setAllPlayerHands(newHands);
            networkClient.dealCards(thisRound);
        }
    }
    public void doRound(GameCards playerMoves){
        for(int r = 0; r < 5; r++){
            //collision with wall needs to happen in every move

            //move for all players
            List<Integer> playerPriority = getHighestPriorityOfNewCard(r,playerMoves);
            
            for(Integer pNr : playerPriority){
                doMove(pNr, playerMoves.getAllPlayerHands().get(pNr).getActiveCard(r));
            }

            //Robots shoot lasers
            //collision hole and flag
            //conveyor belts move blue double and yellow ones
            //collision hole and flag
            //pushers push
            //collision hole and flag
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

    public void setGameGUI(GameScreen gameGUI) {
        this.gameGUI = gameGUI;
    }

    public GameRules getGameRules(){
        return gameRules;
    }
}
