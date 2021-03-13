package inf112.skeleton.app.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.Launcher;
import inf112.skeleton.app.board.Board;

public class GameScreen extends InputAdapter implements Screen {

    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Stage stage;
    private Board board;

    public GameScreen() {
        Gdx.graphics.setContinuousRendering(false);
    }

    public void Rendering() {
        resize(Launcher.WIDTH, Launcher.HEIGHT);

        batch = new SpriteBatch();
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.y -= 360;
        camera.update();
        renderer = new OrthogonalTiledMapRenderer(board.getBoard());
        renderer.setView(camera);
    }

    public void setUp() {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

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