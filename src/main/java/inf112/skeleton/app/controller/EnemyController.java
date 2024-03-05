package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;

import java.util.ArrayList;
import java.util.List;

public class EnemyController implements Render{

    private final Level level;
    private final List<Enemy> enemyList;
    

    public EnemyController(Level level){
        this.level = level;
        this.enemyList = new ArrayList<>();
    }

    public void doubleSpeedClicked() {
    }

    public void normalSpeedClicked() {
    }

    public List<Enemy> getEnemyList(){
        return enemyList;
    }

    public void update(float elapsedTime) {
    }

    @Override
    public void render(SpriteBatch batch) {
    }

    @Override
    public void render(ShapeRenderer renderer) {
    }
}
