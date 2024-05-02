package inf112.skeleton.app.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.level.Level;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WaveControllerTest {

    @Mock
    private Level level;


private static HeadlessApplication application;

    @BeforeAll
    public static void setupBeforeAlla() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        when(Gdx.gl.glGenTexture()).thenReturn(1);
    }

    @Test
    void testNewWaveIncrementsAndDecrements() {
        Level mockLevel = new Level(1);

        EnemyController enemyController = new EnemyController(level);
        WaveController waveController = new WaveController(enemyController, 1, false);

        float initialBaseSpeedMultiplier = waveController.getBaseSpeedMultiplier();
        float initialHealthMultiplier = waveController.getHealthMultiplier();
        float initialSpawnDelay = waveController.getSpawnDelay();

        waveController.newWave(mockLevel);

        assertTrue(waveController.getBaseSpeedMultiplier() > initialBaseSpeedMultiplier);
        assertTrue(waveController.getHealthMultiplier() > initialHealthMultiplier);
        assertTrue(waveController.getSpawnDelay() < initialSpawnDelay);
    }


    @AfterAll
    public static void tearDown() {
        if(application != null) {
            application.exit();
            application = null;
        }
        Gdx.gl = null;
        Gdx.gl20 = null;
    }
}