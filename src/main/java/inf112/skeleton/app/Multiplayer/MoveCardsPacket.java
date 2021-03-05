package inf112.skeleton.app.Multiplayer;

import inf112.skeleton.app.cards.PlayerCards;

public class MoveCardsPacket {
    public PlayerCards playerCards;
    public int playerNr;

    public MoveCardsPacket(int playerNr, PlayerCards playerCards){
        this.playerNr = playerNr;
        this.playerCards = playerCards;
    }
}
