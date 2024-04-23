package inf112.skeleton.app.tower;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.entity.Bullet;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.BulletType;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.resourceHandler.MyAtlas;
import inf112.skeleton.app.util.MusicManager;

import java.util.List;

public class BomberDefender extends BaseDefender{

    private final float explosionRadius;
    public BomberDefender(float xCord, float yCord, List<Enemy> enemyList) {
        super(xCord, yCord, enemyList);
        defenderType = DefenderType.BOMBER;
        damage = GameConstants.TOWER_DAMAGE_BOMBER;
        explosionRadius = GameConstants.BOMBER_EXPLOSION_RADIUS;
        initializeSprites(GameAssets.getInstance().getAtlas(), "bomba0", "bomba0");


    }

    public void applyAreaDamage(Vector2 impactPoint, float explosionRadius, float damage) {
        for (Enemy enemy : enemies) {
            if (impactPoint.dst(enemy.center) <= explosionRadius) {
                enemy.shot(damage);
            }
        }
    }

    @Override
    public void projectileFire() {
        Bullet bullet = new Bullet(center.x, center.y, enemy, damage, BulletType.BOMBER_BULLET, GameConstants.BOMBER_EXPLOSION_RADIUS);
        MusicManager.playBomberShot();
        bullet.setBomberDefender(this);
        bullets.add(bullet);
    }
}
