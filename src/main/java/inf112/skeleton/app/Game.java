package inf112.skeleton.app;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.player.LocalPlayer;


public class Game extends InputAdapter implements ApplicationListener {
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
    LocalPlayer p1;
    private boolean activePlayer = true;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        //Loading the map
        mapTile = new TmxMapLoader().load("src/assets/tiledTest.tmx");
        boardLayer = (TiledMapTileLayer) mapTile.getLayers().get("Board");
        flagLayer = (TiledMapTileLayer) mapTile.getLayers().get("Flag");
        obstacleLayer = (TiledMapTileLayer) mapTile.getLayers().get("Obstacles");
        playerLayer = (TiledMapTileLayer) mapTile.getLayers().get("Player");

        //Placing the player
        p1 = new LocalPlayer(new Vector2(0,0),Direction.NORTH,"src/assets/player.png");
        Vector2 pos = p1.getPosition();
        playerLayer.setCell((int)pos.x,(int) pos.y, p1.getPlayerTileCell());


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
        Vector2 p1Pos = p1.getPosition();
        if(!(flagLayer.getCell((int) p1Pos.x,(int) p1Pos.y)==null)){
            playerLayer.setCell((int) p1Pos.x, (int) p1Pos.y, null);
            p1.setActivePlayer(false);

        }
        //Collision with hole -> player dissapear and can't move
        if(!(obstacleLayer.getCell((int) p1Pos.x,(int) p1Pos.y)==null)){
            playerLayer.setCell((int) p1Pos.x, (int) p1Pos.y, null);
            p1.setActivePlayer(false);

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
		u: 49
		 */
        //Player Dead or Won
        if(!activePlayer)
            return super.keyUp(keycode);

        playerLayer.setCell((int) p1.getPosition().x,(int) p1.getPosition().y,null);
        if(keycode == 19 || keycode == 51){
            //clicked up or w -> move Forward
            p1.move(Action.FORWARD);
        }
        else if(keycode == 21 || keycode == 29){
            //clicked left or a -> rotate counterClockwise
            p1.move(Action.ROTATE_LEFT);
        }
        else if(keycode == 20 || keycode == 47){
            //clicked down or s -> move backwards
            p1.move(Action.REVERSE);
        }
        else if(keycode == 22 || keycode == 32){
            //clicked right or d -> rotate clockwise
            p1.move(Action.ROTATE_RIGHT);
        }
        else if(keycode == 49){
            //clicked u -> do U-turn
            p1.move(Action.U_TURN);
        }

        playerLayer.setCell((int)p1.getPosition().x,(int) p1.getPosition().y, p1.getPlayerTileCell());

        return super.keyUp(keycode);
    }

}