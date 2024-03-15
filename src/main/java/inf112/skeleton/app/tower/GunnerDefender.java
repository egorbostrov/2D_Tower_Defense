package inf112.skeleton.app.tower;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.entity.Bullet;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.BulletType;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.resourceHandler.MyAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import java.util.List;

public class GunnerDefender extends BaseDefender {

    public GunnerDefender(float x, float y, List<Enemy> enemyList) {
        super(x, y, enemyList);
        defenderType = DefenderType.GUNNER;
        sprite = MyAtlas.GUNNER;
        spriteSelected = MyAtlas.GUNNER;
        damage = GameConstants.TOWER_DAMAGE_GUNNER;
        range = GameConstants.TOWER_RANGE;
    }

    // Needs to be changed so that the bullet has an animation that moves towards the enemy
    // and not tracks the enemy's position with a circle
//    @Override
//    public void render(ShapeRenderer renderer) {
//        super.render(renderer);
//        if (enemy != null) {
//            renderer.end();
//            renderer.begin(ShapeType.Filled);
//            renderer.circle(enemy.center.x, enemy.center.y, 5);
//            renderer.end();
//            renderer.begin(ShapeType.Line);
//        }
//    }

//    @Override
//    public void projectileFire() {
//        bullets.add(new Bullet(center.x, center.y, enemy, damage, BulletType.GUNNER_BULLET));
//        enemy.shot(damage);
//    }

    @Override
    public void rappidFire() {
        bullets.add(new Bullet(center.x, center.y, enemy, damage, BulletType.GUNNER_BULLET));
    }
}
