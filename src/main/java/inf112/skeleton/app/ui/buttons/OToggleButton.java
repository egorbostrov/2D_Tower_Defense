package inf112.skeleton.app.ui.buttons;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.ui.components.Pressable;
import inf112.skeleton.app.ui.components.UIComponents;

public class OToggleButton implements UIComponents, Pressable {

    private OToggleButtonListener buttonListener;
    private boolean toggled = false;
    private final OButton button;
    private Sprite toggledIcon;

    public OToggleButton(float x, float y, float width, float height, BitmapFont font, String text,
                         GlyphLayout glyphLayout) {
        this.button = new OButton(x, y, width, height, font, text, glyphLayout);
    }

    public OToggleButton(float width, float height) {
        this(0, 0, width, height, null, null, null);
    }

    @Override
    public void render(SpriteBatch sb) {
        Sprite s = toggled ? toggledIcon : button.getIcon();
        sb.draw(s, button.getPosition().x, button.getPosition().y, button.getSize().x, button.getSize().y);
    }

    @Override
    public Vector2 getSize() {
        return button.getSize();
    }

    @Override
    public boolean contains(float x, float y) {
        return button.contains(x, y);
    }

    public void setToggleListener(OToggleButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void touchDown(float x, float y) {
    }

    public void touchRelease(float x, float y) {
        if (button.contains(x, y)) {
            setToggled(!toggled);
            buttonListener.toggled(toggled);
        }
    }

    public boolean isToggled() {
        return toggled;
    }

    @Override
    public boolean isPressed() {
        return button.isPressed();
    }

    @Override
    public void setPressed(boolean pressed) {
        button.setPressed(pressed);
    }

    @Override
    public void setPos(float x, float y) {
        button.setPos(x, y);
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public void setIcon(Sprite sprite) {
        button.setIcon(sprite);
    }

    public void setToggledIcon(Sprite toggledIcon) {
        this.toggledIcon = toggledIcon;
    }

}
