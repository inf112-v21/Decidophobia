package inf112.skeleton.app.gameboard;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.ArrayList;

public class GameLayers {

    protected final TiledMap tiledMap;

    protected final TiledMapTileLayer playerLayer;
    protected final TiledMapTileLayer flagLayer;
    protected final TiledMapTileLayer wallLayer;
    protected final TiledMapTileLayer laserLayer;
    protected final TiledMapTileLayer groundLayer;

    protected final int boardWidth;
    protected final int boardHeight;

    public GameLayers(String filename) {
        this.tiledMap = new TmxMapLoader().load(filename);

        this.playerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        this.flagLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");
        this.laserLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Laser");
        this.wallLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Wall");
        this.groundLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Ground");

        MapProperties properties = tiledMap.getProperties();
        boardWidth = properties.get("width", Integer.class);
        boardHeight = properties.get("height", Integer.class);
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
