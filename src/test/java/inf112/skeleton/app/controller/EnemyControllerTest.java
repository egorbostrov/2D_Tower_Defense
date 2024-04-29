package inf112.skeleton.app.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.controller.EnemyController;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;

import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EnemyControllerTest {

    @Mock
    private Level mockLevel;
    @Mock
    private Map mockMap;
    @Mock
    private SpriteBatch mockBatch;
    @Mock
    private ShapeRenderer mockRenderer;
    @Mock
    private Enemy mockEnemy;
    private EnemyController enemyController;
    private static HeadlessApplication application;

    @BeforeAll
    public static void setupBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        when(Gdx.gl.glGenTexture()).thenReturn(1);
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        //Mocking needed for the healthbar created in Enemy constructor
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = Gdx.gl;
        when(Gdx.gl.glGenTexture()).thenReturn(1);


        //doNothing().when(mockEnemy).initializeHealthBar(anyFloat(), anyFloat(), anyFloat(), anyFloat(), anyFloat());

        when(mockEnemy.isAlive()).thenReturn(true);

        enemyController = new EnemyController(mockLevel);
    }

    @Test
    void populateEnemyList() {
        for (int i = 0; i < 5; i++) {
            enemyController.newZombie(new Enemy(
                    'R',
                    GameConstants.START_POS.x,
                    GameConstants.START_POS.y,
                    GameConstants.ENEMY_WIDTH,
                    GameConstants.ENEMY_HEIGHT,
                    5,
                    mockMap.getDirections(),
                    5,
                    5,
                    5,
                    GameAssets.zombieSprite,
                    false
            ));
        }
        assertEquals(5, enemyController.getEnemyList().size(), "List should contain 5 enemies");
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

