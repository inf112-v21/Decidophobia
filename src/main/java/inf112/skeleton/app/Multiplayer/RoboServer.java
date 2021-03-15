package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import inf112.skeleton.app.Multiplayer.packets.LobbyInfo;
import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.PlayerCards;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;

public class RoboServer {
    private boolean gameStarted;
    Map<String, Connection> playerIpToConnect;
    List<String> playerNrToIp;
    Server server;
    private GameRules gameRules;
    private LobbyInfo lobby;


    public RoboServer(){
        gameStarted = false;
        playerIpToConnect = new HashMap<>();
        playerNrToIp = new ArrayList<>();
        server = new Server();
        server.getKryo().register(PlayerCards.class);
        server.getKryo().register(Cards.class);
        server.getKryo().register(CardType.class);
        server.getKryo().register(LinkedList.class);
        server.getKryo().register(MoveCardsPacket.class);
        server.getKryo().register(GameRules.class);
        server.getKryo().register(LobbyInfo.class);
        server.getKryo().register(Integer.class);
        server.getKryo().register(Boolean.class);
        server.getKryo().register(HashMap.class);
        gameRules = new GameRules();
        lobby = new LobbyInfo();
    }

    public void runServer() {
        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof String) {
                    String request = (String) object;
                    handleStringRequest(connection,request);
                }
                else if (object instanceof PlayerCards) {
                    PlayerCards playerCards = (PlayerCards) object;
                    System.out.println("Recieved: " + playerCards.getActiveCard(0));
                    handlePlayerCardsRequest(connection, playerCards);
                }
                else if(object instanceof GameRules){
                    GameRules newRules = (GameRules) object;
                    if(connectionToIp(connection).equals(getLANIp()) && !gameRules.equals(newRules))
                        server.sendToAllTCP(gameRules);

                }
            }
        });
    }
    private void handleStringRequest(Connection connection, String request){
        switch (request){
            case "Join":
                if(!gameStarted & !playerIpToConnect.containsKey(connectionToIp(connection))) {
                    playerIpToConnect.put(connectionToIp(connection), connection);
                    playerNrToIp.add(connectionToIp(connection));
                    lobby.addPlayer(getPlayerNumber(connection));
                    server.sendToAllTCP("joined;"+getPlayerNumber(connection));
                }
                else if(playerIpToConnect.containsKey(connectionToIp(connection))) {
                    connection.sendTCP("AlreadyJoined");
                }
                else {
                    connection.sendTCP("Rejected");
                }
                break;

            case "Ready":
                lobby.playerReady(getPlayerNumber(connection));
                server.sendToAllTCP(lobby);
                break;

            case "Unready":
                lobby.playerUnready(getPlayerNumber(connection));
                server.sendToAllTCP(lobby);
                break;

            case "Quit":
                lobby.playerQuit(getPlayerNumber(connection));
                this.playerIpToConnect.remove(connectionToIp(connection));
                this.playerNrToIp.remove(connectionToIp(connection));
                server.sendToAllTCP("quit;"+playerNrToIp.indexOf(connection));
                break;

            case "Start":
                if(connectionToIp(connection).equals(getLANIp()))
                    server.sendToAllTCP("start");
                break;
            case "End":
                if(connectionToIp(connection).equals(getLANIp()))
                    server.sendToAllTCP("end");
                break;
        }

        String[] req = request.split(";");

        switch (req[0]){
            case "ChangeNick":
                lobby.changeNick(getPlayerNumber(connection),req[1]);
                server.sendToAllTCP(lobby);
                break;

            case "ChangeRobot":
                lobby.changeRobot(getPlayerNumber(connection),req[1],req[2]);
                server.sendToAllTCP(lobby);
                break;
        }
    }

    private void handlePlayerCardsRequest(Connection connection, PlayerCards playerCards) {
        System.out.println("Sending: " + playerCards);
        server.sendToAllTCP(new MoveCardsPacket(getPlayerNumber(connection),playerCards));

    }
    private int getPlayerNumber(Connection connection){
        return playerNrToIp.indexOf(connectionToIp(connection));
    }
    private String connectionToIp(Connection con){
        String tcpAddress = con.getRemoteAddressTCP().toString();
        return tcpAddress.substring(1,tcpAddress.length()-6);
    }


    public void sendGameInstance(String game){
        gameStarted = true;
        server.sendToAllTCP(game);

    }


    public void stopServer(){
        server.stop();
    }

    public static String getLANIp(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }
    public static String getPublicIp(){
        ///// https://www.geeksforgeeks.org/java-program-find-ip-address-computer/
        // Find public IP address
        String systemipaddress = "";
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads system IPAddress
            systemipaddress = sc.readLine().trim();
        }
        catch (Exception e)
        {
            systemipaddress = "Cannot Execute Properly";
        }
        System.out.println("Public IP Address: " + systemipaddress +"\n");
        ////
        return systemipaddress;
    }


    public Map<String, Connection> getPlayerIpToConnect() {
        return playerIpToConnect;
    }

    /**
     * Made for running server in main-thread, for testing Host-Client-relation over the internet
     * @param args
     */
    public static void main(String[] args){
        RoboServer host = new RoboServer();
        host.runServer();
    }

}
