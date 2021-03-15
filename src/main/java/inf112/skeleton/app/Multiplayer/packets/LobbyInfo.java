package inf112.skeleton.app.Multiplayer.packets;

import java.util.List;
import java.util.Map;

public class LobbyInfo {
    private List<PlayerInfo> players;
    private Map<Integer, Boolean> playersReady;

    public LobbyInfo(List<PlayerInfo> players, Map<Integer, Boolean> playersReady) {
        this.players = players;
        this.playersReady = playersReady;
    }

    public List<PlayerInfo> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerInfo> players) {
        this.players = players;
    }

    public Map<Integer, Boolean> getPlayersReady() {
        return playersReady;
    }

    public void setPlayersReady(Map<Integer, Boolean> playersReady) {
        this.playersReady = playersReady;
    }

}
