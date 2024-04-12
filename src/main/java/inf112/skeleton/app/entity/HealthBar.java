package inf112.skeleton.app.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.*;
import inf112.skeleton.app.util.HealthBarUtils;

public class HealthBar extends GameObject {


    private final ProgressBar healthBar;

    private float timer = 0;

    public HealthBar(float x, float y, float width, float height, float startHealth){
        super(x, y, width, height);
        ProgressBarStyle barStyle = new ProgressBarStyle();
        barStyle.background = HealthBarUtils.coloredDrawable((int) width, (int) height, Color.RED);
        barStyle.knob = HealthBarUtils.coloredDrawable((int) width, (int) height, Color.GREEN);
        barStyle.knobBefore = HealthBarUtils.coloredDrawable((int) width, (int) height, Color.GREEN);
        healthBar = new ProgressBar(0, startHealth, 1, false, barStyle);
        healthBar.setWidth(width);
        healthBar.setHeight(height);

        healthBar.setAnimateDuration(0.0f);
        healthBar.setValue(startHealth);

        healthBar.setAnimateDuration(0.25f);
    }

    public void setHealth(float newHealth) {
        healthBar.setValue(newHealth);
    }


    @Override
    public void render(SpriteBatch batch){
        healthBar.draw(batch, 1);

    }

    @Override
    public void update(float elapsedTime){
        super.update(elapsedTime);
        timer += elapsedTime;
        if (timer > 1.0f) {
            timer = 0;
            }
    }



}
