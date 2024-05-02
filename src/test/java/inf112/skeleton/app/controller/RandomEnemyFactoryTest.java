package inf112.skeleton.app.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import inf112.skeleton.app.entity.Enemy;

import java.util.LinkedList;

class RandomEnemyFactoryTest {
    @Mock
    Level mockLevel;
    @Mock
    Map mockMap;

    RandomEnemyFactory enemyFactory;
    private static HeadlessApplication application;

    @BeforeAll
    public static void setupBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);
        //Mocking needed for the healthbar created in Enemy constructor
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = Gdx.gl;
        when(Gdx.gl.glGenTexture()).thenReturn(1);
    }

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNext() {
        enemyFactory = new RandomEnemyFactory();
        assertNotNull(enemyFactory, "Factory was not initialized");

        when(mockLevel.getMap()).thenReturn(mockMap);
        when(mockMap.getDirections()).thenReturn(new LinkedList<>());

        float speedMultiplier = 1f;
        float healthMultiplier = 1f;
        float delay = 1f;
        boolean doubleSpeed = false;

        Enemy result = enemyFactory.getNext(mockLevel, speedMultiplier, healthMultiplier, delay, doubleSpeed);
        assertNotNull(result, "enemy should not be null");
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
