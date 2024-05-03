package inf112.skeleton.app.controller;

import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.util.GameConstants;

import java.util.Random;

public class RandomEnemyFactory implements EnemyFactory{

    private final Random random;
    private final String possibleZombies = GameConstants.ENEMY_TYPES;

    /**
     * Creates a new RandomEnemyFactory.
     */
    public RandomEnemyFactory() {
        this.random = new Random();
    }

    /**
     * Creates a new enemy with random type.
     * The type of the enemy is chosen randomly from the possible enemy types.
     *
     * @param level The level to be used for the enemy.
     * @param speedMultiplier The speed multiplier for the enemy.
     * @param healthMultiplier The health multiplier for the enemy.
     * @param delay The delay for the enemy.
     * @param doubleSpeed The double speed flag for the enemy.
     * @return A new enemy with random type.
     */
    @Override
    public Enemy getNext(Level level, float speedMultiplier, float healthMultiplier, float delay, boolean doubleSpeed) {
        int randomIndex = random.nextInt(possibleZombies.length());
        char zombie = possibleZombies.charAt(randomIndex);
        return Enemy.newEnemy(zombie, level, speedMultiplier, healthMultiplier, delay, doubleSpeed);
    }
}
