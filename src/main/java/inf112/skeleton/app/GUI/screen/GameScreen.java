package inf112.skeleton.app.GUI.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import inf112.skeleton.app.GUI.ScreenManager;
import inf112.skeleton.app.GUI.stages.Game.BoardStage;
import inf112.skeleton.app.GUI.stages.Game.CardStage;
import inf112.skeleton.app.GameLogic;
import inf112.skeleton.app.cards.PlayerCards;

public class GameScreen implements Screen {
    public ScreenManager screenManager;
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
        boardStage = new BoardStage();

        roboGame.setGameGUI(this);
        InputMultiplexer inputs = new InputMultiplexer();
        inputs.addProcessor(cardStage.cardStage);
        inputs.addProcessor(boardStage);
        Gdx.input.setInputProcessor(inputs);
        roboGame.dealCards();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        boardStage.moveBoard(Gdx.input.getX(),Gdx.input.getY());

        boardStage.boardCam.update();
        boardStage.mapRenderer.setView(boardStage.boardCam);
        boardStage.mapRenderer.render();
        screenManager.batch.setProjectionMatrix(boardStage.boardCam.combined);

        cardStage.cardStage.getCamera().update();
        screenManager.batch.setProjectionMatrix(cardStage.cardStage.getCamera().combined);
        screenManager.batch.begin();
        for(Actor act : cardStage.cardStage.getActors()){
            if(act instanceof TextButton){
                ((TextButton) act).getBackground().draw(screenManager.batch, act.getX(), act.getY(),act.getWidth(),act.getHeight());
            }
        }
        screenManager.batch.end();
        cardStage.cardStage.draw();





    }

    @Override
    public void resize(int width, int height) {
        cardStage.cardStage.getViewport().update(width,height);
        boardStage.boardCam.setToOrtho(false,width,height);

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
        cardStage.cardStage.dispose();
        boardStage = null;

    }

    public void updateCards(PlayerCards clientsCards) {
        cardStage.updateCards(clientsCards);
    }

    public TiledMap getMap(){
        return boardStage.getBoardMap();
    }
}