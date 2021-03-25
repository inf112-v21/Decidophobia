package inf112.skeleton.app.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cards {

    private CardType type;
    private int priority;

    public Cards(String card) {
        String[] typeAndPrioriy = card.split(":");
        List<String> strLs = Arrays.asList(typeAndPrioriy);
        type = stringToCardType(typeAndPrioriy[0]);
        priority = Integer.parseInt(typeAndPrioriy[1]);
    }

    @Override
    public String toString() {
        return cardTypeToString(type)+":"+priority;
    }

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

    public static CardType stringToCardType(String type){
        switch (type){
            case "FORWARD_1":
                return CardType.FORWARD_1;
            case "FORWARD_2":
                return CardType.FORWARD_2;
            case "FORWARD_3":
                return CardType.FORWARD_3;
            case "REVERSE":
                return CardType.REVERSE;
            case "ROTATE_RIGHT":
                return CardType.ROTATE_RIGHT;
            case "ROTATE_LEFT":
                return CardType.ROTATE_LEFT;
            case "U_TURN":
                return CardType.U_TURN;
        }
        return null;
    }
    public static String cardTypeToString(CardType type){
        switch (type){
            case FORWARD_1:
                return "FORWARD_1";
            case FORWARD_2:
                return "FORWARD_2";
            case FORWARD_3:
                return "FORWARD_3";
            case REVERSE:
                return "REVERSE";
            case ROTATE_LEFT:
                return "ROTATE_LEFT";
            case ROTATE_RIGHT:
                return "ROTATE_RIGHT";
            case U_TURN:
                return "U_TURN";
        }
        return "";
    }
}
