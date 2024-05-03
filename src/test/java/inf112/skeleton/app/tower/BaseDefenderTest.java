package inf112.skeleton.app.tower;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import static inf112.skeleton.app.util.GameConstants.ENEMY_HEIGHT;
import static inf112.skeleton.app.util.GameConstants.ENEMY_WIDTH;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BaseDefenderTest {

    private Enemy mockEnemy;
    private BaseDefender mockBaseDefender;
    private HashMap<Enemy, Float> mockHashMap;
    private List<Enemy> enemies;
    private TestableDefender defender;

    private Enemy enemy;

    @BeforeAll
    public static void init() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        Gdx.gl = Mockito.mock(GL20.class);
        Gdx.gl20 = Mockito.mock(GL20.class);
        new HeadlessApplication(new ApplicationAdapter() {}, config);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        enemies = new ArrayList<>();

        LinkedList<Direction> directions = new LinkedList<>();
        Sprite texture = Mockito.mock(Sprite.class);
        enemy = new Enemy('T', 0, 0, ENEMY_WIDTH, ENEMY_HEIGHT, 100, directions, 10, 1, 0, texture, false);
        enemies.add(enemy);

        defender = new TestableDefender(50, 50, enemies);

    }

    @Test
    public void testEnemyInteraction() {
        defender.update(1.0f);

        assertTrue("Enemy should be targeted by defender", defender.enemy == enemy);
    }

    @Test
    public void testInitialization() {
        assertNotNull("Defender should be initialized", defender);
    }

    @Test
    public void testUpdateWithNoEnemyTargeted() {
        assertNull("Initial enemy should be null", defender.enemy);

        defender.update(1.0f);

        assertNotNull("Enemy should be targeted after update", defender.enemy);
    }

    @Test
    public void testUpdateWithAliveEnemy() {
        float startRotation = 0;
        mockBaseDefender = Mockito.mock(BaseDefender.class);
        when(mockBaseDefender.getEnemy()).thenReturn(mockEnemy);
        mockEnemy = Mockito.mock(Enemy.class);
        when(mockEnemy.isAlive()).thenReturn(true);
        mockHashMap = Mockito.mock(HashMap.class);
        when(mockHashMap.containsKey(mockEnemy)).thenReturn(true);

        mockBaseDefender.update(1.0f);
        assertEquals(startRotation, mockBaseDefender.getRotation(), 0.01);

    }

    @Test
    public void testDamageUpgrade() {
        float initialDamage = 10.0f;
        defender.setDamage(initialDamage);

        defender.damageUpgrade();

        float upgradedDamage = defender.getDamage();
        assertTrue("Damage should increase after upgrade, was: " + upgradedDamage + ", but expected more than: " + initialDamage,
                upgradedDamage > initialDamage);
    }



    @Test
    public void testSpeedUpgrade() {
        float oldSpeed = defender.getSpeed();
        defender.speedUpgrade();
        assertTrue("Speed should increase after upgrade", defender.getSpeed() > oldSpeed);
    }

    @Test
    public void testRangeUpgrade() {
        float oldRange = defender.getRange();
        defender.rangeUpgrade();
        assertTrue("Range should increase after upgrade", defender.getRange() > oldRange);
    }

    @Test
    public void testGetHitBox() {

        TestableDefender defender = new TestableDefender(50, 50, enemies);


        Rectangle hitBox = defender.getHitBox();

        assertEquals(50 - GameConstants.TOWER_SIZE / 2, hitBox.x, 0.01);
        assertEquals(50 - GameConstants.TOWER_SIZE / 2, hitBox.y, 0.01);
        assertEquals(GameConstants.TOWER_SIZE, hitBox.width, 0.01);
        assertEquals(GameConstants.TOWER_SIZE, hitBox.height, 0.01);
    }

    @Test
    public void testSetters(){
        TestableDefender defender = new TestableDefender(50, 50, enemies);
        defender.setDamage(10);
        defender.setRange(10);
        defender.setSpeed(10);
        assertEquals(10, defender.getDamage(), 0.01);
        assertEquals(10, defender.getRange(), 0.01);
        assertEquals(10, defender.getSpeed(), 0.01);
    }


    @Test
    public void testGetPrice() {
        GameAssets.getInstance().init();
        GunnerDefender defender = new GunnerDefender(50, 50, enemies);
        assertTrue(defender.getPrice() == GameConstants.TOWER_PRICE_GUNNER);
    }
    @Test
    public void testSetSpeed() {
        GameAssets.getInstance().init();
        SniperDefender defender = new SniperDefender(50, 50, enemies);
        defender.setSpeed(10);
        assertTrue(defender.getSpeed() == 10);
    }

    @Test
    public void testGetRange() {
        GameAssets.getInstance().init();
        GunnerDefender defender = new GunnerDefender(50, 50, enemies);
        assertTrue(defender.getRange() == GameConstants.TOWER_RANGE);
    }
    @Test
    public void testGetDamage() {
        GameAssets.getInstance().init();
        GunnerDefender defender = new GunnerDefender(50, 50, enemies);
        assertTrue(defender.getDamage() == GameConstants.TOWER_DAMAGE_GUNNER);
    }
    @Test
    public void testGetSpeed() {
        GameAssets.getInstance().init();
        SniperDefender defender = new SniperDefender(50, 50, enemies);
        assertTrue(defender.getSpeed() == GameConstants.TOWER_SPEED_SNIPER);
    }

    @Test
    public void testGetDefender() {
        GameAssets.getInstance().init();
        GunnerDefender defender = new GunnerDefender(50, 50, enemies);
        assertTrue(defender.getDefender() == defender);
    }

    @Test
    public void testGetBoundingRectangle() {
        TestableDefender defender = new TestableDefender(50, 50, enemies);
        Rectangle boundingRectangle = defender.getBoundingRectangle();
        assertEquals(50, boundingRectangle.x, 0.01);
        assertEquals(50, boundingRectangle.y, 0.01);
        assertEquals(GameConstants.TOWER_SIZE, boundingRectangle.width, 0.01);
        assertEquals(GameConstants.TOWER_SIZE, boundingRectangle.height, 0.01);
    }

    @Test
    public void testGetSpeedPrice() {
        GameAssets.getInstance().init();
        GunnerDefender defender = new GunnerDefender(50, 50, enemies);
        assertTrue(defender.getSpeedPrice() == GameConstants.TOWER_SPEED_PRICE);
    }

    @Test
    public void testGetRangePrice() {
        GameAssets.getInstance().init();
        GunnerDefender defender = new GunnerDefender(50, 50, enemies);
        assertTrue(defender.getRangePrice() == GameConstants.TOWER_RANGE_PRICE);
    }

    @Test
    public void testGetAttackCost() {
        GameAssets.getInstance().init();
        GunnerDefender defender = new GunnerDefender(50, 50, enemies);
        assertTrue(defender.getAttackCost() == GameConstants.TOWER_ATTACK_PRICE);
    }
}