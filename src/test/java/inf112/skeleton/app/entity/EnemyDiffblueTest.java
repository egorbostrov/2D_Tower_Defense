package inf112.skeleton.app.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;

import inf112.skeleton.app.level.Level;
import org.junit.jupiter.api.Test;

class EnemyDiffblueTest {
    /**
     * Method under test:
     * {@link Enemy#newEnemy(char, Level, float, float, float, boolean)}
     */
    @Test
    void testNewEnemy() {
        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class, () -> Enemy.newEnemy('A', null, 10.0f, 10.0f, 10.0f, true));
    }
}
