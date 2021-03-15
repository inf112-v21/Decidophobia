package inf112.skeleton.app.Multiplayer.packets;

public class GameRules {
    private int damageTokens;
    private int lifeTokens;

    public GameRules(){
        damageTokens = 9;
        lifeTokens = 3;
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
}
