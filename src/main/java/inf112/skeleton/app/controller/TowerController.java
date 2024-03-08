package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.tower.BaseDefender;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.tower.GunnerDefender;
import inf112.skeleton.app.util.GameConstants;


import java.util.ArrayList;
import java.util.List;

public class TowerController implements Render{

    private final List<BaseDefender> defenderList;
    private BaseDefender currentDefender;

    public TowerController(List<Enemy> enemyList){
        defenderList = new ArrayList<>();
    }
    public int buildTower(float x, float y, List<Enemy> enemyList, DefenderType type, int money){
        defenderList.add(new GunnerDefender(x,y, enemyList));
        return GameConstants.TOWER_PRICE;
    }
    public void doubleSpeedClicked() {
    }

    public void normalSpeedClicked() {
    }

    public void upgradeSpeed() {
    }

    public BaseDefender getSelectedTower(Vector2 tileCenter) {
        for (BaseDefender defender : defenderList){

        }
        return null;
    }

    public BaseDefender getSelectedTower(){
        return currentDefender;
    }

    public void upgradeRange() {
    }

    public void upgradeDamage() {
    }

    public void clearSelectedTower() {
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
}
