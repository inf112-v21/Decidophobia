package inf112.skeleton.app.cards;


import java.util.LinkedList;

/**
 * Controls the cards the player can use for the turn
 */
public class PlayerCards {
    private LinkedList<Cards> cards;
    private LinkedList<Integer> lockedCards;

    public PlayerCards() {
        cards = new LinkedList<>();
        lockedCards = new LinkedList<>();
    }

    public Cards getCard(int index) {
        return null;
    }

    public LinkedList<Cards> getAllCards() {
        return cards;
    }

    public void insertCard(Cards card) {

    }

    public void switchCards(int card1, int card2) {

    }

    public void lockCard(int index) {

    }

    public Cards removeCard(int index) {
        return null;
    }

    public void removeALlCards() {

    }
}
