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
                playerCards.setActiveCard(0, i);
            i++;
        }
    }

    @Test
    public void getFirstAndThirdCard() {
        Cards card1 = cards.get(5);
        Cards card3 = cards.get(7);
        Cards firstCard = playerCards.getCard(0);
        Cards thirdCard = playerCards.getCard(2);
        assertEquals(firstCard, card1);
        assertEquals(thirdCard, card3);
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
    public void getIndexOfSecondAndFourthCard() {
        Cards card2 = cards.get(6);
        Cards card4 = cards.get(8);
        int card2Index = playerCards.getIndexOfCard(card2);
        int card4Index = playerCards.getIndexOfCard(card4);
        assertEquals(1, card2Index);
        assertEquals(3, card4Index);
    }

    @Test
    public void getAllCardsInHand() {
        LinkedList<Cards> cardsInHand = new LinkedList<>();
        for (int i = 5; i < 9; i++)
            cardsInHand.add(cards.get(i));
        assertEquals(playerCards.getCardsInHand(), cardsInHand);
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
    public void setNewFirstAndSecondActiveCardsAndCardsAreRemovedFromHand() {
        Cards card1 = cards.get(5);
        Cards card2 = cards.get(6);
        playerCards.setActiveCard(0, 0);
        playerCards.setActiveCard(0, 1);
        assertEquals(card1, playerCards.getActiveCard(0));
        assertEquals(card2, playerCards.getActiveCard(1));
        assertTrue(!playerCards.getCardsInHand().contains(card1));
        assertTrue(!playerCards.getCardsInHand().contains(card2));
    }

    @Test
    public void notPossibleToSetDuplicateActiveCards() {
        playerCards.setActiveCard(2, 0);
        playerCards.setActiveCard(2,1);
        assertNotEquals(playerCards.getActiveCard(0), playerCards.getActiveCard(1));
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
    public void removeFirstAndMiddleActiveCardsAndFindThemInHand() {
        //First card
        Cards card1 = playerCards.getActiveCard(0);
        Cards removed1 = playerCards.removeActiveCard(0);
        assertEquals(card1, removed1);
        assertNull(playerCards.getActiveCard(0));
        assertTrue(playerCards.getCardsInHand().contains(removed1));

        //Middle card
        Cards card3 = playerCards.getActiveCard(2);
        Cards removed3 = playerCards.removeActiveCard(2);
        assertEquals(card3, removed3);
        assertNull(playerCards.getActiveCard(2));
        assertTrue(playerCards.getCardsInHand().contains(removed3));
    }

    @Test
    public void checkIfCardIsRemovedCorrectly() {
        //Active Card
        Cards card1 = cards.get(0);
        Cards removedCardActive = playerCards.removeCard(card1);
        assertEquals(card1, removedCardActive);
        assertNull(playerCards.getActiveCard(0));

        //Card in hand
        Cards card6 = cards.get(5);
        Cards removedCardHand = playerCards.removeCard(card6);
        assertEquals(card6, removedCardHand);
        assertTrue(!playerCards.getCardsInHand().contains(card6));

        //Locked card
        Cards card2 = cards.get(1);
        playerCards.lockCard(1);
        Cards removedCardLocked = playerCards.removeCard(card2);
        assertEquals(card2, removedCardLocked);
        assertNull(playerCards.getActiveCard(1));
        assertTrue(playerCards.getLockedCards().isEmpty());
    }

    @Test
    public void canNotRemoveLockedActiveCards() {
        Cards card = playerCards.getActiveCard(0);
        playerCards.lockCard(0);
        assertEquals(card, playerCards.getLockedCards().peek());
        playerCards.removeActiveCard(0);
        assertEquals(card, playerCards.getLockedCards().peek());
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
        assertTrue(playerCards.getCardsInHand().isEmpty());
        assertTrue(playerCards.getLockedCards().isEmpty());
    }
}
