package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.tower.BaseDefender;
import inf112.skeleton.app.enums.DefenderType;


import java.util.ArrayList;
import java.util.List;

public class TowerController {

    private final List<BaseDefender> defenderList;
    private BaseDefender currentDefender;

    public TowerController(){
        this.defenderList = new ArrayList<>();
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

    public int buildTower(float x, float y, List<Enemy> enemyList, DefenderType type, int money){
        return 0;
    }

    public void update(float elapsedTime) {
    }

    public void render(SpriteBatch batch) {
    }

    public void render(ShapeRenderer renderer) {
    }
}
