package inf112.skeleton.app.level;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Graphics;



public class LibGDXTestBase {
    private static HeadlessApplication application;

    @BeforeClass
    public static void init() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {}

            @Override
            public void render() {}

            @Override
            public void resize(int width, int height) {}

            @Override
            public void pause() {}

            @Override
            public void resume() {}

            @Override
            public void dispose() {}
        }, config);

        // Mocking the Gdx components
        Gdx.gl = Mockito.mock(GL20.class);
        Gdx.gl20 = Gdx.gl;
        Gdx.graphics = Mockito.mock(Graphics.class);

        // Setting up the expected responses from the mocked Graphics object
        Mockito.when(Gdx.graphics.getWidth()).thenReturn(800);
        Mockito.when(Gdx.graphics.getHeight()).thenReturn(600);
    }
}