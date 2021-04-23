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
    OrthographicCamera camera;

    GameLogic gl;
    public  PlayerCards localCards;

    HashMap<CardType, TextureRegionDrawable> cardImages;
    TextureRegion powerDown;
    Group handCardsGroup;

    BitmapFont font;
    Label.LabelStyle labelStyle;
    TextButton.TextButtonStyle buttonStyle;

    public CardStage(GameScreen gameScreen, GameLogic gl){
        this.gameScreen = gameScreen;
        this.gl = gl;
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(ScreenManager.V_WIDTH,ScreenManager.V_HEIGHT,camera);
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


        //PowerDown texture
        powerDown = (new TextureRegion().split(new Texture("src/assets/botBoardTiles.png"), 300, 300))[0][0];
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

        handCardsGroup = new Group();
        handCardsGroup.setSize(camera.viewportWidth,camera.viewportHeight);
        handCardsGroup.setPosition(0,0);

        cardStage.addActor(handCardsGroup);

    }
    public void updateCards(PlayerCards localCards){
        if(localCards != null) {
            this.localCards = localCards;
            handCardsGroup.clearChildren();
            handCardsGroup.setSize(camera.viewportWidth,camera.viewportHeight);
            float scale = 0.3f;
            int size = localCards.size();
            float totalWidth = (600+380*(2+size))*scale;
            float xDrawn = camera.position.x-totalWidth/2;
            float bottomEdge = camera.position.y-camera.viewportHeight/2;
            Image powerDownCard = new Image(powerDown);
            powerDownCard.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    localCards.removeAllCards();
                    handCardsGroup.clearChildren();
                    gl.networkClient.sendMoves(localCards);
                    super.clicked(event, x, y);
                }
            });
            powerDownCard.setBounds(xDrawn,bottomEdge,600*scale,600*scale);
            xDrawn += 600*scale;

            handCardsGroup.addActor(powerDownCard);
            //Sets up dealt cards and make them clickable
            Label handText = new Label("hand:", labelStyle);
            handText.setBounds(xDrawn,bottomEdge,380*scale,600*scale);
            xDrawn += 380*scale;

            handCardsGroup.addActor(handText);
            //Sets up the cards to be chosen.
            for (Cards card : localCards.getCardsInHand()) {
                Stack cardStack = new Stack();
                Image cardImg = new Image(cardImages.get(card.getType()));
                TextButton cardButton = setupCardButton(card.getPriority() + "", cardImg);
                cardButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        localCards.setLastActiveCard(card);
                        if (localCards.getActiveCards().size() == 5) {
                            handCardsGroup.clearChildren();
                            gl.networkClient.sendMoves(localCards);

                        } else {
                            updateCards(localCards);
                        }
                        super.clicked(event, x, y);
                    }
                });
                cardStack.add(cardImg);
                cardStack.add(cardButton);
                cardStack.setBounds(xDrawn,bottomEdge,380*scale,600*scale);
                xDrawn += 380*scale;
                handCardsGroup.addActor(cardStack);

            }
            Label chosenText = new Label("chosen:", labelStyle);
            chosenText.setBounds(xDrawn,bottomEdge,380*scale,600*scale);
            xDrawn += 380*scale;
            handCardsGroup.addActor(chosenText);
            for (Cards card : localCards.getActiveCards()) {
                Stack cardStack = new Stack();
                Image cardImg = new Image(cardImages.get(card.getType()));
                TextButton cardButton = setupCardButton(card.getPriority() + "", cardImg);
                cardButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                            localCards.removeActiveCard(localCards.getIndexOfActiveCard(card));
                            updateCards(localCards);
                            super.clicked(event, x, y);

                    }
                });
                cardStack.add(cardImg);
                cardStack.add(cardButton);
                cardStack.setBounds(xDrawn,bottomEdge,380*scale,600*scale);
                xDrawn += 380*scale;
                handCardsGroup.addActor(cardStack);
            }

        }

    }
    public TextButton setupCardButton(String cardPriority, Image cardImg){
        TextButton cardButton = new TextButton(cardPriority,buttonStyle);
        cardButton.setSize(cardImg.getWidth(),cardImg.getHeight());
        cardButton.getLabelCell().padBottom(130).padLeft(45);
        return cardButton;
    }
}
