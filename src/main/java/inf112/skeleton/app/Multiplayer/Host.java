package inf112.skeleton.app.Multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;

public class Host extends Thread {

    @Override
    public void run() {

        runServer();

        super.run();
    }

    private void runServer() {
        Server server = new Server();
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
                    System.out.println(request);

                    connection.sendTCP("Thanks");
                }
            }
        });
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

    /**
     * Made for running server in main-thread, for testing Host-Client-relation over the internet
     * @param args
     */
    public static void main(String[] args){
        Host host = new Host();
        host.runServer();
    }
}
