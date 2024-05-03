package inf112.skeleton.app.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.enums.GridType;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import inf112.skeleton.app.map.Tile;
import inf112.skeleton.app.tower.BaseDefender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.*;
import java.util.List;

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
    @Mock
    private Rectangle mockHitBox;

    @Mock
    private BaseDefender mockDefender;

    @BeforeAll
    public static void setupBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);
        Gdx.gl20 = mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        GameAssets.instance.init();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockLevel.getMap()).thenReturn(mockMap);
        when(mockMap.getSelectedTile(anyFloat(), anyFloat())).thenReturn(mockTile);
        when(mockTile.getType()).thenReturn(GridType.GROUND);
        when(mockLevel.getMoney()).thenReturn(1000);

        towerController = new TowerController(mockLevel);
        mockDefender = mock(BaseDefender.class);
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
    public void testLegalPlacementWhenHitboxOverlaps() {
        float x = 0;
        float y = 0;

        when(mockTile.getType()).thenReturn(GridType.GROUND);

        List<BaseDefender> defenderList = towerController.getDefenderList();
        defenderList.add(mockDefender);
        when(mockDefender.getHitBox()).thenReturn(mockHitBox);
        when(mockHitBox.overlaps(any())).thenReturn(true);

        boolean result = towerController.legalPlacement(x, y);

        assertFalse(result);
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
    public void testBuildBomber(){
        when(mockTile.getType()).thenReturn(GridType.GROUND);

        int result = towerController.buildTower(0.0f, 0.0f, mockEnemyController.getEnemyList(), DefenderType.BOMBER);
        assertTrue(result == GameConstants.TOWER_PRICE_BOMBER);
    }

    @Test
    public void testBuildGunner(){
        when(mockTile.getType()).thenReturn(GridType.GROUND);

        int result = towerController.buildTower(0.0f, 0.0f, mockEnemyController.getEnemyList(), DefenderType.GUNNER);
        assertTrue(result == GameConstants.TOWER_PRICE_GUNNER);
    }

    @Test
    public void testBuildSniper(){
        when(mockTile.getType()).thenReturn(GridType.GROUND);

        int result = towerController.buildTower(0.0f, 0.0f, mockEnemyController.getEnemyList(), DefenderType.SNIPER);
        assertTrue(result == GameConstants.TOWER_PRICE_SNIPER);
    }

    @Test
    void testUpgradeDamage() {
        int damageUpgradeCost = 100;
        BaseDefender mockDefender = mock(BaseDefender.class);
        when(mockDefender.getAttackCost()).thenReturn(damageUpgradeCost);
        when(mockLevel.getMoney()).thenReturn(1000);
        towerController.setSelectedTowerUpgrade(mockDefender);

        towerController.upgradeDamage();

        verify(mockLevel, times(1)).removeMoney(damageUpgradeCost);
        verify(mockDefender, times(1)).damageUpgrade();
    }

    @Test
    void testUpgradeRange() {
        int rangeUpgradeCost = 100;
        BaseDefender mockDefender = mock(BaseDefender.class);
        when(mockDefender.getRangePrice()).thenReturn(rangeUpgradeCost);
        when(mockLevel.getMoney()).thenReturn(1000);
        towerController.setSelectedTowerUpgrade(mockDefender);

        towerController.upgradeRange();

        verify(mockLevel, times(1)).removeMoney(rangeUpgradeCost);
        verify(mockDefender, times(1)).rangeUpgrade();
    }

    @Test
    void testUpgradeSpeed() {
        int speedUpgradeCost = 100;
        BaseDefender mockDefender = mock(BaseDefender.class);
        when(mockDefender.getSpeedPrice()).thenReturn(speedUpgradeCost);
        when(mockLevel.getMoney()).thenReturn(1000);
        towerController.setSelectedTowerUpgrade(mockDefender);

        towerController.upgradeSpeed();

        verify(mockLevel, times(1)).removeMoney(speedUpgradeCost);
        verify(mockDefender, times(1)).speedUpgrade();
    }

    @Test
    public void testSellSelectedDefender() {
        BaseDefender mockDefender = mock(BaseDefender.class);
        BaseDefender innerDefender = mock(BaseDefender.class);

        int defenderPrice = 100;
        int expectedRefund = (int) (defenderPrice * 0.75);

        when(innerDefender.getPrice()).thenReturn((float) defenderPrice);
        when(mockDefender.getDefender()).thenReturn(innerDefender);

        when(mockLevel.getMoney()).thenReturn(1000);

        towerController = new TowerController(mockLevel);
        towerController.setSelectedTowerUpgrade(mockDefender);

        towerController.sellSelectedDefender();

        verify(mockLevel, times(1)).addMoney(expectedRefund);
        assertNull(towerController.getSelectedDefenderUpgrade());
    }

    @Test
    public void testDoubleSpeedClicked() {
        BaseDefender mockDefender = mock(BaseDefender.class);
        towerController.getDefenderList().add(mockDefender);
        when(mockDefender.getSpeed()).thenReturn(1.0f);

        towerController.doubleSpeedClicked();

        verify(mockDefender, times(1)).setSpeed(2.0f);
        assertTrue(towerController.isSpeedMode());
    }


    @Test
    public void testNormalSpeedClicked() {
        BaseDefender mockDefender = mock(BaseDefender.class);
        towerController.getDefenderList().add(mockDefender);
        when(mockDefender.getSpeed()).thenReturn(2.0f);

        towerController.normalSpeedClicked();

        verify(mockDefender, times(1)).setSpeed(1.0f);
        assertFalse(towerController.isSpeedMode());
    }

    @Test
    public void testSetTowerSelected() {
        DefenderType expectedType = DefenderType.GUNNER;

        towerController.setTowerSelected(expectedType);

        assertEquals(expectedType, towerController.getSelectedTowerType(), "The selected tower type should be set to GUNNER.");
        assertTrue(towerController.isTowerSelected(), "Tower selection should be set to true.");
    }

    @Test
    public void testRender() {
        BaseDefender mockDefender = mock(BaseDefender.class);
        towerController.getDefenderList().add(mockDefender);

        towerController.render(mock(SpriteBatch.class));

        verify(mockDefender, times(1)).render(any(SpriteBatch.class));
    }

    @Test
    public void testShapeRender() {
        BaseDefender mockDefender = mock(BaseDefender.class);
        towerController.getDefenderList().add(mockDefender);

        towerController.render(mock(ShapeRenderer.class));

        verify(mockDefender, times(1)).render(any(ShapeRenderer.class));
    }

    @Test
public void testClearSelectedTower() {
    BaseDefender mockDefender = mock(BaseDefender.class);
    towerController.setSelectedTowerUpgrade(mockDefender);
    towerController.setTowerSelected(DefenderType.GUNNER);

    towerController.clearSelectedTower();

    assertFalse(towerController.isTowerSelected(), "Tower should no longer be selected after clearing.");
    assertNull(towerController.getSelectedDefenderUpgrade(), "No defender should be selected after clearing.");
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