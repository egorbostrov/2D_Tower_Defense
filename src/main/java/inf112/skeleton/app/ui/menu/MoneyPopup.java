package inf112.skeleton.app.ui.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoneyPopup {
    private String text;
    private float x, y;
    private float timer;
    private BitmapFont font;
    private Color color;

    public MoneyPopup(String text, float x, float y, Color color, float timer) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        this.timer = timer;
        this.font = new BitmapFont();
        this.font.setColor(color);
    }

    public boolean update(float delta) {
        timer -= delta;
        y += 50 * delta;
        return timer <= 0;
    }

    public void draw(SpriteBatch batch) {
        font.draw(batch, text, x, y);
    }
}

