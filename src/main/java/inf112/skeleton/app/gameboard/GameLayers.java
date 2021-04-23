package inf112.skeleton.app.gameboard;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.gameboard.objects.Belt;
import inf112.skeleton.app.gameboard.objects.Flag;
import inf112.skeleton.app.gameboard.objects.Gear;
import inf112.skeleton.app.gameboard.objects.Laser;

import java.util.ArrayList;

public class GameLayers {

    protected final TiledMap tiledMap;

    protected final TiledMapTileLayer robotLayer;
    protected final TiledMapTileLayer flagLayer;
    protected final TiledMapTileLayer wallLayer;
    protected final TiledMapTileLayer laserLayer;
    protected final TiledMapTileLayer groundLayer;

    protected final ArrayList<Laser> lasers;
    protected final ArrayList<Flag> flags;
    protected final ArrayList<Gear> rotatePads;
    protected final ArrayList<Vector2> holes;
    protected final ArrayList<Belt> belts;
    protected final ArrayList<Belt> expressBelts;
    protected final ArrayList<Vector2> repairTiles;

    protected final int boardWidth;
    protected final int boardHeight;

    public GameLayers(String filename) {
        this.tiledMap = new TmxMapLoader().load(filename);

        this.robotLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        this.flagLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");
        this.laserLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Laser");
        this.wallLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Wall");
        this.groundLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Ground");

        MapProperties properties = tiledMap.getProperties();
        boardWidth = properties.get("width", Integer.class);
        boardHeight = properties.get("height", Integer.class);

        this.holes = new ArrayList<>();
        this.rotatePads = new ArrayList<>();
        this.flags = new ArrayList<>();
        this.lasers = new ArrayList<>();
        this.belts = new ArrayList<>();
        this.expressBelts = new ArrayList<>();
        this.repairTiles = new ArrayList<>();

        findFlags();
        findBelts();
        findExpressBelts();
        findHoles();
        findLasers();
        findRepairs();

    }

    public void findBelts() {
        for (int x = 0; x < groundLayer.getWidth(); x++) {
            for (int y = 0; y < groundLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = groundLayer.getCell(x, y);
                int ID = cell.getTile().getId();

                if (ID == Tiles.EAST_TO_SOUTH_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.NORTH_TO_EAST_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.WEST_TO_NORTH_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.SOUTH_TO_WEST_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                } else if (ID == Tiles.EAST_TO_NORTH_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.NORTH_TO_WEST_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                } else if (ID == Tiles.WEST_TO_SOUTH_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.SOUTH_TO_EAST_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.EAST_TO_WEST_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                } else if (ID == Tiles.NORTH_TO_SOUTH_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.WEST_TO_EAST_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.SOUTH_TO_NORTH_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.WESTSOUTH_TO_NORTH_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.EASTSOUTH_TO_NORTH_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.WESTEAST_TO_NORTH_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.WESTNORTH_TO_SOUTH_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.EASTNORTH_TO_SOUTH_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.WESTEAST_TO_SOUTH_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.WESTSOUTH_TO_EAST_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.WESTNORTH_TO_EAST_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.NORTHSOUTH_TO_EAST_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.EASTSOUTH_TO_WEST_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                } else if (ID == Tiles.EASTNORTH_TO_WEST_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                } else if (ID == Tiles.NORTHSOUTH_TO_WEST_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                }
            }
        }
    }

    public void findExpressBelts() {
        for (int x = 0; x < groundLayer.getWidth(); x++) {
            for (int y = 0; y < groundLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = groundLayer.getCell(x, y);
                int ID = cell.getTile().getId();

                if (ID == Tiles.EAST_TO_SOUTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.NORTH_TO_EAST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.WEST_TO_NORTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.SOUTH_TO_WEST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                } else if (ID == Tiles.EAST_TO_NORTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.NORTH_TO_WEST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                } else if (ID == Tiles.WEST_TO_SOUTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.SOUTH_TO_EAST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.EAST_TO_WEST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                } else if (ID == Tiles.NORTH_TO_SOUTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.WEST_TO_EAST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.SOUTH_TO_NORTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.WESTSOUTH_TO_NORTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.EASTSOUTH_TO_NORTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.WESTEAST_TO_NORTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.NORTH, new Vector2(x, y)));
                } else if (ID == Tiles.WESTNORTH_TO_SOUTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.EASTNORTH_TO_SOUTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.WESTEAST_TO_SOUTH_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.SOUTH, new Vector2(x, y)));
                } else if (ID == Tiles.WESTSOUTH_TO_EAST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.WESTNORTH_TO_EAST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.NORTHSOUTH_TO_EAST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.EAST, new Vector2(x, y)));
                } else if (ID == Tiles.EASTSOUTH_TO_WEST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                } else if (ID == Tiles.EASTNORTH_TO_WEST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                } else if (ID == Tiles.NORTHSOUTH_TO_WEST_EXPRESS_BELT.getId()) {
                    belts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                    expressBelts.add(new Belt(Direction.WEST, new Vector2(x, y)));
                }
            }
        }
    }

    public void findHoles() {
        for (int x = 0; x < groundLayer.getWidth(); x++) {
            for (int y = 0; y < groundLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = groundLayer.getCell(x, y);
                int ID = cell.getTile().getId();

                if (ID == Tiles.NORMAL_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.NORMAL_HOLE2.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.NORTHWEST_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.NORTH_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.NORTHEAST_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.EAST_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.NORTH_EAST_SOUTH_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.WEST_EAST_SOUTH_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.SOUTHWEST_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.SOUTH_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.SOUTHEAST_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.WEST_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.NORTH_WEST_SOUTH_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                } else if (ID == Tiles.NORTH_WEST_EAST_HOLE.getId()) {
                    holes.add(new Vector2(x, y));
                }
            }
        }
    }

    public void findFlags() {
        for (int x = 0; x < flagLayer.getWidth(); x++) {
            for (int y = 0; y < flagLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = flagLayer.getCell(x, y);
                if (cell != null) {
                    int ID = cell.getTile().getId();
                    if (ID == Tiles.FLAG_1.getId()) {
                        flags.add(new Flag(1, x, y));
                    } else if (ID == Tiles.FLAG_2.getId()) {
                        flags.add(new Flag(2, x, y));
                    } else if (ID == Tiles.FLAG_3.getId()) {
                        flags.add(new Flag(3, x, y));
                    } else if (ID == Tiles.FLAG_4.getId()) {
                        flags.add(new Flag(4, x, y));
                    }
                }
            }
        }
    }

    public void findLasers() {
        for (int x = 0; x < wallLayer.getWidth(); x++) {
            for (int y = 0; y < wallLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = wallLayer.getCell(x, y);
                if (cell != null) {
                    int ID = cell.getTile().getId();
                    if (ID == Tiles.EAST_LASER_WALL.getId()) {
                        lasers.add(new Laser(x, y, Direction.WEST));
                    } else if (ID == Tiles.WEST_LASER_WALL.getId()) {
                        lasers.add(new Laser(x, y, Direction.EAST));
                    } else if (ID == Tiles.NORTH_LASER_WALL.getId()) {
                        lasers.add(new Laser(x, y, Direction.SOUTH));
                    } else if (ID == Tiles.SOUTH_LASER_WALL.getId()) {
                        lasers.add(new Laser(x, y, Direction.NORTH));
                    }
                }
            }
        }
    }

    public void findRepairs() {
        for (int x = 0; x < groundLayer.getWidth(); x++) {
            for (int y = 0; y < groundLayer.getHeight(); y++) {

                TiledMapTileLayer.Cell cell = groundLayer.getCell(x, y);
                if(cell != null){
                    int ID =  cell.getTile().getId();
                    if (ID == Tiles.WRENCH.getId()){
                        repairTiles.add(new Vector2(x, y));
                    } else if (ID == Tiles.DOUBLE_WRENCH.getId()){
                        repairTiles.add(new Vector2(x, y));
                    }
                }
            }
        }
    }


    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public TiledMapTileLayer getLaserLayer() {
        return laserLayer;
    }

    public TiledMapTileLayer getWallLayer() {
        return wallLayer;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }
}
