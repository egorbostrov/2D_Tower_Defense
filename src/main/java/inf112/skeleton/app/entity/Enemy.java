package inf112.skeleton.app.entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.controller.Render;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.resourceHandler.MyAtlas;
import inf112.skeleton.app.util.GameConstants;

import java.util.LinkedList;


public class Enemy extends GameObject{

    private float currentHealth;
    private final int bounty;
    private int speed;
    private float distanceToTile;
    private Direction currentDirection;
    private final LinkedList<Direction> directionLinkedList;

    private boolean alive = true;
    private float slowTime;
    private boolean isSlowed = false;
    private final HealthBar hpBar;
    public Enemy(float x, float y, float width, float height, float currentHealth, LinkedList<Direction> directionLinkedList, int bounty, int speed){
        super(x, y, width, height);
        this.speed = speed;
        this.directionLinkedList = new LinkedList<>(directionLinkedList);
        this.currentHealth = currentHealth;
        this.bounty = bounty;
        this.sprite = MyAtlas.ATTACKER;

        getNextDirection();

        hpBar = new HealthBar(x, y - height / 5, width, height / 5, currentHealth);
    }



    public void shot(float damage){
        this.currentHealth -= damage;

        if (this.currentHealth <= 0){
            alive = false;
            isVisible = false;
        } else{
            hpBar.setHealth(currentHealth);
        }
    }

    private void getNextDirection(){
        currentDirection = directionLinkedList.pollFirst();
        if (currentDirection != null){
            switch (currentDirection) {
                case UP:
                case DOWN:
                    distanceToTile = GameConstants.TILE_WIDTH;
                    break;
                case RIGHT:
                case LEFT:
                    distanceToTile = GameConstants.TILE_HEIGHT;
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
        super.render(batch);
    }

    @Override
    public void update(float elapsedTime) {
        super.update(elapsedTime);
        if (currentDirection == null){
            return;
        }
        if (distanceToTile < 0){
            getNextDirection();
        }
        if (currentDirection != null){
            float movedDistance = speed * elapsedTime;

            switch (currentDirection) {
                case UP:
                    position.y -= movedDistance;
                    distanceToTile -= movedDistance;
                    if (distanceToTile < 0){
                        position.y += distanceToTile;
                    }
                    break;
                case DOWN:
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

        hpBar.position.x = position.x;
        hpBar.position.y = position.y - size.y / 5;

    }

   public boolean isAlive(){
        return alive;
   }

    public int getBounty(){
        return bounty;
    }

    public void doubleSpeedClicked(){
        speed *= 2;
    }

    public void normalSpeedClicked(){
        speed /= 2;
    }

    private void removeSlowMode(float elapsedTime){
        if (isSlowed){
            slowTime += elapsedTime;
            float slowDownTime = 0.5f;
            if (slowTime >= slowDownTime){
                speed *= 2;
                sprite = MyAtlas.ATTACKER;
                isSlowed = false;
            }
        }
    }

    public void slowDown(){
        if (!isSlowed){
            speed /= 2;
            isSlowed = true;
        }
        slowTime = 0;
    }



}
