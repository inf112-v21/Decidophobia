package inf112.skeleton.app.GUI.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.screen.LobbyScreen;
import inf112.skeleton.app.GUI.screen.ScreenManager;
import inf112.skeleton.app.Multiplayer.RoboClient;

public class LobbyStage {
    public Stage stage;
    private Viewport viewport;

    public LobbyStage(LobbyScreen lobbyScreen, SpriteBatch sb, RoboClient client) {
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);

        BitmapFont font = new BitmapFont();
        font.getData().setScale(10);

        TextButton.TextButtonStyle style =  new TextButton.TextButtonStyle();
        style.font = font;
        style.fontColor = Color.BLACK;
        style.downFontColor = Color.GRAY;

        TextButton ready = new TextButton("|_|",style);
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

        Table renderTable = new Table();
        renderTable.setFillParent(true);
        renderTable.add(ready);

        stage.addActor(renderTable);
    }
}
