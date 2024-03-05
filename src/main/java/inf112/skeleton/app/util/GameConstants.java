package inf112.skeleton.app.util;

public class GameConstants {
    public static float SCREEN_HEIGHT = 720;
    public static float SCREEN_WIDTH = (float) (SCREEN_HEIGHT * 16) / 9;

    public static int COLUMN_SIZE = 20;
    public static int ROW_SIZE = 12;

    public static int MAP_ROWS = 9;
    public static int UI_ROWS = ROW_SIZE - MAP_ROWS;

    public static float TILE_WIDTH = SCREEN_WIDTH / COLUMN_SIZE;
    public static float TILE_HEIGHT = SCREEN_HEIGHT / ROW_SIZE;

    public static float ENEMY_WIDTH = TILE_WIDTH / (float)2;
    public static float ENEMY_HEIGHT = TILE_HEIGHT / (float)2;

    public static int START_MONEY = 1000;
    public static int REMAINING_HEALTH = 6;
    public static int SCORE_INCREASE = 100;
    public static float TOWER_SIZE = TILE_WIDTH;
    public static float TOWER_RANGE = TILE_WIDTH * 2;
    public static int TOWER_ATTACK_PRICE = 15;
    public static int TOWER_RANGE_PRICE = 15;
    public static int TOWER_SPEED_PRICE = 15;

    public static float BULLET_WIDTH = TILE_WIDTH / 2;
    public static float BULLET_HEIGHT = TILE_HEIGHT / 2;
}
