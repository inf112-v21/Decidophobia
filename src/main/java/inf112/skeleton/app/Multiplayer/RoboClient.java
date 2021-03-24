package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.Game;
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

    private Game game;

    private LobbyInfo lobbyInfo;

    private GameRules gameRules;

    public GameCards getRoundCards() {
        return roundCards;
    }

    private GameCards roundCards;

    private PlayerCards clientsCards;

    public int getClientPlayerNr() {
        return clientPlayerNr;
    }

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

    public void sendMoves(PlayerCards cards){
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
        client.sendTCP("Join");

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
        String[] arguments = str.split(",");
        switch (arguments[0]){
            case "joined":
                clientPlayerNr = Integer.parseInt(arguments[1]);
                parser(str.substring("joined,,".length() + arguments[1].length()));
                break;

            case "gameRules":
                gameRules = new GameRules(arguments[1]);
                parser(str.substring("gameRules,,".length() + arguments[1].length()));
                break;

            case "lobby":
                lobbyInfo = new LobbyInfo(arguments[1]);
                parser(str.substring("lobby,,".length() + arguments[1].length()));
                break;

            case "move":
                roundCards.addPlayerCards(Integer.parseInt(arguments[1]),arguments[2]);
                break;

            case "dealCards":
                System.out.println(arguments[1]);
                roundCards = new GameCards(new Deck());
                clientsCards = new PlayerCards(arguments[1]);
                break;

            case "changeNick":
                lobbyInfo.changeNick(Integer.parseInt(arguments[1]),arguments[2]);
                break;

            case "changeRobot":
                lobbyInfo.changeRobot(Integer.parseInt(arguments[1]),arguments[2],arguments[3]);
                break;

            case "ready":
                lobbyInfo.playerSetReady(Integer.parseInt(arguments[1]),true);
                break;

            case "unReady":
                lobbyInfo.playerSetReady(Integer.parseInt(arguments[1]),false);
                break;

            case "quit":
                lobbyInfo.playerQuit(Integer.parseInt(arguments[1]));
                break;

            default:
                return;
        }
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

    public void setGameReference(Game game){
        this.game = game;
    }

    /**
     * Made for running Clientrequest in main-thread, for testing Host-Client-relation over the internet
     * @param args
     */
    public static void main(String[] args){
        RoboClient client = new RoboClient("localhost");
    }

    public GameCards getGameCards() {
        return roundCards;
    }

    public PlayerCards getClientCards() {
        return clientsCards;
    }
}
