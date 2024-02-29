package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main (String[] arg) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setForegroundFPS(60);
        cfg.setTitle("TowerDefence");
        cfg.setWindowedMode(800, 600);

        new Lwjgl3Application(new TDGame(), cfg);
    }
}

