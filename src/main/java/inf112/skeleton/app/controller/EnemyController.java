package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.resourceHandler.MyAtlas;

import java.util.ArrayList;
import java.util.List;

import static inf112.skeleton.app.util.GameConstants.*;

public class EnemyController {

    private final Level level;
    private final List<Enemy> enemyList;
    

    public EnemyController(Level level){
        this.level = level;
        this.enemyList = new ArrayList<>();
        float spawnDelayInc = 2.0f;

        for (int i = 0; i < 5; i++) {
            float spawnDelay = i * spawnDelayInc;
            enemyList.add(new Enemy(START_POS.x, START_POS.y, ENEMY_WIDTH, ENEMY_HEIGHT, 5, level.getMap().getDirections(), 5, 30, spawnDelay));
        }
//        enemyList.add(new Enemy(START_POS.x, START_POS.y, ENEMY_WIDTH, ENEMY_HEIGHT, 5, level.getMap().getDirections(), 5, 30));
//        enemyList.add(new Enemy(START_POS.x, START_POS.y, ENEMY_WIDTH, ENEMY_HEIGHT, 5, level.getMap().getDirections(), 5, 25));
//        enemyList.add(new Enemy(START_POS.x, START_POS.y, ENEMY_WIDTH, ENEMY_HEIGHT, 5, level.getMap().getDirections(), 5, 20));
//        enemyList.add(new Enemy(START_POS.x, START_POS.y, ENEMY_WIDTH, ENEMY_HEIGHT, 5, level.getMap().getDirections(), 5, 15));
//        enemyList.add(new Enemy(START_POS.x, START_POS.y, ENEMY_WIDTH, ENEMY_HEIGHT, 5, level.getMap().getDirections(), 5, 10));
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
    }

    public void render(SpriteBatch batch) {
        for (Enemy enemy : enemyList) {
            enemy.render(batch);
        }
    }

    public void render(ShapeRenderer renderer) {
    }
}
