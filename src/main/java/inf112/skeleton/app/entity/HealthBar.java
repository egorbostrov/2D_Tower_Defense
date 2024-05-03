package inf112.skeleton.app.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class HealthBar extends GameObject {

    private final ProgressBar healthBar;
    private float newHealth;

    /**
     * Health bar that visualizes current health for each enemy
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of the bar
     * @param height height of the bar
     * @param startHealth start health of the bar which will be the max of the bar
     */
    public HealthBar(float x, float y, float width, float height, float startHealth){
        super(x, y, width, height);
        ProgressBarStyle barStyle = new ProgressBarStyle();
        barStyle.background = createDrawable((int) width, (int) height, Color.RED);
        barStyle.knobBefore = createDrawable((int) width, (int) height, Color.GREEN);
        healthBar = new ProgressBar(0, startHealth, 1, false, barStyle);
        healthBar.setWidth(width);
        healthBar.setHeight(height);
        healthBar.setValue(startHealth);
        this.newHealth = startHealth;

    }


    /**
     * Fills the given part of health bar with given color
     * @param width width of health bar part
     * @param height height of health bar part
     * @param color color to be filled in given part of health bar
     * @return colored part of the health bar
     */
    public Drawable createDrawable(int width, int height, Color color){
        Pixmap map = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        map.setColor(color);
        map.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(map)));
        map.dispose();
        return drawable;
    }

    /**
     * Sets the health of the HealthBar.
     * @param newHealth The new health to set.
     */
    public void setHealth(float newHealth) {
        this.newHealth = newHealth;
    }

    /**
     * Returns the current health of the HealthBar.
     * @return The current health of the HealthBar.
     */
    public float getCurrentHealth(){
        return this.newHealth;
    }

    /**
     * Updates the position of the HealthBar.
     * @param x The new x-coordinate to set.
     * @param y The new y-coordinate to set.
     */
    public void updatePosition(float x, float y) {
        this.position.set(x, y);
    }

    /**
     * Renders the HealthBar.
     * @param batch The SpriteBatch used in the project.
     */
    @Override
    public void render(SpriteBatch batch){
        healthBar.setValue(this.newHealth);
        healthBar.setPosition(position.x, position.y);
        healthBar.draw(batch, 1);
    }

    /**
     * Updates the HealthBar.
     * @param elapsedTime The time since the last update.
     */
    @Override
    public void update(float elapsedTime){
        super.update(elapsedTime);
    }
}
