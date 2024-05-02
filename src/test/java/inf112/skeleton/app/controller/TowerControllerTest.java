package inf112.skeleton.app.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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
import inf112.skeleton.app.tower.BaseDefender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

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

    @Test
    void testUpgradeDamage() {
        // Setup
        int damageUpgradeCost = 100;  // Assuming the damage upgrade costs 100
        BaseDefender mockDefender = mock(BaseDefender.class);  // Mocking the defender
        when(mockDefender.getAttackCost()).thenReturn(damageUpgradeCost);  // Setup the cost
        when(mockLevel.getMoney()).thenReturn(1000);  // Ensuring there's enough money
        towerController.setSelectedTowerUpgrade(mockDefender);  // Setting the selected defender

        // Act
        towerController.upgradeDamage();

        // Verify
        verify(mockLevel, times(1)).removeMoney(damageUpgradeCost);  // Verify money is deducted correctly
        verify(mockDefender, times(1)).damageUpgrade();  // Verify the damage upgrade is called
    }

    @Test
    void testUpgradeRange() {
        // Setup
        int rangeUpgradeCost = 100;  // Assuming the range upgrade costs 100
        BaseDefender mockDefender = mock(BaseDefender.class);  // Mocking the defender
        when(mockDefender.getRangePrice()).thenReturn(rangeUpgradeCost);  // Setup the cost
        when(mockLevel.getMoney()).thenReturn(1000);  // Ensuring there's enough money
        towerController.setSelectedTowerUpgrade(mockDefender);  // Setting the selected defender

        // Act
        towerController.upgradeRange();

        // Verify
        verify(mockLevel, times(1)).removeMoney(rangeUpgradeCost);  // Verify money is deducted correctly
        verify(mockDefender, times(1)).rangeUpgrade();  // Verify the range upgrade is called
    }

    @Test
    void testUpgradeSpeed() {
        // Setup
        int speedUpgradeCost = 100;  // Assuming the speed upgrade costs 100
        BaseDefender mockDefender = mock(BaseDefender.class);  // Mocking the defender
        when(mockDefender.getSpeedPrice()).thenReturn(speedUpgradeCost);  // Setup the cost
        when(mockLevel.getMoney()).thenReturn(1000);  // Ensuring there's enough money
        towerController.setSelectedTowerUpgrade(mockDefender);  // Setting the selected defender

        // Act
        towerController.upgradeSpeed();

        // Verify
        verify(mockLevel, times(1)).removeMoney(speedUpgradeCost);  // Verify money is deducted correctly
        verify(mockDefender, times(1)).speedUpgrade();  // Verify the speed upgrade is called
    }

    @Test
    public void testSellSelectedDefender() {
        // Setup
        BaseDefender mockDefender = mock(BaseDefender.class);  // Mocking the defender
        BaseDefender innerDefender = mock(BaseDefender.class); // Mocking the inner defender object that getDefender() should return

        int defenderPrice = 100;
        int expectedRefund = (int) (defenderPrice * 0.75);

        when(innerDefender.getPrice()).thenReturn((float) defenderPrice);  // Set up price for the inner defender
        when(mockDefender.getDefender()).thenReturn(innerDefender); // Ensure getDefender() returns this inner mock

        when(mockLevel.getMoney()).thenReturn(1000); // Initial money setup

        towerController = new TowerController(mockLevel);
        towerController.setSelectedTowerUpgrade(mockDefender); // Set the defender to be sold

        // Act
        towerController.sellSelectedDefender();

        // Verify
        verify(mockLevel, times(1)).addMoney(expectedRefund);  // Verify money is added back
        assertNull(towerController.getSelectedDefenderUpgrade());  // Verify no defender is currently selected
    }

    @Test
    public void testDoubleSpeedClicked() {
        // Setup
        BaseDefender mockDefender = mock(BaseDefender.class);  // Mocking the defender
        towerController.getDefenderList().add(mockDefender); // Adding the mocked defender to the list
        when(mockDefender.getSpeed()).thenReturn(1.0f);  // Initial speed

        // Act
        towerController.doubleSpeedClicked();

        // Verify
        verify(mockDefender, times(1)).setSpeed(2.0f);  // Expect the speed to be doubled
        assertTrue(towerController.isSpeedMode());  // Check if speedMode is true
    }


    @Test
    public void testNormalSpeedClicked() {
        // Setup
        BaseDefender mockDefender = mock(BaseDefender.class);  // Mocking the defender
        towerController.getDefenderList().add(mockDefender); // Adding the mocked defender to the list
        when(mockDefender.getSpeed()).thenReturn(2.0f);  // Assuming the speed was previously doubled to 2.0f

        // Act
        towerController.normalSpeedClicked();

        // Verify
        verify(mockDefender, times(1)).setSpeed(1.0f);  // Expect the speed to be halved
        assertFalse(towerController.isSpeedMode());  // Check if speedMode is false
    }

    @Test
    public void testSetTowerSelected() {
        // Setup
        DefenderType expectedType = DefenderType.GUNNER;  // Example tower type

        // Act
        towerController.setTowerSelected(expectedType);

        // Verify
        assertEquals(expectedType, towerController.getSelectedTowerType(), "The selected tower type should be set to GUNNER.");
        assertTrue(towerController.isTowerSelected(), "Tower selection should be set to true.");
    }

    @Test
    public void testRender() {
        BaseDefender mockDefender = mock(BaseDefender.class);  // Mocking the defender
        towerController.getDefenderList().add(mockDefender); // Adding the mocked defender to the list

        towerController.render(mock(SpriteBatch.class));

        verify(mockDefender, times(1)).render(any(SpriteBatch.class));  // Expect the render method to be called
    }

    @Test
    public void testShapeRender() {
        BaseDefender mockDefender = mock(BaseDefender.class);  // Mocking the defender
        towerController.getDefenderList().add(mockDefender); // Adding the mocked defender to the list

        towerController.render(mock(ShapeRenderer.class));

        verify(mockDefender, times(1)).render(any(ShapeRenderer.class));  // Expect the shapeRender method to be called
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
