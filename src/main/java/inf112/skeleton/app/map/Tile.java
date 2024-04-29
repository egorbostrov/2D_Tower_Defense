package inf112.skeleton.app.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.entity.GameObject;
import inf112.skeleton.app.enums.GridType;
import inf112.skeleton.app.util.GameAssets;
import java.util.Random;

public class Tile extends GameObject {
    private GridType type;
    private TextureAtlas.AtlasRegion tileTexture;
    private TextureAtlas.AtlasRegion pathTexture;
    private TextureAtlas.AtlasRegion groundTexture;
    private TextureAtlas.AtlasRegion groundTexture2;
    private TextureAtlas.AtlasRegion groundTexture3;
    private TextureAtlas.AtlasRegion groundTexture4;


    public Tile(float x, float y, float width, float height, GridType type) {
        super(x, y, width, height);
        this.type = type;
        this.loadTextures();
        this.setRandomTileTexture();
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
        groundTexture2 = GameAssets.groundTexture2;
        groundTexture3 = GameAssets.groundTexture3;
        groundTexture4 = GameAssets.groundTexture4;

    }

    public void render(SpriteBatch batch) {
        batch.draw(tileTexture, this.position.x, this.position.y, this.size.x, this.size.y);
    }

    private void setRandomTileTexture() {
        if(this.type == GridType.GROUND) {
            Random random = new Random();
            int textureIndex = random.nextInt(4);

            this.tileTexture = switch(textureIndex) {
                case 0 -> groundTexture;
                case 1 -> groundTexture2;
                case 2 -> groundTexture3;
                case 3 -> groundTexture4;
                default -> throw new IllegalArgumentException("Found no ground texture for:    " + textureIndex);
            };
        }
        else if(this.type == GridType.PATH) {
            this.tileTexture = pathTexture;
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
    public float getWidth() { return this.size.x; }
    public float getHeight() { return this.size.y; }


}
