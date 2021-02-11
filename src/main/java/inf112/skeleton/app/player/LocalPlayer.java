package inf112.skeleton.app.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Action;
import inf112.skeleton.app.Direction;

import java.util.HashMap;
import java.util.Map;

import static inf112.skeleton.app.DirectionTools.directionToVector;
import static inf112.skeleton.app.DirectionTools.rotationToDirection;

public class LocalPlayer{
    private Vector2 playerPosition;
    private Direction facingDirection;
    private boolean activePlayer;
    private Map<Direction, TiledMapTileLayer.Cell> playerOrientationToTiles;

    public LocalPlayer(Vector2 position, Direction facingDirection, String playerImagePath){
        this.playerPosition = position;
        this.facingDirection = facingDirection;
        this.activePlayer=true;

        if(!(playerImagePath==null)) {
            //Setting up texture for different player orientations
            TextureRegion[][] playerTexture = TextureRegion.split(new Texture(playerImagePath), 300, 300);
            TiledMapTileLayer.Cell playerNorth = new TiledMapTileLayer.Cell();
            TiledMapTileLayer.Cell playerWest = new TiledMapTileLayer.Cell();
            TiledMapTileLayer.Cell playerSouth = new TiledMapTileLayer.Cell();
            TiledMapTileLayer.Cell playerEast = new TiledMapTileLayer.Cell();
            playerNorth.setTile(new StaticTiledMapTile(playerTexture[0][0]));
            playerWest.setTile(new StaticTiledMapTile(playerTexture[0][1]));
            playerSouth.setTile(new StaticTiledMapTile(playerTexture[0][2]));
            playerEast.setTile(new StaticTiledMapTile(playerTexture[0][3]));
            //Putting the textures in a map where orientation is the keys.
            playerOrientationToTiles = new HashMap<>();
            playerOrientationToTiles.put(Direction.NORTH, playerNorth);
            playerOrientationToTiles.put(Direction.WEST, playerWest);
            playerOrientationToTiles.put(Direction.SOUTH, playerSouth);
            playerOrientationToTiles.put(Direction.EAST, playerEast);
        }
    }
    public TiledMapTileLayer.Cell getPlayerTileCell(){
        return playerOrientationToTiles.get(facingDirection);
    }
    public boolean isActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(boolean activePlayer) {
        this.activePlayer = activePlayer;
    }

    public void setPosition(Vector2 position) {
        this.playerPosition = position;
    }

    public Vector2 getPosition() {
        return new Vector2(playerPosition.x,playerPosition.y);
    }

    public void setFacingDirection(Direction facingDir) { this.facingDirection = facingDir; }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void move(Action move) {
        if(!activePlayer)
            return;

        if(move == Action.FORWARD) {playerPosition.add(directionToVector(facingDirection));}

        else if(move == Action.REVERSE) {playerPosition.sub(directionToVector(facingDirection));}

        else if(move == Action.ROTATE_LEFT) {facingDirection = rotationToDirection(facingDirection,false);}

        else if(move == Action.ROTATE_RIGHT) {facingDirection = rotationToDirection(facingDirection,true);}

        else if(move == Action.U_TURN) {
            facingDirection = (rotationToDirection(facingDirection, true));
            facingDirection = (rotationToDirection(facingDirection, true));
        }
    }
}
