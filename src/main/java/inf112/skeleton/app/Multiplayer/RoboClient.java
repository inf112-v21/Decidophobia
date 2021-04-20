package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.GUI.screen.LobbyScreen;
import inf112.skeleton.app.GameLogic;
import inf112.skeleton.app.Multiplayer.packets.GameCards;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import inf112.skeleton.app.Multiplayer.packets.LobbyInfo;

import inf112.skeleton.app.Multiplayer.packets.PlayerInfo;
import inf112.skeleton.app.cards.Deck;
import inf112.skeleton.app.cards.PlayerCards;

import java.io.IOException;

public class RoboClient {

    private String serverAddress;

    public boolean hostOnline;

    public String response;

    private Client client;

    private GameLogic gameLogic;

    private LobbyInfo lobbyInfo;

    private LobbyScreen lobbyScreen;

    private GameRules gameRules;

    private GameCards roundCards;

    private PlayerCards clientsCards;

    private int clientPlayerNr;

    public RoboClient(){
        GameCards roundCards = new GameCards(new Deck());
        client = new Client();
        hostOnline = true;

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

    public void join(String serverAddress) {
        client.start();
        this.serverAddress = serverAddress;
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
                    System.out.println(response);
                    parser((String) object);
                }

            }
        });

    }
    public void parser(String str){
        switch (str){
            case "start":
                lobbyScreen.startGame();
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
                System.out.println("playerJoined " + arguments[1]);
                lobbyInfo.addPlayer(Integer.parseInt(arguments[1]));
                lobbyScreen.updatePlayerTable();
                parser(str.substring("joined,,".length() + arguments[1].length()));
                break;

            case "gameRules":
                gameRules = new GameRules(arguments[1]);
                if (lobbyScreen != null) lobbyScreen.updatePlayerTable();
                parser(str.substring("gameRules,,".length() + arguments[1].length()));
                break;

            case "lobby":
                lobbyInfo = new LobbyInfo(arguments[1]);
                if (lobbyScreen != null) lobbyScreen.updatePlayerTable();
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
                if(gameLogic != null) gameLogic.gameGUI.updateCards(clientsCards);
                break;

            case "changeNick":
                lobbyInfo.changeNick(Integer.parseInt(arguments[1]),arguments[2]);
                lobbyScreen.updatePlayerTable();
                break;

            case "changeRobot":
                lobbyInfo.changeRobot(Integer.parseInt(arguments[1]),arguments[2],arguments[3]);
                lobbyScreen.updatePlayerTable();
                break;

            case "ready":
                lobbyInfo.playerSetReady(Integer.parseInt(arguments[1]),true);
                lobbyScreen.updatePlayerTable();
                boolean allReady = true;
                if(getClientPlayerNr()==0){
                    for(PlayerInfo player : lobbyInfo.getPlayers().values()){
                        allReady = allReady && player.getReady();
                    }
                    if(allReady) this.startGame();
                }
                break;

            case "unReady":
                lobbyInfo.playerSetReady(Integer.parseInt(arguments[1]),false);
                lobbyScreen.updatePlayerTable();
                break;

            case "quit":
                int pNr = Integer.parseInt(arguments[1]);
                lobbyInfo.playerQuit(pNr);
                hostOnline = pNr != 0;
                lobbyScreen.updatePlayerTable();
                break;

            default:
                return;
        }
    }
    public int getClientPlayerNr() {
        return clientPlayerNr;
    }

    public void setLobbyScreen(LobbyScreen lobbyScreen) {
        this.lobbyScreen = lobbyScreen;
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
    public void clientStop(){

    }
}
