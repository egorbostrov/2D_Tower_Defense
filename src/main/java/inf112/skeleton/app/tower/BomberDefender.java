package inf112.skeleton.app.tower;

import inf112.skeleton.app.entity.Bullet;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.BulletType;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.resourceHandler.MyAtlas;

import java.util.List;

public class BomberDefender extends BaseDefender{
    public BomberDefender(float xCord, float yCord, List<Enemy> enemyList) {
        super(xCord, yCord, enemyList);
        defenderType = DefenderType.BOMBER;
        damage = GameConstants.TOWER_DAMAGE_BOMBER;
        sprite = MyAtlas.BOMBER;
        spriteSelected = MyAtlas.BOMBER;
    }

    @Override
    public void projectileFire() {
        bullets.add(new Bullet(center.x, center.y, enemy, damage, BulletType.BOMBER_BULLET, GameConstants.BOMBER_EXPLOSION_RADIUS));
    }

    // needs to have a bigger damage area than the other towers
    // blast radius
}
