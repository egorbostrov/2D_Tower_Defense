// package inf112.skeleton.app.map;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

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
//         LinkedList<Direction> path = new LinkedList<>();

//         enemy = new Enemy(0.0f, 0.0f, 1.0f, 1.0f, 100.0f, path, 50, moveDistance, 0f);

//         originalX = enemy.position.x;
//         originalY = enemy.position.y;
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

package inf112.skeleton.app.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.LinkedList;

import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.Direction;

public class EnemyTest {
    private Enemy enemy;
    private float originalX;
    private float originalY;
    private final int moveDistance = 10;

    @BeforeEach
    public void setUp() {
    LinkedList<Direction> path = new LinkedList<>();
    Sprite mockSprite = Mockito.mock(Sprite.class);

    enemy = new Enemy('R',0.0f, 0.0f, 1.0f, 1.0f, 100.0f, path, 50, 1, 0.0f, mockSprite);

    originalX = enemy.position.x;
    originalY = enemy.position.y;
}


    @Test
    public void testEnemySpawningAtCorrectPosition() {
        // Test that the enemy is not null upon creation
        assertNotNull(enemy, "Enemy should be instantiated");

        // Test the enemy's initial position
        assertEquals(0.0f, enemy.position.x, 0.001, "Enemy should spawn at the correct X position");
        assertEquals(0.0f, enemy.position.y, 0.001, "Enemy should spawn at the correct Y position");

        // You could also test other initial properties like speed, health, etc.
        assertEquals(1, enemy.getSpeed(), "Enemy should have the correct initial speed");
        assertEquals(100.0f, enemy.getEnemyHealth(), 0.001, "Enemy should have the correct initial health");
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
        enemy.update((float) moveDistance / enemy.getSpeed());
        enemy.getDirectionLinkedList().clear();
        enemy.getDirectionLinkedList().add(moveBack);
        enemy.update((float) moveDistance / enemy.getSpeed());
    }
}
