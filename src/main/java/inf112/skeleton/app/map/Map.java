package inf112.skeleton.app.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.controller.Render;

public class Map implements Render{

    private final LinkedList<Direction> directions;
    private final Board board;

    /**
     * Hard coded map using directions that the path has
     */
    public Map() {
        directions = new LinkedList<>();
        directions.add(Direction.UP);
        directions.add(Direction.UP);
        directions.add(Direction.UP);
        directions.add(Direction.UP);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.DOWN);
        directions.add(Direction.DOWN);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);


        Set<Vector2> pathTiles = new HashSet<>();
        pathTiles.add(new Vector2(0, 0));
        pathTiles.add(new Vector2(0, 1));
        pathTiles.add(new Vector2(0, 2));
        pathTiles.add(new Vector2(0, 3));
        pathTiles.add(new Vector2(1, 3));
        pathTiles.add(new Vector2(2, 3));
        pathTiles.add(new Vector2(2, 2));
        pathTiles.add(new Vector2(2, 1));
        pathTiles.add(new Vector2(3, 1));
        pathTiles.add(new Vector2(4, 1));
        pathTiles.add(new Vector2(5, 1));
        pathTiles.add(new Vector2(6, 1));
        pathTiles.add(new Vector2(7, 1));
        pathTiles.add(new Vector2(8, 1));
        pathTiles.add(new Vector2(9, 1));
        pathTiles.add(new Vector2(10, 1));
        pathTiles.add(new Vector2(11, 1));
        pathTiles.add(new Vector2(12, 1));
        pathTiles.add(new Vector2(13, 1));
        pathTiles.add(new Vector2(14, 1));
        pathTiles.add(new Vector2(15, 1));
        pathTiles.add(new Vector2(16, 1));
        pathTiles.add(new Vector2(17, 1));
        pathTiles.add(new Vector2(18, 1));
        pathTiles.add(new Vector2(19, 1));
        pathTiles.add(new Vector2(20, 1));
        pathTiles.add(new Vector2(21, 1));
        pathTiles.add(new Vector2(22, 1));
        pathTiles.add(new Vector2(23, 1));
        pathTiles.add(new Vector2(24, 1));
        pathTiles.add(new Vector2(25, 1));



        board = new Board(pathTiles);
    }


    public void render(ShapeRenderer renderer) {
        board.render(renderer);
    }

    public void render(SpriteBatch batch) {
        board.render(batch);
    }

    /**
     * Updates the board
     * @param elapsedTime time from previous frame to current frame
     */
    public void update(float elapsedTime) {
        board.update(elapsedTime);
    }

    /**
     *
     * @return List of all directions in the map
     */
    public LinkedList<Direction> getDirections() {
        return directions;
    }

    /**
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     *
     * @param x coordinate for tile
     * @param y coordinate for tile
     * @return the selected tile
     */
    public Tile getSelectedTile(float x, float y) {
        List<Tile> gameBoard = this.board.getGameBoard();
        for (Tile tile : gameBoard) {
            if (tile.contains(x, y)) {
                return tile;
            }
        }
        throw new RuntimeException("Could not find tile in gameBoard");
    }
}
