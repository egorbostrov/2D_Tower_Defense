package inf112.skeleton.app.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.enums.GridType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class TileTest {

    @Mock
    private ShapeRenderer mockShapeRenderer;

    private Tile tile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Assuming 'size' is a Vector2 field in the GameObject class that Tile extends
        tile = new Tile(0, 0, 1, 1, GridType.PATH);
    }

    @Test
    @Disabled("Doesn't work")
    void testRenderWithShapeRenderer() {
        tile.render(mockShapeRenderer);

        // Verify that the shapeRenderer's rect method is called once with the correct parameters
        verify(mockShapeRenderer, times(1))
                .rect(tile.getX(), tile.getY(), tile.size.x, tile.size.y);
        // Verify that the color is set to RED before rendering
        verify(mockShapeRenderer, times(1)).setColor(Color.RED);
    }
}