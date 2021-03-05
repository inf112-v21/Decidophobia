package inf112.skeleton.app.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Launcher;
import inf112.skeleton.app.RoboRally;
import org.lwjgl.system.CallbackI;

import javax.swing.text.TabableView;
import java.util.ArrayList;


public class MainMenuScreen implements Screen {

    private Stage stage;
    private final RoboRally game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Table table;
    private Image mapView;
    private int seed;
    private Skin skin;

    private int width;
    private int height;

    private ArrayList<String> mapList;

    public MainMenuScreen(final RoboRally game) {

        this.game = game;
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

    }

    @Override
    public void show() {

        table = new Table();
        table.setWidth(720);
        table.setHeight(stage.getHeight());
        table.setPosition(stage.getWidth() / 2 - table.getWidth(), stage.getHeight() / 2 - table.getHeight());
        stage.addActor(table);
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("Background.png"))));

        Gdx.input.setInputProcessor(stage);

        Texture texture = new Texture(Gdx.files.internal("logo.png"));

        stage = new Stage();
    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);
        table.setHeight(stage.getHeight());
        table.setPosition(stage.getWidth() - table.getWidth(), stage.getHeight() - table.getHeight());

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void setMapViewToCurrentMap() {
        Texture texture;

        try{
            texture = new Texture("MapImg" + mapList.get(seed).substring(0, mapList.get(seed).length()-3) + "png");
        } catch (GdxRuntimeException e) {
            System.out.println(mapList.get(seed) + "404");
            String placeHolderView = mapList.get(0);
            texture = new Texture("MapImg" + placeHolderView.substring(0, placeHolderView.length()-3) + "png");
        }
        mapView.setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
    }

    private void mapSelecter() {

        mapView = new Image();
        table.add(mapView).colspan(4).fillX();
        table.row();
        final Label fileName = new Label(mapList.get(seed), skin);
        fileName.setAlignment(Align.center);
        table.add(fileName).fillX().uniformX().colspan(4).minHeight(100);

        setMapViewToCurrentMap();

        table.row().pad(0, 0, 1, 0);
    }
}
