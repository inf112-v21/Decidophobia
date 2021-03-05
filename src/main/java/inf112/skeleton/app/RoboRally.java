package inf112.skeleton.app;

import inf112.skeleton.app.screen.MainMenuScreen;
import inf112.skeleton.app.screen.ScreenManager;

public class RoboRally  extends GameGUI {

    private MainMenuScreen MainMenuScreen;

    @Override
    public void create() {

        MainMenuScreen mainMenuScreen = new MainMenuScreen(this);
        setScreen(MainMenuScreen);
    }

    public void render() {
        super.render();
    }
}
