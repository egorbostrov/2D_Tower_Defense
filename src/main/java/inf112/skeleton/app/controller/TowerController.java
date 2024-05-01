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
    private BaseDefender currentDefender;
    private boolean isTowerSelected;
    private DefenderType selectedTowerType;
    private BaseDefender selectedDefenderUpgrade;
    private boolean speedMode = false;

    private final Level level;
    private final Map map;

    public TowerController(Level level){
        defenderList = new ArrayList<>();
        this.level = level;
        this.map = level.getMap();
    }

    public static synchronized TowerController getInstance(Level level) {
        if (instance == null) {
            instance = new TowerController(level);
        }
        return instance;
    }

    public int buildTower(float x, float y, List<Enemy> enemyList, DefenderType type){
        if (!legalPlacement(x, y)){
            return 0;
        }
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

    public void doubleSpeedClicked() {
        speedMode = true;
        for (BaseDefender currentDefender : defenderList) {
            currentDefender.setSpeed(currentDefender.getSpeed() * 2);
        }
    }

    public void normalSpeedClicked() {
        speedMode = false;
        for (BaseDefender currentDefender : defenderList) {
            currentDefender.setSpeed(currentDefender.getSpeed() / 2);
        }
    }

    public BaseDefender getSelectedDefender(){
        return currentDefender;
    }

    public void upgradeSpeed() {
        if (selectedDefenderUpgrade != null && level.getMoney() >= selectedDefenderUpgrade.getSpeedPrice()) {
            level.removeMoney(selectedDefenderUpgrade.getSpeedPrice());
            selectedDefenderUpgrade.speedUpgrade();
        }
    }

    public void upgradeDamage() {
        if (selectedDefenderUpgrade != null && level.getMoney() >= selectedDefenderUpgrade.getAttackCost()) {
            level.removeMoney(selectedDefenderUpgrade.getAttackCost());
            selectedDefenderUpgrade.damageUpgrade();
        }
    }

    public void upgradeRange() {
        if (selectedDefenderUpgrade != null && level.getMoney() >= selectedDefenderUpgrade.getRangePrice()) {
            level.removeMoney(selectedDefenderUpgrade.getRangePrice());
            selectedDefenderUpgrade.rangeUpgrade();
        }
    }

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
     * Calls the shaperender method for each tower, which renders their range, shown using a red circle.
     * @param renderer the ShapeRenderer used
     */
    @Override
    public void render(ShapeRenderer renderer) {
        for (BaseDefender tower : defenderList) {
            tower.render(renderer);
        }
    }

    public void sellSelectedDefender() {
        if (selectedDefenderUpgrade != null) {
            level.addMoney(TOWER_PRICE_GUNNER); // FIX THIS, should be diffrent price for each tower
            defenderList.remove(selectedDefenderUpgrade);
            selectedDefenderUpgrade = null;
        }
    }

    public void setTowerSelected(DefenderType type) {
        selectedTowerType = type;
        isTowerSelected = true;
    }

    public void setSelectedTowerUpgrade(BaseDefender defender) {
        defender.selectedDefender(true);
        selectedDefenderUpgrade = defender;
        isSelectedDefender = true;
    }

    public boolean isSelectedTowerUpgrade(){
        return isSelectedDefender;
    }

    public void clearSelectedDefenderUpgrade() {
        isSelectedDefender = false;
    }

    public BaseDefender getSelectedDefenderUpgrade() {
        return selectedDefenderUpgrade;
    }

    public boolean isTowerSelected() {
        return isTowerSelected;
    }

    public DefenderType getSelectedTowerType() {
        return selectedTowerType;
    }

    public List<BaseDefender> getDefenderList() {
        return defenderList;
    }

    public void clearDefenders() {
        defenderList.clear();
    }
}
