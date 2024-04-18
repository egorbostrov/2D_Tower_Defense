package inf112.skeleton.app.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {
    private static Music currentMusic;

    public static void play(String filename, boolean loop) {
        stopCurrentMusic();
        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(filename));
        currentMusic.setLooping(loop);
        currentMusic.play();
    }

    public static void stopCurrentMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();  // Release the resource
            currentMusic = null;
        }
    }
}
