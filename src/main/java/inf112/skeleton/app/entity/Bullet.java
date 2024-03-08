package inf112.skeleton.app.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.resourceHandler.MyAtlas;
import inf112.skeleton.app.util.GameConstants;

import static inf112.skeleton.app.util.GameConstants.BULLET_HEIGHT;
import static inf112.skeleton.app.util.GameConstants.BULLET_WIDTH;
import inf112.skeleton.app.enums.BulletType;

public class Bullet extends GameObject{

    private final BulletType bulletType;
    private final Enemy target;
    private final float damage;


    public Bullet(float x, float y, Enemy target, float damage, BulletType bulletType) {
        super(x, y, BULLET_WIDTH, BULLET_HEIGHT);

        this.target = target;
        this.damage = damage;
        this.bulletType = bulletType;

        if (bulletType == BulletType.GUNNER_BULLET) {
            sprite = MyAtlas.GROUND_TILE;
        }
    }

    private void checkRemove() {
        float distance = target.center.dst(center);
        if (distance <= GameConstants.BULLET_HEIGHT) {
            visible = false;
            target.shot(damage);
        }
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkRemove();
        if (visible) {

            Vector2 targetCopy = new Vector2();
            targetCopy.set(target.center.x, target.center.y);

            Vector2 temp = targetCopy.sub(this.center).clamp(10,10)
                    .scl(deltaTime);
            this.position.add(temp);

        }
    }
    @Override
    public void render(ShapeRenderer renderer) {
        super.render(renderer);
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }
}
