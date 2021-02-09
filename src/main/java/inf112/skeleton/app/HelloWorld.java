package inf112.skeleton.app;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

public class HelloWorld extends InputAdapter implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMap mapTile;
    private TiledMapTileLayer boardLayer;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer obstacleLayer;
    private TiledMapTileLayer playerLayer;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera cam;

    //PLayer
    private boolean activePlayer = true;
    private Vector2 playerPos;
    private Direction playerOrientation;
    private Map<Direction, TiledMapTileLayer.Cell> playerOrientationToTiles;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        //Setting up texture for different player orientations
        TextureRegion[][] playerTexture = TextureRegion.split(new Texture("src/assets/player.png"),300,300);
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
        playerOrientationToTiles.put(Direction.NORTH,playerNorth);
        playerOrientationToTiles.put(Direction.WEST,playerWest);
        playerOrientationToTiles.put(Direction.SOUTH,playerSouth);
        playerOrientationToTiles.put(Direction.EAST,playerEast);


        //Loading the map
        mapTile = new TmxMapLoader().load("src/assets/tiledTest.tmx");
        boardLayer = (TiledMapTileLayer) mapTile.getLayers().get("Board");
        flagLayer = (TiledMapTileLayer) mapTile.getLayers().get("Flag");
        obstacleLayer = (TiledMapTileLayer) mapTile.getLayers().get("Obstacles");
        playerLayer = (TiledMapTileLayer) mapTile.getLayers().get("Player");

        //Placing the player
        playerPos = new Vector2(0,0);
        playerOrientation = Direction.NORTH;
        playerLayer.setCell((int)playerPos.x,(int) playerPos.y, playerOrientationToTiles.get(playerOrientation));


        cam = new OrthographicCamera();
        cam.setToOrtho(false, boardLayer.getWidth(), boardLayer.getHeight());

        cam.update();
        //Divide uniiteScale on width with the assumption that tiles as equal height and width (300x300).
        renderer = new OrthogonalTiledMapRenderer(mapTile, (float) 1/boardLayer.getTileWidth());
        renderer.setView(cam);

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //Collision with flag -> player dissapear and can't move
        if(!(flagLayer.getCell((int) playerPos.x,(int) playerPos.y)==null)){
            playerLayer.setCell((int) playerPos.x, (int) playerPos.y, null);
            activePlayer = false;
        }
        //Collision with hole -> player dissapear and can't move
        if(!(obstacleLayer.getCell((int) playerPos.x,(int) playerPos.y)==null)){
            playerLayer.setCell((int) playerPos.x, (int) playerPos.y, null);
            activePlayer = false;
        }
        renderer.render();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public boolean keyUp(int keycode) {
		/*
		Found keycodes
		up,left,down,right: 19,21,20,22
		w,a,s,d: 51,29,47,32
		 */
        //Player Dead or Won
        if(!activePlayer)
            return super.keyUp(keycode);

        playerLayer.setCell((int) playerPos.x,(int) playerPos.y,null);
        if(keycode==19 || keycode == 51){
            //clicked up or w -> move backwards
            playerPos.add(directionToVector(playerOrientation));
        }
        if(keycode==21 || keycode == 29){
            //clicked left or a -> rotate counterClockwise
            playerOrientation = rotationToDirection(playerOrientation, false);
        }
        if(keycode==20 || keycode == 47){
            //clicked down or s -> move backwards
            playerPos.sub(directionToVector(playerOrientation));
        }
        if(keycode==22 || keycode == 32){
            //clicked right or d -> rotate clockwise
            playerOrientation = rotationToDirection(playerOrientation, true);
        }
        playerPos.x = playerPos.x>=boardLayer.getWidth() ? boardLayer.getWidth()-1 : playerPos.x;
        playerPos.x = playerPos.x<0 ? 0 : playerPos.x;
        playerPos.y = playerPos.y>=boardLayer.getHeight() ? boardLayer.getHeight()-1 : playerPos.y;
        playerPos.y = playerPos.y<0 ? 0 : playerPos.y;

        playerLayer.setCell((int)playerPos.x,(int) playerPos.y, playerOrientationToTiles.get(playerOrientation));

        return super.keyUp(keycode);
    }
    private Vector2 directionToVector(Direction dir){
        if (dir==Direction.NORTH){
            return new Vector2(0,1);
        }
        else if (dir==Direction.WEST){
            return new Vector2(-1,0);
        }
        else if (dir==Direction.SOUTH){
            return new Vector2(0,-1);
        }
        else if (dir==Direction.EAST){
            return new Vector2(1,0);
        }
        return null;
    }
    private Direction rotationToDirection(Direction playerDir, boolean clockwise){
        Direction[] directionList = {Direction.NORTH,Direction.EAST,Direction.SOUTH,Direction.WEST};
        int dirIndex=directionList.length;
        for(int i = 0; i<directionList.length; i++){
            if(directionList[i]==playerDir){
                dirIndex = i;
                i+=directionList.length;
            }
        }
        dirIndex += clockwise ? 1 : -1;
        if(dirIndex<0)
            dirIndex = directionList.length-1;
        if(dirIndex>=directionList.length)
            dirIndex = 0;
        return directionList[dirIndex];
    }
}