package inf112.skeleton.app.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.entity.GameObject;
import inf112.skeleton.app.resourceHandler.MyAtlas;

public class Tile extends GameObject {
    private EnumGridType type;

    public Tile(float x, float y, float width, float height, EnumGridType type) {
        super(x, y, width, height);
        this.type = type;
    }

    @Override
    public void render(ShapeRenderer shapeRender) {
        shapeRender.setColor(Color.RED);
        super.render(shapeRender);
    }

    public void render(SpriteBatch batch) {
        switch (type) {
            case PATH:
                batch.draw(MyAtlas.PATH_TILE, this.position.x, this.position.y, this.size.x, this.size.y);

            case GROUND:
                batch.draw(MyAtlas.GROUND_TILE, this.position.x, this.position.y, this.size.x, this.size.y);

            default:
                break;
        }
    }

    public boolean contains(float x, float y) {
        return x >= position.x && x < position.x + size.x && y >= position.y && y < position.y + size.y;
    }

    public EnumGridType getType() {
        return type;
    }

    public void setType(EnumGridType type) {
        this.type = type;
    }
    public enum EnumGridType {
        PATH, GROUND, TOWER
    }

    public float getX(){
        return this.position.x;
    }

    public float getY(){
        return this.position.y;
    }

}
