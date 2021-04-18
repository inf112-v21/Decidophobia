package inf112.skeleton.app.GUI.stages.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.ScreenManager;
import inf112.skeleton.app.GUI.screen.GameScreen;
import inf112.skeleton.app.GameLogic;

public class BoardStage extends InputAdapter {
    private GameLogic roboGame;
    private GameScreen gameScreen;
    public Viewport viewport;

    public OrthogonalTiledMapRenderer mapRenderer;
    public OrthographicCamera boardCam;

    private TiledMap boardMap;
    TiledMapTileLayer boardLayer;

    public BoardStage(GameScreen gameScreen, GameLogic roboGame){
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
        this.viewport = new ExtendViewport(ScreenManager.V_WIDTH,ScreenManager.V_HEIGHT,boardCam);
        boardLayer = (TiledMapTileLayer) boardMap.getLayers().get("Board");
        boardCam.setToOrtho(false, boardLayer.getWidth(), boardLayer.getHeight());
        boardCam.translate(boardLayer.getWidth()/2,boardLayer.getHeight()/2);

        //Creating the map renderer
        mapRenderer = new OrthogonalTiledMapRenderer(boardMap,(float) 1/10);


    }

    public TiledMap getBoardMap() {
        return boardMap;
    }

    public void setBoardMap(TiledMap boardMap) {
        this.boardMap = boardMap;
    }


    @Override
    public boolean scrolled(int amount) {
        if(boardCam.zoom+amount>0) boardCam.zoom += amount;
        return super.scrolled(amount);
    }

    public void moveBoard(int screenX, int screenY) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        float boardWidth = boardLayer.getWidth()*boardLayer.getTileWidth()/boardCam.viewportWidth;
        float boardHeight = boardLayer.getHeight()*boardLayer.getTileHeight()/boardCam.viewportHeight;

        int fraction = 10;
        int amount = 2;
        float padding = Math.min(width/fraction,height/fraction);
        if(screenX<padding){
            if(boardCam.position.x>boardCam.viewportWidth/2) boardCam.translate(-amount,0);
        }
        else if(screenX>width-padding){
            if(boardCam.position.x<boardWidth-boardCam.viewportWidth/2) boardCam.translate(amount,0);
        }
        if(screenY<padding){
            if(boardCam.position.y<boardHeight-boardCam.viewportHeight/2) boardCam.translate(0,amount);
        }
        else if(screenY>height-padding){
            if(boardCam.position.y>boardCam.viewportHeight/2) boardCam.translate(0,-amount);
        }
    }
}
