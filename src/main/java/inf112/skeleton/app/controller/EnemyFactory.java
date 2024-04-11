package inf112.skeleton.app.controller;

import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;

public interface EnemyFactory {
    /**
     *Returns the next zombie type to be spawned.
     * @return
     */
    public Enemy getNext(Level level, float delay);
}
