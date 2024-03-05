package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.map.Tile;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.enums.GridType;

import java.util.List;

public class ExampleTest {

    private Board board;
    private Set<Vector2> pathPoints;

    @BeforeEach
public void setUp() {
    pathPoints = new HashSet<>();

    pathPoints.add(new Vector2(0, 0 + 1));
    pathPoints.add(new Vector2(1, 1 + 1));
    board = new Board(pathPoints);
}


@Test
public void testCorrectTilesCreated() {
    List<Tile> gameBoardTiles = board.getGameBoard();
    assertEquals(GameConstants.MAP_ROWS * GameConstants.COLUMN_SIZE, gameBoardTiles.size(), "Game board should have the correct number of tiles.");

    for (Tile tile : gameBoardTiles) {

        int xGrid = (int)(tile.getX() / GameConstants.TILE_WIDTH);
        int yGrid = (int)(tile.getY() / GameConstants.TILE_HEIGHT);
        if (pathPoints.contains(new Vector2(xGrid, yGrid))) {
            assertEquals(GridType.PATH, tile.getType(), "Tile at (" + xGrid + "," + yGrid + ") should be a path tile.");
        } else {
            assertEquals(GridType.GROUND, tile.getType(), "Tile at (" + xGrid + "," + yGrid + ") should be a ground tile.");
        }
    }
}




/**
 * Unit tests (example).
 * 
 * (Run using `mvn test`)
 */
// public class ExampleTest {
	/**
	 * Static method run before everything else
	 */
	@BeforeAll
	static void setUpBeforeAll() {
	}

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
	}

	/**
	 * Simple test case
	 */
	@Test
	void dummy1() {
		// Expected result is the first argument, value to be tested is the second.
		// The message is optional.
		assertEquals("foo", "f".concat("oo"), "fooo?");
	}

	/**
	 * Simple test case
	 */
	@Test
	void dummy2() {
		// For floats and doubles it's best to use assertEquals with a delta, since
		// floating-point numbers are imprecise
		float a = 100000;
		a = a + 0.1f;
		assertEquals(100000.1, a, 0.01);
	}

	/**
	 * Parameterized test case, reading arguments from comma-separated strings
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	@CsvSource(value = { "1,1,2", "1,2,3", "2,3,5", "3,5,8", "5,8,13", "8,13,21" })
	@ParameterizedTest(name = "{0}+{1} == {2}")
	void addTest(int a, int b, int c) {
		assertEquals(c, a + b);
	}
}