package inf112.skeleton.app.util;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class MusicManagerTest {
    private static HeadlessApplication application;
    private static Music mockedMusic;
    private static Audio mockAudio;
    @BeforeAll
    public static void setupBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);

        mockedMusic = mock(Music.class);
        mockAudio = mock(Audio.class);
        when(mockAudio.newMusic(any(FileHandle.class))).thenReturn(mockedMusic);

        Gdx.audio = mockAudio;
    }

    @BeforeEach
    void setUp() {
        reset(mockedMusic, mockAudio);
        Files filesMock = mock(Files.class);
        FileHandle fileHandleMock = mock(FileHandle.class);
        when(filesMock.internal(anyString())).thenReturn(fileHandleMock);
        Gdx.files = filesMock;

        when(mockAudio.newMusic(fileHandleMock)).thenReturn(mockedMusic);
        when(mockedMusic.isPlaying()).thenReturn(true);
        GameSettings.setMusic(true);
    }

    @Test
    void stopMusicTest() {
        MusicManager.play("filename.ogg", true);
        MusicManager.stopCurrentMusic();
        verify(mockedMusic).stop();
        assertEquals(MusicManager.getCurrentTrack(), "", "Expected no track, but got one.");
    }

    @Test
    void changeMusicVolumeTest() {
        MusicManager.play("filename.ogg", true);
        MusicManager.changeMusicVolume();
        verify(mockAudio).newMusic(any(FileHandle.class));
        verify(mockedMusic).setVolume(GameSettings.getVolMusic());
        assertEquals(GameSettings.getVolMusic(), MusicManager.getMusicVolume(), "Volume mismatch.");
    }

    @AfterAll
    public static void tearDown() {
        if (application != null) {
            application.exit();
            application = null;
        }
    }
}
