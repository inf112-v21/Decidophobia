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
import inf112.skeleton.app.GUI.screen.*;

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
        TextButton startLocal = new TextButton("play solo", style);
        TextButton settings = new TextButton("settings", style);
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
        startLocal.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                menuScreen.screenManager.setScreen(new GameScreen());
                System.out.println("Starting simple singleplayer game");
            }
        });
        settings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.dispose();
                menuScreen.screenManager.setScreen(new SettingScreen());
                System.out.println("Go to settings");
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
                Gdx.app.exit();
            }
        });

        //Organization
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(start).expandX();
        table.row();
        table.add(startLocal).expandX();
        table.row();
        table.add(settings).expandX();
        table.row();
        table.add(exit).expandX();
        table.row();
        table.add(testGameButton).expandX();

        stage.addActor(table);
    }
}
