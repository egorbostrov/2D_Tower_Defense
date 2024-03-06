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

    public List<Tile> getGameBoard() {
        return gameBoard;
    }


    public void render(ShapeRenderer renderer) {
        if (render) {
            for (Tile tile : gameBoard) {
                tile.render(renderer);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Tile tile : gameBoard) {
            tile.render(batch);
        }
    }

    public void update(float elapsedTime) {
        for (Tile tile : gameBoard) {
            tile.update(elapsedTime);
        }
    }

    public void renderSwitch(boolean onOrOff) {
        this.render = onOrOff;
    }

    public List<Tile> getTileList() {
        return gameBoard;
    }

    public void setRender(boolean b) {
    }
}
