package inf112.skeleton.app.Multiplayer.packets;

import java.util.*;

public class LobbyInfo {
    private Map<Integer,PlayerInfo> players;



    public LobbyInfo() {
        this.players = new HashMap<Integer,PlayerInfo>();
    }
    //"lobby,{1;Truls;1;T;T;2;Truls;2;F;F},gameRules,{4;10},"
    //"lobby","{1;Truls;1;T;T;2;Truls;2;F;F}","gameRules","{4;10}" {} er ikke faktisk med...
    //"2;1:Truls:1:T:T;2:Truls:2:F:F"                  "4;10"
    public LobbyInfo(String lobbyinfo) {
        this.players = new HashMap<Integer,PlayerInfo>();
        String[] arguments = lobbyinfo.split(":");
        for(String player : arguments){
            PlayerInfo p = new PlayerInfo(player);
            players.put(p.getPlayerNr(), p);
        }

    }

    @Override
    public String toString() {
        String lobbyInf = "lobby,";
        for(PlayerInfo pX : players.values()){
            lobbyInf += pX+":";
        }
        lobbyInf += ",";
        return lobbyInf;
    }

    public Map<Integer,PlayerInfo> getPlayers() {
        return players;
    }

    public void addPlayer(int playerNumber) {
        players.put(playerNumber, new PlayerInfo(playerNumber, playerNumber == 0));
    }

    public void playerSetReady(int playerNumber, boolean isReady) {
        players.get(playerNumber).setReady(isReady);
    }

    public boolean playerIsReady(int playerNumber){
        return players.get(playerNumber).getReady();
    }

    public void playerQuit(int playerNumber) {
        players.remove(playerNumber);
    }

    public void changeNick(int playerNumber, String s) {
        players.get(playerNumber).setNickname(s);
    }

    public void changeRobot(int playerNumber, String robotReff, String robotColor) {
        players.get(playerNumber).setRobotReferenceNr(Integer.parseInt(robotReff));
    }
}
