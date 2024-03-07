package inf112.skeleton.app.tower;

import inf112.skeleton.app.entity.Bullet;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.BulletType;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.resourceHandler.MyAtlas;

import java.util.List;

public class SniperDefender extends BaseDefender{

    public SniperDefender(float x, float y, List<Enemy> enemyList) {
        super(x, y, enemyList);
        defenderType = DefenderType.SNIPER;
        damage = GameConstants.TOWER_DAMAGE_SNIPER;
        range = GameConstants.TOWER_RANGE_SNIPER;
        speed = GameConstants.TOWER_SPEED_SNIPER;
        sprite = MyAtlas.SNIPER;
        spriteSelected = MyAtlas.SNIPER;
    }

    @Override
    public void projectileFire() {
        bullets.add(new Bullet(center.x, center.y, enemy, damage, BulletType.SNIPER_BULLET));
    }

    // needs to have a bigger range than the other towers
    // needs to have a slower fire rate than the other towers
}
