package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.GUI.screen.ScreenManager;

public class Launcher {

    //Wanted fullHD
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");
        cfg.setWindowedMode(Launcher.WIDTH, Launcher.HEIGHT);

        new Lwjgl3Application(new ScreenManager(), cfg);
    }
}