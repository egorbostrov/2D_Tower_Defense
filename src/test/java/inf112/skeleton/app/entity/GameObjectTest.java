package inf112.skeleton.app.entity;

import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    private GameObject gameObject;
    private final float xCord = 10.0f;
    private final float yCord = 15.0f;
    private final float width = 5.0f;
    private final float height = 10.0f;

    @BeforeEach
    void setUp() {
        // Since GameObject is abstract, we create an anonymous subclass for testing.
        gameObject = new GameObject(xCord, yCord, width, height) {};
    }

    @Test
    void constructorShouldInitializeFields() {
        // Test that the position is set correctly
        assertEquals(xCord, gameObject.getPositionOfObject().x);
        assertEquals(yCord, gameObject.getPositionOfObject().y);

        // Test that the size is set correctly
        assertEquals(width, gameObject.size.x);
        assertEquals(height, gameObject.size.y);

        // Test that the bounds rectangle is created correctly
        Rectangle bounds = gameObject.boundsRectangle;
        assertEquals(xCord, bounds.x);
        assertEquals(yCord, bounds.y);
        assertEquals(width, bounds.width);
        assertEquals(height, bounds.height);

        // Test that the object is visible
        assertTrue(gameObject.isVisible);
    }

    @Test
    void updateShouldCorrectlyUpdateBoundsAndCenter() {
        // Move the GameObject and call update
        float elapsedTime = 1.0f; // The actual value of elapsedTime doesn't affect this test
        gameObject.position.set(20.0f, 30.0f);
        gameObject.update(elapsedTime);

        // Check that the bounds have been updated
        Rectangle bounds = gameObject.boundsRectangle;
        assertEquals(20.0f, bounds.x);
        assertEquals(30.0f, bounds.y);
        assertEquals(width, bounds.width);
        assertEquals(height, bounds.height);

        // Check that the center has been updated
        assertEquals(20.0f + width / 2, gameObject.center.x);
        assertEquals(30.0f + height / 2, gameObject.center.y);
    }

    // Add more tests for other methods and behaviors...
}
