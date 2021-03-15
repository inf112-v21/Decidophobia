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

    public List<PlayerInfo> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerInfo> players) {
        this.players = players;
    }

    public void addPlayer(int playerNumber) {
    }

    public void playerReady(int playerNumber) {
    }

    public void playerUnready(int playerNumber) {
    }

    public void playerQuit(int playerNumber) {
    }

    public void changeNick(int playerNumber, String s) {
    }

    public void changeRobot(int playerNumber, String s, String s1) {
    }
}
