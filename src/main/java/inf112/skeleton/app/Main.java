package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.util.GameConstants;

public class Main {
    public static void main (String[] arg) {
        TDGame game = new TDGame();
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setForegroundFPS(60);
        cfg.setResizable(false);
        cfg.setTitle("TowerDefense");
        cfg.setWindowedMode((int)GameConstants.SCREEN_WIDTH, (int)GameConstants.SCREEN_HEIGHT);

        new Lwjgl3Application(game, cfg);
    }
}

