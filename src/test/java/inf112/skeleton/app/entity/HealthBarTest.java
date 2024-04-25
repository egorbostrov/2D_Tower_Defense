package inf112.skeleton.app.entity;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import inf112.skeleton.app.util.GameConstants;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Random;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class HealthBarTest {

    private static HeadlessApplication application;
    @BeforeAll
    static void beforeAll(){
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {}

            @Override
            public void resize(int width, int height) {}

            @Override
            public void render() {}

            @Override
            public void pause() {}

            @Override
            public void resume() {}

            @Override
            public void dispose() {}
        }, config);

        // Ensure that the environment is initialized
        Gdx.graphics = mock(com.badlogic.gdx.Graphics.class);
        Gdx.files = mock(com.badlogic.gdx.Files.class);
        Gdx.input = mock(com.badlogic.gdx.Input.class);
        Gdx.net = mock(com.badlogic.gdx.Net.class);
    }


    @Test
    public void testHealthRemoveEnemyVsHealthBar(){
        Enemy enemy = new Enemy('R',100, 100, GameConstants.ENEMY_WIDTH, GameConstants.ENEMY_HEIGHT, 100, new LinkedList<>(), 0, 0, 0, null);
        Random random = new Random();
        int randomNumber = random.nextInt(101);
        enemy.shot(randomNumber);
        assertEquals(enemy.getHpBar().getCurrentHealth(), enemy.getEnemyHealth() - randomNumber);
    }

    @AfterAll
    static void afterAll() {
        if (application != null) {
            application.exit();
            application = null;
        }
    }
}
