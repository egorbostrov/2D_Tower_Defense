package inf112.skeleton.app.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicManager {

    private static Music currentMusic;
    private static String currentTrack = "";

    // SFX
    // Shooting sfx
    private static final Sound sniperShotSound = Gdx.audio.newSound(Gdx.files.internal("sniper_shot.ogg"));
    private static final Sound gunnerShotSound = Gdx.audio.newSound(Gdx.files.internal("gunner_shot.ogg"));
    private static final Sound bomberShotSound = Gdx.audio.newSound(Gdx.files.internal("bombashoot.ogg"));
    private static final Sound bombExplodeSound = Gdx.audio.newSound(Gdx.files.internal("bombexplode.ogg"));
    // Enemy sfx
    private static final Sound zombieDeathScream = Gdx.audio.newSound(Gdx.files.internal("zombie_death.ogg"));


    public static void play(String filename, boolean loop) {
        if (GameSettings.getMusic()) {
            if (!filename.equals(currentTrack) || (currentMusic != null && !currentMusic.isPlaying())) {
                // Stop current music if it's not the right track or not playing
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

    public static void changeMusicVolume(){
        if(currentMusic!=null) {
            currentMusic.setVolume(GameSettings.getVolMusic());
        }
    }

    public static float getMusicVolume() {
        return GameSettings.getVolMusic();
    }
    public static String getCurrentTrack() {
        return currentTrack;
    }

    public static void playSniperShot() {
        sniperShotSound.play();
    }
    public static void playGunnerShot() {
        gunnerShotSound.play();
    }
    public static void playBomberShot() { bomberShotSound.play();
    }
    public static void playBombExplode() { bombExplodeSound.play();
    }

    // Enemies
    public static void playZombieDeathScream() {
        zombieDeathScream.play();
    }



}