package inf112.skeleton.app.Multiplayer.packets;

import java.io.Serializable;

public class GameRules implements Serializable {
    private int damageTokens;
    private int lifeTokens;
    private String boardPath;
    private String readBoardFile;

    public GameRules(){
        this.damageTokens = 9;
        this.lifeTokens = 3;
    }

    public GameRules( int lifeTokens, int damageTokens){
        this.damageTokens = damageTokens;
        this.lifeTokens = lifeTokens;
    }

    public GameRules(String str) {
        String[] rules = str.split(";");
        this.lifeTokens = Integer.valueOf(rules[0]);
        this.damageTokens = Integer.valueOf(rules[1]);
    }

    public int getDamageTokens() {
        return damageTokens;
    }

    public void setDamageTokens(int damageTokens) {
        this.damageTokens = damageTokens;
    }

    public int getLifeTokens() {
        return lifeTokens;
    }

    public void setLifeTokens(int lifeTokens) {
        this.lifeTokens = lifeTokens;
    }

    @Override
    public boolean equals(Object obj) {
        GameRules newGame = (GameRules) obj;
        return getDamageTokens() == newGame.getDamageTokens() && getLifeTokens() == newGame.getLifeTokens();
    }

    @Override
    public String toString() {
        return "gameRules,"+lifeTokens+";"+damageTokens+",";
    }

    public String getBoardPath(){
        return boardPath;
    }

    public void setBoard(String file) {
        readBoardFile = file;
    }

    public String getBoard() {
        return readBoardFile;
    }
}
