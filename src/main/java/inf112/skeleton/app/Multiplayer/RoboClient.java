package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.Game;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import inf112.skeleton.app.Multiplayer.packets.LobbyInfo;

import inf112.skeleton.app.cards.PlayerCards;

import java.io.IOException;

public class RoboClient {

    private String serverAddress;

    public String response;

    private Client client;

    private Game game;

    private LobbyInfo lobbyInfo;

    private GameRules gameRules;

    public int getClientPlayerNr() {
        return clientPlayerNr;
    }

    public void setClientPlayerNr(int clientPlayerNr) {
        this.clientPlayerNr = clientPlayerNr;
    }

    private int clientPlayerNr;

    public RoboClient(String serverAddress){
        this.serverAddress = serverAddress;
        client = new Client();

        client.start();
    }

    public void sendRequest(Object request){
        client.sendTCP(request);
    }

    public void changeDamageTokens(int i) {

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
        System.out.println(str);
        if(str.equals("")){
            return;
        }
        String[] arguments = str.split(",");
        switch (arguments[0]){
            case "joined":
                clientPlayerNr = Integer.parseInt(arguments[1]);
                parser(str.substring(2+6+arguments[1].length()));
                break;
            case "gameRules":
               gameRules = new GameRules(arguments[1]);
                parser(str.substring(2+9+arguments[1].length()));
               break;
            case "lobby":
                lobbyInfo = new LobbyInfo(arguments[1]);
                parser(str.substring(2+5+arguments[1].length()));
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
        client.sendRequest(new MoveCardsPacket(1,new PlayerCards()));
    }
}
