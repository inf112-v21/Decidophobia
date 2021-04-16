package inf112.skeleton.app.GUI.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.ScreenManager;
import inf112.skeleton.app.GUI.screen.GameScreen;
import inf112.skeleton.app.GameLogic;
import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.PlayerCards;

import java.util.HashMap;

public class GameStage {
    private GameScreen gameScreen;
    public Stage cardStage;
    Viewport viewport;

    GameLogic gl;

    HashMap<CardType,Image> cardImages;

    Table handCardsTable;

    public GameStage(GameScreen gameScreen, GameLogic gl){
        this.gameScreen = gameScreen;
        this.gl = gl;
        viewport = new FitViewport(ScreenManager.V_WIDTH,ScreenManager.V_HEIGHT);
        cardStage = new Stage(viewport);

        handCardsTable = new Table();
        handCardsTable.setFillParent(true);
        handCardsTable.bottom();

        TextureRegion[][] cardTextures = TextureRegion.split(new Texture("src/assets/cardTiles.png"), 380, 600);
        cardImages = new HashMap<>();
        cardImages.put(CardType.FORWARD_1,new Image(cardTextures[0][0]));
        cardImages.put(CardType.FORWARD_2,new Image(cardTextures[0][1]));
        cardImages.put(CardType.FORWARD_3,new Image(cardTextures[0][2]));
        cardImages.put(CardType.U_TURN,new Image(cardTextures[0][3]));
        cardImages.put(CardType.REVERSE,new Image(cardTextures[0][4]));
        cardImages.put(CardType.ROTATE_LEFT,new Image(cardTextures[0][5]));
        cardImages.put(CardType.ROTATE_RIGHT,new Image(cardTextures[0][6]));
        cardImages.put(CardType.CLOSED,new Image(cardTextures[0][7]));



        cardStage.addActor(handCardsTable);
    }
    public void updateCards(PlayerCards localCards){
        System.out.printf(localCards.toString() + " <-- komt hit");
        float scale = 0.5f;
        for(Cards card : localCards.getCardsInHand()){
            Image cardImg = cardImages.get(card.getType());
            cardImg.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    localCards.getCardsInHand().remove(card);
                    localCards.insertCard(card);
                    super.clicked(event, x, y);
                }
            });
            handCardsTable.add(cardImg).size(cardImg.getWidth()*scale,cardImg.getHeight()*scale);
        }
    }
}
