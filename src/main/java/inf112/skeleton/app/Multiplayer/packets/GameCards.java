package inf112.skeleton.app.Multiplayer.packets;

import inf112.skeleton.app.cards.Deck;
import inf112.skeleton.app.cards.PlayerCards;

import java.util.HashMap;
import java.util.Map;

public class GameCards {
    private Map<Integer, PlayerCards> allPlayerHands;
    private Deck deck;

    public GameCards(Deck deck) {
        this.allPlayerHands = new HashMap<Integer, PlayerCards>();
        this.deck = deck;
    }

    public Map<Integer, PlayerCards> getAllPlayerHands() {
        return allPlayerHands;
    }

    public void setAllPlayerHands(Map<Integer, PlayerCards> allPlayerHands) {
        this.allPlayerHands = allPlayerHands;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
