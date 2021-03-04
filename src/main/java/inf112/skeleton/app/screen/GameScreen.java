package inf112.skeleton.app.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import inf112.skeleton.app.Game;
import inf112.skeleton.app.Launcher;
import inf112.skeleton.app.board.Board;
import org.lwjgl.opengl.GL30;

public class GameScreen extends InputAdapter implements Screen {

    private static final int TILE_SIZE = 250;
    private final OrthoCachedTiledMapRenderer mapRenderer;
    private final Game game;
    private final TiledMap board;
    private final OrthographicCamera camera;
    private final Launcher launcher;


    public GameScreen(Board board, OrthoCachedTiledMapRenderer mapRenderer, Launcher launcher) {

        this.mapRenderer = mapRenderer;
        this.board = board.getBoard();
        this.launcher = launcher;
        game = new Game();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, board.getBoardWidth() * TILE_SIZE, board.getBoardHeight() * TILE_SIZE);
        camera.update();

        mapRenderer = new OrthoCachedTiledMapRenderer(board.getBoard());
        mapRenderer.setView(camera);

        Gdx.input.setInputProcessor(this);
        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();


    }

    @Override
    public void resize(int width, int height) {

        game.getBoard().resize(width, height);
        //stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
