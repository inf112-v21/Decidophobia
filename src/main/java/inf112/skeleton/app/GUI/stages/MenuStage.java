package inf112.skeleton.app.GUI.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.ScreenManager;
import inf112.skeleton.app.GUI.screen.*;
import inf112.skeleton.app.GameLogic;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.RoboServer;
import inf112.skeleton.app.Multiplayer.packets.GameRules;

public class MenuStage {
    public Stage stage;
    private Viewport viewport;
    public MenuStage(MenuScreen menuScreen, SpriteBatch sb){
        viewport = new FitViewport(ScreenManager.V_WIDTH,ScreenManager.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);

        BitmapFont font = new BitmapFont();

        TextButton.TextButtonStyle style =  new TextButton.TextButtonStyle();
        style.font = font;
        style.font.getData().setScale(10);
        style.fontColor = Color.BLACK;
        style.downFontColor = Color.GRAY;
        //TODO set color when mouse over.

        //Declearing buttons
        TextButton start = new TextButton("play multiplayer", style);
        TextButton exit = new TextButton("quit", style);
        //Remove on launch
        TextButton testGameButton = new TextButton("test game locally", style);

        //add Events
        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                menuScreen.screenManager.setScreen(new JoinScreen(menuScreen.screenManager));
                System.out.println("Starting Multiplayer");
            }
        });
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        testGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                ScreenManager.server = new RoboServer();
                ScreenManager.server.runServer();
                RoboClient client = new RoboClient();
                client.join(RoboServer.getLANIp());
                GameRules rules = new GameRules(3,9,"src/assets/boards/vault.tmx");
                client.sendRules(rules);
                while(client.getGameRules() == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ScreenManager.server.gameStarted = true;
                GameLogic gl = new GameLogic(client);
                client.setGameReference(gl);
                menuScreen.screenManager.setScreen(new GameScreen(menuScreen.screenManager,gl));
                System.out.println("Starting testenvirourmeant");
            }
        });

        //Organization
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(start).expandX();;
        table.row();
        table.add(exit).expandX();
        table.row();
        table.add(testGameButton).expandX();

        stage.addActor(table);
    }
}
