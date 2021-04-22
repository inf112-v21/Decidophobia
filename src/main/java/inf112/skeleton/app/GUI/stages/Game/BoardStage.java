package inf112.skeleton.app.GUI.stages.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.GUI.screen.GameScreen;

public class BoardStage extends InputAdapter {

    public OrthogonalTiledMapRenderer mapRenderer;
    public OrthographicCamera boardCam;

    private TiledMap boardMap;
    TiledMapTileLayer boardLayer;

    public BoardStage(GameScreen gameScreen){

        String boardPath = gameScreen.roboGame.networkClient.getGameRules().getBoardPath();
        AssetManager assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader());
        assetManager.load(boardPath, TiledMap.class);
        assetManager.finishLoading();

        boardMap = assetManager.get(boardPath);

        //Setting up camera for game board
        boardCam = new OrthographicCamera();
        boardLayer = (TiledMapTileLayer) boardMap.getLayers().get("Board");
        boardCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        boardCam.translate(boardLayer.getWidth()/2,boardLayer.getHeight()/2);
        boardCam.zoom = 0.01f;

        //Creating the map renderer
        mapRenderer = new OrthogonalTiledMapRenderer(boardMap,(float) 1/300);


    }

    public TiledMap getBoardMap() {
        return boardMap;
    }

    public void setBoardMap(TiledMap boardMap) {
        this.boardMap = boardMap;
    }


    @Override
    public boolean scrolled(int amount) {
        float zoomScale = (float) amount/300;
        if(boardCam.zoom+zoomScale>0) boardCam.zoom += zoomScale;
        return super.scrolled(amount);
    }

    public void moveBoard(int screenX, int screenY) {
        if(Gdx.input.isButtonPressed(1)) { //Right mouse button.
            float width = Gdx.graphics.getWidth();
            float height = Gdx.graphics.getHeight();

            float boardWidth = boardLayer.getWidth();
            float boardHeight = boardLayer.getHeight();

            int fraction = 2;
            float amount = (float) 1 / 150; //amount*1 till 10 good boundaries

            int topSpeed = 10;
            float paddingY = height / fraction ;
            float paddingX = width / fraction ;
            if (screenX < paddingX) {
                if (boardCam.position.x > 0) {
                    float speed = topSpeed * amount * (paddingX - screenX) / paddingX;
                    boardCam.translate(-speed, 0);
                }
            } else if (screenX > width - paddingX) {
                if (boardCam.position.x < boardWidth) {
                    float speed = topSpeed * amount * (paddingX - (width - screenX)) / paddingX;
                    boardCam.translate(speed, 0);
                }
            }
            if (screenY < paddingY) {
                if (boardCam.position.y < boardHeight) {
                    float speed = topSpeed * amount * (paddingY - screenY) / paddingY;
                    boardCam.translate(0, speed);
                }
            } else if (screenY > height - paddingY) {
                if (boardCam.position.y > 0) {
                    float speed = topSpeed * amount * (paddingY - (height - screenY)) / paddingY;
                    boardCam.translate(0, -speed);
                }
            }
        }
    }
}
