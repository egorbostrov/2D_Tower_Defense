package inf112.skeleton.app.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.controller.Render;

import java.awt.*;


public abstract class GameObject implements Render {
    /**
     * position
     */
    public Vector2 position;

    /**
     * width and height
     */
    public Vector2 size;

    /**
     * X and Y coordinate, center of obj.
     */
    public Vector2 center;

    /**
     * Image for obj.
     */
    protected Sprite sprite;

    /**
     * Image for things that are visible and selected
     */
    protected Sprite spriteSelected;

    /**
     * Sets visibility of object
     */
    protected boolean isVisible;

    /**
     * Rectangle bounds used for collision detection
     */
    protected Rectangle boundsRectangle;

    /**
     * See if selected or not
     */
    protected boolean isSelected = false;

    protected boolean visible;


    public GameObject(float xCord, float yCord, float width, float height) {
        this.position = new Vector2(xCord, yCord);
        this.size = new Vector2(width, height);
        this.center = new Vector2((position.x + size.x) / 2, (position.y + size.y) / 2);
        this.boundsRectangle = new Rectangle(xCord, yCord, this.size.x, this.size.y);
        this.isVisible = true;
    }

    /**
     * Renders a bounds rectangle if the object is visible, used in collision
     * @param render renderer
     */
    public void render(ShapeRenderer render) {
        if (isVisible) {
            render.rect(this.position.x, this.position.y, this.size.x, this.size.y);
        }
    }

    /**
     * Renders texture of object of it's visible
     * @param batch
     */
    public void render(SpriteBatch batch) {
        if (!isVisible) {
            return;
        }
        batch.draw(sprite, this.position.x, this.position.y, this.size.x, this.size.y);
    }

    /**
     * Updates the bounds of object, used in collision between bullets and enemies
     * @param elapsedTime time since last frame
     */
    public void update(float elapsedTime) {
        //position
        boundsRectangle.x = position.x;
        boundsRectangle.y = position.y;
        boundsRectangle.height = size.y;
        boundsRectangle.width = size.x;

        //center
        center.x = position.x + size.x / 2;
        center.y = position.y + size.y / 2;
    }

    public Vector2 getPositionOfObject() {
        return position;
    }

}
