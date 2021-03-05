package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cards {

    private final CardType type;
    private final int priority;
    private TextureRegion texture;
    public Cards(){
        type = null;
        priority = -1;
    }
    public Cards(CardType type, int priority) {
        TextureRegion[][] cardTextures = TextureRegion.split(new Texture("src/assets/cardTiles.png"), 380, 600);
        TextureRegion texture;
        switch (type) {
            case FORWARD_1:
                texture = cardTextures[0][0];
                break;
            case FORWARD_2:
                texture = cardTextures[0][1];
                break;
            case FORWARD_3:
                texture = cardTextures[0][2];
                break;
            case U_TURN:
                texture = cardTextures[0][3];
                break;
            case REVERSE:
                texture = cardTextures[0][4];
                break;
            case ROTATE_LEFT:
                texture = cardTextures[0][5];
                break;
            case ROTATE_RIGHT:
                texture = cardTextures[0][6];
                break;
            default:
                texture = cardTextures[0][7];


        }

        this.texture = texture;
        this.type = type;
        this.priority = priority;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public CardType getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }
}
