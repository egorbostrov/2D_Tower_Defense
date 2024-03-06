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

public class Map {

    private final LinkedList<Direction> directions;
    private final Board board;
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

        board = new Board(pathTiles);
    }

    public void render(ShapeRenderer renderer) {
        board.render(renderer);
    }

    public void render(SpriteBatch batch) {
        board.render(batch);
    }

    public void update(float elapsedTime) {
        board.update(elapsedTime);
    }

    public LinkedList<Direction> getDirections() {
        return directions;
    }

    public Board getBoard() {
        return board;
    }

    public Tile getSelectedTile(float x, float y) {
        List<Tile> gameBoard = this.board.getTileList();
        for (Tile tile : gameBoard) {
            if (tile.contains(x, y)) {
                return tile;
            }
        }
        throw new RuntimeException("Could not find tile in gameBoard");
    }
}
