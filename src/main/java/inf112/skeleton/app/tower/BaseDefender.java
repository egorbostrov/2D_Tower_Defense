package inf112.skeleton.app.tower;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.entity.Bullet;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.entity.GameObject;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.util.GameConstants;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseDefender extends GameObject {

    protected List<Enemy> enemies;
    protected List<Bullet> bullets;
    protected HashMap<Enemy, Float> enemyDistanceMap;
    protected DefenderType defenderType;

    protected Enemy enemy;
    protected float range;
    protected float damage;

    protected float rotation = 0;
    protected float speedCounter = 0;
    protected float speed = 2f; // per second

    protected int rangePrice;
    protected int attackPrice;
    protected int speedPrice;

    public BaseDefender(float xCord, float yCord, List<Enemy> enemies) {
        super(xCord, yCord, GameConstants.TOWER_SIZE, GameConstants.TOWER_SIZE);
        this.enemies = enemies;
        this.range = GameConstants.TOWER_RANGE;

        attackPrice = GameConstants.TOWER_ATTACK_PRICE;
        rangePrice = GameConstants.TOWER_RANGE_PRICE;
        speedPrice = GameConstants.TOWER_SPEED_PRICE;
    }

    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.RED);
        renderer.circle(center.x, center.y, range);

//        for (Bullet bullet : bulletList) {
//            bullet.render(renderer);
//        }
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        updateEnemyMap();
        //bulletList.forEach(bullet -> bullet.update(deltaTime));
        if (enemy == null || !enemy.isAlive()) {
            findTarget();
            if (enemy == null){
                return;
            }
        }
        checkRotation();
        startFiring(deltaTime);
        //removeBullet();
    }

    public void render(SpriteBatch batch) {
        Sprite activeSprite = isSelected ? spriteSelected : sprite;
        batch.draw(
                activeSprite,
                position.x,
                position.y,
                size.x / 2,
                size.y / 2,
                size.x,
                size.y,
                1,
                1,
                rotation);

        //bulletList.forEach(bullet -> bullet.render(batch));
    }


    public void damageUpgrade(){
        damage *= 1.5;
        attackPrice *= 2;
    }

    public void rangeUpgrade(){
        range *= 1.1;
        rangePrice *= 2;
    }

    public void speedUpgrade(){
        speed *= 1.1;
        speedPrice *= 2;
    }

    private void checkRotation(){
        rotation = new Vector2(enemy.center).sub(center).angle() + 90;
    }

    public void projectileFire(){
       // Override this if tower shoots sometimes, attacks per speed of tower
    }

    public void rappidFire(){
        // Override this if tower shoots rapidly, attacks per update
    }

    private void startFiring(float deltaTime){
        rappidFire();
        speedCounter += deltaTime;
        if (speedCounter >= 1f / speed) {
            speedCounter = 0;
            projectileFire();
        }
    }

    private void removeBullet(){
        bullets.removeIf(bullet -> !bullet.isVisible());
    }

    private void updateEnemyMap() {
        enemyDistanceMap = new HashMap<>();
        for (Enemy enemy : enemies) {
            float distance = center.dst(enemy.position);
            if (distance <= range && enemy.isAlive()) {
                enemyDistanceMap.put(enemy, distance);
            }
            else {
                enemyDistanceMap.remove(enemy);
            }
        }
    }

    private void findTarget() {
        Enemy closestEnemy = null;
        float closestDistance = Float.MAX_VALUE;

        for (Map.Entry<Enemy, Float> entry : enemyDistanceMap.entrySet()) {
            if (entry.getValue() < closestDistance) {
                closestEnemy = entry.getKey();
                closestDistance = entry.getValue();
            }
        }

        this.enemy = closestEnemy;
    }


    // getters and setters
    public void setSpeed(float attackSpeed) {
        this.speed = attackSpeed;
    }
    public void setDamage(float damage) {
        this.damage = damage;
    }
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public float getRange() {
        return range;
    }
    public float getDamage() {
        return damage;
    }
    public float getSpeed() {
        return speed;
    }
    public int getSpeedPrice() {
        return speedPrice;
    }
    public int getRangePrice() {
        return rangePrice;
    }
    public int getAttackCost() {
        return attackPrice;
    }
    public DefenderType getDefenderType() {
        return defenderType;
    }

}
