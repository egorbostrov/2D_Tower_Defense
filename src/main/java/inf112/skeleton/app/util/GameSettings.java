package inf112.skeleton.app.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class GameSettings {

    public static GameSettings instance = new GameSettings();

    private static boolean sound;
    private static boolean music;
    private static float volSound;
    private static float volMusic;
    private static boolean fullscreen;
    private Preferences preference;

    private GameSettings() {
        preference = Gdx.app.getPreferences(GameConstants.PREFERENCES);
    }

    // Public method to get the instance
    public static synchronized GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        return instance;
    }

    /**
     * Load preferences from My Preferences.
     */
    public void load () {
        sound = preference.getBoolean("sound", true);
        music = preference.getBoolean("music", true);
        fullscreen = preference.getBoolean("fullscreen", true);
        volSound = MathUtils.clamp(preference.getFloat("volSound", 0.5f),0.0f, 1.0f);
        volMusic = MathUtils.clamp(preference.getFloat("volMusic", 0.5f),0.0f, 1.0f);
    }

    /**
     * Save preferences to My Preferences.
     */
    public void save () {
        preference.putBoolean("sound", sound);
        preference.putBoolean("music", music);
        preference.putFloat("volSound", volSound);
        preference.putFloat("volMusic", volMusic);
        preference.putBoolean("fullscreen", fullscreen);
        preference.flush();
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
