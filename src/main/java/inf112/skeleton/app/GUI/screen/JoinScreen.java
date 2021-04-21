package inf112.skeleton.app.GUI.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.ScreenManager;

import javax.swing.text.LabelView;


public class JoinScreen implements Screen {
    ScreenManager screenManager;
    OrthographicCamera joinCam;
    Viewport joinPort;
    String message;

    Stage joinStage;

    public JoinScreen(ScreenManager screenManager){
        this.screenManager = screenManager;
        joinCam = new OrthographicCamera();
        joinPort = new StretchViewport(ScreenManager.V_WIDTH,ScreenManager.V_HEIGHT,joinCam);
    }
    public JoinScreen(ScreenManager screenManager, String message){
        this.screenManager = screenManager;
        joinCam = new OrthographicCamera();
        joinPort = new StretchViewport(ScreenManager.V_WIDTH,ScreenManager.V_HEIGHT,joinCam);
        this.message = message;
    }


    @Override
    public void show() {
        joinStage = new Stage();
        BitmapFont font = new BitmapFont();
        font.getData().setScale(5);

        Pixmap labelColor = new Pixmap(500, 60, Pixmap.Format.RGB888);
        labelColor.setColor(Color.GRAY);
        labelColor.fill();

        Pixmap cursorColor = new Pixmap(1, 30, Pixmap.Format.RGB888);
        cursorColor.setColor(Color.BLACK);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.RED;

        TextField.TextFieldStyle textStyle = new TextField.TextFieldStyle();
        textStyle.font = font;
        textStyle.fontColor = Color.BLACK;
        textStyle.cursor = new Image(new Texture(cursorColor)).getDrawable();
        textStyle.background = new Image(new Texture(labelColor)).getDrawable();

        TextField ipInput = new TextField("", textStyle);
        ipInput.setMessageText("ip-address");

        TextButton.TextButtonStyle buttonStyle =  new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.BLACK;
        buttonStyle.downFontColor = Color.GRAY;

        //Declearing buttons
        TextButton join = new TextButton("join", buttonStyle);
        TextButton host = new TextButton("host", buttonStyle);
        TextButton cancel = new TextButton("cancel", buttonStyle);

        //add Events
        join.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String ip = ipInput.getText();
                LobbyScreen lobbyScreen = new LobbyScreen(screenManager, ip);
                if(lobbyScreen.client.getLobbyInfo()!=null){
                    dispose();
                    screenManager.setScreen(lobbyScreen);
                }
            }
        });
        host.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                screenManager.setScreen(new LobbyScreen(screenManager));
            }
        });
        cancel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                screenManager.setScreen(new MenuScreen(screenManager));
            }
        });

        Pixmap tableColor = new Pixmap(1910, 1070, Pixmap.Format.RGB888);
        tableColor.setColor(Color.CYAN);
        tableColor.fill();

        Table joinTable = new Table();
        joinTable.setFillParent(true);
        joinTable.center();
        joinTable.add(join);
        joinTable.add(ipInput).size(500,120);
        joinTable.row();
        joinTable.add(host);
        joinTable.row();
        joinTable.add(cancel);
        if(message != null){
            Label mes = new Label(message, labelStyle);
            mes.setBounds(250, 50, mes.getWidth(),30);
            joinStage.addActor(mes);
        }

        joinStage.addActor(joinTable);

        Gdx.input.setInputProcessor(joinStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        screenManager.batch.setProjectionMatrix(joinStage.getCamera().combined);
        joinStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        joinStage.getViewport().update(width, height);
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
        joinStage.dispose();
    }
}
