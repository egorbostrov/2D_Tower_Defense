package inf112.skeleton.app.controller;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import inf112.skeleton.app.controller.PatternedEnemyFactory;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;

import java.util.LinkedList;

public class WaveEnemyFactoryTest {

    private Level mockLevel;
    private Map mockMap;
    private static Application application;

    private PatternedEnemyFactory patternedEnemyFactory;

    @BeforeAll
    public static void init() {
        // Initialize your libGDX environment here
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        }, conf);

        Gdx.gl20 = mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }

    @BeforeEach
    void setUp() {
        // Manually create mocks
        mockLevel = mock(Level.class);
        mockMap = mock(Map.class);

        // Define the behavior for your mocks here
        when(mockLevel.getMap()).thenReturn(mockMap);
        when(mockMap.getDirections()).thenReturn(new LinkedList<>());

        // Initialize your factory
        patternedEnemyFactory = new PatternedEnemyFactory("RT");
    }

    @Test
    void testGetNextReturnsCorrectEnemy() {
        float delay = 0.5f; // Define any delay value needed for your test
        Enemy enemyR = patternedEnemyFactory.getNext(mockLevel, 1f, 1, 0, false);
        assertNotNull(enemyR, "Enemy should not be null for type 'R'");

        Enemy enemyT = patternedEnemyFactory.getNext(mockLevel, 1f, 1f, 0, false);
        assertNotNull(enemyT, "Enemy should not be null for type 'T'");
    }

    @AfterEach
    void tearDown() {
        // Perform teardown tasks here, such as releasing resources or resetting static information
    }

    @AfterAll
    public static void cleanUp() {
        if (application != null) {
            application.exit();
            application = null;
        }
        Gdx.gl = null;
        Gdx.gl20 = null;
    }
}
