package inf112.skeleton.app.gameboard;

import inf112.skeleton.app.Direction;
import java.util.HashMap;

public enum Tiles {

    ;

    private final int id;


    Tiles(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static HashMap<Direction, Integer> getPlayerID(String colour) {
        HashMap<Direction, Integer> IDs = new HashMap<>();
        for (int i = 0; i < 36; i++) {
            if (values() [i].toString().equals("PLAYER_" + colour.toUpperCase().replace(" ", "") + "_SOUTH")) {
                IDs.put(Direction.SOUTH, values() [i].getId());
            } else if (values() [i].toString().equals("PLAYER_" + colour.toUpperCase().replace(" ", "") + "_NORTH")) {
                IDs.put(Direction.NORTH, values() [i].getId());
            } else if (values() [i].toString().equals("PLAYER_" + colour.toUpperCase().replace(" ","") + "_WEST")) {
                IDs.put(Direction.WEST, values() [i].getId());
            } else if (values() [i].toString().equals("PLAYER_" + colour.toUpperCase().replace(" ", "") + "_EAST")) {
                IDs.put(Direction.EAST, values() [i].getId());
            }
        }
        return IDs;
    }
}
