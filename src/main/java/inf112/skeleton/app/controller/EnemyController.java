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


    public void newZombie(Enemy zombie) {
        enemyList.add(zombie);
    }

    /**
     * Iterates the enemies and removes the ones that are either: outside the map or killed(thus also rewards the player for the kill)
     */
    private void removeEnemy() {
        List<Enemy> shouldRemoved = new ArrayList<>();
        for (Enemy e : enemyList) {
            if (e.position.x + e.size.x > GameConstants.SCREEN_WIDTH || e.position.y + e.size.y > GameConstants.SCREEN_HEIGHT) {
                shouldRemoved.add(e);
                level.enemyCompletedPath();
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
