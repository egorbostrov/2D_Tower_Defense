package inf112.skeleton.app.level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockConstruction;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.util.GameConstants;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import com.badlogic.gdx.Graphics;

import java.util.Set;

public class LevelTest {
    private static HeadlessApplication application;
    private Level level;
    private Board board;
    private Set<Vector2> mockPathPoints;
    
    static MockedConstruction<SpriteBatch> batch;
    static MockedConstruction<BitmapFont> font;
    static MockedConstruction<Animation> animation;
    static MockedConstruction<TextureRegion> region;

    @BeforeAll
    static void setupBeforeAll() {
        batch = mockConstruction(SpriteBatch.class);
        font = mockConstruction(BitmapFont.class);
        animation = mockConstruction(Animation.class);
        region = mockConstruction(TextureRegion.class);
    }

    @AfterAll
    static void deregister() {
        batch.close();
        font.close();
        animation.close();
        region.close();
    }

    @BeforeEach
    public void init() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationListener() {
            @Override
            public void create() {}
            @Override
            public void resize(int width, int height) {}
            @Override
            public void render() {}
            @Override
            public void pause() {}
            @Override
            public void resume() {}
            @Override
            public void dispose() {}
        };

        Gdx.gl = Mockito.mock(GL20.class);
        Gdx.graphics = Mockito.mock(Graphics.class);
        
        new HeadlessApplication(listener, config);
        

        // Setting up the expected responses from the mocked Graphics object
        
    }

    @BeforeEach
    public void setUp() {
        level = new Level(1);
    }

    @Test
    public void testInitialConditions() {
        assertEquals(0, level.getScore());
        assertEquals(GameConstants.START_MONEY, level.getMoney());
        assertEquals(GameConstants.REMAINING_HEALTH, level.getUserHealth());
        assertEquals(0, level.getCurrentWave());
        assertNotNull(level.getMap());
    }

    @Test
    public void testAddMoney() {
        int initialMoney = level.getMoney();
        level.addMoney(100);
        assertEquals(initialMoney + 100, level.getMoney());
    }

    @Test
    public void testRemoveMoney() {
        int moneyToBeRemoved = 100;
        level.removeMoney(moneyToBeRemoved);
        assertEquals(GameConstants.START_MONEY - moneyToBeRemoved, level.getMoney());
    }

    @Test
    public void testEnemyKilledUpdates() {
        int initialScore = level.getScore();
        int initialMoney = level.getMoney();
        int initialEnemies = level.getEnemiesKilled();

        level.enemyKilled(100); // assuming reward is 100 money units

        assertEquals(initialScore + GameConstants.SCORE_INCREASE, level.getScore());
        assertEquals(initialMoney + 100, level.getMoney());
        assertEquals(initialEnemies + 1, level.getEnemiesKilled());
    }

    @Test
    public void testRestartGameResetsValues() {
        level.restart();
        testInitialConditions();
    }

    @Test
    public void removeMoneyDoesNotGoBelowZero() {
        level.removeMoney(GameConstants.START_MONEY + 1);
        assertEquals(GameConstants.START_MONEY, level.getMoney());
    }

    @Test
    public void testEnemyCompletedPath() {
        int initialHealth = level.getUserHealth();
        level.enemyCompletedPath();
        assertEquals(initialHealth - 1, level.getUserHealth());
    }

    @Disabled("Does not test anything useful yet")
    public void testNextWaveIncreasesWaveCount() {
        int currentWave = level.getCurrentWave();
        //level.newWaveCreated(10);
        assertEquals(currentWave + 1, level.getCurrentWave());
    }
    
}
