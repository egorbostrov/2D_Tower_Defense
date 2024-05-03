package inf112.skeleton.app.entity;

import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameObjectTest {

    private GameObject gameObject;
    private final float xCord = 10.0f;
    private final float yCord = 15.0f;
    private final float width = 5.0f;
    private final float height = 10.0f;

    @BeforeEach
    void setUp() {
        gameObject = new GameObject(xCord, yCord, width, height) {};
    }

    @Test
    void constructorShouldInitializeFields() {
        assertEquals(xCord, gameObject.getPositionOfObject().x);
        assertEquals(yCord, gameObject.getPositionOfObject().y);

        assertEquals(width, gameObject.size.x);
        assertEquals(height, gameObject.size.y);

        Rectangle bounds = gameObject.boundsRectangle;
        assertEquals(xCord, bounds.x);
        assertEquals(yCord, bounds.y);
        assertEquals(width, bounds.width);
        assertEquals(height, bounds.height);

        assertTrue(gameObject.isVisible);
    }

    @Test
    void updateShouldCorrectlyUpdateBoundsAndCenter() {
        float elapsedTime = 1.0f;
        gameObject.position.set(20.0f, 30.0f);
        gameObject.update(elapsedTime);

        Rectangle bounds = gameObject.boundsRectangle;
        assertEquals(20.0f, bounds.x);
        assertEquals(30.0f, bounds.y);
        assertEquals(width, bounds.width);
        assertEquals(height, bounds.height);

        assertEquals(20.0f + width / 2, gameObject.center.x);
        assertEquals(30.0f + height / 2, gameObject.center.y);
    }
}
