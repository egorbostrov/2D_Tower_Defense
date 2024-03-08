package inf112.skeleton.app.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        enemy = new Enemy(0.0f, 0.0f, 1.0f, 1.0f, 100.0f, path, 50, moveDistance, 0f);

        originalX = enemy.position.x;
        originalY = enemy.position.y;
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
