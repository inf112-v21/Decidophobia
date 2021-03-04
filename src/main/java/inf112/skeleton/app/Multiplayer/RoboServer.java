package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.PlayerCards;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RoboServer {
    private boolean gameStarted;
    Map<String, Connection> playerIpToConnect;
    Server server;

    public RoboServer(){
        gameStarted = false;
        playerIpToConnect = new HashMap<>();
        server = new Server();
        server.getKryo().register(PlayerCards.class);
        server.getKryo().register(Cards.class);
        server.getKryo().register(LinkedList.class);
        server.getKryo().register(MoveCardsPacket.class);
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
                    System.out.println("Her er ip " + connectionToIp(connection));
                    handleStringRequest(connection,(String) object);
                }
                else if (object instanceof MoveCardsPacket) {
                    System.out.println(connection.getRemoteAddressTCP().toString());
                    handleMoveCardsRequest(connection,(MoveCardsPacket) object);
                }
            }
        });
    }
    private String connectionToIp(Connection con){
        String tcpAddress = con.getRemoteAddressTCP().toString();
        return tcpAddress.substring(1,tcpAddress.length()-6);
    }
    private void handleMoveCardsRequest(Connection connection, MoveCardsPacket object) {
    }

    public void sendGameInstance(String game){
        gameStarted = true;

    }

    private void handleStringRequest(Connection connection, String request){
        if(request.equals("Join")){
            if(!gameStarted & !playerIpToConnect.containsKey(connectionToIp(connection))) {
                playerIpToConnect.put(connectionToIp(connection), connection);
                connection.sendTCP("Joined");
            }
            else {
                connection.sendTCP("Rejected");
            }
        }
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
