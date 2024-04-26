package inf112.skeleton.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import inf112.skeleton.app.controller.PatternedEnemyFactory;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;

import static org.mockito.Mockito.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class WaveEnemyFactoryTest {

    @Mock
    private Level mockLevel;
    @Mock
    private Map mockMap;

    private PatternedEnemyFactory waveEnemyFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock the behavior of the Level and Map objects
        when(mockLevel.getMap()).thenReturn(mockMap);
        when(mockMap.getDirections()).thenReturn(new LinkedList<>()); // Provide an empty LinkedList for directions

        // Initialize the WaveEnemyFactory with the zombie types string
        waveEnemyFactory = new PatternedEnemyFactory("RT");
    }

    @Test
    void testGetNextReturnsCorrectEnemy() {
        // Call getNext to create an enemy based on the first character 'R'
        Enemy enemyR = waveEnemyFactory.getNext(mockLevel, 1f, 1f, 1f);
        assertNotNull(enemyR, "Enemy should not be null for type 'R'");

        // Call getNext to create an enemy based on the next character 'T'
        Enemy enemyT = waveEnemyFactory.getNext(mockLevel, 1f, 1f, 1f);
        assertNotNull(enemyT, "Enemy should not be null for type 'T'");
    }
}
