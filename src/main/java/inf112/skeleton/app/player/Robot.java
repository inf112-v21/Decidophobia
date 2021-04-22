package inf112.skeleton.app.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Action;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GameLogic;
import inf112.skeleton.app.cards.Cards;

import java.util.HashMap;
import java.util.Map;

import static inf112.skeleton.app.DirectionTools.directionToVector;
import static inf112.skeleton.app.DirectionTools.rotationToDirection;

public class Robot {
    private Vector2 robotPosition;
    private Direction facingDirection;
    private boolean activeRobot;
    private Map<Direction, TiledMapTileLayer.Cell> robotOrientationToTiles;
    private TiledMapTileLayer robotLayer;
    private int robotNr; //same as playerNr

    public Robot(Vector2 position, Direction facingDirection, String RobotImagePath, TiledMapTileLayer robotLayer){
        this.robotPosition = position;
        this.facingDirection = facingDirection;
        this.activeRobot = true;
        this.robotLayer = robotLayer;

        if(!(RobotImagePath==null)) {
            //Setting up texture for different player orientations
            TextureRegion[][] playerTexture = TextureRegion.split(new Texture(RobotImagePath), 300, 300);

            TiledMapTileLayer.Cell robotNorth = new TiledMapTileLayer.Cell();
            TiledMapTileLayer.Cell robotWest = new TiledMapTileLayer.Cell();
            TiledMapTileLayer.Cell robotSouth = new TiledMapTileLayer.Cell();
            TiledMapTileLayer.Cell robotEast = new TiledMapTileLayer.Cell();

            robotNorth.setTile(new StaticTiledMapTile(playerTexture[0][0]));
            robotWest.setTile(new StaticTiledMapTile(playerTexture[0][1]));
            robotSouth.setTile(new StaticTiledMapTile(playerTexture[0][2]));
            robotEast.setTile(new StaticTiledMapTile(playerTexture[0][3]));

            //Putting the textures in a map where orientation is the keys.
            robotOrientationToTiles = new HashMap<>();
            robotOrientationToTiles.put(Direction.NORTH, robotNorth);
            robotOrientationToTiles.put(Direction.WEST, robotWest);
            robotOrientationToTiles.put(Direction.SOUTH, robotSouth);
            robotOrientationToTiles.put(Direction.EAST, robotEast);
        }

        //Robot start position
        Vector2 pos = getPosition();
        robotLayer.setCell((int)pos.x,(int) pos.y, getPlayerTileCell());
    }

    public TiledMapTileLayer.Cell getPlayerTileCell(){
        return robotOrientationToTiles.get(facingDirection);
    }

    public boolean isActiveRobot() {
        return activeRobot;
    }

    public void setActiveRobot(boolean activeRobot) {
        this.activeRobot = activeRobot;
    }

    public void setPosition(Vector2 position) {
        this.robotPosition = position;
    }

    public Vector2 getPosition() {
        return new Vector2(robotPosition.x, robotPosition.y);
    }

    public void setFacingDirection(Direction facingDir) {
        this.facingDirection = facingDir; }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public int getRobotNr() {
        return robotNr;
    }

    public void setRobotNr(int robotNr) {
        this.robotNr = robotNr;
    }

    /**
     * Takes a card and moves the robot according to card dealt
     * @param card that defines which move to make
     */
    public void useCard(Cards card){
        int moveDelay = 500;
        // Removes sprite in current position
        robotLayer.setCell((int) getPosition().x,(int) getPosition().y,null);

        switch (card.getType()) {
            case FORWARD_1 :
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y,null);
                robotPosition.add(directionToVector(facingDirection));
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y, getPlayerTileCell());
                GameLogic.wait(moveDelay);
                break;

            case FORWARD_2:
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y,null);
                robotPosition.add(directionToVector(facingDirection));
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y, getPlayerTileCell());
                GameLogic.wait(moveDelay);
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y,null);
                robotPosition.add(directionToVector(facingDirection));
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y, getPlayerTileCell());
                GameLogic.wait(moveDelay);
                break;

            case FORWARD_3:
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y,null);
                robotPosition.add(directionToVector(facingDirection));
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y, getPlayerTileCell());
                GameLogic.wait(moveDelay);
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y,null);
                robotPosition.add(directionToVector(facingDirection));
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y, getPlayerTileCell());
                GameLogic.wait(moveDelay);
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y,null);
                robotPosition.add(directionToVector(facingDirection));
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y, getPlayerTileCell());
                GameLogic.wait(moveDelay);
                break;

            case REVERSE:
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y,null);
                robotPosition.sub(directionToVector(facingDirection));
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y, getPlayerTileCell());
                GameLogic.wait(moveDelay);
                break;

            case ROTATE_RIGHT:
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y,null);
                facingDirection = rotationToDirection(facingDirection,true);
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y, getPlayerTileCell());
                GameLogic.wait(moveDelay);
                break;

            case ROTATE_LEFT:
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y,null);
                facingDirection = rotationToDirection(facingDirection,false);
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y, getPlayerTileCell());
                GameLogic.wait(moveDelay);
                break;

            case U_TURN:
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y,null);
                facingDirection = (rotationToDirection(facingDirection, true));
                facingDirection = (rotationToDirection(facingDirection, true));
                robotLayer.setCell((int) getPosition().x,(int) getPosition().y, getPlayerTileCell());
                GameLogic.wait(moveDelay);
                break;
        }

        //Adds sprite in new position

    }

    //Needs to be removed with GameGUI Class
    public void move(Action move) {
        if(!activeRobot)
            return;
        switch (move) {
            case FORWARD:
                robotPosition.add(directionToVector(facingDirection));
                break;
            case REVERSE:
                robotPosition.sub(directionToVector(facingDirection));
                break;
            case ROTATE_LEFT:
                facingDirection = rotationToDirection(facingDirection,false);
                break;
            case ROTATE_RIGHT:
                facingDirection = rotationToDirection(facingDirection,true);
                break;
            case U_TURN:
                facingDirection = (rotationToDirection(facingDirection, true));
                facingDirection = (rotationToDirection(facingDirection, true));
                break;
        }
    }
}
