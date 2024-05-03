package inf112.skeleton.app.tower;

import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.util.GameConstants;

import java.util.List;



public class TestableDefender extends BaseDefender {
    public TestableDefender(float xCord, float yCord, List<Enemy> enemies) {
        super(xCord, yCord, enemies);
        this.price = GameConstants.TOWER_PRICE_GUNNER;
        this.range = GameConstants.TOWER_RANGE;
        this.damage = GameConstants.TOWER_DAMAGE_GUNNER;
    }

    @Override
    public void projectileFire() {

    }


}
