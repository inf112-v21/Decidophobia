package inf112.skeleton.app.gameboard.utilities;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.Direction;

import java.util.ArrayList;
import java.util.Locale;

public enum TileLayer {

    PLAYER_LAYER,
    HOLE_LAYER,
    FLAG_LAYER,
    WALL_LAYER,
    LASER_LAYER,
    PUSH_LAYER,
    CONVEYOR_LAYER,
    WRENCH_LAYER,
    GEAR_LAYER,
    HEALTH_LAYER;


    public static TileLayer asTileLayer (String name) {
        try {
            return valueOf(TileLayer.class, name.toUpperCase(Locale.ROOT));

        } catch (IllegalArgumentException | NullPointerException e2) {
            return null;
        }
    }

    public static Object getProperty(TiledMapTileLayer.Cell cell, String property) {
        if (cell == null) return null;
        try {
            return cell.getTile().getProperties().get(property);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static String getType(TiledMapTileLayer.Cell cell) {
        try {
            return (String) cell.getTile().getProperties().get("type");

        } catch (NullPointerException e) {
            return null;
        }
    }

    public static Direction getDirection(TiledMapTileLayer.Cell cell) {
        int directionNumber = (int) cell.getTile().getProperties().get("direction");

        switch (directionNumber) {
            case (0):
                return Direction.NORTH;
            case (1):
                return Direction.EAST;
            case (2):
                return Direction.SOUTH;
            case (3):
                return Direction.WEST;
            default:
                return null;
        }
    }

    public String toString() {
        return this.name().toLowerCase();
    }
}
