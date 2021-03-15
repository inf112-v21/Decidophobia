package inf112.skeleton.app.cards;


import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * Controls the cards the player can use for the turn
 */
public class PlayerCards {
    private Map<Boolean, Cards> allCards;
    private LinkedList<Cards> activeCards;
    private Stack<Cards> lockedCards;

    public PlayerCards() {
        activeCards = new LinkedList<>();
        lockedCards = new Stack<>();
    }

    public Cards getCard(int index) {
        return null;
    }

    public int getIndexOfCard(Cards card) {
        return 0;
    }

    public Cards getActiveCard(int index) {
        return activeCards.get(index);
    }

    /**
     * @return all active cards
     */
    public LinkedList<Cards> getActiveCards() {
        return activeCards;
    }

    /**
     * @return all cards in hand (allCards)
     */
    public LinkedList<Cards> getAllCards() {
        return null;
    }

    public void insertCard(Cards card) {
        activeCards.add(card);
    }

    /**
     * Sets activeCards from one of the cards in allCards
     * @param cardsIndex of card to be set as active
     * @param activeCardsIndex the index where the new active card should be
     */
    public void setActiveCard(int cardsIndex, int activeCardsIndex) {

    }

    public Cards removeActiveCard(int index) {
        return null;
    }

    /**
     * Switches positions in of two cards in activeCards
     * @param card1 card to be switched
     * @param card2 card getting switched
     */
    public void switchCards(int card1, int card2) {

    }

    /**
     * Locks cards so you can't switch card positions
     * @param index in activeCards
     */
    public void lockCard(int index) {

    }

    /**
     * Unlocks the uppermost card
     */
    public void unlockCard() {

    }

    public Stack<Cards> getLockedCards() {
        return null;
    }

    /**
     * removes Card from allCards and ActiveCards
     * @param index for allCards
     * @return removed card
     */
    public Cards removeCard(int index) {
        return null;
    }

    /**
     * removes every card in both allCards and activeCards
     */
    public void removeAllCards() {

    }
}
