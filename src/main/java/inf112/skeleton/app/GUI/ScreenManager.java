package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GUI.screen.MenuScreen;
import inf112.skeleton.app.Multiplayer.RoboServer;

public class ScreenManager extends Game {
    public SpriteBatch batch;
    public static final int V_WIDTH = 1920;
    public static final int V_HEIGHT = 1080;

    public static RoboServer server;

    private static ScreenManager screenManagerInstance = null;

    public static ScreenManager getInstance() {

        if(screenManagerInstance == null)
            screenManagerInstance = new ScreenManager();

        return screenManagerInstance;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        if(ScreenManager.server != null){
            ScreenManager.server.stopServer();
        }
        Gdx.app.exit();
    }

    @Override
    public void resize(int width, int height) {
         super.resize(width,height);
    }

    @Override
    public void pause() {
        //left empty
    }

    @Override
    public void resume() {
        //left empty
    }
}
