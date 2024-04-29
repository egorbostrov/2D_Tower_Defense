package inf112.skeleton.app.entity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.util.GameConstants;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.Random;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class HealthBarTest {

    private static HeadlessApplication application;

    @BeforeAll
    public static void setupBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationAdapter() {
        };
        new HeadlessApplication(listener, config);
        Gdx.gl20 = Mockito.mock(GL20.class);
    }

    @Mock
    private Enemy mockEnemy;

    @Test
    public void testHealthRemoveEnemyVsHealthBar(){
        mockEnemy = new Enemy('R',100, 100, GameConstants.ENEMY_WIDTH, GameConstants.ENEMY_HEIGHT, 100, new LinkedList<>(), 0, 0, 0, null, false);
        Random random = new Random();
        int randomNumber = random.nextInt(101);
        mockEnemy.shot(randomNumber);
        assertEquals(mockEnemy.getHpBar().getCurrentHealth(), mockEnemy.getEnemyHealth() - randomNumber);
    }

    @AfterAll
    public static void tearDown() {
        if (application != null) {
            application.exit();
            application = null;
        }
    }


}
