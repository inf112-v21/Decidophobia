package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.GUI.stages.LobbyStage;
import inf112.skeleton.app.GameLogic;
import inf112.skeleton.app.Multiplayer.packets.GameCards;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import inf112.skeleton.app.Multiplayer.packets.LobbyInfo;

import inf112.skeleton.app.cards.Deck;
import inf112.skeleton.app.cards.PlayerCards;

import java.io.IOException;

public class RoboClient {

    private String serverAddress;

    public String response;

    private Client client;

    private GameLogic gameLogic;

    private LobbyInfo lobbyInfo;



    private LobbyStage lobbyStage;

    private GameRules gameRules;

    public GameCards getRoundCards() {
        return roundCards;
    }

    private GameCards roundCards;

    private PlayerCards clientsCards;

    private int clientPlayerNr;

    public RoboClient(String serverAddress){
        roundCards = new GameCards(new Deck());
        this.serverAddress = serverAddress;
        client = new Client();

        client.start();
    }

    public void sendRequest(String request){
        client.sendTCP(request);
    }

    public void ready(){
        sendRequest("Ready");
    }

    public void unReady(){
        sendRequest("Unready");
    }

    public void quit(){
        sendRequest("Quit");
    }

    public void startGame(){
        sendRequest("Start");
    }

    public void endGame(){
        sendRequest("End");
    }

    public void sendNick(String nickname) {
        sendRequest("ChangeNick," + nickname + ",");
    }

    public void sendRobot(int robTexture, int color) {
        sendRequest("ChangeRobot," + robTexture + "," + color + ",");
    }

    public void sendMoves(PlayerCards cards){
        System.out.println("Move,"+cards+",");
        sendRequest("Move,"+cards+",");
    }

    public void dealCards(GameCards gameCards){
        String request = "DealCards,";
        for(Integer pNr : gameCards.getAllPlayerHands().keySet()){
            request += pNr+","+ gameCards.getAllPlayerHands().get(pNr) + ",";
        }
        sendRequest(request);
    }

    public void join() {
        try {
            System.out.println(serverAddress);
            client.connect(5000, serverAddress, 9000, 54777); //ip-addresse til server
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendRequest("Join");

        client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof String) {
                    response = (String) object;
                    parser((String) object);
                }

            }
        });

    }
    public void parser(String str){
        switch (str){
            case "start":
                lobbyStage.startGame();
                break;

            case "end":
                break;
        }
        String[] arguments = str.split(",");
        switch (arguments[0]){
            case "joined":
                clientPlayerNr = Integer.parseInt(arguments[1]);
                if(gameLogic != null) gameLogic.setLocalPlayerNumber(clientPlayerNr);
                parser(str.substring("joined,,".length() + arguments[1].length()));
                break;

            case "playerJoined":
                parser(str.substring("joined,,".length() + arguments[1].length()));
                break;

            case "gameRules":
                gameRules = new GameRules(arguments[1]);
                if (lobbyStage != null) lobbyStage.updatePlayerTable();
                parser(str.substring("gameRules,,".length() + arguments[1].length()));
                break;

            case "lobby":
                lobbyInfo = new LobbyInfo(arguments[1]);
                if (lobbyStage != null) lobbyStage.updatePlayerTable();
                parser(str.substring("lobby,,".length() + arguments[1].length()));
                break;

            case "move":
                roundCards.addPlayerCards(Integer.parseInt(arguments[1]),arguments[2]);
                if(roundCards.getAllPlayerHands().keySet().size()==lobbyInfo.getPlayers().size()){
                    if(gameLogic !=null) gameLogic.doRound(roundCards);
                }
                break;

            case "dealCards":
                roundCards = new GameCards(new Deck());
                clientsCards = new PlayerCards(arguments[1]);
                roundCards.addPlayerCards(clientPlayerNr, clientsCards);
                if(gameLogic != null) gameLogic.stage.updateCards(clientsCards);
                break;

            case "changeNick":
                lobbyInfo.changeNick(Integer.parseInt(arguments[1]),arguments[2]);
                lobbyStage.updatePlayerTable();
                break;

            case "changeRobot":
                lobbyInfo.changeRobot(Integer.parseInt(arguments[1]),arguments[2],arguments[3]);
                lobbyStage.updatePlayerTable();
                break;

            case "ready":
                lobbyInfo.playerSetReady(Integer.parseInt(arguments[1]),true);
                lobbyStage.updatePlayerTable();
                break;

            case "unReady":
                lobbyInfo.playerSetReady(Integer.parseInt(arguments[1]),false);
                lobbyStage.updatePlayerTable();
                break;

            case "quit":
                lobbyInfo.playerQuit(Integer.parseInt(arguments[1]));
                lobbyStage.updatePlayerTable();
                break;

            default:
                return;
        }
    }
    public int getClientPlayerNr() {
        return clientPlayerNr;
    }

    public void setLobbyStage(LobbyStage lobbyStage) {
        this.lobbyStage = lobbyStage;
    }

    public LobbyInfo getLobbyInfo() {
        return lobbyInfo;
    }

    private void setLobbyInfo(LobbyInfo lobbyInfo) {
        this.lobbyInfo = lobbyInfo;
    }

    public GameRules getGameRules() {
        return gameRules;
    }

    private void setGameRules(GameRules gameRules) {
        this.gameRules = gameRules;
    }

    public void setGameReference(GameLogic gameLogic){
        this.gameLogic = gameLogic;
    }

    public GameCards getGameCards() {
        return roundCards;
    }

    public PlayerCards getClientCards() {
        return clientsCards;
    }
}
