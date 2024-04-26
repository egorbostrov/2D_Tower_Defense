package inf112.skeleton.app.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class GameSettings {

    public static final GameSettings instance = new GameSettings();

    private static boolean sound;
    private static boolean music;
    private static float volSound;
    private static float volMusic;
    private static boolean fullscreen;
    private Preferences prefs = Gdx.app.getPreferences(GameConstants.PREFERENCES);

    /**
     * Load preferences from My Preferences.
     */
    public void load () {
        sound = prefs.getBoolean("sound", true);
        music = prefs.getBoolean("music", true);
        fullscreen = prefs.getBoolean("fullscreen", true);
        volSound = MathUtils.clamp(prefs.getFloat("volSound", 0.5f),0.0f, 1.0f);
        volMusic = MathUtils.clamp(prefs.getFloat("volMusic", 0.5f),0.0f, 1.0f);
    }

    /**
     * Save preferences to My Preferences.
     */
    public void save () {
        prefs.putBoolean("sound", sound);
        prefs.putBoolean("music", music);
        prefs.putFloat("volSound", volSound);
        prefs.putFloat("volMusic", volMusic);
        prefs.putBoolean("fullscreen", fullscreen);
        prefs.flush();
        System.out.println("Settings saved. (My Preferences in %userprofile% backslash .prefs)");
    }

    // Setters
    public static void setSound(boolean sound) {
        GameSettings.sound = sound;
    }

    public static void setFullscreen(boolean fullscreen) {
        GameSettings.fullscreen = fullscreen;
    }

    public static void setMusic(boolean music) {
        GameSettings.music = music;
    }

    public static void setVolSound(float volSound) {
        GameSettings.volSound = volSound;
    }

    public static void setVolMusic(float volMusic) {
        GameSettings.volMusic = volMusic;
    }

    // Getters
    public static float getVolMusic() {
        return volMusic;
    }

    public static float getVolSound() {
        return volSound;
    }
    public static boolean getSound() {
        return sound;
    }
    public static boolean getFullscreen () {
        return fullscreen;
    }
    public static boolean getMusic() {
        return music;
    }

}
