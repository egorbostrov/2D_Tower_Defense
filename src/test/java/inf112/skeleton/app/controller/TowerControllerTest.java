package inf112.skeleton.app.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;

import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.enums.GridType;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import inf112.skeleton.app.map.Tile;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class TowerControllerTest {

    @Mock
    private Level mockLevel;
    @Mock
    private Map mockMap;

    @Mock
    private TowerController towerController;

    @Mock
    private EnemyController mockEnemyController;

    private static HeadlessApplication application;
    @Mock
    private Tile mockTile;

    @BeforeAll
    public static void setupBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);
        Gdx.gl20 = mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  //// Initializes annotated mocks
        when(mockLevel.getMap()).thenReturn(mockMap);
        when(mockMap.getSelectedTile(anyFloat(), anyFloat())).thenReturn(mockTile);
        when(mockTile.getType()).thenReturn(GridType.GROUND);
        when(mockLevel.getMoney()).thenReturn(1000);
    
        // Assuming you want to mock the behavior of methods called on towerController
        towerController = new TowerController(mockLevel);
    }

    @Test
    public void testLegalPlacement() {
        float x = 0;
        float y = 0;

        when(mockTile.getType()).thenReturn(GridType.GROUND);

        boolean result = towerController.legalPlacement(x, y);

        assertTrue(result);
    }

    @Test
    public void testNotLegalPlacement() {
        float x = 0;
        float y = 0;

        when(mockTile.getType()).thenReturn(GridType.PATH);

        boolean result = towerController.legalPlacement(x, y);

        assertFalse(result);
    }

    @Test
    public void testBuildTowerWhenNotLegalPlacement() {
        when(mockTile.getType()).thenReturn(GridType.PATH);

        int result = towerController.buildTower(0.0f, 0.0f, mockEnemyController.getEnemyList(), DefenderType.BOMBER);

        assertTrue(result == 0);
    }

    
    
    @AfterAll
    public static void tearDown() {
        if (application != null) {
            application.exit();
            application = null;
        }
        Gdx.gl = null;
        Gdx.gl20 = null;
    }
}
