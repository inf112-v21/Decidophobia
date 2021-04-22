package inf112.skeleton.app.GUI.stages.Lobby;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.ScreenManager;
import inf112.skeleton.app.GUI.screen.LobbyScreen;
import inf112.skeleton.app.Multiplayer.RoboClient;
import inf112.skeleton.app.Multiplayer.packets.GameRules;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameRuleStage {
    public Stage stage;
    private Viewport viewport;

    boolean isHost;

    private LobbyScreen lobbyScreen;
    Group gameRulesGroup;

    private RoboClient client;

    private TextButton.TextButtonStyle buttonStyle;
    private Label.LabelStyle labelStyle;
    private TextField.TextFieldStyle textStyle;

    List<String> boardFiles;
    String boardFolderPath;

    BitmapFont font;

    public GameRuleStage(LobbyScreen lobbyScreen) {
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);
        this.client = lobbyScreen.client;
        isHost = client.getClientPlayerNr() == 0;
        this.lobbyScreen = lobbyScreen;
        gameRulesGroup = new Group();
        stage.addActor(gameRulesGroup);

        boardFolderPath = "src/assets/boards";
        final File folder = new File(boardFolderPath);
        boardFiles = listFilesForFolder(folder);

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
        cursorColor.fill();

        textStyle = new TextField.TextFieldStyle();
        textStyle.font = font;
        textStyle.fontColor = Color.BLACK;
        textStyle.messageFontColor = Color.BLACK;
        textStyle.cursor = new Image(new Texture(cursorColor)).getDrawable();
        textStyle.background = new Image(new Texture(fieldColor)).getDrawable();

        if(isHost){
            int damage = client.getGameRules().getDamageTokens();
            int life = client.getGameRules().getLifeTokens();
            String path = boardFolderPath+"/"+boardFiles.get(0);
            GameRules newRules = new GameRules(life,damage,path);
            client.sendRules(newRules);
        }


    }
    public void updateGameRulesGroup(){
        gameRulesGroup.clearChildren();
        labelStyle.font.getData().setScale(4);
        Label heading = new Label("GAMERULES",labelStyle);
        heading.setPosition(1500,1000);


        labelStyle.font.getData().setScale(3);
        Label damageTokens = new Label("dmg :  ",labelStyle);
        damageTokens.setPosition(1500,900);

        Label lifeTokens = new Label("lifes :  ",labelStyle);
        lifeTokens.setPosition(1500,800);

        Label map = new Label("board :  ",labelStyle);
        map.setPosition(1500, 700);

        Actor countDamageTokens;
        Actor countLifeTokens;
        Actor chosenMap;
        Actor applyChanges;
        //Sets up GameRules (it is editable for the host but not others)
        if(!isHost){
            countDamageTokens = new Label(client.getGameRules().getDamageTokens()+"",labelStyle);

            countLifeTokens = new Label(client.getGameRules().getLifeTokens()+"",labelStyle);

            chosenMap = new Label(client.getGameRules().getBoardPath().substring(boardFolderPath.length()+1),labelStyle);

            applyChanges = new Label("host chooses rules", labelStyle);
        }else{
            countDamageTokens = new TextField(client.getGameRules().getDamageTokens()+"",textStyle);

            countLifeTokens = new TextField(client.getGameRules().getLifeTokens()+"",textStyle);

            //rules.add(chosenMap).padLeft(4).expandX();
            chosenMap = new TextButton(client.getGameRules().getBoardPath().substring(boardFolderPath.length()+1),buttonStyle);
            chosenMap.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Group boards = new Group();
                    Pixmap boxFormat = new Pixmap(300, 50*boardFiles.size(), Pixmap.Format.RGB888);
                    boxFormat.setColor(Color.GRAY);
                    boxFormat.fill();
                    Image box = new Image(new Texture(boxFormat));
                    box.setPosition(1650,750-50*boardFiles.size());
                    boards.addActor(box);
                    for(int i = 0; i<boardFiles.size(); i++){
                        TextButton selectFile = new TextButton(boardFiles.get(i),buttonStyle);
                        selectFile.addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event1, float x, float y){
                                boards.clearChildren();
                                ((TextButton) chosenMap).setLabel(selectFile.getLabel());
                            }
                        });
                        selectFile.setPosition(chosenMap.getX()+50, chosenMap.getY()-50*i);
                        selectFile.setSize(100,50);
                        boards.addActor(selectFile);
                        gameRulesGroup.addActor(boards);
                    }
                    super.clicked(event, x, y);
                }
            });
            applyChanges = new TextButton("apply rules", buttonStyle);
            applyChanges.setSize(300,50);
            applyChanges.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    GameRules newRules;
                    try{
                        int damage = Integer.parseInt(((TextField)countDamageTokens).getText());
                        int life = Integer.parseInt(((TextField)countLifeTokens).getText());
                        String path = boardFolderPath+"/"+((TextButton)chosenMap).getText();
                        newRules = new GameRules(life,damage,path);
                        client.sendRules(newRules);

                    }catch (Exception e){}
                    super.clicked(event, x, y);
                }
            });

        }
        countDamageTokens.setPosition(1700,900);
        countLifeTokens.setPosition(1700,800);
        chosenMap.setPosition(1700,700);
        applyChanges.setPosition(1500, 600);
        gameRulesGroup.addActor(heading);
        gameRulesGroup.addActor(damageTokens);
        gameRulesGroup.addActor(countDamageTokens);
        gameRulesGroup.addActor(lifeTokens);
        gameRulesGroup.addActor(countLifeTokens);
        gameRulesGroup.addActor(map);
        gameRulesGroup.addActor(applyChanges);

        gameRulesGroup.addActor(chosenMap);

    }

    public List<String> listFilesForFolder(final File folder) {
        List<String> fileList = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                fileList.add(fileEntry.getName());
            }
        }
        return fileList;
    }

}
