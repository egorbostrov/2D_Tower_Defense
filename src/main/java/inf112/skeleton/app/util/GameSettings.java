package inf112.skeleton.app.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class GameSettings {
    public static final String TAG = GameSettings.class.getName();

    public static final GameSettings instance = new GameSettings();

    public boolean sound;
    public boolean music;
    public float volSound;
    public float volMusic;
    private Preferences prefs = Gdx.app.getPreferences(GameConstants.PREFERENCES);


    public void load () {
        sound = prefs.getBoolean("sound", true);
        music = prefs.getBoolean("music", true);
        volSound = MathUtils.clamp(prefs.getFloat("volSound", 0.5f),0.0f, 1.0f);
        volMusic = MathUtils.clamp(prefs.getFloat("volMusic", 0.5f),0.0f, 1.0f);
    }
    public void save () {
        prefs.putBoolean("sound", sound);
        System.out.println();
        prefs.putBoolean("music", music);
        prefs.putFloat("volSound", volSound);
        prefs.putFloat("volMusic", volMusic);
        prefs.flush();
    }
}
