package inf112.skeleton.app.GUI.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.GUI.stages.LobbyStage;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.RoboServer;

public class LobbyScreen implements Screen {
    public ScreenManager screenManager;
    private LobbyStage lobbyStage;

    public RoboServer server;

    public RoboClient client;

    //Constructor for hosts
    public LobbyScreen(ScreenManager screenManager){
        this.screenManager = screenManager;
        server = new RoboServer();
        server.runServer();
        clientJoin();

    }
    //Constructor for clients
    public LobbyScreen(ScreenManager screenManager, String ip){
        this.screenManager = screenManager;
        clientJoin();
    }


    @Override
    public void show() {
        lobbyStage = new LobbyStage(this, screenManager.batch, client);
        Gdx.input.setInputProcessor(lobbyStage.stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        screenManager.batch.setProjectionMatrix(lobbyStage.stage.getCamera().combined);
        lobbyStage.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        lobbyStage.stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        System.out.println("I'm hiding");
    }

    @Override
    public void dispose() {
        lobbyStage.stage.dispose();
    }

    public void clientJoin(){
        client = new RoboClient("localhost");
        client.join();
        try {
            for(int i = 0; i<5;i++){
                i = client.getClientPlayerNr() != 0 ? 6 : i;
                Thread.sleep(1000);
            }
        } catch (Exception e) {}
    }
}
