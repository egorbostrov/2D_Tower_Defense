package inf112.skeleton.app.controller;

import inf112.skeleton.app.controller.EnemyController;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;

import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EnemyControllerTest {

    @Mock
    private Level mockLevel;
    @Mock
    private Map mockMap;

    private EnemyController enemyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup mocks for Level and Map
        when(mockLevel.getMap()).thenReturn(mockMap);
        // Assuming your Map class has a getDirections method which returns a LinkedList<Direction>
        when(mockMap.getDirections()).thenReturn(new LinkedList<>()); // return an empty list or a list with some directions

        // Now create an instance of EnemyController that will use the mocked Level
        enemyController = new EnemyController(mockLevel);
    }

    @Test
    void populateEnemyList() {
        for (int i = 0; i < 5; i++) {
            enemyController.newZombie(new Enemy(
                    'R',
                    GameConstants.START_POS.x,
                    GameConstants.START_POS.y,
                    GameConstants.ENEMY_WIDTH,
                    GameConstants.ENEMY_HEIGHT,
                    5,
                    mockMap.getDirections(),
                    5,
                    5,
                    5,
                    GameAssets.zombieSprite
            ));
        }
        assertEquals(5, enemyController.getEnemyList().size(), "List should contain 5 enemies");
    }
}

