package inf112.skeleton.app.ControllerTest;

import inf112.skeleton.app.controller.EnemyController;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;

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
        enemyController = new EnemyController(mockLevel, "RT");
    }

    @Test
    void testEnemySpawning() {
        // Verify that the EnemyController's list is populated with the correct number of enemies
        assertEquals(2, enemyController.getEnemyList().size(), "Enemy list should have two enemies");
    }
}

