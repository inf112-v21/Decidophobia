package inf112.skeleton.app.GUI.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Launcher;

public class MainMenuScreen implements Screen {
    private ScreenManager screenManager;

    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Skin skin;

    public MainMenuScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Launcher.WIDTH, Launcher.HEIGHT, camera);
        viewport.apply();
        stage = new Stage(viewport, batch);
        skin = new Skin(Gdx.files.internal("skin.json"), atlas);
        atlas = new TextureAtlas("skin.atlas");

    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.top();

        TextButton gameButton = new TextButton("Play", skin);
        TextButton settingsButton = new TextButton("Setting", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        /*
        It will start the game,
        or take the player to the network screen to either join or host,
        or play have the player play singleplayer.
         */
        gameButton.addListener(new ClickListener() {

           public void clicked(InputEvent event, float x, float y) {
           }
        });

        //It will take the player to the setting screen
        settingsButton.addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Quits the game
        exitButton.addListener(new ClickListener() {

           public void clicked(InputEvent event, float x, float y) {
               Gdx.app.exit();
           }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 1);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
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

        skin.dispose();
        atlas.dispose();
    }
}
