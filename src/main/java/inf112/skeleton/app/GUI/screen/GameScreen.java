package inf112.skeleton.app.GUI.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.GUI.ScreenManager;
import inf112.skeleton.app.GUI.stages.GameStage;
import inf112.skeleton.app.GameLogic;

public class GameScreen implements Screen {
    ScreenManager screenManager;
    GameLogic roboGame;

    GameStage gameStage;

    public GameScreen(ScreenManager screenManager, GameLogic roboGame) {
        this.screenManager = screenManager;
        this.roboGame = roboGame;
    }


    @Override
    public void show() {
        gameStage = new GameStage(this, roboGame);
        roboGame.setStage(gameStage);
        Gdx.input.setInputProcessor(gameStage.cardStage);
        roboGame.dealCards();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        screenManager.batch.setProjectionMatrix(gameStage.cardStage.getCamera().combined);
        gameStage.cardStage.draw();


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