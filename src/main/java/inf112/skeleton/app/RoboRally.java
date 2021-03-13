package inf112.skeleton.app;

import inf112.skeleton.app.screen.MainMenuScreen;

public class RoboRally  extends GameGUI {

    private MainMenuScreen MainMenuScreen;

    @Override
    public void create() {

        MainMenuScreen mainMenuScreen = new MainMenuScreen();
        setScreen(MainMenuScreen);
    }

    public void render() {
        super.render();
    }
}
