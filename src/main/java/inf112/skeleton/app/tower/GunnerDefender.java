package inf112.skeleton.app.tower;

import inf112.skeleton.app.entity.Bullet;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.BulletType;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.MusicManager;

import java.util.List;

public class GunnerDefender extends BaseDefender {

    /**
     * Create a defender that shoots bullets
     * @param x position on the x-axis
     * @param y position on the y-axis
     * @param enemyList enemies to defeat
     */
    public GunnerDefender(float x, float y, List<Enemy> enemyList) {
        super(x, y, enemyList);
        defenderType = DefenderType.GUNNER;
        initializeSprites(GameAssets.getInstance().getAtlas(), "gunna0", "gunna0");
        damage = GameConstants.TOWER_DAMAGE_GUNNER;
        range = GameConstants.TOWER_RANGE;
        price = GameConstants.TOWER_PRICE_GUNNER;
    }

    /**
     * Fire a bullet projectile
     */
    @Override
    public void projectileFire() {
        MusicManager.playGunnerShot();
        bullets.add(new Bullet(center.x, center.y, enemy, damage, BulletType.GUNNER_BULLET));
    }
}
