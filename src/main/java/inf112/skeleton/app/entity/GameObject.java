package inf112.skeleton.app.entity;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.controller.Render;


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

    /**
     * Creates a new GameObject.
     * @param xCord The x-coordinate of the GameObject.
     * @param yCord The y-coordinate of the GameObject.
     * @param width The width of the GameObject.
     * @param height The height of the GameObject.
     */
    public GameObject(float xCord, float yCord, float width, float height) {
        this.position = new Vector2(xCord, yCord);
        this.size = new Vector2(width, height);
        this.center = new Vector2(xCord + width / 2, yCord + height / 2);
        this.boundsRectangle = new Rectangle(xCord, yCord, width, height);
        this.isVisible = true;
    }

    /**
     * Initializes the sprites for the GameObject.
     * @param atlas The TextureAtlas to use for the sprites.
     * @param regularRegionName The name of the region for the regular sprite.
     * @param selectedRegionName The name of the region for the selected sprite.
     */
    protected void initializeSprites(TextureAtlas atlas, String regularRegionName, String selectedRegionName) {
        TextureAtlas.AtlasRegion regularRegion = atlas.findRegion(regularRegionName);
        TextureAtlas.AtlasRegion selectedRegion = atlas.findRegion(selectedRegionName);
        this.sprite = new Sprite(regularRegion);
        this.spriteSelected = new Sprite(selectedRegion);
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
     * @param batch spritebatch
     */
    @Override
    public void render(SpriteBatch batch) {
        if (!isVisible) return;
        Sprite toDraw = isSelected ? spriteSelected : sprite;
        if (toDraw != null) {
            batch.draw(toDraw, position.x, position.y, size.x, size.y);
        }
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

        //test
        boundsRectangle.set(position.x, position.y, size.x, size.y);
    }

    /**
     * Returns the position of the GameObject.
     * @return The position of the GameObject.
     */
    public Vector2 getPositionOfObject() {
        return position;
    }
}
