package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.entity.Reward;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.util.GameConstants;

import java.util.ArrayList;
import java.util.List;

public class EnemyController {

    private final List<Enemy> enemyList;
    private final List<Reward> rewardList;
    private static EnemyController instance;

    private Level level;


    /**
     * Creates a new EnemyController.
     * Used to: add and remove(kill) enemies.
     * @param level used to check whether zombies has completed the path, and award player for kill
     */
    public EnemyController(Level level){
        this.level = level;
        this.enemyList = new ArrayList<>();
        rewardList = new ArrayList<>();
    }
    public static EnemyController getInstance(Level level) {
        if (instance == null) {
            instance = new EnemyController(level);
        }
        return instance;
    }

    /**
     * Adds a new zombie to the list of zombies that's controlled on the map
     * @param zombie the zombie to add
     */
    public void newZombie(Enemy zombie) {
        enemyList.add(zombie);
    }

    /**
     * Iterates the enemies and removes the ones that are either: outside the map or killed(thus also rewards the player for the kill)
     */
    private void removeEnemy() {
        List<Enemy> shouldRemoved = new ArrayList<>();
        for (Enemy enemy : enemyList) {
            if (checkBoundsForEnemy(enemy) && enemy.hasEnteredMap) {
                System.out.println("utenfor bounds og har entret mappet");
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
     * @param enemy enemy we check bounds for
     * @return true if enemy is our of bounds
     */
    private boolean checkBoundsForEnemy(Enemy enemy) {
            return (enemy.position.x + enemy.size.x > GameConstants.SCREEN_WIDTH ||
                    enemy.position.x - enemy.size.x < 0 ||
                    enemy.position.y + enemy.size.y > GameConstants.SCREEN_HEIGHT - (GameConstants.UI_ROWS_TOP * GameConstants.TILE_HEIGHT) ||
                    enemy.position.y - enemy.size.y < GameConstants.UI_ROWS_BOTTOM * GameConstants.TILE_HEIGHT
            );
    }

    public void doubleSpeedClicked() {
        for (Enemy enemy : enemyList) {
            enemy.doubleSpeedClicked();

        }
    }

    public void normalSpeedClicked() {
        for (Enemy enemy : enemyList) {
            enemy.normalSpeedClicked();

        }
    }

    public List<Enemy> getEnemyList(){
        return enemyList;
    }

    public void clearEnemies() {
        enemyList.clear();
    }


    public void update(float elapsedTime) {
        for (Enemy enemy : enemyList) {
            if (!checkBoundsForEnemy(enemy) && !enemy.hasEnteredMap) {
                enemy.enemyEnteredMap();
            }
            enemy.update(elapsedTime);

        }
        removeEnemy();
    }

    public void render(SpriteBatch batch) {
        for (Enemy enemy : enemyList) {
            enemy.render(batch);
        }
    }

    public void render(ShapeRenderer renderer) {
    }
}
