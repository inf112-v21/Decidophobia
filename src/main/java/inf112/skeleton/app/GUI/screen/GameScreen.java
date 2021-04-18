package inf112.skeleton.app.GUI.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.GUI.ScreenManager;
import inf112.skeleton.app.GUI.stages.Game.BoardStage;
import inf112.skeleton.app.GUI.stages.Game.CardStage;
import inf112.skeleton.app.GameLogic;
import inf112.skeleton.app.cards.PlayerCards;

public class GameScreen implements Screen {
    ScreenManager screenManager;
    GameLogic roboGame;

    CardStage cardStage;
    BoardStage boardStage;

    public GameScreen(ScreenManager screenManager, GameLogic roboGame) {
        this.screenManager = screenManager;
        this.roboGame = roboGame;
    }


    @Override
    public void show() {
        cardStage = new CardStage(this, roboGame);
        boardStage = new BoardStage(this, roboGame);

        roboGame.setGameGUI(this);
        Gdx.input.setInputProcessor(cardStage.cardStage);
        roboGame.dealCards();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        boardStage.boardCam.update();
        boardStage.mapRenderer.setView(boardStage.boardCam);
        boardStage.mapRenderer.render();
        screenManager.batch.setProjectionMatrix(boardStage.boardCam.combined);

        cardStage.cardStage.getCamera().update();
        screenManager.batch.setProjectionMatrix(cardStage.cardStage.getCamera().combined);
        cardStage.cardStage.draw();


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

    public void updateCards(PlayerCards clientsCards) {
        cardStage.updateCards(clientsCards);
    }

    public TiledMap getMap(){
        return boardStage.getBoardMap();
    }
}