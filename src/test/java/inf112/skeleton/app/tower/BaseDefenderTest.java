package inf112.skeleton.app.tower;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.entity.Enemy;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BaseDefenderTest {

    @Mock
    private Enemy mockEnemy;
    private List<Enemy> enemies;
    private TestableDefender defender;

    @BeforeClass
    public static void init() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new ApplicationListener() {
            public void create() {
            }

            public void resize(int width, int height) {
            }

            public void render() {
            }

            public void pause() {
            }

            public void resume() {
            }

            public void dispose() {
            }
        }, config);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        enemies = new ArrayList<>();
        enemies.add(mockEnemy);
        when(mockEnemy.isAlive()).thenReturn(true);
        when(mockEnemy.position).thenReturn(new Vector2(100, 100));

        defender = new TestableDefender(50, 50, enemies);
    }

    @Test
    public void testInitialization() {
        assertNotNull("Defender should be initialized", defender);
    }

    @Test
    public void testFindTarget() {
        defender.updateEnemyMap();
        defender.findTarget();
        assertEquals("Defender should find the closest enemy", mockEnemy, defender.enemy);
    }

    @Test
    public void testDamageUpgrade() {
        float initialDamage = defender.getDamage();
        defender.damageUpgrade();
        assertTrue("Damage should increase after upgrade", defender.getDamage() > initialDamage);
    }
}
