package inf112.skeleton.app.tower;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.entity.Bullet;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.entity.GameObject;
import inf112.skeleton.app.util.GameConstants;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseDefender extends GameObject {

    protected List<Enemy> enemyList;
    protected List<Bullet> bulletList;
    protected HashMap<Enemy, Float> enemyMap;
    protected DefenderType type;

    protected Enemy target;
    protected float range;
    protected float damage;

    protected float rotation = 0;
    protected float speedCounter = 0;
    protected float speed = 2f; // per second

    protected int rangePrice;
    protected int attackPrice;
    protected int speedPrice;

    public BaseDefender(float xCord, float yCord, List<Enemy> enemyList) {
        super(xCord, yCord, GameConstants.TOWER_SIZE, GameConstants.TOWER_SIZE);
        this.enemyList = enemyList;
        this.range = GameConstants.TOWER_RANGE;

        attackPrice = GameConstants.TOWER_ATTACK_PRICE;
        rangePrice = GameConstants.TOWER_RANGE_PRICE;
        speedPrice = GameConstants.TOWER_SPEED_PRICE;
    }

    public enum DefenderType {
        GUNNER, SNIPER, BOMBER
    }

    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.RED);
        if (isSelected) {
            renderer.circle(center.x, center.y, range);
        }
        for (Bullet bullet : bulletList) {
            bullet.render(renderer);
        }
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        updateTargetMap();
        bulletList.forEach(bullet -> bullet.update(deltaTime));
        if (target == null || !target.isAlive()) {
            findTarget();
            if (target == null){
                return;
            }
        }
        calculateRotation();
        startShooting(deltaTime);
        removeBullet();
    }

    public void render(SpriteBatch batch) {
        Sprite activeSprite = isSelected ? spriteSelected : sprite;
        batch.draw(activeSprite, position.x, position.y, size.x / 2, size.y / 2, size.x, size.y, 1, 1, rotation);

        bulletList.forEach(bullet -> bullet.render(batch));
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

    private void calculateRotation(){
        Vector2 temp = new Vector2(target.center).sub(center);
        rotation = temp.angle() + 90f;
    }

    public void projectileShoot(){
       // Override this if tower shoots sometimes, attacks per speed of tower
    }

    public void rappidShoot(){
        // Override this if tower shoots rapidly, attacks per update
    }

    private void startShooting(float deltaTime){
        rappidShoot();
        speedCounter += deltaTime;
        if (speedCounter >= 1f / speed) {
            speedCounter = 0;
            projectileShoot();
        }
    }

    private void removeBullet(){
        bulletList.removeIf(bullet -> !bullet.isVisible());
    }

    private void updateTargetMap() {
        enemyMap = new HashMap<>();
        for (Enemy enemy : enemyList) {
            float distance = center.dst(enemy.position);
            if (distance <= range && enemy.isAlive()) {
                enemyMap.put(enemy, distance);
            }
        }
    }

    private void findTarget() {
        this.target = enemyMap.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
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
    public DefenderType getType() {
        return type;
    }

}
