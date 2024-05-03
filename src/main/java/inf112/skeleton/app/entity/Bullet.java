package inf112.skeleton.app.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.tower.BomberDefender;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;

import inf112.skeleton.app.enums.BulletType;
import inf112.skeleton.app.util.MusicManager;

import static inf112.skeleton.app.util.GameConstants.*;

public class Bullet extends GameObject {

    private final BulletType bulletType;
    private BomberDefender bomberDefender;
    private final Enemy enemy;
    private final float damage;
    private float explosionRadius;

    /**
     * Creates a new Bullet.
     * @param x The x-coordinate of the bullet.
     * @param y The y-coordinate of the bullet.
     * @param enemy The enemy that the bullet is targeting.
     * @param damage The damage that the bullet will inflict.
     * @param bulletType The type of the bullet.
     */
    public Bullet(float x, float y, Enemy enemy, float damage, BulletType bulletType) {
        super(x, y, GameConstants.BULLET_WIDTH, GameConstants.BULLET_HEIGHT);
        this.enemy = enemy;
        this.damage = damage;
        this.bulletType = bulletType;
        this.explosionRadius = 0;

        switch (bulletType) {
            case GUNNER_BULLET:
                sprite = GameAssets.gunnerBulletSprite;
                break;
            case SNIPER_BULLET:
                sprite = GameAssets.sniperBulletSprite;
                break;
            case BOMBER_BULLET:
                sprite = GameAssets.bomberBulletSprite;
                break;
        }
    }

    /**
     * Sets the BomberDefender for the bullet.
     * @param bomberDefender The BomberDefender to set.
     */
    public void setBomberDefender(BomberDefender bomberDefender) {
        this.bomberDefender = bomberDefender;
    }

    private void checkRemove() {
        float distance = enemy.center.dst(center);
        if (distance <= GameConstants.BULLET_HEIGHT) {
            isVisible = false;
            if (bulletType == BulletType.BOMBER_BULLET && bomberDefender != null) {
                bomberDefender.applyAreaDamage(center, explosionRadius, damage);
                MusicManager.playBombExplode();
            } else {
                enemy.shot(damage);
            }
        }
    }

    /**
     * Checks if the bullet is still visible.
     * @return true if the bullet is still visible, false otherwise.
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Updates the bounds rectangle, checks if the bullet has hit an enemy and thus should be removed.
     * If the bullet is visible, and has not hit an enemy, we compute the direction vector from the bullet to the target.
     * @param deltaTime time since last update
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (isVisible) {
            if (enemy != null) {
                checkRemove();
                Vector2 targetPosition = new Vector2().set(enemy.center.x, enemy.center.y);

                Vector2 temporary = targetPosition.sub(this.center).clamp(TILE_WIDTH * 8, TILE_WIDTH * 8)
                        .scl(deltaTime);
                this.position.add(temporary);
            }
        }
    }

    /**
     * Sets the explosion radius for the bullet.
     * @param explosionRadius The explosion radius to set.
     */
    public void setExplosionRadius(float explosionRadius) {
        this.explosionRadius = explosionRadius;
    }

    /**
     * renders shapes allocated to bullet
     * @param renderer shapeRenderer used in our project
     */
    @Override
    public void render(ShapeRenderer renderer) {
        super.render(renderer);

    }

    /**
     * Renders texture
     * @param batch SpriteBatch used in our project
     */
    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }
}
