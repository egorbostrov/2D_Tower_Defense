// package inf112.skeleton.app.map;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;

// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.Sprite;

// import java.util.LinkedList;

// import inf112.skeleton.app.entity.Enemy;
// import inf112.skeleton.app.enums.Direction;

// public class EnemyTest {
//     private Enemy enemy;
//     private float originalX;
//     private float originalY;
//     private final int moveDistance = 10;

//     @BeforeEach
//     public void setUp() {
//     LinkedList<Direction> path = new LinkedList<>();
//     Sprite mockSprite = Mockito.mock(Sprite.class);

//     enemy = new Enemy(0.0f, 0.0f, 1.0f, 1.0f, 100.0f, path, 50, 1, 0.0f, mockSprite);

//     originalX = enemy.position.x;
//     originalY = enemy.position.y;
// }


//     @Test
//     public void testEnemySpawningAtCorrectPosition() {
//         // Test that the enemy is not null upon creation
//         assertNotNull(enemy, "Enemy should be instantiated");

//         // Test the enemy's initial position
//         assertEquals(0.0f, enemy.position.x, 0.001, "Enemy should spawn at the correct X position");
//         assertEquals(0.0f, enemy.position.y, 0.001, "Enemy should spawn at the correct Y position");

//         // You could also test other initial properties like speed, health, etc.
//         assertEquals(1, enemy.getSpeed(), "Enemy should have the correct initial speed");
//         assertEquals(100.0f, enemy.getEnemyHealth(), 0.001, "Enemy should have the correct initial health");
//     }

//     @Test
//     public void testMovementInAllDirectionsReturnsToSamePlace() {
//         simulateMovement(Direction.UP, Direction.DOWN);
//         simulateMovement(Direction.RIGHT, Direction.LEFT);

//         assertEquals(originalX, enemy.position.x, 0.001, "Enemy should return to original X position");
//         assertEquals(originalY, enemy.position.y, 0.001, "Enemy should return to original Y position");
//     }

//     private void simulateMovement(Direction moveOut, Direction moveBack) {
//         enemy.getDirectionLinkedList().clear();
//         enemy.getDirectionLinkedList().add(moveOut);
//         enemy.update((float) moveDistance / enemy.getSpeed());
//         enemy.getDirectionLinkedList().clear();
//         enemy.getDirectionLinkedList().add(moveBack);
//         enemy.update((float) moveDistance / enemy.getSpeed());
//     }
// }

// package inf112.skeleton.app.map;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;

// import com.badlogic.gdx.ApplicationAdapter;
// import com.badlogic.gdx.backends.headless.HeadlessApplication;
// import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.GL20;
// import com.badlogic.gdx.graphics.g2d.Sprite;

// import java.util.LinkedList;

// import inf112.skeleton.app.entity.Enemy;
// import inf112.skeleton.app.enums.Direction;

// public class EnemyTest {
//     private Enemy enemy;
//     private float originalX;
//     private float originalY;

//     @BeforeEach
//     public void setUp() {
//         // Set up headless application for libGDX
//         HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
//         new HeadlessApplication(new ApplicationAdapter() {
//             @Override
//             public void create() {
//                 Gdx.gl = Mockito.mock(GL20.class);
//             }
//         }, config);

//         // Mock dependencies
//         LinkedList<Direction> path = new LinkedList<>();
//         Sprite mockSprite = Mockito.mock(Sprite.class);

//         // Create the enemy with mock data
//         enemy = new Enemy(0.0f, 0.0f, 1.0f, 1.0f, 100.0f, path, 50, 1, 0.0f, mockSprite);
//         originalX = enemy.position.x;
//         originalY = enemy.position.y;
//     }

//     @Test
//     public void testEnemySpawningAtCorrectPosition() {
//         assertNotNull(enemy, "Enemy should be instantiated");
//         assertEquals(0.0f, enemy.position.x, 0.001, "Enemy should spawn at the correct X position");
//         assertEquals(0.0f, enemy.position.y, 0.001, "Enemy should spawn at the correct Y position");
//         assertEquals(1, enemy.getSpeed(), "Enemy should have the correct initial speed");
//         assertEquals(100.0f, enemy.getEnemyHealth(), 0.001, "Enemy should have the correct initial health");
//     }

//     @Test
//     public void testMovementInAllDirectionsReturnsToSamePlace() {
//         simulateMovement(Direction.UP, Direction.DOWN);
//         simulateMovement(Direction.RIGHT, Direction.LEFT);

//         assertEquals(originalX, enemy.position.x, 0.001, "Enemy should return to original X position");
//         assertEquals(originalY, enemy.position.y, 0.001, "Enemy should return to original Y position");
//     }

//     private void simulateMovement(Direction moveOut, Direction moveBack) {
//         enemy.getDirectionLinkedList().clear();
//         enemy.getDirectionLinkedList().add(moveOut);
//         enemy.update((float) 10 / enemy.getSpeed());
//         enemy.getDirectionLinkedList().clear();
//         enemy.getDirectionLinkedList().add(moveBack);
//         enemy.update((float) 10 / enemy.getSpeed());
//     }

//     @AfterEach
//     public void cleanUp() {
//         // Proper clean-up to prevent memory leaks or interference with other tests
//         Gdx.app.exit();
//         Gdx.graphics = null;
//         Gdx.gl = null;
//         Gdx.gl20 = null;
//     }
// }

package inf112.skeleton.app.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.LinkedList;

import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.Direction;

public class EnemyTest {
    private static Enemy enemy;
    private static float originalX;
    private static float originalY;

    @BeforeAll
    public static void setUpClass() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {
                Gdx.gl = Mockito.mock(GL20.class);
                Gdx.gl20 = Gdx.gl;
                // Mock the glGenTexture method to avoid native calls
                Mockito.when(Gdx.gl.glGenTexture()).thenReturn(1);
            }

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
        });

        // Mock dependencies
        LinkedList<Direction> path = new LinkedList<>();
        Sprite mockSprite = Mockito.mock(Sprite.class);
        Mockito.when(mockSprite.getTexture()).thenReturn(Mockito.mock(com.badlogic.gdx.graphics.Texture.class));

        // Initialize enemy with mock data
        enemy = new Enemy('R', 0.0f, 0.0f, 1.0f, 1.0f, 100.0f, path, 50, 1, 0.0f, mockSprite);
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
    public void testMovementInAllDirectionsReturnsToSamePlace() {
        simulateMovement(Direction.UP, Direction.DOWN);
        simulateMovement(Direction.RIGHT, Direction.LEFT);

        assertEquals(originalX, enemy.position.x, 0.001, "Enemy should return to original X position");
        assertEquals(originalY, enemy.position.y, 0.001, "Enemy should return to original Y position");
    }

    private void simulateMovement(Direction moveOut, Direction moveBack) {
        enemy.getDirectionLinkedList().clear();
        enemy.getDirectionLinkedList().add(moveOut);
        enemy.update((float) 10 / enemy.getSpeed());
        enemy.getDirectionLinkedList().clear();
        enemy.getDirectionLinkedList().add(moveBack);
        enemy.update((float) 10 / enemy.getSpeed());
    }

    @AfterAll
    public static void cleanUp() {
        // Properly clean up the libGDX application to prevent memory leaks
        if (Gdx.app != null) {
            Gdx.app.exit();
        }
        Gdx.graphics = null;
        Gdx.gl = null;
        Gdx.gl20 = null;
    }
}

