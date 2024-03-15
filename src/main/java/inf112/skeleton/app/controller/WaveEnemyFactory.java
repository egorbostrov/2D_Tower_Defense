package inf112.skeleton.app.controller;

import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;

public class WaveEnemyFactory implements EnemyFactory{
    private String zombieChars;
    private int counter = 0;

    public WaveEnemyFactory(String zombieChars) {
        this.zombieChars = zombieChars;
    }

    /**
     * Creates a new zombie according to the string of zombie types
     * @param level used to access enemyList
     * @return new zombie
     */
    @Override
    public Enemy getNext(Level level) {
        Character zombie = zombieChars.charAt(counter);
        counter = (counter + 1) % zombieChars.length();
        return Enemy.newEnemy(zombie, level, counter);
    }
}
