package inf112.skeleton.app.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.entity.Bullet;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.entity.GameObject;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;

import java.util.*;

public abstract class BaseDefender extends GameObject {

    protected List<Enemy> enemies;
    protected List<Bullet> bullets;
    protected HashMap<Enemy, Float> enemyDistanceMap;
    protected DefenderType defenderType;
    private boolean isSelected = false;
    private boolean isSelectedDefender = false;

    protected Enemy enemy;
    protected float range;
    protected float damage;

    protected float rotation = 0;
    protected float speedCounter = 0;
    protected float speed = 2f; // per second

    protected int price;
    protected int rangePrice;
    protected int attackPrice;
    protected int speedPrice;

    /**
     * Creates a Defender GameObject with given position size and enemies to defeat.
     * @param xCord position on a-axis
     * @param yCord position on y-axis
     * @param enemies enemies to defeat
     */
    public BaseDefender(float xCord, float yCord, List<Enemy> enemies) {
        super(xCord, yCord, GameConstants.TOWER_SIZE, GameConstants.TOWER_SIZE);
        this.enemies = enemies;
        this.range = GameConstants.TOWER_RANGE;
        attackPrice = GameConstants.TOWER_ATTACK_PRICE;
        rangePrice = GameConstants.TOWER_RANGE_PRICE;
        speedPrice = GameConstants.TOWER_SPEED_PRICE;

        bullets = new ArrayList<>();
    }

    @Override
    public void render(ShapeRenderer renderer) {
        super.render(renderer);

//
//        for (Bullet bullet : bullets) {
//            bullet.render(renderer);
//        }
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        updateEnemyMap();
        for (Bullet bullet : bullets) {
            bullet.update(deltaTime);
        }
        if (enemy == null) {
            findTarget();
            return;
        }
        if (enemy.isAlive() && enemyDistanceMap.containsKey(enemy)) {
            checkRotation();
            startFiring(deltaTime);
        } else {
            enemy = null;
        }
        removeBullet();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (sprite == null || spriteSelected == null) {
            Gdx.app.error("BaseDefender", "Sprite textures are not initialized!");
            return;
        }

        if (isSelected) {
            batch.draw(
                    spriteSelected,
                    position.x,
                    position.y,
                    size.x / 2,
                    size.y / 2,
                    size.x,
                    size.y,
                    1,
                    1,
                    rotation);
        } else {
            batch.draw(
                    sprite,
                    position.x,
                    position.y,
                    size.x / 2,
                    size.y / 2,
                    size.x,
                    size.y,
                    1,
                    1,
                    rotation);
        }

        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
    }


    /**
     * Upgrades damage the tower does by 1.5
     * Also ups the next upgrade price
     */
    public void damageUpgrade(){
        damage *= 1.5f;
        attackPrice *= 2;
    }

    /**
     * Upgrades range the tower has by 1.1
     * Also ups the next upgrade price
     */
    public void rangeUpgrade(){
        range *= 1.1f;
        rangePrice *= 2;
    }

    /**
     * Upgrades the speed the tower attacks by 1.1
     * Also ups the next upgrade price
     */
    public void speedUpgrade(){
        speed *= 1.1f;
        speedPrice *= 2;
    }

    /**
     * Calculates the rotation between the center of BaseDefender and enemy
     */
    private void checkRotation(){
        if (enemy != null) {
            Vector2 direction = new Vector2(enemy.center).sub(center);
            float angle = direction.angleDeg();
            boolean shouldFlip = direction.x < 0;
            if (sprite.isFlipX() != shouldFlip) {
                sprite.flip(true, false);
            }
            if (shouldFlip) {
                rotation = angle - 180;
                if (rotation < 0) rotation += 360;
            }else {
                rotation = angle;
            }
        }
    }

    /**
     * Override this if tower shoots sometimes, attacks per speed of tower
     */
    public void projectileFire(){

    }

    /**
     * Override this if tower shoots rapidly, attacks per update
     */
    public void rappidFire(){

    }

    /**
     * Makes the base defender shoot. Ensures the rate of fire is consistent while
     * also allowing for rapid fire condition
     * @param deltaTime time since last frame update
     */
    private void startFiring(float deltaTime){
        rappidFire();
        speedCounter += deltaTime;
        if (speedCounter >= 1f / speed) {
            speedCounter = 0;
            projectileFire();
        }
    }

    /**
     * Removes bullet if bullet is not visible, which means it is outside of map or hit target.
     */
    private void removeBullet(){
        List<Bullet> tempList = new ArrayList<>();
        for (Bullet bullet : bullets) {
            if (!bullet.isVisible()) {
                tempList.add(bullet);
            }
        }
        bullets.removeAll(tempList);
    }

    /**
     * Updates the hashmap containing distance from enemies to the center of the base defender
     */
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

    /**
     * Finds the closest enemy to teh base defender
     */
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
    public Rectangle getHitBox(){
        return new Rectangle(
                position.x - size.x / 2, // Left edge of the tower
                position.y - size.y / 2, // Bottom edge of the tower
                size.x, // Width of the tower
                size.y  // Height of the tower
        );
    }

    public void setSpeed(float attackSpeed) {
        this.speed = attackSpeed;
    }
    public void setDamage(float damage) {
        this.damage = damage;
    }
    public void setRange(float range) {
        this.range = range;
    }
    public float getPrice() { return price;}
    public float getRange() { return range;}
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
    public BaseDefender getDefender() {
        return this;
    }
    public DefenderType getDefenderType() {
        return defenderType;
    }
    public void selectedDefender(boolean selectedDefender){
        isSelectedDefender = selectedDefender;
    }
    public Rectangle getBoundingRectangle() {
        return new Rectangle(position.x, position.y, size.x, size.y);
    }

    /**
     * @return list of bullets shot by the enemy
     */
    public List<Bullet> getBullets() {
        return bullets;
    }
}
