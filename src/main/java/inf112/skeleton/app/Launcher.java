package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
public class Launcher {

    //Wanted fullHD
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");
        cfg.setWindowedMode(WIDTH, HEIGHT);

        new Lwjgl3Application(new Game(), cfg);
    }
}