package inf112.skeleton.app.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

public class MyAtlas {
    public static Sprite PATH_TILE;

    private static TextureAtlas atlas;

    public static void init() {
        atlas = new TextureAtlas(Gdx.files.internal("texturePack.atlas"));
        atlas.getTextures().forEach(t -> t.setFilter(TextureFilter.Linear, TextureFilter.Linear));

        PATH_TILE = createSprite(atlas.findRegion("johanne"));
    }

    public static Sprite createSprite(AtlasRegion region) {
        final Sprite sprite = new Sprite(region);
          sprite.flip(false, true);
          return sprite;
    }

    public void dispose() {
        atlas.dispose();
    }

}
