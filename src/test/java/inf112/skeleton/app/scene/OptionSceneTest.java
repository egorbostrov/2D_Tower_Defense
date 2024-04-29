package inf112.skeleton.app.scene;

import static org.mockito.Mockito.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.GameSettings;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.*;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class OptionSceneTest {

    @Mock private Game mockGame;
    @Mock private Stage mockStage;
    @Mock private Skin mockSkin;
    @Mock private CheckBox mockChkSound, mockChkMusic, mockChkFullscreen;
    @Mock private Slider mockSldSound, mockSldMusic;
    @Mock private TextButton mockSaveButton, mockCancelButton;

    private OptionScene optionScene;

//    @BeforeClass
//    public static void setUpClass() {
//        // Use HeadlessApplication to avoid actual window pop-up
//        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
//        new HeadlessApplication(new ApplicationAdapter() {}, conf);
//
//        // After application is created, Gdx.app and Gdx.files are now non-null.
//        // Now, when GameSettings.instance is accessed, it won't throw NullPointerException.
//    }

    @BeforeClass
    public static void setUpClass() {
        // Set up the headless LibGDX application
        Application mockApplication = mock(Application.class);
        Gdx.app = mockApplication;

        // Mock the Preferences
        Preferences mockPreferences = mock(Preferences.class);
        when(mockApplication.getPreferences(GameConstants.PREFERENCES)).thenReturn(mockPreferences);

        // Define what the mocked preferences should return when queried
        when(mockPreferences.getBoolean("sound", true)).thenReturn(true);
        when(mockPreferences.getBoolean("music", true)).thenReturn(false); // Assuming false is correct here
        when(mockPreferences.getFloat("volSound", 0.5f)).thenReturn(0.5f);
        when(mockPreferences.getFloat("volMusic", 0.5f)).thenReturn(0.5f);
        when(mockPreferences.getBoolean("fullscreen", false)).thenReturn(false);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        optionScene = new OptionScene(mockGame);
        optionScene.stage = mockStage;
        optionScene.skin = mockSkin;
        optionScene.chkSound = mockChkSound;
        optionScene.chkMusic = mockChkMusic;
        optionScene.chkFullscreen = mockChkFullscreen;
        optionScene.sldSound = mockSldSound;
        optionScene.sldMusic = mockSldMusic;
        optionScene.saveButton = mockSaveButton;
        optionScene.cancelButton = mockCancelButton;

        doNothing().when(mockStage).act(anyFloat());
        doNothing().when(mockStage).draw();
    }

    @Test
    public void testSaveSettingsInteraction() {
        when(mockChkSound.isChecked()).thenReturn(true);
        when(mockChkMusic.isChecked()).thenReturn(false);
        when(mockSldSound.getValue()).thenReturn(0.8f);
        when(mockSldMusic.getValue()).thenReturn(0.3f);
        when(mockChkFullscreen.isChecked()).thenReturn(true);

        optionScene.onSaveClicked();

        verify(mockChkSound).isChecked();
        verify(mockChkMusic).isChecked();
        verify(mockSldSound).getValue();
        verify(mockSldMusic).getValue();
        verify(mockChkFullscreen).isChecked();
        // Additionally, verify that preferences are saved and screen transition occurs
        verify(mockGame).setScreen(any(MenuScene.class));
    }

    @Test
    public void testCancelSettingsInteraction() {
        optionScene.onCancelClicked();
        verify(mockGame).setScreen(any(MenuScene.class));
    }


    @Test
    public void testLoadSettings() {
        // Assuming your OptionScene.loadSettings() method does something like this:
        // GameSettings settings = GameSettings.getInstance();
        // this.mockChkSound.setChecked(settings.getSound());
        // ... etc.

        optionScene.loadSettings();

        // Verify that the UI elements are set to the expected values
        verify(mockChkSound).setChecked(true);
        verify(mockChkMusic).setChecked(false); // This should match the value set in mockPreferences
        verify(mockSldSound).setValue(0.5f);
        verify(mockSldMusic).setValue(0.5f);
        verify(mockChkFullscreen).setChecked(false);
    }



    @Test
    public void testShowInitializesStageAndInputs() {
        optionScene.show();
        verify(mockStage).clear();
        verify(mockGame, never()).setScreen(any());
    }

    @Test
    public void testHideDisposesResources() {
        optionScene.hide();
        verify(mockStage).dispose();
        verify(mockSkin).dispose();
    }


}

