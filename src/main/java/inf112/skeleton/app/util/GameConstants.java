package inf112.skeleton.app.util;

import com.badlogic.gdx.math.Vector2;

/**
 * All constants used in the game
 */
public class GameConstants {
    // Screen dimensions
    public static final int TOWER_PRICE = 500;
    public static float SCREEN_HEIGHT = 720;
    public static float SCREEN_WIDTH = (float) (SCREEN_HEIGHT * 16) / 9;

    // Grid sizes
    public static int COLUMN_SIZE = 20;
    public static int ROW_SIZE = 12;
    public static int MAP_ROWS = 9;
    public static int UI_ROWS_BOTTOM = 2;

    // Tile dimensions
    public static float TILE_WIDTH = SCREEN_WIDTH / COLUMN_SIZE;
    public static float TILE_HEIGHT = SCREEN_HEIGHT / ROW_SIZE;

    // Enemy dimensions
    public static float ENEMY_WIDTH = TILE_WIDTH / 1.2f;
    public static float ENEMY_HEIGHT = TILE_HEIGHT / 1.2f;

    // Regular Zombie values
    public static int EMEMY_REGULAR_SPEED = (int) TILE_WIDTH;
    public static float ENEMY_REGULAR_START_HP = 100f;
    public static int ENEMY_REGULAR_BOUNTY = 100;

    // Tank Zombie values
    public static int ENEMY_TANK_SPEED = (int) (TILE_WIDTH / 2);
    public static float ENEMY_TANK_START_HP = 20f;
    public static int ENEMY_TANK_BOUNTY = 150;

    // Game values
    public static int START_MONEY = 1000;
    public static int REMAINING_HEALTH = 6;
    public static int SCORE_INCREASE = 100;

    // Tower dimensions and prices
    public static float TOWER_SIZE = TILE_WIDTH;
    public static float TOWER_RANGE = TILE_WIDTH * 2;
    public static int TOWER_ATTACK_PRICE = 15;
    public static int TOWER_RANGE_PRICE = 15;
    public static int TOWER_SPEED_PRICE = 15;
    public static final int TOWER_PRICE_GUNNER = 10;
    public static final int TOWER_PRICE_BOMBER = 10;
    public static final int TOWER_PRICE_SNIPER = 10;

    // Tower attributes
    public static int TOWER_DAMAGE_GUNNER = 1;
    public static final float TOWER_DAMAGE_SNIPER = 15;
    public static final float TOWER_RANGE_SNIPER = 1000;
    public static final float TOWER_SPEED_SNIPER = 1;
    public static final float TOWER_DAMAGE_BOMBER = 10;
    public static final float BOMBER_EXPLOSION_RADIUS = 75;


    // Bullet dimensions
    public static float BULLET_WIDTH = TILE_WIDTH / 2;
    public static float BULLET_HEIGHT = TILE_HEIGHT / 2;

    // Start position
    public static Vector2 START_POS = new Vector2(0, UI_ROWS_BOTTOM * TILE_HEIGHT - TILE_HEIGHT);
}
