package inf112.skeleton.app.tower;

import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.entity.GameObject;
import inf112.skeleton.app.util.GameConstants;

import java.util.List;

public abstract class BaseDefender extends GameObject {

    protected List<Enemy> enemyList;
    protected Enemy target;
    protected float range;
    protected float damage;
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

    public int getSpeedPrice() {
        return speedPrice;
    }

    public int getRangePrice() {
        return rangePrice;
    }

    public int getAttackCost() {
        return attackPrice;
    }
}
