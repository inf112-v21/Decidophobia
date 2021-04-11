package inf112.skeleton.app.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class ScreenManager extends Game {

    private static ScreenManager screenManagerInstance = null;

    public ScreenManager() {
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
