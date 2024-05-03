package inf112.skeleton.app.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.*;

import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.controller.Render;
import inf112.skeleton.app.enums.GridType;

public class Map implements Render{
    private FileHandle filehandle;
    private LinkedList<Direction> directions;
    private Board board;
    private final int selectedMap;

    /**
     * Hard coded map using directions that the path has
     */
    public Map(int mapSelection) {
        this.selectedMap = mapSelection;
        initializeMap();
    }

    private void initializeMap() {
        selectFileHandle();
        String fileContent = filehandle.readString();
        this.directions = createDirectionList(fileContent);
        Set<Vector2> pathTiles = createPathSet(fileContent);
        board = new Board(pathTiles);
    }

    private void selectFileHandle() {
        switch (selectedMap) {
            case 1 -> filehandle = Gdx.files.internal("maps/map1.txt");
            case 2 -> filehandle = Gdx.files.internal("maps/map2.txt");
            default -> throw new IllegalArgumentException("Found no map for the value:  " + selectedMap);
        }
    }

    private LinkedList<Direction> createDirectionList(String fileContent) {
        LinkedList<Direction> directions = new LinkedList<>();
        String[] lines = fileContent.split("\n");
        boolean readingDir = false;

        for(String line : lines) {
            if(line.startsWith("Directions:")) {
                readingDir = true;
                continue;
            }
            if(line.startsWith("Paths:")) {
                readingDir = false;
                break;
            }
            if (readingDir) {
                String[] dirs = line.split(",\\s*");
                for(String dir : dirs) {
                    dir = dir.trim();
                    switch (dir) {
                        case "U" -> directions.add(Direction.UP);
                        case "D" -> directions.add(Direction.DOWN);
                        case "L" -> directions.add(Direction.LEFT);
                        case "R" -> directions.add(Direction.RIGHT);
                    }
                }
            }
        }
        return directions;
    }

//Some help from chatGPT with the string handling here.
    private Set<Vector2> createPathSet(String fileContent) {
        Set<Vector2> pathTiles = new HashSet<>();
        String[] lines = fileContent.split("\n");
        boolean readingPath = false;

        for(String line : lines) {
            if (line.startsWith("Paths:")) {
                readingPath = true;
                continue;
            }
            if(readingPath) {
                for(String coordinate : line.split(", ")) {
                    String[] parts = coordinate.trim().replace("(", "").replace(")","").split(",");
                    float x = Float.parseFloat(parts[0]);
                    float y = Float.parseFloat(parts[1]);
                    pathTiles.add(new Vector2(x, y));
                }
            }
        }
        return pathTiles;
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
        return new Tile(-1, -1, 0, 0, GridType.ILLEGALPLACEMENT);
    }

    /**
     * Getter used in test
     * @return current filehandle for map
     */
    public FileHandle getFilehandle(){
        return this.filehandle;
    }
}
