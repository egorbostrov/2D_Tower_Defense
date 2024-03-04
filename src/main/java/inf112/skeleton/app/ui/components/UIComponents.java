package inf112.skeleton.app.ui.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface UIComponents {

    void setPos(float cx, float cy);
    void render(SpriteBatch sb);
    Vector2 getSize();
}
