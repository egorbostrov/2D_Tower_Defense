package inf112.skeleton.app.tower;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.entity.Bullet;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.BulletType;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.MusicManager;

import java.util.List;

public class BomberDefender extends BaseDefender{

    private final float explosionRadius;

    /**
     * Create a defender that throws bombs, yielding a high radius of damage
     * @param xCord position on the x-axis
     * @param yCord position on the y-axis
     * @param enemyList enemies to defeat
     */
    public BomberDefender(float xCord, float yCord, List<Enemy> enemyList) {
        super(xCord, yCord, enemyList);
        defenderType = DefenderType.BOMBER;
        damage = GameConstants.TOWER_DAMAGE_BOMBER;
        explosionRadius = GameConstants.BOMBER_EXPLOSION_RADIUS;
        price = GameConstants.TOWER_PRICE_BOMBER;
        initializeSprites(GameAssets.getInstance().getAtlas(), "bomba0", "bomba0");


    }

    /**
     * Deal damage to enemies within the bomb explosion radius based on their distance to the bomb impact
     * @param impactPoint position where bomb explodes
     * @param explosionRadius radius in which the bomb deals damage
     * @param maxDamage amount of damage dealt by bomb
     */
    public void applyAreaDamage(Vector2 impactPoint, float explosionRadius, float maxDamage) {
        for (Enemy enemy : enemies) {
            float impactDistance = impactPoint.dst(enemy.center);
            if (impactDistance <= explosionRadius) {
                float damageReductionFactor = impactDistance / explosionRadius;
                float damageToApply = maxDamage * (1 - damageReductionFactor);
                enemy.shot(damageToApply);
            }
        }
    }

    /**
     * Fire a bomb projectile
     */
    @Override
    public void projectileFire() {
        Bullet bullet = new Bullet(center.x, center.y, enemy, damage, BulletType.BOMBER_BULLET);
        bullet.setExplosionRadius(explosionRadius);
        MusicManager.playBomberShot();
        bullet.setBomberDefender(this);
        bullets.add(bullet);
    }


}
