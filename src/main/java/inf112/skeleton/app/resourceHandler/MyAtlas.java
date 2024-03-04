package inf112.skeleton.app.resourceHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class MyAtlas {
    public static Sprite PATH_TILE;
    public static Sprite GROUND_TILE;

    private static TextureAtlas atlas;

    public static void init() {
        atlas = new TextureAtlas(Gdx.files.internal("texturePack.atlas"));
        atlas.getTextures().forEach(t -> t.setFilter(TextureFilter.Linear, TextureFilter.Linear));

        PATH_TILE = createSprite(atlas.findRegion("johanne"));
        GROUND_TILE = createSprite(atlas.findRegion("etter"));
    }

    public static Sprite createSprite(AtlasRegion region) {
        final Sprite sprite = new Sprite(region);
          sprite.flip(false, true);
          return sprite;
    }

    public static void dispose() {
        atlas.dispose();
    }

}
