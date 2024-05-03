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
        mockLevel = mock(Level.class);
        mockMap = mock(Map.class);

        when(mockLevel.getMap()).thenReturn(mockMap);
        when(mockMap.getDirections()).thenReturn(new LinkedList<>());

        patternedEnemyFactory = new PatternedEnemyFactory("RT");
    }

    @Test
    void testGetNextReturnsCorrectEnemy() {
        float delay = 0.5f;
        Enemy enemyR = patternedEnemyFactory.getNext(mockLevel, 1f, 1, 0, false);
        assertNotNull(enemyR, "Enemy should not be null for type 'R'");

        Enemy enemyT = patternedEnemyFactory.getNext(mockLevel, 1f, 1f, 0, false);
        assertNotNull(enemyT, "Enemy should not be null for type 'T'");
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
