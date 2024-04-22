package inf112.skeleton.app.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.math.Rectangle;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.resourceHandler.MyAtlas;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;

import java.util.LinkedList;


import static inf112.skeleton.app.util.GameConstants.*;


public class Enemy extends GameObject{

    private float currentHealth;
    private final int reward;
    private int speed;
    private float distanceToTile;
    private Direction currentDirection;
    private final LinkedList<Direction> directionLinkedList;
    private final float spawnDelay;
    private float elapsedTimeStart;

    private boolean alive = true;
    private float slowTime;
    private boolean isSlowed = false;
    private final HealthBar hpBar;

    private final float height;
    public Enemy(float x, float y, float width, float height, float currentHealth, LinkedList<Direction> directionLinkedList, int reward, int speed, float spawnDelay, Sprite texture){
        super(x, y, width, height);
        this.height = height;
        this.speed = speed;
        this.directionLinkedList = new LinkedList<>(directionLinkedList);
        this.currentHealth = currentHealth;
        this.reward = reward;
        this.sprite = texture;

        this.spawnDelay = spawnDelay;
        this.elapsedTimeStart = 0;

        getNextDistance();
        hpBar = new HealthBar(x, y - height / 5, width, height / 10, currentHealth);
    }

    public static Enemy newEnemy(char type, Level level, float spawnDelay) {
        return switch(type) {
            case 'R'-> new Enemy(
                    START_POS.x,
                    START_POS.y,
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    ENEMY_REGULAR_START_HP,
                    level.getMap().getDirections(),
                    ENEMY_REGULAR_BOUNTY,
                    ENEMY_REGULAR_SPEED,
                    (spawnDelay),
                    GameAssets.zombieSprite); //GameAssets.
            case 'T' -> new Enemy(
                    START_POS.x,
                    START_POS.y,
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    ENEMY_TANK_START_HP,
                    level.getMap().getDirections(),
                    ENEMY_TANK_BOUNTY,
                    ENEMY_TANK_SPEED,
                    (spawnDelay),
                    GameAssets.zombieSprite);
            default -> throw new IllegalArgumentException("No available zombie for: " + type);
        };
    }

    /**
     * Called when an enemy is hit by a bullet.
     * Removes health according to the bullets damage, then checks if the enemy is still alive.
     * Updates the hpBar according to the new health.
     * @param damage the amount of health to remove from the enemy
     */
    public void shot(float damage){
        this.currentHealth -= damage;

        if (this.currentHealth <= 0){
            alive = false;
            isVisible = false;
        } else{
            hpBar.setHealth(currentHealth);
        }
    }

    /**
     * Get method used in testing
     * @return this.speed
     */
    public int getSpeed(){
        return this.speed;
    }

    /**
     * Get method used in testing
     * @return directionLinkedList
     */
    public LinkedList<Direction> getDirectionLinkedList(){
        return this.directionLinkedList;
    }

    /**
     *Sets the next movement distance, according to the current directions.
     * As the zombie never rotates more than 90 degrees,
     */
    private void getNextDistance(){
        currentDirection = directionLinkedList.pollFirst();
        if (currentDirection != null){
            switch (currentDirection) {
                case UP:
                case DOWN:
                    distanceToTile = GameConstants.TILE_HEIGHT;
                    break;
                case RIGHT:
                case LEFT:
                    distanceToTile = GameConstants.TILE_WIDTH;
                    break;
            }
        }
    }

    @Override
    public void render(ShapeRenderer renderer){
        hpBar.render(renderer);
    }
    @Override
    public void render(SpriteBatch batch){
        if (elapsedTimeStart >= spawnDelay) {
            super.render(batch);
            hpBar.render(batch);
        }
    }

    /**
     * Changes the direction of the enemy, according to the elapsed time, speed and direction.
     * @param elapsedTime time since last frame
     */
    @Override
    public void update(float elapsedTime) {
        super.update(elapsedTime);
        elapsedTimeStart += elapsedTime;
        if (elapsedTimeStart < spawnDelay || currentDirection == null) {
            return;
        }
        if (distanceToTile < 0){
            getNextDistance();
        }
        if (currentDirection != null){
            float movedDistance = speed * elapsedTime;

            switch (currentDirection) {
                case DOWN:
                    position.y -= movedDistance;
                    distanceToTile -= movedDistance;
                    if (distanceToTile < 0){
                        position.y += distanceToTile;
                    }
                    break;
                case UP:
                    position.y += movedDistance;
                    distanceToTile -= movedDistance;
                    if (distanceToTile < 0){
                        position.y += distanceToTile;
                    }
                    break;
                case LEFT:
                    position.x -= movedDistance;
                    distanceToTile -= movedDistance;
                    break;
                case RIGHT:
                    position.x += movedDistance;
                    distanceToTile -= movedDistance;
                    if (distanceToTile < 0){
                        position.x += distanceToTile;
                    }
                    break;

                default:
                    break;
            }

        }
        removeSlowMode(elapsedTime);

        hpBar.updatePosition(position.x, position.y + this.height);

    }



   public boolean isAlive(){
        return alive;
   }

    public int getReward(){
        return reward;
    }

    public void doubleSpeedClicked(){
        speed *= 2;
    }

    public void normalSpeedClicked(){
        speed /= 2;
    }

    /**
     * Remove slowness effect from a zombie.
     * @param elapsedTime time since last render
     */
    private void removeSlowMode(float elapsedTime){
        if (isSlowed){
            slowTime += elapsedTime;
            float slowDownTime = 0.5f;
            if (slowTime >= slowDownTime){
                speed *= 2;
                sprite = MyAtlas.REGULAR_ZOMBIE; //FIX Change to the texture of a normal zombie
                isSlowed = false;
            }
        }
    }

    /**
     * Put on a slowness effect.
     * Used when shot by a specific type of tower(ice tower?) FIX
     */
    public void slowDown(){
        if (!isSlowed){
            slowTime = 0;
            sprite = MyAtlas.REGULAR_ZOMBIE_SLOWED; //FIX
            speed /= 2;
            isSlowed = true;
        }
        else {
            slowTime = 0;
        }
    }

    public float getEnemyHealth() {
        return this.currentHealth;
    }

    public Rectangle getBoundsRectangle() {
        return this.boundsRectangle;
    }

}
