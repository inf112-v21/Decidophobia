package inf112.skeleton.app.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.Launcher;


public class MainMenuScreen implements Screen {

    private final Stage stage;

    public MainMenuScreen() {
        stage = new Stage(new StretchViewport(Launcher.WIDTH, Launcher.HEIGHT));
    }

    @Override
    public void show() {

        Table mainMenuComponents = new Table();
        Skin mainMenuTexture = new Skin(Gdx.files.internal("UI.png"));

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
