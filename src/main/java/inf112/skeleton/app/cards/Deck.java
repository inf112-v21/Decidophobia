package inf112.skeleton.app.cards;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class Deck extends Stack<Cards> {

    /*
    Quantity of different cards:

    forward 1 = 18
    forward 2 = 12
    forward 3 = 6
    reverse = 6
    turn right = 18
    turn left  = 18
    u turn = 6
     */

    public Deck() {
        super();

        LinkedList<Integer> priority = new LinkedList<>();
        for(int i = 1; i < 85; i++) {
            priority.add(i * 10);
        }

        //Forward 1 Cards
        for(int i = 0; i < 18; i++) {
            Cards cards = new Cards(CardType.FORWARD_1, priority.pop());
            this.add(cards);
        }

        //Forward 2 Cards
        for(int i = 0; i < 12; i++) {
            Cards cards = new Cards(CardType.FORWARD_2, priority.pop());
            this.add(cards);
        }

        //Forward 3 Cards
        for(int i = 0; i < 6; i++) {
            Cards cards = new Cards(CardType.FORWARD_3, priority.pop());
            this.add(cards);
        }

        //Reverse Cards
        for(int i = 0; i < 6; i++) {
            Cards cards = new Cards(CardType.REVERSE, priority.pop());
            this.add(cards);
        }

        //Rotation Cards
        boolean  left = true;
        for(int i = 0; i < 18 * 2; i++) {
            Cards cards;
            if (left)
                cards = new Cards(CardType.ROTATE_LEFT, priority.pop());
            else
                cards  = new Cards(CardType.ROTATE_RIGHT, priority.pop());
            this.add(cards);

            left = !left;
        }

        //U turn Cards
        for(int i = 0; i < 6; i++) {
            Cards cards = new Cards(CardType.U_TURN, priority.pop());
            this.add(cards);
        }

        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(this);
    }
}
