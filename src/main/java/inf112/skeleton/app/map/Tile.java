package inf112.skeleton.app.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.entity.GameObject;
import inf112.skeleton.app.resourceHandler.MyAtlas;
import inf112.skeleton.app.enums.GridType;
import inf112.skeleton.app.util.GameAssets;

public class Tile extends GameObject {
    private GridType type;
    private TextureAtlas.AtlasRegion pathTexture;
    private TextureAtlas.AtlasRegion groundTexture;


    public Tile(float x, float y, float width, float height, GridType type) {
        super(x, y, width, height);
        this.type = type;
        this.loadTextures();
    }

    @Override
    public void render(ShapeRenderer shapeRender) {
        shapeRender.setColor(Color.RED);
        super.render(shapeRender);
    }

    private void loadTextures() {
        // Access the texture regions from GameAssets
        pathTexture = GameAssets.pathTexture;
        groundTexture = GameAssets.groundTexture;

    }

    public void render(SpriteBatch batch) {
        switch (type) {
            case PATH:
                batch.draw(pathTexture, this.position.x, this.position.y, this.size.x, this.size.y);
                break;
            case GROUND:
                batch.draw(groundTexture, this.position.x, this.position.y, this.size.x, this.size.y);
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param x coordinate
     * @param y coordinate
     * @return true/false if the x and y coordinates are in the tile
     */
    public boolean contains(float x, float y) {
        return x >= position.x && x < position.x + size.x && y >= position.y && y < position.y + size.y;
    }

    /**
     *
     * @return the type of the tile
     */
    public GridType getType() {
        return type;
    }

    /**
     * Sets the type of the tile
     * @param type to be given to the tile
     */
    public void setType(GridType type) {
        this.type = type;
    }


    /**
     *
     * @return x coordinate of the tile
     */
    public float getX(){
        return this.position.x;
    }

    /**
     *
     * @return y coordinate of the tile
     */
    public float getY(){
        return this.position.y;
    }

}
