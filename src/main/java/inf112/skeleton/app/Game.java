package inf112.skeleton.app;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.player.LocalPlayer;


public class Game extends InputAdapter implements ApplicationListener {
    private int windowHeight;
    private int windowWidth;

    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMap mapTile;
    private AssetManager assetManager;

    private TiledMapTileLayer boardLayer;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer obstacleLayer;
    private TiledMapTileLayer playerLayer;
    private MapLayer deckLayer;

    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera cam;

    private TextureRegion[][] cards;

    //PLayer
    LocalPlayer p1;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);
        windowHeight = 500;
        windowWidth = 500;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        //Setting up map an asset manager
        String mapFile = "src/assets/tiledTest.tmx";
        assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader());

        //Loading the map
        assetManager.load(mapFile, TiledMap.class);
        assetManager.finishLoading();
        mapTile = assetManager.get(mapFile);

        //Declaring layers
        boardLayer = (TiledMapTileLayer) mapTile.getLayers().get("Board");
        flagLayer = (TiledMapTileLayer) mapTile.getLayers().get("Flag");
        obstacleLayer = (TiledMapTileLayer) mapTile.getLayers().get("Obstacles");
        deckLayer = mapTile.getLayers().get("PlayerDeck");

        //Placing the player
        playerLayer = (TiledMapTileLayer) mapTile.getLayers().get("Player");
        p1 = new LocalPlayer(new Vector2(1,3),Direction.NORTH,"src/assets/player.png");
        Vector2 pos = p1.getPosition();
        playerLayer.setCell((int)pos.x,(int) pos.y, p1.getPlayerTileCell());

        //Setting up camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, boardLayer.getWidth(), boardLayer.getHeight());
        cam.update();

        //Creating the map renderer
        //Divide uniiteScale on width with the assumption that tiles as equal height and width (300x300).
        renderer = new OrthogonalTiledMapRenderer(mapTile, (float) 1/boardLayer.getTileWidth());

        cards = TextureRegion.split(new Texture("src/assets/cardTiles.png"), 380, 600);
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

        //Renders the map
        cam.update();
        renderer.setView(cam);
        renderer.render();

        //Collision with flag -> player dissapear and can't move
        Vector2 p1Pos = p1.getPosition();
        if(!(flagLayer.getCell((int) p1Pos.x,(int) p1Pos.y)==null)){
            playerLayer.setCell((int) p1Pos.x, (int) p1Pos.y, null);
            p1.setActivePlayer(false);

        }
        //Collision with hole or player goes off-board -> player dissapear and can't move (Dies)
        boolean playerOnBoard = p1Pos.x>=0 && p1Pos.y>=0 && p1Pos.x<boardLayer.getWidth() && p1Pos.y<boardLayer.getHeight();
        if(!(obstacleLayer.getCell((int) p1Pos.x,(int) p1Pos.y)==null) || !playerOnBoard){
            playerLayer.setCell((int) p1Pos.x, (int) p1Pos.y, null);
            p1.setActivePlayer(false);

        }

        //Renders objects for player deck
        batch.setProjectionMatrix(cam.combined);
        for (MapObject object : deckLayer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                batch.begin();
                batch.draw(cards[0][0], rect.x/300, rect.y/300,380/300f, 600/300f);
                batch.end();
            }
        }

    }

    @Override
    public void resize(int width, int height) {
        windowHeight = height;
        windowWidth = width;
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
        if(!p1.isActivePlayer())
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

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX + " : " + screenY);
        int[] mousePos = screenToPos(screenX,screenY);
        System.out.println(mousePos[0] + " : " + mousePos[1]);

        System.out.println(deckLayer);

        return super.touchUp(screenX, screenY, pointer, button);
    }
    private int[] screenToPos(int screenX, int screenY){
        int[] pos = {screenX * boardLayer.getWidth()/windowWidth, (windowHeight-screenY) * boardLayer.getHeight()/windowHeight};
        return pos;
    }

}