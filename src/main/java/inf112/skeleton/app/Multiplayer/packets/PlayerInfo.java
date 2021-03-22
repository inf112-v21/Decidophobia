package inf112.skeleton.app.Multiplayer.packets;

public class PlayerInfo {
    private int playerNr;
    private String nickname;
    private int robotReferenceNr;
    private boolean isHost;
    private boolean ready;

    public PlayerInfo(int playerNr, boolean isHost) {
        this.playerNr = playerNr;
        nickname = "P"+(playerNr+1);
        robotReferenceNr = playerNr;
        this.isHost = isHost;
        this.ready = false;
    }

    public PlayerInfo(String player) {
        String[] pInfo = player.split(";");
        this.playerNr = Integer.parseInt(pInfo[0]);
        this.nickname = pInfo[1];
        this.robotReferenceNr = Integer.parseInt(pInfo[2]);
        this.isHost = Boolean.parseBoolean(pInfo[3]);
        this.ready = Boolean.parseBoolean(pInfo[4]);

    }

    public int getPlayerNr() {
        return playerNr;
    }

    public void setPlayerNr(int playerNr) {
        this.playerNr = playerNr;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRobotReferenceNr() {
        return robotReferenceNr;
    }

    public void setRobotReferenceNr(int robotReferenceNr) {
        this.robotReferenceNr = robotReferenceNr;
    }

    public Boolean getHost() {
        return isHost;
    }

    public void setHost(Boolean host) {
        isHost = host;
    }

    @Override
    public String toString() {
        return playerNr+";"+nickname+";"+robotReferenceNr+";"+isHost+";"+ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean getReady() {
        return ready;
    }
}
