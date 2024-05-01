package inf112.skeleton.app.tower;

import inf112.skeleton.app.entity.Bullet;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.BulletType;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.MusicManager;

import java.util.List;

public class SniperDefender extends BaseDefender{

    private float fireRate;
    private float lastFireTime;

    public SniperDefender(float x, float y, List<Enemy> enemyList) {
        super(x, y, enemyList);
        defenderType = DefenderType.SNIPER;
        damage = GameConstants.TOWER_DAMAGE_SNIPER;
        range = GameConstants.TOWER_RANGE_SNIPER;
        speed = GameConstants.TOWER_SPEED_SNIPER;
        price = GameConstants.TOWER_PRICE_SNIPER;
        initializeSprites(GameAssets.getInstance().getAtlas(), "snipa0", "snipa0");

        fireRate = 2.0f;
        lastFireTime = -fireRate;
    }

    public float getPrice() {
        return this.price;
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        lastFireTime += deltaTime;
        projectileFire();
    }

    @Override
    public void projectileFire() {
        if (lastFireTime >= fireRate && !enemies.isEmpty()) {
                bullets.add(new Bullet(center.x, center.y, enemy, damage, BulletType.SNIPER_BULLET));
                lastFireTime = 0;
            MusicManager.playSniperShot();
        }
    }
}
