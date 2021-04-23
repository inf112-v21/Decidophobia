package inf112.skeleton.app.cards;


import java.util.*;


/**
 * Controls the cards the player can use for the turn
 */
public class PlayerCards {
    private LinkedList<Cards> cardsInHand;
    private Cards[] activeCards;
    private Stack<Cards> lockedCards;

    public PlayerCards(String str) {
        cardsInHand = new LinkedList<>();
        activeCards = new Cards[5];
        lockedCards = new Stack<>();
        String[] cards = str.split(";");
        // Adding cards to hand
        int cardTypeStartIndex = 0;
        for(int i = 1; i < cards.length; i++){
            if(cards[i].equals("l")) {
                cardTypeStartIndex = i;
                i = cards.length;
            }
            else{
                cardsInHand.add(new Cards(cards[i]));
            }
        }
        // Adding cards to locked cards and active cards
        int activeCardsIndexer = 0;
        for(int i = cardTypeStartIndex+1; i < cards.length; i++){
            if(cards[i].equals("a")) {
                cardTypeStartIndex = i;
                i = cards.length;
            }
            else if(activeCardsIndexer<5){
                Cards newCard = new Cards(cards[i]);
                activeCards[activeCardsIndexer] = newCard;
                lockedCards.push(newCard);
                activeCardsIndexer++;
            }
        }
        // Adding non locked cards to active cards
        for(int i = cardTypeStartIndex+1; i < cards.length; i++){
            activeCards[activeCardsIndexer] = new Cards(cards[i]);
            activeCardsIndexer++;
        }
    }

    @Override
    public String toString() {
        String cardsString = "h;";
        for(Cards card : cardsInHand){
            cardsString += card.toString() +";";
        }
        cardsString += "l;";
        int indexOfActiveCards = 0;
        for(Cards card : lockedCards) {
            cardsString += card.toString() + ";";
            indexOfActiveCards++;
        }
        cardsString += "a;";
        for(int i = indexOfActiveCards; i < activeCards.length; i++) {
            if(activeCards[i] != null) {
                cardsString += activeCards[i].toString() + ";";
            }
        }
        return cardsString;
    }

    public PlayerCards() {
        cardsInHand = new LinkedList<>();
        activeCards = new Cards[5];
        lockedCards = new Stack<>();
    }

    public Cards getCard(int index) {
        return cardsInHand.get(index);
    }

    public int getIndexOfCard(Cards card) {
        return cardsInHand.indexOf(card);
    }

    public Cards getActiveCard(int index) {
        return activeCards[index];
    }

    public int getIndexOfActiveCard(Cards card) {
        int i = 0;
        for (Cards c : activeCards) {
            if (c == card)
                break;
            i++;
        }
        return i;
    }

    /**
     * @return all active cards
     */
    public LinkedList<Cards> getActiveCards() {
        LinkedList<Cards> cards = new LinkedList<>();
        for(Cards card : activeCards)
            if(card != null) cards.add(card);
        return cards;
    }

    /**
     * @return all cards in hand
     */
    public LinkedList<Cards> getCardsInHand() {
        return cardsInHand;
    }

    public void insertCard(Cards card) {
        cardsInHand.add(card);
    }

    /**
     * Sets activeCards from one of the cards in allCards
     * @param cardsIndex the index in cardsInHand
     * @param activeCardsIndex the index where the new active card should be
     */
    public void setActiveCard(int cardsIndex, int activeCardsIndex) {
        Cards card = cardsInHand.get(cardsIndex);
        if (activeCardsContains(card))
            throw new IllegalArgumentException("Card is already active");
        activeCards[activeCardsIndex] = card;
        cardsInHand.remove(cardsIndex);
    }
    public void setLastActiveCard(Cards card) {
        if(cardsInHand.contains(card)){
            cardsInHand.remove(card);
            activeCards[getActiveCards().size()] = card;
        }
    }

    private boolean activeCardsContains(Cards card) {
        for (Cards c : activeCards) {
            if (c == card)
                return true;
        }
        return false;
    }

    public Cards removeActiveCard(int index) {
        Cards card = activeCards[index];
        if(lockedCards.contains(card))
            return null;
        if (card != null) {
            activeCards[index] = null;
            cardsInHand.add(card);
        }
        return card;
    }

    /**
     * Switches positions in of two cards in activeCards
     * @param card1Index card to be switched
     * @param card2Index card getting switched
     */
    public void switchCards(int card1Index, int card2Index) {
        Cards card1 = activeCards[card1Index];
        Cards card2 = activeCards[card2Index];
        if (!(lockedCards.contains(card1) || lockedCards.contains(card2))) {
            activeCards[card1Index] = card2;
            activeCards[card2Index] = card1;
        }
    }

    /**
     * Locks cards so you can't switch card positions
     * @param index in activeCards
     */
    public void lockCard(int index) {
        Cards card = activeCards[index];
        if(!lockedCards.contains(card)) {
            lockedCards.push(card);
        }
    }

    /**
     * Unlocks the uppermost card
     */
    public void unlockCard() {
        if(lockedCards.isEmpty())
            throw new IllegalStateException("There are no cards to unlock");
        lockedCards.pop();
    }

    public Stack<Cards> getLockedCards() {
        return lockedCards;
    }

    /**
     * removes Card from allCards and ActiveCards
     * @param card to be removed
     * @return removed card
     */
    public Cards removeCard(Cards card) {
        if(cardsInHand.contains(card))
            cardsInHand.remove(card);
        else if (activeCardsContains(card)) {
            activeCards[getIndexOfActiveCard(card)] = null;
            if (lockedCards.contains(card))
                lockedCards.remove(card);
        }
        else
            throw new IllegalArgumentException("Card doesn't exist in hand or activeCards");
        return card;
    }

    /**
     * removes every card in both allCards and activeCards
     */
    public void removeAllCards() {
        cardsInHand = new LinkedList<>();
        lockedCards = new Stack<>();
        for (int i = 0; i < 5; i++)
            activeCards[i] = null;
    }

    public void dealToHand(Cards card) {
        cardsInHand.add(card);
    }

    public int size() {
        return cardsInHand.size()+getActiveCards().size();
    }
}
