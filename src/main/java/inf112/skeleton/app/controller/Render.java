package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Render {

    public void render(SpriteBatch batch);

    public void render(ShapeRenderer renderer);
}
