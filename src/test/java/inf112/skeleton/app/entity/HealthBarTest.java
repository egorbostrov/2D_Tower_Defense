package inf112.skeleton.app.entity;

import inf112.skeleton.app.util.GameConstants;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HealthBarTest {


    @Test
    public void testHealthRemoveEnemyVsHealthBar(){
        Enemy enemy = new Enemy('R',100, 100, GameConstants.ENEMY_WIDTH, GameConstants.ENEMY_HEIGHT, 100, new LinkedList<>(), 0, 0, 0, null);
        Random random = new Random();
        int randomNumber = random.nextInt(101);
        enemy.shot(randomNumber);
        assertEquals(enemy.getHpBar().getCurrentHealth(), enemy.getEnemyHealth() - randomNumber);
    }
}
