package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.IBoard;
import inf112.skeleton.app.screen.GameScreen;

import java.util.Map;

public class Launcher {

    public static final int WIDTH = 1920;
    private static final int DECK_WINDOW_SIZE = 360;
    public static final int HEIGHT = 720 + DECK_WINDOW_SIZE;

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");
        cfg.setWindowedMode(WIDTH, HEIGHT);

        new Lwjgl3Application(new Game(), cfg);
    }
}