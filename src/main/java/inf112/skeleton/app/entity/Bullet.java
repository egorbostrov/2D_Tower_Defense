package inf112.skeleton.app.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.util.GameConstants;

import static inf112.skeleton.app.util.GameConstants.BULLET_HEIGHT;
import static inf112.skeleton.app.util.GameConstants.BULLET_WIDTH;

public class Bullet extends GameObject{

    private final EnumBulletType bulletType;

    public Bullet(float x, float y, Enemy target, float damage, EnumBulletType bulletType) {
        super(x, y, BULLET_WIDTH, BULLET_HEIGHT);

        this.bulletType = bulletType;
    }
    public enum EnumBulletType {
    }
    public boolean isVisible() {
        return false;
    }

    public void update(float deltaTime) {
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
