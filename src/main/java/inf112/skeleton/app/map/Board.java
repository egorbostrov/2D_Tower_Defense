package inf112.skeleton.app.map;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.util.GameConstants;

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

        for (int i = 0; i < GameConstants.MAP_ROWS; i++) {
            for (int j = 0; j < GameConstants.COLUMN_SIZE; j++) {
                tileXCordStart = j * tileWidth;
                tileYCordStart = i * tileHeight + tileHeight;//Add tileHeight to leave an empty line of tiles at the top

                if (pathPoints.contains(new Vector2(i, j))) {
                    gameBoard.add(new Tile(tileXCordStart, tileYCordStart, tileWidth, tileHeight, Tile.EnumGridType.PATH));
                }
                else {
                    gameBoard.add(new Tile(tileXCordStart, tileYCordStart, tileWidth, tileHeight, Tile.EnumGridType.GROUND));
                }
            }
        }
    }

    public List<Tile> getGameBoard() {
        return gameBoard;
    }

    public void render(ShapeRenderer sr) {
        if (render) {
            for (Tile tile : gameBoard) {
                tile.render(sr);
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
        this.render = render;
    }
}
