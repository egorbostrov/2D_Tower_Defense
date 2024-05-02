package inf112.skeleton.app.util;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameSettingsTest {

    @Mock
    private Preferences mockPrefs;

    @BeforeEach
    public void setUp() throws Exception {
        Application mockApp = mock(Application.class);
        Gdx.app = mockApp;
        when(Gdx.app.getPreferences(GameConstants.PREFERENCES)).thenReturn(mockPrefs);

        // Using reflection to access the private constructor and instantiate GameSettings
        Constructor<GameSettings> constructor = GameSettings.class.getDeclaredConstructor();
        constructor.setAccessible(true);  // Make the private constructor accessible
        GameSettings.instance = constructor.newInstance();  // Create a new instance using the private constructor
    }

    @Test
    public void testLoad() {
        // Setup initial conditions
        when(mockPrefs.getBoolean("sound", true)).thenReturn(false);
        when(mockPrefs.getBoolean("music", true)).thenReturn(false);
        when(mockPrefs.getBoolean("fullscreen", true)).thenReturn(false);
        when(mockPrefs.getFloat("volSound", 0.5f)).thenReturn(0.3f);
        when(mockPrefs.getFloat("volMusic", 0.5f)).thenReturn(0.3f);

        // Execute load method
        GameSettings.instance.load();

        // Validate settings were loaded correctly
        assertFalse(GameSettings.instance.getMusic());
        assertFalse(GameSettings.instance.getSound());
        assertFalse(GameSettings.instance.getFullscreen());
        assertEquals(0.3f, GameSettings.instance.getVolSound());
        assertEquals(0.3f, GameSettings.instance.getVolMusic());
    }

    @Test
    public void testSave() {
        // Setup expected values
        GameSettings.instance.setMusic(true);
        GameSettings.instance.setSound(true);
        GameSettings.instance.setFullscreen(true);
        GameSettings.instance.setVolSound(0.8f);
        GameSettings.instance.setVolMusic(0.8f);

        // Execute save method
        GameSettings.instance.save();

        // Verify preferences are being saved correctly
        verify(mockPrefs).putBoolean("sound", true);
        verify(mockPrefs).putBoolean("music", true);
        verify(mockPrefs).putFloat("volSound", 0.8f);
        verify(mockPrefs).putFloat("volMusic", 0.8f);
        verify(mockPrefs).putBoolean("fullscreen", true);
        verify(mockPrefs).flush();  // Ensure changes are persisted
    }
}
