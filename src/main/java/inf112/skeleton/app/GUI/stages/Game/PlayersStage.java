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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.screen.GameScreen;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.packets.PlayerInfo;
import inf112.skeleton.app.player.Robot;


public class PlayersStage {
    public GameScreen gameScreen;
    public Stage stage;
    public Viewport viewport;
    OrthographicCamera pCam;

    RoboClient client;

    Label.LabelStyle labelStyle;

    TextureRegionDrawable playerTagTexture;
    TextureRegion[][] botBoardTextures;

    Group playerTagsTable;

    public PlayersStage(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        this.client = gameScreen.roboGame.networkClient;
        playerTagTexture = new TextureRegionDrawable(new Texture("src/assets/playerTag.png"));
        botBoardTextures = TextureRegion.split(new Texture("src/assets/botBoardTiles.png"), 300, 300);
        pCam = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),pCam);
        stage = new Stage(viewport);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.WHITE;

        playerTagsTable = new Group();

        stage.addActor(playerTagsTable);
    }
    public void updatePlayerTags(){
        playerTagsTable.clearChildren();
        PlayerInfo localPlayer = client.getLobbyInfo().getPlayers().get(client.getClientPlayerNr());
        int index = 0;
        playerTagSetup(localPlayer,0.5f,index);
        for(PlayerInfo playerInfo : client.getLobbyInfo().getPlayers().values()){
            if(playerInfo != localPlayer){
                index++;
                playerTagSetup(playerInfo,0.4f,index);
            }
        }

    }

    public void playerTagSetup(PlayerInfo playerInfo, float scale, int index){
        Robot playerRobot = gameScreen.roboGame.robots.get(playerInfo.getPlayerNr());

        //Group contains all available information about players.
        Group pInfo = new Group();

        Image tagFrame = new Image(playerTagTexture);
        tagFrame.setSize(tagFrame.getWidth()*scale,tagFrame.getHeight()*scale);
        tagFrame.setPosition(0,0);

        Image robotIcon = new Image(playerRobot.getPlayerTileCell().getTile().getTextureRegion());
        robotIcon.setBounds(22*scale,22*scale,60*scale,60*scale);

        labelStyle.font.getData().setScale(2*scale);

        Label nickName = new Label(playerInfo.getNickname(),labelStyle);
        nickName.setPosition(102*scale,50*scale);

        Image damageImage = new Image(botBoardTextures[1][2]);
        damageImage.setSize(40*scale,40*scale);
        damageImage.setPosition(110*scale,18*scale);

        Label dmg = new Label(": "+playerRobot.getDamageTokens(),labelStyle);
        dmg.setPosition(150*scale,20*scale);

        Image lifeImage = new Image(botBoardTextures[2][0]);
        lifeImage.setSize(40*scale,40*scale);
        lifeImage.setPosition(200*scale,18*scale);

        Label life = new Label(": "+playerRobot.getLifeTokens(),labelStyle);
        life.setPosition(240*scale,20*scale);

        Image powerDown = new Image(botBoardTextures[0][0]);
        powerDown.setSize(70*scale,70*scale);
        powerDown.setPosition(50*scale,-18*scale);

        //add all information to group
        pInfo.addActor(robotIcon);
        pInfo.addActor(tagFrame);
        pInfo.addActor(nickName);
        pInfo.addActor(damageImage);
        pInfo.addActor(dmg);
        pInfo.addActor(lifeImage);
        pInfo.addActor(life);
        if(playerRobot.isPowerDown()) pInfo.addActor(powerDown);

        pInfo.setPosition(pCam.position.x-pCam.viewportWidth/2 +5,pCam.position.y+pCam.viewportHeight/2-55-index*50);

        //stack information over frame
        playerTagsTable.addActor(pInfo);
    }
}
