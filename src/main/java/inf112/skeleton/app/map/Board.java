package inf112.skeleton.app.map;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.enums.GridType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Board {
    private final List<Tile> gameBoard; //All tiles, building a gameBoard, holding either path or grass tiles.
    private final Set<Vector2> pathPoints; //Set with the coordinates of all path tiles on the map
    private boolean render = false;

    public Board(Set<Vector2> pathPoints) {
        this.pathPoints = pathPoints;
        gameBoard = new ArrayList<>();
        createGameBoard();
    }

    /**
     * Creates board with tiles using given gameconstants
     */
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
     * Returns a list of all the tiles in the gameboard.
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
        if (render) {
            for (Tile tile : gameBoard) {
                tile.render(renderer);
            }
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
     * Updates the tiles on hte game board
     * @param elapsedTime time from previous frame to current frame
     *
     */
    public void update(float elapsedTime) {
        for (Tile tile : gameBoard) {
            tile.update(elapsedTime);
        }
    }

    /**
     * Sets this.render to true/false based on the given param.
     * If ture, them render will happen, if false then render will not happen
     * @param onOrOff switch for rendering
     */
    public void renderSwitch(boolean onOrOff) {
        this.render = onOrOff;
    }
}
