package inf112.skeleton.app.Multiplayer.packets;

public class PlayerInfo {
    private String nickname;
    private int robotReferenceNr;
    private Boolean isHost;

    public PlayerInfo(int playerNr, Boolean isHost) {
        nickname = "P"+playerNr+1;
        robotReferenceNr = playerNr;
        this.isHost = isHost;
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
