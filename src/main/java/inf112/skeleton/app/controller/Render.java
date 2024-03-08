package inf112.skeleton.app.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Render {

    /**
     * Renders the given batch
     * @param batch given batch to be rendered
     */
    public void render(SpriteBatch batch);

    /**
     * Renders a given gdx shape to be rendered
     * @param renderer given shape to be rendered
     */
    public void render(ShapeRenderer renderer);
}
