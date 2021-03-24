package inf112.skeleton.app.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.GameGUI;

public class ScreenManager extends GameGUI implements ApplicationListener {

    private static ScreenManager screenManagerInstance = null;

    private ScreenManager() {
        super();
    }

    public static ScreenManager getInstance() {

        if(screenManagerInstance == null)
            screenManagerInstance = new ScreenManager();

        return screenManagerInstance;
    }

    @Override
    public void create() {
        super.setScreen(new MainMenuScreen());
        super.getScreen().show();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        //left empty
    }

    @Override
    public void resize(int width, int height) {
         //left empty
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
