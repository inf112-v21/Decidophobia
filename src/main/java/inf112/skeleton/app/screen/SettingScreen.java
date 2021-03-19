package inf112.skeleton.app.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Launcher;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingScreen implements Screen {

    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Skin skin;

    public SettingScreen() {

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Launcher.WIDTH, Launcher.HEIGHT, camera);
        viewport.apply();
        stage = new Stage(viewport, batch);
        skin = new Skin(Gdx.files.internal("skin.json"), atlas);
        atlas = new TextureAtlas("skin.atlas");
    }

    //lagde volumeslider, må kanskje bli flytta inn til show() mwtoden
    public class VolumeSlider {
        JSlider volume;
        VolumeSlider(final FloatControl volumeControl) {
            volume = new JSlider
                    (
                    (int) volumeControl.getMinimum() * 100,
                    (int) volumeControl.getMaximum() * 100,
                    (int) volumeControl.getValue() * 100
            );
            ChangeListener listener = e -> {
                float val = volume.getValue() / 100f;
                volumeControl.setValue(val);
            };
            volume.addChangeListener(listener);
        }
        public JSlider getVolume() {
            return volume;
        }
    }

    @Override
    public void show() {

        TextButton fullScreen = new TextButton("FullScreen", skin);
        TextButton backButton = new TextButton("Back", skin);

        //Tenkte vi trengte enn fullscreen knapp
        fullScreen.addListener(new ClickListener() {

            public void clicked(InputEvent event,  float x, float y) {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            }
        });

        //Tar  speleren tilbake til MainMenu istadenfor å måtte slutte spillet
        backButton.addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {

            }
        });
    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

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
}
