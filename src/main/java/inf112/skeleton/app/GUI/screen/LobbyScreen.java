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
        clientJoin("localhost");

    }
    //Constructor for clients
    public LobbyScreen(ScreenManager screenManager, String ip){
        this.screenManager = screenManager;
        this.server = null;
        clientJoin(ip);
    }


    @Override
    public void show() {
        lobbyStage = new LobbyStage(this, client);
        lobbyStage.show();
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

    }

    @Override
    public void dispose() {
        if(server != null)
            server.stopServer();
        lobbyStage.stage.dispose();
    }

    public void clientJoin(String ip){
        client = new RoboClient(ip);
        client.join();
        try {
            for(int i = 0; i<5;i++){
                i = (client.getLobbyInfo() != null) ? 6 : i;
                Thread.sleep(1000);
            }
        } catch (Exception e) {}
    }
}
