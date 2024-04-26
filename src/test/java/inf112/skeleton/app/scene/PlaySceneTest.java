package inf112.skeleton.app.scene;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.TDGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.suppress;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;
import static org.powermock.api.mockito.PowerMockito.constructor;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SpriteBatch.class, PlayScene.class})
class PlaySceneTest {
    
    private TDGame game;
    private HeadlessApplicationConfiguration config;

    @BeforeEach
    void setUp() throws Exception {
        // Mock the libGDX environment
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = Gdx.gl;
        Gdx.graphics = mock(Graphics.class);
        when(Gdx.graphics.getWidth()).thenReturn(800);
        when(Gdx.graphics.getHeight()).thenReturn(600);
        Gdx.app = mock(Application.class);

        // Suppress the SpriteBatch constructor to prevent shader compilation
        suppress(constructor(SpriteBatch.class));

        // Create the actual game object
        game = mock(TDGame.class);
        
        // Initialize the headless application for libGDX
        config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        }, config);
    }

    @Test
    void playSceneConstructorTest() {
        // Test the PlayScene constructor
        PlayScene scene = new PlayScene(game);
        // Verify that the necessary methods are called on the PlayScene object
        // Note: Since constructors cannot be directly verified and
        // methods called within are private, this test checks if the
        // object is created without exceptions.
        // If you need to verify internal state changes, you would
        // need to expose those states via public methods or use reflection.
    }
}
