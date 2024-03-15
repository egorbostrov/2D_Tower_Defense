package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.entity.Reward;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.util.GameConstants;

import java.util.ArrayList;
import java.util.List;

import static inf112.skeleton.app.util.GameConstants.*;

public class EnemyController {

    private final Level level;
    private final List<Enemy> enemyList;
    private final List<Reward> rewardList;
    

    public EnemyController(Level level){
        this.level = level;
        this.enemyList = new ArrayList<>();
        rewardList = new ArrayList<>();
        float spawnDelayInc = 2.0f;

        for (int i = 0; i < 6; i++) {
            float spawnDelay = i * spawnDelayInc;
            enemyList.add(new Enemy(
                    START_POS.x,
                    START_POS.y,
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    10,
                    level.getMap().getDirections(),
                    5,
                    50,
                    spawnDelay
            ));
        }
    }

    /**
     * Iterates the enemies and removes the ones that are either: outside the map or killed(thus also rewards the player for the kill)
     */
    private void removeEnemy() {
        List<Enemy> shouldRemoved = new ArrayList<>();
        for (Enemy e : enemyList) {
            if (e.position.x + e.size.x > GameConstants.SCREEN_WIDTH || e.position.y + e.size.y > GameConstants.SCREEN_HEIGHT) {
            //FIX , we have to check bound of the gameboard, not the screen. Changing this might create issues where enemies will be removed instantly, as they are spawned outside the gameboard.
                shouldRemoved.add(e);
                level.enemyPassedTheCheckPoint();
            }
            if (!e.isAlive()) {
                shouldRemoved.add(e);
                level.enemyKilled(e.getReward());
            }
        }
        enemyList.removeAll(shouldRemoved);
    }

    public void doubleSpeedClicked() {
    }

    public void normalSpeedClicked() {
    }

    public List<Enemy> getEnemyList(){
        return enemyList;
    }

    public void update(float elapsedTime) {
        for (Enemy enemy : enemyList) {
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
