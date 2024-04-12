package inf112.skeleton.app.util;

import com.badlogic.gdx.math.Vector2;

/**
 * All constants used in the game
 * */
public class GameConstants {
    public static final int TOWER_PRICE = 0;
    public static float SCREEN_HEIGHT = 720;
    public static float SCREEN_WIDTH = (float) (SCREEN_HEIGHT * 16) / 9;

    public static int COLUMN_SIZE = 20;
    public static int ROW_SIZE = 12;

    public static int MAP_ROWS = 9;
    public static int UI_ROWS_BOTTOM = 2;

    public static float TILE_WIDTH = SCREEN_WIDTH / COLUMN_SIZE;
    public static float TILE_HEIGHT = SCREEN_HEIGHT / ROW_SIZE;

    public static float ENEMY_WIDTH = TILE_WIDTH / (float)1.2;
    public static float ENEMY_HEIGHT = TILE_HEIGHT / (float)1.2;

    //REGULAR ZOMBIE VALUES:
    public static int EMEMY_REGULAR_SPEED = (int) TILE_WIDTH;
    public static float ENEMY_REGULAR_START_HP = 100f;
    public static int ENEMY_REGULAR_BOUNTY = 100;
    public static float ENEMY_REGULAR_SPAWN_DELAY = 1.5f;

    //TANK ZOMBIE VALUES:
    public static int ENEMY_TANK_SPEED = (int) TILE_WIDTH/2;
    public static float ENEMY_TANK_START_HP = 20f;
    public static int ENEMY_TANK_BOUNTY = 150;
    public static float ENEMY_TANK_SPAWN_DELAY = 3f;

    public static int START_MONEY = 1000;
    public static int REMAINING_HEALTH = 6;
    public static int SCORE_INCREASE = 100;
    public static float TOWER_SIZE = TILE_WIDTH;
    public static float TOWER_RANGE = TILE_WIDTH * 2;
    public static int TOWER_ATTACK_PRICE = 15;
    public static int TOWER_RANGE_PRICE = 15;
    public static int TOWER_SPEED_PRICE = 15;

    public static int TOWER_DAMAGE_GUNNER = 5;
    public static final float TOWER_DAMAGE_SNIPER = 15;
    public static final float TOWER_RANGE_SNIPER = 999;
    public static final float TOWER_SPEED_SNIPER = 5;


    public static final float TOWER_DAMAGE_BOMBER = 10;



    public static float BULLET_WIDTH = TILE_WIDTH / 2;
    public static float BULLET_HEIGHT = TILE_HEIGHT / 2;

    public static Vector2 START_POS = new Vector2(0, UI_ROWS_BOTTOM * TILE_HEIGHT - TILE_HEIGHT);
}
