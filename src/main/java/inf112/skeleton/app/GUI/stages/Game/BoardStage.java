package inf112.skeleton.app.GUI.stages.Game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.GUI.screen.GameScreen;
import inf112.skeleton.app.GameLogic;

public class BoardStage {
    private GameLogic roboGame;
    private GameScreen gameScreen;

    public OrthogonalTiledMapRenderer mapRenderer;
    public OrthographicCamera boardCam;

    private TiledMap boardMap;

    public BoardStage(GameScreen gameScreen, GameLogic roboGame) {
        this.gameScreen = gameScreen;
        this.roboGame = roboGame;
        String boardPath = "src/assets/tiledTest.tmx";
        AssetManager assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader());
        assetManager.load(boardPath, TiledMap.class); // should refference the GameRules.
        assetManager.finishLoading();

        boardMap = assetManager.get(boardPath); // should refference the GameRules.

        //Setting up camera for game board
        boardCam = new OrthographicCamera();
        TiledMapTileLayer boardLayer = (TiledMapTileLayer) boardMap.getLayers().get("Board");
        boardCam.setToOrtho(false, boardLayer.getWidth(), boardLayer.getHeight());
        boardCam.update();

        //Creating the map renderer
        //Divide uniiteScale on width with the assumption that tiles as equal height and width (300x300).
        mapRenderer = new OrthogonalTiledMapRenderer(boardMap, (float) 1/boardLayer.getTileWidth());


    }

    public TiledMap getBoardMap() {
        return boardMap;
    }

    public void setBoardMap(TiledMap boardMap) {
        this.boardMap = boardMap;
    }
}
