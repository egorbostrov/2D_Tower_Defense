package inf112.skeleton.app.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.enums.GridType;
import inf112.skeleton.app.util.GameConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    private Board board;
    private Set<Vector2> pathPoints;
    private final int totalTiles = GameConstants.MAP_ROWS * GameConstants.COLUMN_SIZE;

    @BeforeEach
    void setUp() {
        pathPoints = new HashSet<>();
        pathPoints.add(new Vector2(0, 0));
        pathPoints.add(new Vector2(1, 0));
        board = new Board(pathPoints);
    }

    @Test
    void boardInitializesCorrectly() {
        List<Tile> gameBoard = board.getGameBoard();
        assertEquals(totalTiles, gameBoard.size(), "Game board should have the correct number of tiles");

        assertTrue(gameBoard.get(0).getType() == GridType.PATH, "The first tile should be a PATH tile");
        assertTrue(gameBoard.get(1).getType() == GridType.PATH, "The second tile should also be a PATH tile");
    }
}
