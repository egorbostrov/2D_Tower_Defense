package inf112.skeleton.app.controller;

import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;

public class PatternedEnemyFactory implements EnemyFactory{
    private final String zombieChars;
    private int counter = 0;

    /**
     * creates a wave of zombies based of the current wave.
     * @param zombieChars string of zombie types that should spawn at the current wave.
     */
    public PatternedEnemyFactory(String zombieChars) {
        this.zombieChars = zombieChars;
    }

    /**
     * Creates a new zombie according to the string of zombie types
     * @param level used to give the enemy access to the directions it should follow
     * @return new zombie
     */
    @Override
    public Enemy getNext(Level level, float speedMultiplier, float healthMultiplier, float delay, boolean doubleSpeed) {
        char zombie = zombieChars.charAt(counter);
        counter = (counter + 1) % zombieChars.length();
        return Enemy.newEnemy(zombie, level, speedMultiplier, healthMultiplier, delay, doubleSpeed);
    }
}
