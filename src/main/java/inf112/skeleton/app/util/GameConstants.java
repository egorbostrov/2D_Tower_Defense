package inf112.skeleton.app.util;

public class GameConstants {
    public static float SCREEN_HEIGHT = 720;
    public static float SCREEN_WIDTH = (float) (SCREEN_HEIGHT * 16) / 9;

    public static int COLUMN_SIZE = 16;
    public static int ROW_SIZE = 9;

    public static int MAP_ROWS = 6;
    public static int UI_ROWS = ROW_SIZE - MAP_ROWS;

    public static float TILE_WIDTH = SCREEN_WIDTH / COLUMN_SIZE;
    public static float TILE_HEIGHT = SCREEN_HEIGHT / ROW_SIZE;

    public static float ENEMY_WIDTH = TILE_WIDTH / (float)2;
    public static float ENEMY_HEIGHT = TILE_HEIGHT / (float)2;
}
