package inf112.skeleton.app.resourceHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class MyAtlas {
    public static Sprite PATH_TILE;
    public static Sprite GROUND_TILE;
    public static Sprite GUNNER;
    public static Sprite SNIPER;
    public static Sprite BOMBER;
    public static Sprite ZOMBIE;
    private static TextureAtlas atlas;

    public static void init() {
        atlas = new TextureAtlas(Gdx.files.internal("zombie.atlas"));
        atlas.getTextures().forEach(t -> t.setFilter(TextureFilter.Linear, TextureFilter.Linear));

        PATH_TILE = createSprite(atlas.findRegion("path"));
        GROUND_TILE = createSprite(atlas.findRegion("gritty1"));

        GUNNER = createSprite(atlas.findRegion("gunna0"));
        SNIPER = createSprite(atlas.findRegion("gunna1"));
        BOMBER = createSprite(atlas.findRegion("gunna2"));

        ZOMBIE = createSprite(atlas.findRegion("gunna5"));//Change atlas to zombie texture, when available :)


    }

    public static Sprite createSprite(AtlasRegion region) {
        final Sprite sprite = new Sprite(region);
          sprite.flip(false, false);
          return sprite;
    }

    public static void dispose() {
        atlas.dispose();
    }

}
