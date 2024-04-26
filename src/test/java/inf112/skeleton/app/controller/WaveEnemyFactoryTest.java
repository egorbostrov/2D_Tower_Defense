// package inf112.skeleton.app.ControllerTest;

// import static org.mockito.Mockito.*;

// import java.util.LinkedList;

// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import inf112.skeleton.app.controller.WaveEnemyFactory;
// import inf112.skeleton.app.entity.Enemy;
// import inf112.skeleton.app.level.Level;
// import inf112.skeleton.app.map.Map;

// public class WaveEnemyFactoryTest {

//     @Mock
//     private Level mockLevel;
//     @Mock
//     private Map mockMap;

//     private WaveEnemyFactory waveEnemyFactory;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);

//         // Mock the behavior of the Level and Map objects
//         when(mockLevel.getMap()).thenReturn(mockMap);
//         when(mockMap.getDirections()).thenReturn(new LinkedList<>()); // Provide an empty LinkedList for directions

//         // Initialize the WaveEnemyFactory with the zombie types string
//         waveEnemyFactory = new WaveEnemyFactory("RT");
//     }

//     @Test
//     void testGetNextReturnsCorrectEnemy() {
//         // Assuming a delay value is needed, you can define it here. For example:
//         float delay = 0.5f;

//         // Call getNext to create an enemy based on the first character 'R'
//         Enemy enemyR = waveEnemyFactory.getNext(mockLevel, delay);
//         assertNotNull(enemyR, "Enemy should not be null for type 'R'");

//         // Call getNext to create an enemy based on the next character 'T'
//         Enemy enemyT = waveEnemyFactory.getNext(mockLevel, delay);
//         assertNotNull(enemyT, "Enemy should not be null for type 'T'");
//     }
// }

package inf112.skeleton.app.ControllerTest;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import inf112.skeleton.app.controller.WaveEnemyFactory;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;

import java.util.LinkedList;

public class WaveEnemyFactoryTest {

    @Mock
    private Level mockLevel;
    @Mock
    private Map mockMap;
    private static Application application;

    private WaveEnemyFactory waveEnemyFactory;

    @BeforeAll
    public static void init() {
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {}

            @Override
            public void resize(int width, int height) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'resize'");
            }

            @Override
            public void render() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'render'");
            }

            @Override
            public void pause() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'pause'");
            }

            @Override
            public void resume() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'resume'");
            }

            @Override
            public void dispose() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'dispose'");
            }
        }, conf);

        Gdx.gl20 = mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(mockLevel.getMap()).thenReturn(mockMap);
        when(mockMap.getDirections()).thenReturn(new LinkedList<>());

        waveEnemyFactory = new WaveEnemyFactory("RT");
    }

    @Test
    void testGetNextReturnsCorrectEnemy() {
        float delay = 0.5f;
        Enemy enemyR = waveEnemyFactory.getNext(mockLevel, delay);
        assertNotNull(enemyR, "Enemy should not be null for type 'R'");

        Enemy enemyT = waveEnemyFactory.getNext(mockLevel, delay);
        assertNotNull(enemyT, "Enemy should not be null for type 'T'");
    }

    @AfterEach
    void tearDown() {
        // This is where you would normally close or release resources opened in @BeforeEach
    }

    @AfterAll
    public static void cleanUp() {
        if (application != null) {
            application.exit();
            application = null;
        }
        Gdx.gl = null;
        Gdx.gl20 = null;
    }
}
