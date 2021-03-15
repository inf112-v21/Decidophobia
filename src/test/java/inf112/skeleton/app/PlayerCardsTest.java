package inf112.skeleton.app;

import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.PlayerCards;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

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
        cards.add(new Cards(CardType.FORWARD_1, 6));
        cards.add(new Cards(CardType.REVERSE, 7));
        cards.add(new Cards(CardType.ROTATE_LEFT, 8));
        cards.add(new Cards(CardType.ROTATE_RIGHT, 9));

        int i = 0;
        for (Cards card : cards) {
            playerCards.insertCard(card);
            if (i < 5)
                playerCards.setActiveCard(i, i);
            i++;
        }
    }

    @Test
    public void getFirstAndFourthCard() {
        Cards card1 = cards.get(0);
        Cards card4 = cards.get(3);
        Cards firstCard = playerCards.getCard(0);
        Cards fourthCard = playerCards.getCard(3);
        assertEquals(firstCard, card1);
        assertEquals(fourthCard, card4);
    }

    @Test
    public void getFirstAndFourthActiveCard() {
        Cards card1 = cards.get(0);
        Cards card4 = cards.get(3);
        Cards firstCard = playerCards.getActiveCard(0);
        Cards fourthCard = playerCards.getActiveCard(3);
        assertEquals(firstCard, card1);
        assertEquals(fourthCard, card4);
    }

    @Test
    public void getIndexOfSecondAndSixthCard() {
        Cards card2 = cards.get(1);
        Cards card6 = cards.get(5);
        int card2Index = playerCards.getIndexOfCard(card2);
        int card6Index = playerCards.getIndexOfCard(card6);
        assertEquals(card2Index, 1);
        assertEquals(card6Index, 5);
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
        Cards insertedCard = playerCards2.getCard(0);
        assertEquals(card, insertedCard);
    }

    @Test
    public void setNewFirstAndSecondActiveCards() {
        Cards card1 = cards.get(6);
        Cards card2 = cards.get(7);
        playerCards.setActiveCard(6, 0);
        playerCards.setActiveCard(7, 1);
        assertEquals(card1, playerCards.getActiveCard(0));
        assertEquals(card2, playerCards.getActiveCard(1));
    }

    @Test
    public void notPossibleToSetDuplicateActiveCards() {
        playerCards.setActiveCard(8, 0);
        playerCards.setActiveCard(8,1);
        assertNotEquals(playerCards.getActiveCard(0), playerCards.getActiveCard(1));
    }

    @Test
    public void checkIfActiveCardsAreAlwaysSize5() {
        PlayerCards playerCards2 = new PlayerCards();
        assertEquals(5, playerCards2.getActiveCards().size());

        playerCards.removeActiveCard(0);
        playerCards.removeActiveCard(1);
        assertEquals(5, playerCards.getActiveCards().size());
    }

    @Test
    public void checkIfCardsGetCorrectlySwitched() {
        Cards card = cards.get(0);
        Cards card2 = cards.get(3);
        playerCards.switchCards(0, 3);

        assertEquals(card, playerCards.getActiveCard(3));
        assertEquals(card2, playerCards.getActiveCard(0));
    }

    @Test
    public void checkIfCardsGetLockedCorrectly() {
        Cards card1 = playerCards.getActiveCard(0);
        Cards card2 = playerCards.getActiveCard(1);
        playerCards.lockCard(0);
        playerCards.lockCard(1);
        assertEquals(card1, playerCards.getLockedCards().get(0));
        assertEquals(card2, playerCards.getLockedCards().get(1));
        assertEquals(2, playerCards.getLockedCards().size());
    }

    @Test
    public void checkIfUnlockCardWorksCorrectly() {
        playerCards.lockCard(0);
        playerCards.unlockCard();
        assertTrue(playerCards.getLockedCards().isEmpty());
    }

    @Test
    public void checkIfLockedCardsCantBeSwitched() {
        Cards card = cards.get(0);
        playerCards.lockCard(0);
        playerCards.switchCards(0,1);
        assertEquals(card, playerCards.getActiveCard(0));
    }

    @Test
    public void cantLockCardsThatAreNotActiveCards() {
        Cards lockedCard = cards.get(8);
        playerCards.lockCard(8);
        Stack<Cards> lockedCards = playerCards.getLockedCards();
        for (Cards card : lockedCards)
            assertNotEquals(lockedCard, card);
    }

    @Test
    public void removeFirstAndMiddleActiveCards() {
        playerCards.removeActiveCard(0);
        playerCards.removeActiveCard(2);

        assertNull(playerCards.getActiveCard(0));
        assertNull(playerCards.getActiveCard(2));
    }

    @Test
    public void checkIfCardIsRemovedCorrectly() {
        Cards card1 = cards.get(0);
        Cards removedCard = playerCards.removeCard(0);
        assertEquals(card1, removedCard);
        assertNull(playerCards.getActiveCard(0));
        playerCards.lockCard(1);
        playerCards.removeCard(1);
        assertNull(playerCards.getActiveCard(1));
        assertTrue(playerCards.getLockedCards().isEmpty());
    }

    @Test
    public void checkIfActiveCardsIsRemovedCorrectly() {
        Cards card = playerCards.getActiveCard(0);
        Cards removedCard = playerCards.removeActiveCard(0);
        assertEquals(card, removedCard);
        assertNull(playerCards.getActiveCard(0));
    }

    @Test
    public void checkIfLockedCardIsRemovedWithActiveCard() {
        Cards card = playerCards.getActiveCard(0);
        playerCards.lockCard(0);
        assertEquals(card, playerCards.getLockedCards().get(0));
        playerCards.removeActiveCard(0);
        assertTrue(playerCards.getLockedCards().isEmpty());
    }

    @Test
    public void checkIfAllCardsAreRemoved() {
        playerCards.lockCard(0);
        playerCards.removeAllCards();

        LinkedList<Cards> cardsList = playerCards.getActiveCards();
        LinkedList<Cards> nullList = new LinkedList<>();
        for (int i = 0; i < 5; i++)
            nullList.add(null);

        assertEquals(nullList, cardsList);
        assertTrue(playerCards.getAllCards().isEmpty());
        assertTrue(playerCards.getLockedCards().isEmpty());
    }
}
