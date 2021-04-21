package inf112.skeleton.app.GUI.stages.Game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.ScreenManager;
import inf112.skeleton.app.GUI.screen.GameScreen;
import inf112.skeleton.app.GameLogic;
import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.Cards;
import inf112.skeleton.app.cards.PlayerCards;

import java.util.HashMap;

public class CardStage {
    public GameScreen gameScreen;
    public Stage cardStage;
    Viewport viewport;

    GameLogic gl;

    HashMap<CardType, TextureRegionDrawable> cardImages;
    Table handCardsTable;

    BitmapFont font;
    Label.LabelStyle labelStyle;
    TextButton.TextButtonStyle buttonStyle;

    public CardStage(GameScreen gameScreen, GameLogic gl){
        this.gameScreen = gameScreen;
        this.gl = gl;
        viewport = new ExtendViewport(ScreenManager.V_WIDTH,ScreenManager.V_HEIGHT,new OrthographicCamera());
        cardStage = new Stage(viewport);

        font = new BitmapFont();
        font.getData().setScale(3);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        buttonStyle =  new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont();
        buttonStyle.fontColor = Color.WHITE;
        buttonStyle.font.getData().setScale(1.3f);

        //Set up cardTextures
        TextureRegion[][] cardTextures = TextureRegion.split(new Texture("src/assets/cardTiles.png"), 380, 600);
        cardImages = new HashMap<>();
        cardImages.put(CardType.FORWARD_1,new TextureRegionDrawable(cardTextures[0][0]));
        cardImages.put(CardType.FORWARD_2,new TextureRegionDrawable(cardTextures[0][1]));
        cardImages.put(CardType.FORWARD_3,new TextureRegionDrawable(cardTextures[0][2]));
        cardImages.put(CardType.U_TURN,new TextureRegionDrawable(cardTextures[0][3]));
        cardImages.put(CardType.REVERSE,new TextureRegionDrawable(cardTextures[0][4]));
        cardImages.put(CardType.ROTATE_LEFT,new TextureRegionDrawable(cardTextures[0][5]));
        cardImages.put(CardType.ROTATE_RIGHT,new TextureRegionDrawable(cardTextures[0][6]));
        cardImages.put(CardType.CLOSED,new TextureRegionDrawable(cardTextures[0][7]));

        handCardsTable = new Table();
        handCardsTable.setFillParent(true);
        handCardsTable.bottom();

        cardStage.addActor(handCardsTable);

    }
    public void updateCards(PlayerCards localCards){
        System.out.println(localCards.toString() + " <-- komt hit");
        handCardsTable.clearChildren();
        float scale = 0.3f;
        //Sets up dealt cards and make them clickable
        handCardsTable.add(new Label("hand:",labelStyle)).padRight(50);
        for(Cards card : localCards.getCardsInHand()) {
            Stack cardStack = new Stack();
            Image cardImg = new Image(cardImages.get(card.getType()));
            TextButton cardButton = setupCardButton(card.getPriority()+"", cardImg);
            cardButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    localCards.setLastActiveCard(card);
                    if(localCards.getActiveCards().size() == 5){
                        System.out.println("moves sent");
                        handCardsTable.clearChildren();
                        gl.networkClient.sendMoves(localCards);

                    }else{
                        updateCards(localCards);
                    }
                    super.clicked(event, x, y);
                }
            });
            cardStack.add(cardImg);
            cardStack.add(cardButton);
            handCardsTable.add(cardStack).size(cardImg.getWidth()*scale,cardImg.getHeight()*scale);

        }
        handCardsTable.add(new Label("chosen:",labelStyle)).padRight(50);
        for(Cards card : localCards.getActiveCards()){
            System.out.println("activeCard: " + card);
            Stack cardStack = new Stack();
            Image cardImg = new Image(cardImages.get(card.getType()));
            TextButton cardButton = setupCardButton(card.getPriority()+"", cardImg);
            cardButton.addListener(new ClickListener() {
                   @Override
                   public void clicked(InputEvent event, float x, float y) {
                       if (!localCards.getLockedCards().contains(card)) cardImg.addListener(new ClickListener() {
                           @Override
                           public void clicked(InputEvent event, float x, float y) {
                               localCards.removeActiveCard(localCards.getIndexOfActiveCard(card));
                               updateCards(localCards);
                               super.clicked(event, x, y);
                           }
                       });

                   }
               });
            cardStack.add(cardImg);
            cardStack.add(cardButton);
            handCardsTable.add(cardStack).size(cardImg.getWidth() * scale, cardImg.getHeight() * scale);
        }

    }
    public TextButton setupCardButton(String cardPriority, Image cardImg){
        TextButton cardButton = new TextButton(cardPriority,buttonStyle);
        cardButton.setSize(cardImg.getWidth(),cardImg.getHeight());
        cardButton.getLabelCell().padBottom(130).padLeft(45);
        return cardButton;
    }
}
