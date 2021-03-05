package inf112.skeleton.app;

import inf112.skeleton.app.Multiplayer.RoboServer;

public class Launcher {

    //Wanted fullHD
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

    public static void main(String[] args) {
        RoboServer server = new RoboServer();
        server.runServer();
        Game game = new Game();

        game.getNetworkClient().setGameReference(game);
        game.getNetworkClient().join();
    }
}