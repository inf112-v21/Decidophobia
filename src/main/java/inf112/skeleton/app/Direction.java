package inf112.skeleton.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Direction {
    NORTH,
    WEST,
    SOUTH,
    EAST;

    public Direction turnLeft() {
        return getDirection(NORTH, WEST, SOUTH, EAST);
    }

    public Direction turnRight() {
        return getDirection(SOUTH, EAST, NORTH, WEST);
    }

    public Direction turnAround() {
        return getDirection(WEST, SOUTH, EAST, NORTH);
    }

    private Direction getDirection(Direction baseCaseEAST, Direction baseCaseNORTH, Direction baseCaseWEST, Direction baseCaseSOUTH) {
        switch (this) {
            case EAST:
                return baseCaseEAST;
            case NORTH:
                return baseCaseNORTH;
            case WEST:
                return baseCaseWEST;
            case SOUTH:
                return baseCaseSOUTH;
            default:
                return null;
        }
    }

    public static List<Direction> getDirectionRandomOrder() {
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);
        return directions;
    }
}
