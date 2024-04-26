package inf112.skeleton.app.controller;

import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.tower.*;
import inf112.skeleton.app.enums.DefenderType;
import static inf112.skeleton.app.util.GameConstants.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class TowerControllerTest {
    private TowerController towerController;
    private Level mockLevel;
    private List<Enemy> enemyList;

    @BeforeEach
    void setUp() {
        mockLevel = mock(Level.class);
        towerController = new TowerController(mockLevel);
        enemyList = new ArrayList<>();
    }

    @Test
    void testBuildGunnerTowerWhenEnoughMoney() {
        // Setup
        when(mockLevel.getMoney()).thenReturn(TOWER_PRICE_GUNNER);
        doNothing().when(mockLevel).removeMoney(TOWER_PRICE_GUNNER);

        // Action
        int moneySpent = towerController.buildTower(0, 0, enemyList, DefenderType.GUNNER);

        // Assert
        assertEquals(TOWER_PRICE_GUNNER, moneySpent);
        assertEquals(1, towerController.getDefenderList().size());
        assertTrue(towerController.getDefenderList().get(0) instanceof GunnerDefender);
    }

    @Test
    void testBuildBomberTowerWhenEnoughMoney() {
        when(mockLevel.getMoney()).thenReturn(TOWER_PRICE_BOMBER);
        doNothing().when(mockLevel).removeMoney(TOWER_PRICE_BOMBER);

        int moneySpent = towerController.buildTower(0, 0, enemyList, DefenderType.BOMBER);

        assertEquals(TOWER_PRICE_BOMBER, moneySpent);
        assertEquals(1, towerController.getDefenderList().size());
        assertTrue(towerController.getDefenderList().get(0) instanceof BomberDefender);
    }

    @Test
    void testBuildSniperTowerWhenEnoughMoney() {
        when(mockLevel.getMoney()).thenReturn(TOWER_PRICE_SNIPER);
        doNothing().when(mockLevel).removeMoney(TOWER_PRICE_SNIPER);

        int moneySpent = towerController.buildTower(0, 0, enemyList, DefenderType.SNIPER);

        assertEquals(TOWER_PRICE_SNIPER, moneySpent);
        assertEquals(1, towerController.getDefenderList().size());
        assertTrue(towerController.getDefenderList().get(0) instanceof SniperDefender);
    }

    @Test
    void testBuildTowerWhenNotEnoughMoney() {
        when(mockLevel.getMoney()).thenReturn(0);

        int moneySpent = towerController.buildTower(0, 0, enemyList, DefenderType.GUNNER);

        assertEquals(0, moneySpent);
        assertTrue(towerController.getDefenderList().isEmpty());
    }
}
