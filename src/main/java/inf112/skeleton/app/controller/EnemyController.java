package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.util.GameConstants;

import java.util.ArrayList;
import java.util.List;

public class EnemyController {

    private final List<Enemy> enemyList;

    private static EnemyController instance;

    private final Level level;


    /**
     * Creates a new EnemyController.
     * Used to: add and remove(kill) enemies.
     * @param level used to check whether zombies has completed the path, and award player for kill
     */
    public EnemyController(Level level){
        this.level = level;
        this.enemyList = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the EnemyController class.
     * If the instance does not exist, it creates a new one with the provided level.
     *
     * @param level The level to be used for the EnemyController.
     * @return The singleton instance of the EnemyController.
     */
    public static EnemyController getInstance(Level level) {
        if (instance == null) {
            instance = new EnemyController(level);
        }
        return instance;
    }

    /**
     * Adds a new zombie to the list of zombies that's controlled on the map
     * @param zombie the enemy to add
     */
    public void newZombie(Enemy zombie) {
        enemyList.add(zombie);
    }

    /**
     * Iterates over the enemies and removes the ones that are either outside the map or killed.
     * Also rewards the player for the kill.
     */
    private void removeEnemy() {
        List<Enemy> shouldRemoved = new ArrayList<>();
        for (Enemy enemy : enemyList) {
            if (checkBoundsForEnemy(enemy) && enemy.hasEnteredMap) {
                shouldRemoved.add(enemy);
                level.enemyCompletedPath();
            }
            if (!enemy.isAlive()) {
                shouldRemoved.add(enemy);
                level.enemyKilled(enemy.getReward());
            }
        }
        enemyList.removeAll(shouldRemoved);
    }

    /**
     * Checks if the enemy is out of bounds.
     * @param enemy we check bounds for.
     * @return true if enemy is our of bounds.
     */
    private boolean checkBoundsForEnemy(Enemy enemy) {
            return (enemy.position.x + enemy.size.x > GameConstants.SCREEN_WIDTH ||
                    enemy.position.x - enemy.size.x < 0 ||
                    enemy.position.y + (enemy.size.y / 2) > GameConstants.SCREEN_HEIGHT - (GameConstants.UI_ROWS_TOP * GameConstants.TILE_HEIGHT) ||
                    enemy.position.y + (enemy.size.y / 2) < GameConstants.UI_ROWS_BOTTOM * GameConstants.TILE_HEIGHT
            );
    }

    /**
     * Doubles the speed of all enemies.
     */
    public void doubleSpeedClicked() {
        for (Enemy enemy : enemyList) {
            enemy.doubleSpeedClicked();

        }
    }

    /**
     * Resets the speed of all enemies to their normal speed.
     */
    public void normalSpeedClicked() {
        for (Enemy enemy : enemyList) {
            enemy.normalSpeedClicked();

        }
    }


    /**
     * Returns the list of enemies.
     * @return The list of enemies.
     */
    public List<Enemy> getEnemyList(){
        return enemyList;
    }


    /**
     * Clears the list of enemies.
     */
    public void clearEnemies() {
        enemyList.clear();
    }

    /**
     * Updates the state of all enemies and removes any that are out of bounds or killed.
     * @param elapsedTime The time elapsed since the last update.
     */
    public void update(float elapsedTime) {
        for (Enemy enemy : enemyList) {
            if (!checkBoundsForEnemy(enemy) && !enemy.hasEnteredMap) {
                enemy.enemyEnteredMap();
            }
            enemy.update(elapsedTime);

        }
        removeEnemy();
    }

    /**
     * Renders all enemies.
     * @param batch The SpriteBatch used for rendering.
     */
    public void render(SpriteBatch batch) {
        for (Enemy enemy : enemyList) {
            enemy.render(batch);
        }
    }

    /**
     * Checks if the enemy is out of bounds.
     * This method is used for testing.
     * @param enemy The enemy to check bounds for.
     * @return true if the enemy is out of bounds.
     */
    public boolean boundsPublic(Enemy enemy) {
        return checkBoundsForEnemy(enemy);
    }
}
