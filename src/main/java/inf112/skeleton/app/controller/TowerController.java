package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.GridType;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;
import inf112.skeleton.app.map.Tile;
import inf112.skeleton.app.tower.BaseDefender;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.tower.SniperDefender;
import inf112.skeleton.app.tower.BomberDefender;
import inf112.skeleton.app.tower.GunnerDefender;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;

import static inf112.skeleton.app.util.GameConstants.*;


import java.util.ArrayList;
import java.util.List;

public class TowerController implements Render{

    private static TowerController instance;

    private final List<BaseDefender> defenderList;
    private BaseDefender currentDefender;
    private boolean isTowerSelected;
    private DefenderType selectedTowerType;

    private Level level;
    private Map map = new Map();
    private GameAssets asset = this.asset;


    public TowerController(Level level){
        defenderList = new ArrayList<>();
        this.level = level;
    }

    public static synchronized TowerController getInstance(Level level) {
        if (instance == null) {
            instance = new TowerController(level);
        }
        return instance;
    }

    public int buildTower(float x, float y, List<Enemy> enemyList, DefenderType type){
        Tile tile = map.getSelectedTile(x, y);
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

    //BUGGED WIP
    private boolean legalPlacement(float x, float y) {
        // Calculate the bounds of the tower based on its center.
        float towerLeft = x - GameConstants.TOWER_SIZE / 2;
        float towerBottom = y - GameConstants.TOWER_SIZE / 2;
        Rectangle newTowerBounds = new Rectangle(towerLeft, towerBottom, GameConstants.TOWER_SIZE, GameConstants.TOWER_SIZE);

        // Check all tiles that the tower overlaps with for legality.
        // Assuming map.getSelectedTile() returns the tile based on bottom-left corner.
        if (map.getSelectedTile(x, y).getType() == GridType.PATH) {
            return false; // The center of the tower would be on a path, so illegal.
        }

        // Check if any existing tower occupies the calculated bounds.
        for (BaseDefender defender : defenderList) {
            if (defender.getHitBox().overlaps(newTowerBounds)) {
                return false; // The position is already taken by another tower.
            }
        }

        return true;
    }



    private int buildSniperTower(float x, float y, List<Enemy> enemyList) {
        SniperDefender sniperDefender = new SniperDefender(x, y, enemyList);
        defenderList.add(sniperDefender);
        return TOWER_PRICE_SNIPER;
    }

    private int buildBomberTower(float x, float y, List<Enemy> enemyList) {
        BomberDefender bomberDefender = new BomberDefender(x, y, enemyList);
        defenderList.add(bomberDefender);
        return TOWER_PRICE_BOMBER;
    }

    private int buildGunnerTower(float x, float y, List<Enemy> enemyList) {
        GunnerDefender gunnerDefender = new GunnerDefender(x, y, enemyList);
        defenderList.add(gunnerDefender);
        System.out.println("Tower added at: " + x + ", " + y + " | Total towers: " + defenderList.size());
        System.out.println(map.getSelectedTile(x,y));
        System.out.println();
        return TOWER_PRICE_GUNNER;
    }

    public void doubleSpeedClicked() {
    }

    public void normalSpeedClicked() {
    }

    public void upgradeSpeed() {
    }

    public BaseDefender getSelectedDefender(Vector2 tileCenter) {
        for (BaseDefender defender : defenderList){

        }
        return null;
    }

    public BaseDefender getSelectedDefender(){
        return currentDefender;
    }

    public void upgradeRange() {
    }

    public void upgradeDamage() {
    }

    public void clearSelectedTower() {
        isTowerSelected = false;
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

    // from libgdx-kev
    public void setTowerSelected(DefenderType type) {
        selectedTowerType = type;
        isTowerSelected = true;

    }

    public boolean isTowerSelected() {
        return this.isTowerSelected;
    }


    public DefenderType getSelectedTowerType() {
        return selectedTowerType;
    }

    public void clearDefenders(){
        defenderList.clear();
    }
}
