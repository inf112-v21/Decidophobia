package inf112.skeleton.app.GUI.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.GUI.ScreenManager;
import inf112.skeleton.app.GUI.stages.Lobby.GameRuleStage;
import inf112.skeleton.app.GUI.stages.Lobby.LobbyStage;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.RoboServer;

public class LobbyScreen implements Screen {
    public ScreenManager screenManager;
    private LobbyStage lobbyStage;
    private GameRuleStage gameRuleStage;

    boolean startGame;


    public RoboClient client;


    //Constructor for hosts
    public LobbyScreen(ScreenManager screenManager){
        startGame = false;
        this.screenManager = screenManager;
        ScreenManager.server = new RoboServer();
        ScreenManager.server.runServer();
        clientJoin(RoboServer.getLANIp());

    }
    //Constructor for clients
    public LobbyScreen(ScreenManager screenManager, String ip){
        this.screenManager = screenManager;
        clientJoin(ip);
    }


    @Override
    public void show() {
        //connects client-object to screen
        client.setLobbyScreen(this);
        lobbyStage = new LobbyStage(this);
        lobbyStage.show();
        gameRuleStage = new GameRuleStage(this);

        InputMultiplexer inputs = new InputMultiplexer();
        inputs.addProcessor(lobbyStage.stage);
        inputs.addProcessor(gameRuleStage.stage);
        Gdx.input.setInputProcessor(inputs);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!client.hostOnline){
            destroyLobby();
            return;
        }
        if(startGame){
            lobbyStage.startGame();
            return;
        } else {
            lobbyStage.stage.getCamera().update();
            screenManager.batch.setProjectionMatrix(lobbyStage.stage.getCamera().combined);
            lobbyStage.stage.draw();

            gameRuleStage.stage.getCamera().update();
            screenManager.batch.setProjectionMatrix(gameRuleStage.stage.getCamera().combined);
            gameRuleStage.stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        lobbyStage.stage.getViewport().update(width, height);
        gameRuleStage.stage.getViewport().update(width, height);
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
        lobbyStage.stage.dispose();
        gameRuleStage.stage.dispose();
    }

    public void clientJoin(String ip){
        client = new RoboClient();
        client.join(ip);
        try {
            for(int i = 0; i<5;i++){
                i = (client.getLobbyInfo() != null) ? 6 : i;
                Thread.sleep(1000);
            }
        } catch (Exception e) {}
    }

    public void startGame() {
        startGame = true;
    }

    public void updatePlayerTable() {
        lobbyStage.updatePlayerTable();

    }

    public void destroyLobby() {
        client.clientStop();
        dispose();
        screenManager.setScreen(new JoinScreen(screenManager, "host quit the lobby"));
    }

    public void updateRulesTable() {
        gameRuleStage.updateGameRulesGroup();
    }
}
