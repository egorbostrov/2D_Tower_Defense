package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.entity.Bounty;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;
import inf112.skeleton.app.util.GameConstants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EnemyController implements Render{

    private final Level level;
    private float timeToSpwn = 0;
    private int count;
    private float spwnPeriod;
    private float health;
    private int limitOfEnemies;
    private int bounty;
    private final LinkedList<Direction> directionLinkedList;

    private int timerForNextWave = 0;

    private int enemySpeed = GameConstants.ENEMY_SPEED;

    private final List<Bounty> allBounties;

    private final List<Enemy> allEnemies;

    

    public EnemyController(Level level){
        this.level = level;
        this.allBounties = new ArrayList<>();
        this.allEnemies = new ArrayList<>();
        Map map = level.getMap();
        this.directionLinkedList = map.getDirections();
        this.health = level.getEnemyHealth();
        this.limitOfEnemies = level.getEnemyNumber();
        this.count = level.getEnemyNumber();

    }

    public void doubleSpeedClicked() {
    }

    public void normalSpeedClicked() {
    }

    public List<Enemy> getallEnemies(){
        return allEnemies;
    }

    public void update(float elapsedTime) {
        for (Enemy enemy : allEnemies){
            enemy.update(elapsedTime);
        }
        for (Bounty bounty : allBounties){
            bounty.update(elapsedTime);
        }

    }

    private void deleteMoney(){
        List<Bounty> bounties = new ArrayList<>();
        for (Bounty bounty : allBounties){
            if(bounty.isRemovable()){
                bounties.add(bounty);
            }
        }
        allBounties.removeAll(bounties);
    }

    @Override
    public void render(SpriteBatch batch) {
        for (Enemy enemy : allEnemies){
            enemy.render(batch);
        }
        for (Bounty bounty : allBounties){
            bounty.render(batch);
        }
    }

    @Override
    public void render(ShapeRenderer renderer) {
        for (Enemy enemy : allEnemies){
            enemy.render(renderer);
        }
    }


}
