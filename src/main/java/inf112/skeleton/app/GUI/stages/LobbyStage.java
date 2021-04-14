package inf112.skeleton.app.GUI.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.screen.LobbyScreen;
import inf112.skeleton.app.GUI.screen.MenuScreen;
import inf112.skeleton.app.GUI.screen.ScreenManager;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.packets.PlayerInfo;


public class LobbyStage {
    public Stage stage;
    private Viewport viewport;

    private LobbyScreen lobbyScreen;

    private Table playerTable;

    private RoboClient client;

    private TextButton.TextButtonStyle buttonStyle;
    private Label.LabelStyle labelStyle;
    private TextField.TextFieldStyle textStyle;

    BitmapFont font;

    public LobbyStage(LobbyScreen lobbyScreen, RoboClient client) {
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);
        this.client = client;
        this.lobbyScreen = lobbyScreen;

    }
    public void show(){
        //connects client-object to screen
        client.setLobbyStage(this);
        //Sets up style
        font = new BitmapFont();
        font.getData().setScale(3);

        buttonStyle =  new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.BLACK;
        buttonStyle.downFontColor = Color.GRAY;

        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK;

        Pixmap labelColor = new Pixmap(500, 60, Pixmap.Format.RGB888);
        labelColor.setColor(Color.GRAY);

        Pixmap fieldColor = new Pixmap(500, 60, Pixmap.Format.RGB888);
        fieldColor.setColor(Color.GRAY);
        fieldColor.fill();

        Pixmap cursorColor = new Pixmap(2, 30, Pixmap.Format.RGB888);
        cursorColor.setColor(Color.BLACK);

        textStyle = new TextField.TextFieldStyle();
        textStyle.font = font;
        textStyle.fontColor = Color.BLACK;
        textStyle.messageFontColor = Color.BLACK;
        textStyle.cursor = new Image(new Texture(cursorColor)).getDrawable();
        textStyle.background = new Image(new Texture(fieldColor)).getDrawable();

        //adds button to quit lobby
        TextButton quit = new TextButton("quit", buttonStyle);

        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                client.quit();
                lobbyScreen.dispose();
                lobbyScreen.screenManager.setScreen(new MenuScreen(lobbyScreen.screenManager));
            }
        });

        //The table that shows the players
        updatePlayerTable();

        Table renderTable = new Table();
        renderTable.setFillParent(true);
        renderTable.left();
        renderTable.add(playerTable);

        renderTable.bottom();
        renderTable.add(quit);

        stage.addActor(renderTable);
    }
    public void updatePlayerTable(){
        playerTable = new Table();
        playerTable.setFillParent(true);
        playerTable.top();
        playerTable.add(new Label("Player",labelStyle)).padRight(10).expandX();
        playerTable.add(new Label("Host",labelStyle)).padRight(10).expandX();
        playerTable.add(new Label("Ready",labelStyle)).padRight(10).expandX();
        playerTable.row();

        TextField localNick = new TextField("", textStyle);


        //Draws playerTable in lobby
        for(PlayerInfo pl : this.client.getLobbyInfo().getPlayers().values()){
            //Local player
            if(client.getClientPlayerNr()==pl.getPlayerNr()){
                localNick.setMessageText(pl.getNickname());

                TextButton changeNick = new TextButton("edit", buttonStyle);

                changeNick.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if(!localNick.getText().equals("")){
                            System.out.println("this is it");
                            client.sendNick(localNick.getText());
                            localNick.next(true);
                        }
                    }
                });
                Table together = new Table();
                together.add(localNick).expandX();
                together.add(changeNick);
                playerTable.add(together).padRight(10).expandX();
                playerTable.add(new Label(pl.getHost() ? "x" : "", labelStyle)).expandX();
                TextButton ready = new TextButton(pl.getReady() ? "|v|" : "|_|", buttonStyle);
                playerTable.add(ready).expandX();
                ready.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if(client.getLobbyInfo().playerIsReady(client.getClientPlayerNr())){
                            client.unReady();
                            ready.setText("|_|");
                        } else {
                            client.ready();
                            ready.setText("|v|");
                        }
                    }
                });
                //Other players
            } else{
                playerTable.add(new Label(pl.getNickname(),labelStyle)).padRight(10).expandX();
                playerTable.add(new Label(pl.getHost() ? "x" : "", labelStyle)).padRight(10).expandX();
                playerTable.add(new Label(pl.getReady() ? "|v|" : "|_|", labelStyle)).padRight(10).expandX();
            }
            playerTable.row();
        }
    }
}
