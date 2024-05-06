package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.GridType;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;
import inf112.skeleton.app.tower.BaseDefender;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.tower.SniperDefender;
import inf112.skeleton.app.tower.BomberDefender;
import inf112.skeleton.app.tower.GunnerDefender;
import inf112.skeleton.app.util.GameConstants;

import static inf112.skeleton.app.util.GameConstants.*;


import java.util.ArrayList;
import java.util.List;

public class TowerController implements Render{

    private static TowerController instance;

    private final List<BaseDefender> defenderList;
    private boolean isSelectedDefender;
    private boolean isTowerSelected;
    private DefenderType selectedTowerType;
    private BaseDefender selectedDefenderUpgrade;
    private boolean speedMode = false;

    private final Level level;
    private final Map map;

    /**
     * Creates a new TowerController.
     * @param level The level to be used for the TowerController.
     */
    public TowerController(Level level){
        defenderList = new ArrayList<>();
        this.level = level;
        this.map = level.getMap();
    }

    /**
     * Returns the singleton instance of the TowerController class.
     * If the instance does not exist, it creates a new one with the provided level.
     *
     * @param level The level to be used for the TowerController.
     * @return The singleton instance of the TowerController.
     */
    public static synchronized TowerController getInstance(Level level) { //Might be the source of bug. This would hinder the generation of a new TowerController when we go from pause/gameoverScene.
        if (instance == null) {
            instance = new TowerController(level);
        }
        return instance;
    }

    /**
     * Builds a tower of the specified type at the given coordinates.
     * The tower is only built if the placement is legal and the player has enough money.
     *
     * @param x The x-coordinate for the tower.
     * @param y The y-coordinate for the tower.
     * @param enemyList The list of enemies for the tower to target.
     * @param type The type of the tower to build.
     * @return The price of the built tower, or 0 if the tower was not built.
     */
    public int buildTower(float x, float y, List<Enemy> enemyList, DefenderType type){
        if (!legalPlacement(x, y)){
            return 0;
        }

        x = x - GameConstants.TOWER_SIZE / 2;
        y = y - GameConstants.TOWER_SIZE / 2;

        switch (type){
           case GUNNER:
               if (this.level.getMoney() >= TOWER_PRICE_GUNNER){
                   this.level.removeMoney(TOWER_PRICE_GUNNER);
                   return buildGunnerTower(x, y, enemyList);
               } break;
           case BOMBER:
                if (this.level.getMoney() >= TOWER_PRICE_BOMBER){
                    this.level.removeMoney(TOWER_PRICE_BOMBER);
                    return buildBomberTower(x, y, enemyList);
                } break;
           case SNIPER:
                if (this.level.getMoney() >= TOWER_PRICE_SNIPER){
                    this.level.removeMoney(TOWER_PRICE_SNIPER);
                   return buildSniperTower(x, y, enemyList);
                } break;
       }
       return 0;
    }

    /**
     * Checks if the placement of a tower is legal.
     * Checks if the tower is placed on a path, on top of another tower or on an illegal placement tile (outside board).
     *
     * @param x x-coordinate of the tower
     * @param y y-coordinate of the tower
     * @return true if the placement is legal, false otherwise
     */
    public boolean legalPlacement(float x, float y) {
        float towerLeft = x - GameConstants.TOWER_SIZE / 2;
        float towerBottom = y - GameConstants.TOWER_SIZE / 2;
        Rectangle newTowerBounds = new Rectangle(towerLeft, towerBottom, GameConstants.TOWER_SIZE, GameConstants.TOWER_SIZE);

        if (map.getSelectedTile(x, y).getType() == GridType.ILLEGALPLACEMENT) {
            return false;
        }

        if (map.getSelectedTile(x, y).getType() == GridType.PATH) {
            return false;
        }

        for (BaseDefender defender : defenderList) {
            if (defender.getHitBox().overlaps(newTowerBounds)) {
                return false;
            }
        }

        return true;
    }



    private int buildSniperTower(float x, float y, List<Enemy> enemyList) {
        SniperDefender sniperDefender = new SniperDefender(x, y, enemyList);
        if (speedMode) {
            sniperDefender.setSpeed(sniperDefender.getSpeed() * 2);
        }
        defenderList.add(sniperDefender);
        return TOWER_PRICE_SNIPER;
    }

    private int buildBomberTower(float x, float y, List<Enemy> enemyList) {
        BomberDefender bomberDefender = new BomberDefender(x, y, enemyList);
        if (speedMode) {
            bomberDefender.setSpeed(bomberDefender.getSpeed() * 2);
        }
        defenderList.add(bomberDefender);
        return TOWER_PRICE_BOMBER;
    }

    private int buildGunnerTower(float x, float y, List<Enemy> enemyList) {
        GunnerDefender gunnerDefender = new GunnerDefender(x, y, enemyList);
        if (speedMode) {
            gunnerDefender.setSpeed(gunnerDefender.getDamage() * 2);
        }
        defenderList.add(gunnerDefender);
        return TOWER_PRICE_GUNNER;
    }

    /**
     * Doubles the speed of all towers.
     */
    public void doubleSpeedClicked() {
        speedMode = true;
        for (BaseDefender currentDefender : defenderList) {
            currentDefender.setSpeed(currentDefender.getSpeed() * 2);
        }
    }

    /**
     * Resets the speed of all towers to their normal speed.
     */
    public void normalSpeedClicked() {
        speedMode = false;
        for (BaseDefender currentDefender : defenderList) {
            currentDefender.setSpeed(currentDefender.getSpeed() / 2);
        }
    }

    /**
     * Upgrades the speed of the selected tower.
     */
    public void upgradeSpeed() {
        if (selectedDefenderUpgrade != null && level.getMoney() >= selectedDefenderUpgrade.getSpeedPrice()) {
            level.removeMoney(selectedDefenderUpgrade.getSpeedPrice());
            selectedDefenderUpgrade.speedUpgrade();
        }
    }

    /**
     * Upgrades the damage of the selected tower.
     */
    public void upgradeDamage() {
        if (selectedDefenderUpgrade != null && level.getMoney() >= selectedDefenderUpgrade.getAttackCost()) {
            level.removeMoney(selectedDefenderUpgrade.getAttackCost());
            selectedDefenderUpgrade.damageUpgrade();
        }
    }

    /**
     * Upgrades the range of the selected tower.
     */
    public void upgradeRange() {
        if (selectedDefenderUpgrade != null && level.getMoney() >= selectedDefenderUpgrade.getRangePrice()) {
            level.removeMoney(selectedDefenderUpgrade.getRangePrice());
            selectedDefenderUpgrade.rangeUpgrade();
        }
    }

    /**
     * Clears the selected tower.
     */
    public void clearSelectedTower() {
        isTowerSelected = false;
        selectedDefenderUpgrade = null;
    }

    /**
     * Updates the logic and environment for all placed defending towers
     * @param elapsedTime time since last frame
     */
    public void update(float elapsedTime) {
        for (BaseDefender tower : defenderList) {
            tower.update(elapsedTime);
        }
    }

    /**
     * Gives a render call to the tower class, for each tower.
     * @param batch SpriteBatch used to render.
     */
    @Override
    public void render(SpriteBatch batch) {
        for (BaseDefender tower : defenderList) {
            tower.render(batch);
        }
    }

    /**
     * Calls the shapeRender method for each tower, which renders their range, shown using a red circle.
     * @param renderer the ShapeRenderer used
     */
    @Override
    public void render(ShapeRenderer renderer) {
        for (BaseDefender tower : defenderList) {
            tower.render(renderer);
        }
    }

    /**
     * Sells the selected tower and refunds 75% of its price.
     */
    public void sellSelectedDefender() {
        if (selectedDefenderUpgrade != null) {
            int refundAmount = (int)(selectedDefenderUpgrade.getDefender().getPrice() * 0.75);
            level.addMoney(refundAmount);
            defenderList.remove(selectedDefenderUpgrade);
            selectedDefenderUpgrade = null;
        }
    }

    /**
     * Sets the selected tower type and marks that a tower is selected.
     * @param type The type of the tower to select.
     */
    public void setTowerSelected(DefenderType type) {
        selectedTowerType = type;
        isTowerSelected = true;
    }

    /**
     * Sets the selected tower for upgrade and marks that a tower is selected for upgrade.
     * @param defender The tower to select for upgrade.
     */
    public void setSelectedTowerUpgrade(BaseDefender defender) {
        defender.selectedDefender(true);
        selectedDefenderUpgrade = defender;
        isSelectedDefender = true;
    }

    /**
     * Checks if a tower is selected for upgrade.
     * @return true if a tower is selected for upgrade, false otherwise.
     */
    public boolean isSelectedTowerUpgrade(){
        return isSelectedDefender;
    }

    /**
     * Clears the selected tower for upgrade.
     */
    public void clearSelectedDefenderUpgrade() {
        isSelectedDefender = false;
    }

    /**
     * Returns the selected tower for upgrade.
     * @return The selected tower for upgrade.
     */
    public BaseDefender getSelectedDefenderUpgrade() {
        return selectedDefenderUpgrade;
    }

    /**
     * Checks if a tower is selected.
     * @return true if a tower is selected, false otherwise.
     */
    public boolean isTowerSelected() {
        return isTowerSelected;
    }

    /**
     * Returns the selected tower type.
     * @return The selected tower type.
     */
    public DefenderType getSelectedTowerType() {
        return selectedTowerType;
    }

    /**
     * Returns the list of defenders.
     * @return The list of defenders.
     */
    public List<BaseDefender> getDefenderList() {
        return defenderList;
    }

    /**
     * Clears the list of defenders.
     */
    public void clearDefenders() {
        defenderList.clear();
    }

    /**
     * Checks if the speed mode is enabled.
     * @return true if the speed mode is enabled, false otherwise.
     */
    public boolean isSpeedMode() {
        return speedMode;
    }
}
