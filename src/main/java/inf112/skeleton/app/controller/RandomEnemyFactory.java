package inf112.skeleton.app.controller;

import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.util.GameConstants;

import java.util.Random;

public class RandomEnemyFactory implements EnemyFactory{
    private final Random random;
    private final String possibleZombies = GameConstants.ENEMY_TYPES;
    public RandomEnemyFactory() {
        this.random = new Random();
    }
    @Override
    public Enemy getNext(Level level, float speedMultiplier, float healthMultiplier, float delay, boolean doubleSpeed) {
        int randomIndex = random.nextInt(possibleZombies.length());
        char zombie = possibleZombies.charAt(randomIndex);
        return Enemy.newEnemy(zombie, level, speedMultiplier, healthMultiplier, delay, doubleSpeed);
    }
}
