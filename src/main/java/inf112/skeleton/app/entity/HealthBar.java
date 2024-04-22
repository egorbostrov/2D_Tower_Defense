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



    public HealthBar(float x, float y, float width, float height, float startHealth){
        super(x, y, width, height);
        ProgressBarStyle barStyle = new ProgressBarStyle();
        barStyle.background = createDrawable((int) width, (int) height, Color.RED);
        barStyle.knobBefore = createDrawable((int) width, (int) height, Color.GREEN);
        healthBar = new ProgressBar(0, startHealth, 1, false, barStyle);
        healthBar.setWidth(width);
        healthBar.setHeight(height);

        healthBar.setAnimateDuration(0.0f);
        healthBar.setValue(startHealth);
        this.newHealth = startHealth;

    }

    public static Drawable createDrawable(int width, int height, Color color){
        Pixmap map = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        map.setColor(color);
        map.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(map)));
        map.dispose();
        return drawable;
    }

    public void setHealth(float newHealth) {
        this.newHealth = newHealth;
    }

    public void updatePosition(float x, float y) {
        this.position.set(x, y);
    }


    @Override
    public void render(SpriteBatch batch){
        healthBar.setValue(this.newHealth);
        healthBar.setPosition(position.x, position.y);
        healthBar.draw(batch, 1);
    }



    @Override
    public void update(float elapsedTime){
        super.update(elapsedTime);
    }



}
