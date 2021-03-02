package inf112.skeleton.app;

import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.PlayerCards;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

public class PlayerCardsTest {
    private PlayerCards playerCards;
    private LinkedList<Cards> cards;

    @Before
    public void setUp() {
        playerCards = new PlayerCards();

        cards = new LinkedList<>();
        cards.add(new Cards(CardType.FORWARD_1, 1));
        cards.add(new Cards(CardType.FORWARD_2, 2));
        cards.add(new Cards(CardType.FORWARD_3, 3));
        cards.add(new Cards(CardType.REVERSE, 4));
        cards.add(new Cards(CardType.U_TURN, 5));

        for (Cards card : cards)
            playerCards.insertCard(card);
    }

    @Test
    public void getFirstCard() {
        Cards card = cards.get(0);
        Cards firstCard = playerCards.getCard(0);
        assertEquals(firstCard, card);
    }

    @Test
    public void getAllCards() {
        assertEquals(playerCards.getAllCards(), cards);
    }

    @Test
    public void insertCardAndGetInsertedCard() {
        PlayerCards playerCards2 = new PlayerCards();
        Cards card = cards.get(0);
        playerCards2.insertCard(card);
        Cards card2 = playerCards2.getCard(0);
        assertEquals(card2, card);
    }

    @Test
    public void checkIfThereAreFiveCards() {
        playerCards.insertCard(cards.get(0));
        playerCards.removeCard(0);
        LinkedList<Cards> allCards = playerCards.getAllCards();
        assertTrue(allCards.size() <= 5);
    }

    @Test
    public void checkIfCardsGetCorrectlySwitched() {
        Cards card = cards.get(0);
        Cards card2 = cards.get(3);
        playerCards.switchCards(0, 3);

        assertEquals(card, playerCards.getCard(3));
        assertEquals(card2, playerCards.getCard(0));
    }

    @Test
    public void checkIfLockedCardsCantBeSwitched() {
        Cards card = cards.get(0);
        playerCards.lockCard(0);
        playerCards.switchCards(0,1);
        assertEquals(card, playerCards.getCard(0));
    }

    @Test
    public void removeFirstAndMiddleCards() {
        playerCards.removeCard(0);
        playerCards.removeCard(2);

        assertNull( playerCards.getCard(0));
        assertNull( playerCards.getCard(2));
    }

    @Test
    public void checkIfRemovedCardIsCorrect() {
        Cards card = playerCards.getCard(0);
        Cards removedCard = playerCards.removeCard(0);
        assertEquals(card, removedCard);
    }

    @Test
    public void checkIfAllCardsAreRemoved() {
        playerCards.removeALlCards();
        LinkedList<Cards> cardsList = playerCards.getAllCards();

        LinkedList<Cards> nullList = new LinkedList<>();
        for (int i = 0; i < 5; i++)
            nullList.add(null);

        assertEquals(cardsList, nullList);
    }
}
