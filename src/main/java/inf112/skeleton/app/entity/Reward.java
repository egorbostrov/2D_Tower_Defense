package inf112.skeleton.app.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Reward extends GameObject{
    public Reward(float xCord, float yCord, float width, float height) {
        super(xCord, yCord, width, height);
    }

    @Override
    public void render(SpriteBatch batch) {
    }

    @Override
    public void render(ShapeRenderer renderer){

    }

    public boolean isRemovable() {
        return false;
    }
}
