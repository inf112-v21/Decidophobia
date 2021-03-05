package inf112.skeleton.app.board;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.Map;

public class IBoard {

    //tenkte vi kunne hente objektene inn hit seinare

    private final Map<String, TiledMapTileLayer> boardMap;


    public IBoard(Map<String, TiledMapTileLayer> boardMap) {
        this.boardMap = boardMap;
    }
}
