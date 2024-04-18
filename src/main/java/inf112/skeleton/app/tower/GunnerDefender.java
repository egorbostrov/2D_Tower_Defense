package inf112.skeleton.app.tower;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.entity.Bullet;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.BulletType;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.resourceHandler.MyAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import java.util.List;

public class GunnerDefender extends BaseDefender {

    public GunnerDefender(float x, float y, List<Enemy> enemyList) {
        super(x, y, enemyList);
        defenderType = DefenderType.GUNNER;
        initializeSprites(GameAssets.getInstance().getAtlas(), "gunna0", "gunna0");
        damage = GameConstants.TOWER_DAMAGE_GUNNER;
        range = GameConstants.TOWER_RANGE;
    }

//    @Override
//    public void projectileFire() {
//        bullets.add(new Bullet(center.x, center.y, enemy, damage, BulletType.GUNNER_BULLET));
//    }

    @Override
    public void rappidFire() {
        bullets.add(new Bullet(center.x, center.y, enemy, damage, BulletType.GUNNER_BULLET));
    }
}
