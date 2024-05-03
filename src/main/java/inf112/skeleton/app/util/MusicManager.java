package inf112.skeleton.app.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicManager {

    private static Music currentMusic;
    private static String currentTrack = "";

    // SFX
    private static final Sound sniperShotSound = Gdx.audio.newSound(Gdx.files.internal("snipershot.ogg"));
    private static final Sound gunnerShotSound = Gdx.audio.newSound(Gdx.files.internal("gunnershot.ogg"));
    private static final Sound bomberShotSound = Gdx.audio.newSound(Gdx.files.internal("bombershot.ogg"));
    private static final Sound bombExplodeSound = Gdx.audio.newSound(Gdx.files.internal("bombexplosion.ogg"));
    private static final Sound zombieDeathScream = Gdx.audio.newSound(Gdx.files.internal("zombiedeathsound.ogg"));

    public static void play(String filename, boolean loop) {
        if (GameSettings.getMusic()) {
            if (!filename.equals(currentTrack) || (currentMusic != null && !currentMusic.isPlaying())) {
                stopCurrentMusic();
                currentMusic = Gdx.audio.newMusic(Gdx.files.internal(filename));
                currentMusic.setLooping(loop);
                currentMusic.play();
                currentTrack = filename;
            }
        }
    }

    public static void stopCurrentMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
            currentMusic = null;
            currentTrack = "";
        }
    }

    public static void changeMusicVolume() {
        if (currentMusic != null) {
            currentMusic.setVolume(GameSettings.getVolMusic());
        }
    }

    public static String getCurrentTrack() {
        return currentTrack;
    }

    public static void playSniperShot() {
        if (GameSettings.getSound()) { // Check if sound is enabled
            float volume = GameSettings.getVolSound(); // Get volume setting
            sniperShotSound.play(volume); // Play the sound with the specified volume
        }
    }

    public static void playGunnerShot() {
        if (GameSettings.getSound()) { // Check if sound is enabled
            float volume = GameSettings.getVolSound(); // Get volume setting
            gunnerShotSound.play(volume); // Play the sound with the specified volume
        }
    }

    public static void playBomberShot() {
        if (GameSettings.getSound()) { // Check if sound is enabled
            float volume = GameSettings.getVolSound(); // Get volume setting
            bomberShotSound.play(volume); // Play the sound with the specified volume
        }
    }

    public static void playBombExplode() {
        if (GameSettings.getSound()) { // Check if sound is enabled
            float volume = GameSettings.getVolSound(); // Get volume setting
            bombExplodeSound.play(volume); // Play the sound with the specified volume
        }
    }

    public static void playZombieDeathScream() {
        if (GameSettings.getSound()) { // Check if sound is enabled
            float volume = GameSettings.getVolSound(); // Get volume setting
            zombieDeathScream.play(volume); // Play the sound with the specified volume
        }
    }
}
