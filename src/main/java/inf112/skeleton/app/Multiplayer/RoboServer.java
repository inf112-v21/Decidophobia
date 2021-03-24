package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.Multiplayer.packets.GameRules;
import inf112.skeleton.app.Multiplayer.packets.LobbyInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;

public class RoboServer {
    private boolean gameStarted;

    //server.getConnections();
    Map<String, Connection> playerIpToConnect;

    Map<Integer,String> playerNrToIp;

    int highestPlayerNumb = 0;

    Server server;

    private GameRules gameRules;

    private LobbyInfo lobby;

    public RoboServer(){
        gameStarted = false;
        playerIpToConnect = new HashMap<>();
        playerNrToIp = new HashMap<>();
        server = new Server();

        gameRules = new GameRules(3,9);
        lobby = new LobbyInfo();
    }

    public void runServer() {
        server.start();
        try {
            server.bind(9000, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof String) {
                    String request = (String) object;
                    handleStringRequest(connection,request);
                }
            }
        });
    }
    private void handleStringRequest(Connection connection, String request){
        switch (request){
            case "Join":
                if(!gameStarted & !playerIpToConnect.containsKey(connectionToIp(connection))) {
                    playerIpToConnect.put(connectionToIp(connection), connection);
                    playerNrToIp.put(highestPlayerNumb,connectionToIp(connection));
                    highestPlayerNumb++;
                    lobby.addPlayer(getPlayerNumber(connection));
                    send(connection,"joined,"+getPlayerNumber(connection)+","+gameRules+lobby);
                    for(String ips : playerIpToConnect.keySet()) {
                        if(!ips.equals(connectionToIp(connection)))
                            playerIpToConnect.get(ips).sendTCP("playerJoined,"+getPlayerNumber(connection)+","+gameRules+lobby);
                    }
                }
                else if(playerIpToConnect.containsKey(connectionToIp(connection))) {
                    connection.sendTCP("AlreadyJoined");
                }
                else {
                    connection.sendTCP("Rejected");
                }
                break;

            case "Ready":
                lobby.playerSetReady(getPlayerNumber(connection), true);
                sendToAll("ready,"+getPlayerNumber(connection)+",");
                break;

            case "Unready":
                lobby.playerSetReady(getPlayerNumber(connection), false);
                sendToAll("unReady,"+getPlayerNumber(connection)+",");
                break;

            case "Quit":
                lobby.playerQuit(getPlayerNumber(connection));
                this.playerIpToConnect.remove(connectionToIp(connection));
                playerNrToIp.remove(getPlayerNumber(connection));
                sendToAll("quit;"+getPlayerNumber(connection)+",");
                break;

            case "Start":
                if(connectionToIp(connection).equals(getLANIp()))
                    sendToAll("start");
                break;
            case "End":
                if(connectionToIp(connection).equals(getLANIp()))
                    sendToAll("end");
                break;
        }

        String[] req = request.split(",");

        switch (req[0]){
            case "ChangeNick":
                lobby.changeNick(getPlayerNumber(connection),req[1]);
                sendToAll("changeNick,"+getPlayerNumber(connection)+","+req[1]+",");
                break;

            case "ChangeRobot":
                lobby.changeRobot(getPlayerNumber(connection),req[1],req[2]);
                sendToAll("changeRobot,"+getPlayerNumber(connection)+","+req[1]+","+req[2]+",");
                break;

            case "Move":
                sendToAll("move,"+getPlayerNumber(connection)+","+req[1]+",");
                break;

            case "DealCards":
                if(connectionToIp(connection).equals(getLANIp())) {
                    System.out.println(req[1] + "," + req[2] + "  <--   SE HER");
                    for (int i = 1; i < req.length; i += 2) {
                        int pNr = Integer.parseInt(req[i]);
                        playerIpToConnect.get(playerNrToIp.get(pNr)).sendTCP("dealCards," + req[i + 1] + ",");
                    }
                }
                break;
        }
    }

    private void send(Connection connection, String str){
        connection.sendTCP(str);
    }

    private void sendToAll(String str){
        for(Connection cons : playerIpToConnect.values()){
            cons.sendTCP(str);
        }
    }

    private int getPlayerNumber(Connection connection){
        for(Integer pNr : playerNrToIp.keySet()){
            if(playerNrToIp.get(pNr).equals(connectionToIp(connection)));
                return pNr;
        }
        return -1;
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
