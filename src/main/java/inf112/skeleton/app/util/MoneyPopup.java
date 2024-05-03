package inf112.skeleton.app.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoneyPopup {
    private String text;
    private float x, y;
    private float timer;
    private BitmapFont font;
    private Color color;

    /**
     * Creates a new MoneyPopup when money is added or removed from users money.
     * @param text text to be displayed
     * @param x coordinate
     * @param y coordinate
     * @param color color of the text
     * @param timer time before the popup disappears
     */
    public MoneyPopup(String text, float x, float y, Color color, float timer) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        this.timer = timer;
        this.font = new BitmapFont();
        this.font.setColor(color);
    }

    /**
     * Updates the position of the text and the timer
     * @param delta time since last frame
     * @return
     */
    public boolean update(float delta) {
        timer -= delta;
        y += 50 * delta;
        return timer <= 0;
    }

    /**
     * Draws the text on the screen
     * @param batch SpriteBatch to draw the text
     */
    public void draw(SpriteBatch batch) {
        font.draw(batch, text, x, y);
    }

    /**
     * @return timer. Used in test
     */
    public float getTimer() {
        return timer;
    }
}

