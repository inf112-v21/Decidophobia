package inf112.skeleton.app.GUI.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.GUI.ScreenManager;
import inf112.skeleton.app.GUI.stages.MenuStage;

public class MenuScreen implements Screen {
    public ScreenManager screenManager;
    private MenuStage menuStage;


    public MenuScreen(ScreenManager screenManager){
        this.screenManager = screenManager;

    }
    @Override
    public void show() {
        menuStage = new MenuStage(this, screenManager.batch);
        Gdx.input.setInputProcessor(menuStage.stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        screenManager.batch.setProjectionMatrix(menuStage.stage.getCamera().combined);
        menuStage.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        menuStage.stage.getViewport().update(width, height);
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
        menuStage.stage.dispose();
    }
}
