package inf112.skeleton.app.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.Game;
import inf112.skeleton.app.GameGUI;

public class ScreenManager extends GameGUI implements ApplicationListener {

    private static ScreenManager screenManager = null;
    private ScreenManager() {
        super();
    }

    public static ScreenManager getInstance() {

        if(screenManager == null)
            screenManager = new ScreenManager();
        return screenManager;
    }

    public void create() {

        //super.setScreen(new MainMenuScreen());
        super.getScreen().show();
    }

    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        super.getScreen().render(Gdx.graphics.getDeltaTime());
    }

    public void dispose() {
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {

    }

    public void setScreen() {
    }
}
