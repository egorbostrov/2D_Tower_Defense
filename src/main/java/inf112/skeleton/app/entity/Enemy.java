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
import inf112.skeleton.app.util.MusicManager;

import java.util.LinkedList;


import static inf112.skeleton.app.util.GameConstants.*;


public class Enemy extends GameObject{

    private char type;
    private float currentHealth;
    private final int reward;
    private float speed;
    private float distanceToTile;
    private Direction currentDirection;
    private final LinkedList<Direction> directionLinkedList;
    private final float spawnDelay;
    private float elapsedTimeStart;

    private boolean alive = true;
    private float slowTime;
    private boolean isSlowed = false;
    private final HealthBar hpBar;
    private boolean doubleSpeed;
    private final float height;
    public boolean hasEnteredMap;

    /**
     * Create a new enemy object
     * @param type char representing which type of zombie this is
     * @param x start position on the x-axis
     * @param y start position on the y-axis
     * @param width width of the zombie(gameObject)
     * @param height height of the zombie(gameObject)
     * @param startHealth Start health of the zombie
     * @param directionLinkedList The directions used to reach the end, following the path
     * @param reward money awarded for killing this zombie
     * @param speed start speed of the zombie
     * @param spawnDelay the delay before the zombie get put on the map
     * @param texture the visual zombie texture
     */
    public Enemy(char type, float x, float y, float width, float height, float startHealth, LinkedList<Direction> directionLinkedList, int reward, float speed, float spawnDelay, Sprite texture, boolean doubleSpeed){
        super(x, y, width, height);
        this.doubleSpeed = false;
        this.type = type;
        this.height = height;
        this.speed = speed;
        this.directionLinkedList = new LinkedList<>(directionLinkedList);
        this.currentHealth = startHealth;
        this.reward = reward;
        this.sprite = texture;
        this.doubleSpeed = doubleSpeed;

        this.spawnDelay = spawnDelay;
        this.elapsedTimeStart = 0;

        getNextDistance();
        this.hpBar = new HealthBar(x + 5, y + this.height, width - 10, height / 10, currentHealth);
    }

    /**
     * Creates a new zombie according to the char
     * @param type char symbolizing the type of zombie
     * @param level used to access the direction list
     * @param speedMultiplier increases the speed of zombies for each wave
     * @param healthMultiplier increases the health of zombies for each wave
     * @param spawnDelay sets the game time of which the zombie will spawn
     * @return new zombie/enemy with these assigned values
     */
    public static Enemy newEnemy(char type, Level level,float speedMultiplier, float healthMultiplier, float spawnDelay, boolean doubleSpeed) {
        return switch(type) {
            case 'R'-> new Enemy(
                    type,
                    START_POS.x,
                    START_POS.y,
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    (ENEMY_REGULAR_START_HP * healthMultiplier),
                    level.getMap().getDirections(),
                    ENEMY_REGULAR_BOUNTY,
                    (ENEMY_REGULAR_SPEED * speedMultiplier),
                    (spawnDelay),
                    GameAssets.zombieSprite,
                    doubleSpeed
            );
            case 'T' -> new Enemy(
                    type,
                    START_POS.x,
                    START_POS.y,
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    (ENEMY_TANK_START_HP * healthMultiplier),
                    level.getMap().getDirections(),
                    ENEMY_TANK_BOUNTY,
                    (ENEMY_TANK_SPEED * speedMultiplier),
                    (spawnDelay),
                    GameAssets.tankSprite,
                    doubleSpeed
            );
            case 'Q' -> new Enemy(
                    type,
                    START_POS.x,
                    START_POS.y,
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    (ENEMY_QUICK_START_HP * healthMultiplier),
                    level.getMap().getDirections(),
                    ENEMY_QUICK_BOUNTY,
                    (ENEMY_QUICK_SPEED * speedMultiplier),
                    (spawnDelay),
                    GameAssets.quickzombieSprite,
                    doubleSpeed
            );
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
            MusicManager.playZombieDeathScream();
        } else{
            hpBar.setHealth(currentHealth);
        }
    }

    /**
     * Get method used in testing
     * @return this.speed
     */
    public float getSpeed(){
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

        hpBar.updatePosition(position.x + 5, position.y + this.height);

    }


    /**
     * @return true of the enemy is alive
     */
   public boolean isAlive(){
        return alive;
   }

    /**
     * @return the bounty reward
     */
    public int getReward(){
        return reward;
    }

    public HealthBar getHpBar(){
        return this.hpBar;
    }

    public void doubleSpeedClicked(){
        if(!this.doubleSpeed){
            speed *= 2;
            this.doubleSpeed = true;
        }
    }

    public void normalSpeedClicked(){
        if(this.doubleSpeed){
            speed /= 2;
            this.doubleSpeed = false;
        }
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

    /**
     * Used in testing
     * @return current health of the enemy
     */
    public float getEnemyHealth() {
        return this.currentHealth;
    }

    public Rectangle getBoundsRectangle() {
        return this.boundsRectangle;
    }

    public char getType() {
        return this.type;
    }

    public void enemyEnteredMap() {
        this.hasEnteredMap = true;
    }
}