package inf112.skeleton.app.GUI.stages.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.screen.GameScreen;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.packets.PlayerInfo;


public class PlayersStage {
    public GameScreen gameScreen;
    public Stage stage;
    public Viewport viewport;

    RoboClient client;

    Label.LabelStyle labelStyle;

    TextureRegionDrawable playerTagTexture;
    TextureRegion[][] botBoardTextures;

    Table playerTagsTable;

    public PlayersStage(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        this.client = gameScreen.roboGame.networkClient;
        playerTagTexture = new TextureRegionDrawable(new Texture("src/assets/playerTag.png"));
        botBoardTextures = TextureRegion.split(new Texture("src/assets/botBoardTiles.png"), 300, 300);
        viewport = new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera());
        stage = new Stage(viewport);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.WHITE;

        playerTagsTable = new Table();
        playerTagsTable.setFillParent(true);
        playerTagsTable.left();

        stage.addActor(playerTagsTable);
    }
    public void updatePlayerTags(){
        playerTagsTable.clearChildren();
        playerTagsTable.left();
        PlayerInfo localPlayer = client.getLobbyInfo().getPlayers().get(client.getClientPlayerNr());
        playerTagSetup(localPlayer,0.5f);
        for(PlayerInfo playerInfo : client.getLobbyInfo().getPlayers().values()){
            if(playerInfo != localPlayer){
                playerTagsTable.row();
                playerTagSetup(playerInfo,0.3f);
            }
        }

    }

    public void playerTagSetup(PlayerInfo playerInfo, float scale){
        //Stacks information on top of frame
        Image tagFrame = new Image(playerTagTexture);
        tagFrame.setSize(tagFrame.getWidth()*scale,tagFrame.getHeight()*scale);
        tagFrame.setPosition(0,0);
        //Group contains all information about player.
        Group pInfo = new Group();

        Label nickName = new Label(playerInfo.getNickname(),labelStyle);
        nickName.setPosition(102*scale,50*scale);

        labelStyle.font.getData().setScale(2*scale);

        Image damageImage = new Image(botBoardTextures[1][2]);
        damageImage.setSize(40*scale,40*scale);
        damageImage.setPosition(110*scale,18*scale);

        Label dmg = new Label(": 9",labelStyle);
        dmg.setPosition(150*scale,20*scale);

        Image lifeImage = new Image(botBoardTextures[2][0]);
        lifeImage.setSize(40*scale,40*scale);
        lifeImage.setPosition(200*scale,18*scale);

        Label life = new Label(": 3",labelStyle);
        life.setPosition(240*scale,20*scale);

        //add all information to group
        pInfo.addActor(tagFrame);
        pInfo.addActor(nickName);
        pInfo.addActor(damageImage);
        pInfo.addActor(dmg);
        pInfo.addActor(lifeImage);
        pInfo.addActor(life);

        //stack information over frame
        playerTagsTable.add(pInfo).size(tagFrame.getWidth()*scale,tagFrame.getHeight()*scale);
    }
}
