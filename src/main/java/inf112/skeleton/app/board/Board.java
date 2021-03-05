package inf112.skeleton.app.board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.screen.GameScreen;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final TiledMap boardMap;
    private final int boardWidth;
    private final int boardHeight;
    private Map<String, TiledMapTileLayer> boardLayers;

    /*
        The idea is to load the map from a file
     */
    public Board(GameScreen filename) {

        boardMap = new TmxMapLoader().load(String.valueOf(this));
        boardLayers = new HashMap<>();
        for(int i = 0; i < boardMap.getLayers().size(); i++) {
            boardLayers.put(boardMap.getLayers().get(i).getName(), (TiledMapTileLayer) boardMap.getLayers().get(i));
        }

        boardHeight = boardMap.getProperties().get("Width", Integer.class);
        boardWidth = boardMap.getProperties().get("Height", Integer.class);
    }

    public Board(TiledMap boardMap, int height, int width) {
        this.boardMap = boardMap;
        boardWidth = width;
        boardHeight = height;
    }

    public Map<String, TiledMapTileLayer> getBoardLayers() {
        return boardLayers;
    }

    public int getBoardWidth() {
        return this.boardWidth;
    }

    public int getBoardHeight() {
        return this.boardHeight;
    }

    public TiledMap getBoard() {
            return boardMap;
    }
}
