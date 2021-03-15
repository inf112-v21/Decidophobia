package inf112.skeleton.app.Multiplayer.packets;

public class PlayerInfo {
    private int playerNr;
    private String nickname;
    private int robotReferenceNr;
    private Boolean isHost;

    public PlayerInfo(int playerNr, Boolean isHost) {
        this.playerNr = playerNr;
        nickname = "P"+playerNr+1;
        robotReferenceNr = playerNr;
        this.isHost = isHost;
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
}
