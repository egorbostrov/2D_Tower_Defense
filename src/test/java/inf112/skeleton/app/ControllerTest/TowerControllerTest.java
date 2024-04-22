package inf112.skeleton.app.ControllerTest;

import inf112.skeleton.app.controller.TowerController;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.tower.BaseDefender;
import inf112.skeleton.app.tower.GunnerDefender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static inf112.skeleton.app.util.GameConstants.*;

class TowerControllerTest {

    private TowerController towerController;
    private List<Enemy> enemyList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        towerController = new TowerController();
        enemyList = new ArrayList<>();
    }

    @Test
    void testBuildTowerAddsGunnerTower() {
        // Starting with more than enough money to build a tower
        int startingMoney = TOWER_PRICE_GUNNER + 100;

        // Build the tower
        int moneySpent = towerController.buildTower(0, 0, enemyList, DefenderType.GUNNER, startingMoney);

        // Check that money spent is equal to the price of the gunner tower
        assertEquals(TOWER_PRICE_GUNNER, moneySpent);

        // Check that the defender list has one tower in it
        assertEquals(1, towerController.getDefenderList().size());

        // Check that the tower added is a GunnerDefender
        assertTrue(towerController.getDefenderList().get(0) instanceof GunnerDefender);
    }

    // Similar tests for Bomber and Sniper towers can be added here...

    // In order to make the below method work, you'd need to add a getter in your TowerController class
    public List<BaseDefender> getDefenderList() {
        return towerController.defenderList;
    }
}
