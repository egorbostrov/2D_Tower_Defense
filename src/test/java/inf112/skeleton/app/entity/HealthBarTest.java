package inf112.skeleton.app.entity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.util.GameConstants;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.Random;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class HealthBarTest {

    private static HeadlessApplication application;

    @BeforeAll
    public static void setupBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        when(Gdx.gl.glGenTexture()).thenReturn(1);
    }

    @Mock
    private Enemy mockEnemy;

    @Mock
    private SpriteBatch mockBatch;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockEnemy = new Enemy('R',100, 100, GameConstants.ENEMY_WIDTH, GameConstants.ENEMY_HEIGHT, 100, new LinkedList<>(), 0, 0, 0, null, false);
    }

    @Test
    public void testHealthRemoveEnemyVsHealthBar(){
        Random random = new Random();
        int randomNumber =random.nextInt(100);
        System.out.println(randomNumber);
        mockEnemy.shot(randomNumber);
        assertEquals(mockEnemy.getHpBar().getCurrentHealth(), mockEnemy.getEnemyHealth());
    }



    @AfterAll
    public static void tearDown() {
        if (application != null) {
            application.exit();
            application = null;
        }
        Gdx.gl = null;
        Gdx.gl20 = null;
    }
}
