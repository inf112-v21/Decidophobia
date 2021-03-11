package inf112.skeleton.app.cards;

public class Cards {

    private final CardType type;
    private final int priority;

    public Cards(){
        type = null;
        priority = -1;
    }

    public Cards(CardType type, int priority) {
        this.type = type;
        this.priority = priority;
    }

    public CardType getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }
}
