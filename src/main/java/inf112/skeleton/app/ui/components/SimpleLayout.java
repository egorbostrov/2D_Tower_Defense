package inf112.skeleton.app.ui.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class SimpleLayout implements UIComponents, Pressable {

    private final List<UIComponents> elements;
    private final int xOffset;
    private final int yOffset;
    private Vector2 size;
    private Vector2 position;

    public SimpleLayout(float x, float y, float width, float height, int offset) {
        this(x, y, width, height, offset, offset);
    }

    public SimpleLayout(float width, float height, int offset) {
        this(0, 0, width, height, offset, offset);
    }

    public SimpleLayout(float width, float height, int xOffset, int yOffset) {
        this(0, 0, width, height, xOffset, yOffset);
    }

    public SimpleLayout(float x, float y, float width, float height, int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.elements = new ArrayList<>();
        this.position = new Vector2(x, y);
        this.size = new Vector2(width, height);
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public void addComponents(List<? extends UIComponents> elements) {
        this.elements.addAll(elements);
    }

    public void addComponents(UIComponents... elements) {
        this.elements.addAll(List.of(elements));
    }

    public void pack() {

        float x = xOffset + position.x;
        float y = yOffset + position.y;
        float maxHeight = (float) elements.stream().
                mapToDouble(e -> e.getSize().y).max().orElse(0);
        float yPos;

        for (int i = 0; i < elements.size(); i++) {
            UIComponents c = elements.get(i);
            if (x + c.getSize().x + xOffset > size.x + position.x) {
                x = xOffset + position.x;
                y += maxHeight + yOffset;
            }
            yPos = (maxHeight - c.getSize().y) / 2;
            c.setPos(x, y + yPos);
            x += c.getSize().x + xOffset;
        }
        elements.stream().
                filter(SimpleLayout.class::isInstance).
                map(SimpleLayout.class::cast).
                forEach(SimpleLayout::pack);
    }

    @Override
    public void setPos(float x, float y) {
        this.position = new Vector2(x, y);
    }

    public void render(ShapeRenderer sr) {
        sr.setColor(Color.WHITE);
        sr.rect(position.x, position.y, size.x, size.y);
    }

    @Override
    public void render(SpriteBatch sb) {
        elements.forEach(e -> e.render(sb));

    }

    @Override
    public Vector2 getSize() {
        return size;
    }

    @Override
    public boolean contains(float x, float y) {
        return x >= position.x && x <= position.x + size.x &&
                y >= position.y && y <= position.y + size.y;
    }

    @Override
    public void touchDown(float x, float y) {

    }

    @Override
    public void touchRelease(float x, float y) {

    }

    @Override
    public boolean isPressed() {
        return false;
    }

    @Override
    public void setPressed(boolean pressed) {

    }
}
