package inf112.skeleton.app.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.controller.Render;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.resourceHandler.MyAtlas;

import static inf112.skeleton.app.util.GameConstants.BULLET_HEIGHT;
import static inf112.skeleton.app.util.GameConstants.BULLET_WIDTH;
import inf112.skeleton.app.enums.BulletType;

public class Bullet extends GameObject {

    private final BulletType bulletType;
    private final Enemy target;
    private final float damage;


    public Bullet(float x, float y, Enemy target, float damage, BulletType bulletType) {
        super(x, y, BULLET_WIDTH, BULLET_HEIGHT);

        this.target = target;
        this.damage = damage;
        this.bulletType = bulletType;
        if (bulletType == BulletType.GUNNER_BULLET){
            sprite = MyAtlas.GUNNER_BULLET;
        }
    }

    /**
     * Checks if a bullet has hit a zombie, if that is the case, we call a help method shot() which handles the damage etc
     */
    private void checkRemove() {
        float distance = target.center.dst(center);
        if (distance <= GameConstants.BULLET_HEIGHT) {
            visible = false;
            target.shot(damage);
        }
    }

    /**
     * @return boolean showing if the bullet is still visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     *Updates the bounds rectangle, checks if the bullet has hit an enemy and thus should be removed.
     * If the bullet is visible, and has not hit an enemy, we compute the direction vector from the bullet to the target.
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkRemove();

        if (visible) {

            Vector2 targetPosition = new Vector2().set(target.center.x, target.center.y);

            Vector2 temporary = targetPosition.sub(this.center).clamp(10,10)//FIX here we set restriction to the length of the projectile shooting, maybe we should set a limit here according to the range of the tower shooting the bullet?
                    .scl(deltaTime);
            this.position.add(temporary);

        }
    }

    /**
     * renders shapes allocated to bullet
     * @param renderer
     */
    @Override
    public void render(ShapeRenderer renderer) {
        super.render(renderer);
    }

    /**
     * Renders texture
     * @param batch
     */
    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }
}
