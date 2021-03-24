package inf112.skeleton.app.Multiplayer.packets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LobbyInfo {
    private List<PlayerInfo> players;



    public LobbyInfo() {
        this.players = new ArrayList<>();
    }
    //"lobby,{1;Truls;1;T;T;2;Truls;2;F;F},gameRules,{4;10},"
    //"lobby","{1;Truls;1;T;T;2;Truls;2;F;F}","gameRules","{4;10}" {} er ikke faktisk med...
    //"2;1:Truls:1:T:T;2:Truls:2:F:F"                  "4;10"
    public LobbyInfo(String lobbyinfo) {
        this.players = new ArrayList<>();
        String[] arguments = lobbyinfo.split(":");
        for(String player : arguments){
            players.add(new PlayerInfo(player));
        }

    }

    @Override
    public String toString() {
        String lobbyInf = "lobby,";
        for(PlayerInfo pX : players){
            lobbyInf += pX+":";
        }
        lobbyInf += ",";
        return lobbyInf;
    }

    public List<PlayerInfo> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerInfo> players) {
        this.players = players;
    }

    public void addPlayer(int playerNumber) {
        players.add(new PlayerInfo(playerNumber, playerNumber == 0));
    }

    public void playerSetReady(int playerNumber, boolean isReady) {
    }

    public boolean playerIsReady(int playerNumber){
        return players.get(playerNumber).getReady();
    }

    public void playerQuit(int playerNumber) {
    }

    public void changeNick(int playerNumber, String s) {
    }

    public void changeRobot(int playerNumber, String s, String s1) {
    }
}
