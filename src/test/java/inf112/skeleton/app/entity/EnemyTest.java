package inf112.skeleton.app.entity;

import com.badlogic.gdx.ApplicationAdapter;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.LinkedList;

import inf112.skeleton.app.enums.Direction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EnemyTest {
    private static Enemy enemy;
    private static float originalX;
    private static float originalY;
    private static HeadlessApplication application;
    @BeforeAll
    public static void setupBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);
        Gdx.gl = Mockito.mock(GL20.class);
        Gdx.gl20 = Gdx.gl;
        Mockito.when(Gdx.gl.glGenTexture()).thenReturn(1);
    }

    @BeforeEach
    public void setUpClass() {


        LinkedList<Direction> path = new LinkedList<>();
        Sprite mockSprite = Mockito.mock(Sprite.class);
        Mockito.when(mockSprite.getTexture()).thenReturn(Mockito.mock(com.badlogic.gdx.graphics.Texture.class));

        enemy = new Enemy('R', 0.0f, 0.0f, 1.0f, 1.0f, 100.0f, path, 50, 1, 0.0f, mockSprite, false);
        originalX = enemy.position.x;
        originalY = enemy.position.y;
    }

    @Test
    public void testEnemySpawningAtCorrectPosition() {
        assertNotNull(enemy, "Enemy should be instantiated");
        assertEquals(0.0f, enemy.position.x, 0.001, "Enemy should spawn at the correct X position");
        assertEquals(0.0f, enemy.position.y, 0.001, "Enemy should spawn at the correct Y position");
    }

    @Test
    public void testMovementUpDown() {
        simulateMovement(Direction.UP, Direction.DOWN);
        assertEquals(originalY, enemy.position.y, 0.001, "Enemy should return to original Y position");
    }

    @Test
    public void testMovementDownUp() {
        simulateMovement(Direction.DOWN, Direction.UP);
        assertEquals(originalY, enemy.position.y, 0.001, "Enemy should return to original Y position");
    }

    @Test
    public void testMovementRightLeft() {
        simulateMovement(Direction.RIGHT, Direction.LEFT);
        assertEquals(originalX, enemy.position.x, 0.001, "Enemy should return to original X position");
    }

    @Test
    public void testMovementLeftRight() {
        simulateMovement(Direction.LEFT, Direction.RIGHT);
        assertEquals(originalX, enemy.position.x, 0.001, "Enemy should return to original X position");
    }

    @Test
    public void testEnemyCreationWithTypeQAndDirectionList() {
        Level mockLevel = Mockito.mock(Level.class);
        Map mockMap = Mockito.mock(Map.class);
        LinkedList<Direction> mockDirections = new LinkedList<>();

        when(mockLevel.getMap()).thenReturn(mockMap);
        when(mockMap.getDirections()).thenReturn(mockDirections);

        Enemy enemy = Enemy.newEnemy('Q', mockLevel, 1.0f, 1.0f, 0.0f, false);

        assertEquals('Q', enemy.getType(), "Enemy type should be 'Q'");
        assertEquals(mockDirections, enemy.getDirectionLinkedList(), "Enemy should have the correct direction list");
    }

    @Test
    void testReward() {
        assertEquals(50, enemy.getReward());
    }

    private void simulateMovement(Direction moveOut, Direction moveBack) {
        enemy.getDirectionLinkedList().clear();
        enemy.getDirectionLinkedList().add(moveOut);
        enemy.update((float) 10 / enemy.getSpeed());
        enemy.getDirectionLinkedList().clear();
        enemy.getDirectionLinkedList().add(moveBack);
        enemy.update((float) 10 / enemy.getSpeed());
    }


    @Test
    void testEnemyEnteredMap() {
        enemy.enemyEnteredMap();
        assertTrue(enemy.hasEnteredMap);
    }

    @AfterAll
    public static void cleanUp() {
        if (Gdx.app != null) {
            Gdx.app.exit();
        }
        Gdx.graphics = null;
        Gdx.gl = null;
        Gdx.gl20 = null;
    }
}

