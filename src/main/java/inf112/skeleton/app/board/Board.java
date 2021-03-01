package inf112.skeleton.app.board;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.*;

public class Board {

    private final TiledMap tiledMap;
    private final int boardWidth;
    private final int boardHeight;
    private int numberOfFlags;
//    private final List<MapLayers> layers = new ArrayList<>();


    /*
        The idea is to load the map from a file
     */
    public Board(String filename, int boardWidth, int boardHeight) {

        tiledMap = new TmxMapLoader().load(filename);
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        for(int i = 0; i < this.boardWidth; i++) {
            for(int j = 0; j < this.boardHeight; j++) {
                if(getFlagLayer().getCell(i,j) != null) {
                    numberOfFlags += 1;
                }
            }
        }
    }

    public Board(int boardHeight, int boardWidth, int boardWidth1, int boardHeight1) {
        this.boardWidth = boardWidth1;
        this.boardHeight = boardHeight1;

        tiledMap = new TiledMap();
        tiledMap.getProperties().put("Width", boardWidth);
        tiledMap.getProperties().put("Height", boardHeight);
        MapLayers layers = tiledMap.getLayers();

        int tileSize = 100;
        TiledMapTileLayer tileLayer = new TiledMapTileLayer(boardWidth, boardHeight,tileSize, tileSize);
        tileLayer.setName(IBoard.TILE);
        layers.add(tileLayer);

        for(int i = 0; i < boardWidth; i++) {
            for(int j = 0; j < boardHeight; j++) {
                tileLayer.setCell(i, j, new TiledMapTileLayer.Cell());
                TiledMapTile tile = new StaticTiledMapTile(new TextureRegion());
                tile.getProperties().put("type", "tile");
                getTileLayer().getCell(i ,j).setTile(tile);
            }
        }
    }

    private TiledMapTileLayer getTileLayer() {
        return (TiledMapTileLayer) tiledMap.getLayers().get(IBoard.TILE);
    }


    private TiledMapTileLayer getFlagLayer() {
        return (TiledMapTileLayer) tiledMap.getLayers().get(IBoard.FLAG);
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }
}
