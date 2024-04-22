package inf112.skeleton.app.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.tower.BomberDefender;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.resourceHandler.MyAtlas;

import inf112.skeleton.app.enums.BulletType;

import static inf112.skeleton.app.util.GameConstants.*;

public class Bullet extends GameObject {

    private final BulletType bulletType;
    private BomberDefender bomberDefender;
    private final Enemy enemy;
    private final float damage;
    private final float explosionRadius;

    public Bullet(float x, float y, Enemy enemy, float damage, BulletType bulletType) {
        this(x, y, enemy, damage, bulletType, 0); // Call the other constructor with explosionRadius = 0
    }

    public Bullet(float x, float y, Enemy enemy, float damage, BulletType bulletType, float explosionRadius) {
        super(x, y, GameConstants.BULLET_WIDTH, GameConstants.BULLET_HEIGHT);
        this.enemy = enemy;
        this.damage = damage;
        this.bulletType = bulletType;
        this.explosionRadius = BOMBER_EXPLOSION_RADIUS;

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

    public void setBomberDefender(BomberDefender bomberDefender) {
        this.bomberDefender = bomberDefender;
    }


    /**
     * Checks if a bullet has hit a zombie, if that is the case, we call a help method shot() which handles the damage etc
     */
    private void checkRemove() {
        float distance = enemy.center.dst(center);
        if (distance <= GameConstants.BULLET_HEIGHT) {
            isVisible = false;
            if (bulletType == BulletType.BOMBER_BULLET && bomberDefender != null) {
                bomberDefender.applyAreaDamage(center, explosionRadius, damage);
            } else {
                enemy.shot(damage);
            }
        }
    }

    /**
     * @return boolean showing if the bullet is still visible
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     *Updates the bounds rectangle, checks if the bullet has hit an enemy and thus should be removed.
     * If the bullet is visible, and has not hit an enemy, we compute the direction vector from the bullet to the target.
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (isVisible) {
            if (enemy != null) {
                checkRemove();
                Vector2 targetPosition = new Vector2().set(enemy.center.x, enemy.center.y);

                Vector2 temporary = targetPosition.sub(this.center).clamp(TILE_WIDTH * 8, TILE_WIDTH * 8)//FIX here we set restriction to the length of the projectile shooting, maybe we should set a limit here according to the range of the tower shooting the bullet?
                        .scl(deltaTime);
                this.position.add(temporary);
            }
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
