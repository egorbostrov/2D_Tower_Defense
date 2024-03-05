package inf112.skeleton.app.tower;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.resourceHandler.MyAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import java.util.List;

public class GunnerDefender extends BaseDefender {

    public GunnerDefender(float x, float y, List<Enemy> enemyList) {
        super(x, y, enemyList);
        type = DefenderType.GUNNER;
        sprite = MyAtlas.GUNNER;
        spriteSelected = MyAtlas.GUNNER;
        damage = GameConstants.TOWER_DAMAGE_GUNNER;
        range = GameConstants.TILE_WIDTH * 3;
    }

    @Override
    public void render(ShapeRenderer renderer) {
        super.render(renderer);
        if (target != null) {
            renderer.end();
            renderer.begin(ShapeType.Filled);
            renderer.triangle(center.x, center.y,
                    target.center.x, target.center.y + target.size.x / 2,
                    target.center.x, target.center.y - target.size.x / 2);
            renderer.end();
            renderer.begin(ShapeType.Line);
        }
    }
    /*@Override
    public void continuousShoot() {
        target.shoot(damage);
    }*/
}
