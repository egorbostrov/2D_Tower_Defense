package inf112.skeleton.app.map;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.enums.GridType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Board {
    private final List<Tile> gameBoard;
    private final Set<Vector2> pathPoints;

    /**
     * Create a gameBoard with a given set of path coordinates
     * @param pathPoints set containing coordinates of all path tiles
     */
    public Board(Set<Vector2> pathPoints) {
        this.pathPoints = pathPoints;
        gameBoard = new ArrayList<>();
        createGameBoard();
    }


    private void createGameBoard() {
        float tileXCordStart, tileYCordStart;
        float tileWidth = GameConstants.TILE_WIDTH;
        float tileHeight = GameConstants.TILE_HEIGHT;

        for (int y = 0; y < GameConstants.MAP_ROWS; y++) {
            for (int x = 0; x < GameConstants.COLUMN_SIZE; x++) {
                tileXCordStart = x * tileWidth;
                tileYCordStart = y * tileHeight + GameConstants.UI_ROWS_BOTTOM * tileHeight;

                if (pathPoints.contains(new Vector2(x, y))) {
                    gameBoard.add(new Tile(tileXCordStart, tileYCordStart, tileWidth, tileHeight, GridType.PATH));
                }
                else {
                    gameBoard.add(new Tile(tileXCordStart, tileYCordStart, tileWidth, tileHeight, GridType.GROUND));
                }
            }
        }
    }

    /**
     * Returns a list of all the tiles in the gameBoard.
     * @return gameBoard
     */
    public List<Tile> getGameBoard() {
        return gameBoard;
    }


    /**
     * Renders given shape
     * @param renderer shape
     */
    public void render(ShapeRenderer renderer) {
        for (Tile tile : gameBoard) {
            tile.render(renderer);
        }
    }


    /**
     * Renders all the tiles in gameBoard
     * @param batch given tile to be rendered
     */
    public void render(SpriteBatch batch) {
        for (Tile tile : gameBoard) {
            tile.render(batch);
        }
    }

    /**
     * Updates the tiles on the game board
     * @param elapsedTime time from previous frame to current frame
     *
     */
    public void update(float elapsedTime) {
        for (Tile tile : gameBoard) {
            tile.update(elapsedTime);
        }
    }
}
