package inf112.skeleton.app.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class GameAssets implements Disposable {
    public static final GameAssets instance = new GameAssets();
    private final AssetManager assetManager = new AssetManager();
    private TextureAtlas atlas;

    public static TextureAtlas.AtlasRegion playButtonUp;
    public static TextureAtlas.AtlasRegion playButtonDown;
    public static TextureAtlas.AtlasRegion gunnerTexture;
    public static TextureAtlas.AtlasRegion zombieTexture;
    public static TextureAtlas.AtlasRegion pathTexture;
    public static TextureAtlas.AtlasRegion groundTexture;
    public static Sprite bomberSprite;
    public static Sprite zombieSprite;
    public static Sprite zombieslowedSprite;
    public static Sprite tankSprite;
    public static Sprite tankslowedSprite;
    public static Sprite sniperSprite;
    public static Sprite sniperBulletSprite;
    public static Sprite gunnerSprite;
    public static Sprite gunnerBulletSprite;
    public static Sprite bomberBulletSprite;

    private GameAssets() {
    }

    public static GameAssets getInstance() {
        return instance;
    }

    public void init() {
        assetManager.load(GameConstants.TEXTURE_ATLAS_UI, TextureAtlas.class);
        assetManager.finishLoading(); // Blocks until all assets are loaded

        atlas = assetManager.get(GameConstants.TEXTURE_ATLAS_UI, TextureAtlas.class);
        if (atlas == null) {
            Gdx.app.error("GameAssets", "Failed to load TextureAtlas: " + GameConstants.TEXTURE_ATLAS_UI);
            throw new RuntimeException("Failed to load TextureAtlas");
        }

        // Load individual textures
        playButtonUp = atlas.findRegion("play-up");
        playButtonDown = atlas.findRegion("play-dn");
        gunnerTexture = atlas.findRegion("gunna0");
        zombieTexture = atlas.findRegion("gunna0");
        pathTexture = atlas.findRegion("path");
        groundTexture = atlas.findRegion("gritty2");

        // sprite form
        gunnerSprite = createSprite(gunnerTexture);
        sniperSprite = createSprite(gunnerTexture);
        bomberSprite = createSprite(gunnerTexture);

        zombieSprite = createSprite(zombieTexture);//FIX Change atlas to zombie texture, when available :)
        zombieslowedSprite = createSprite(zombieTexture);//FIX Change to frozen zombie when available

        tankSprite = createSprite(zombieTexture);
        tankslowedSprite = createSprite(zombieTexture);

        gunnerBulletSprite = createSprite(atlas.findRegion("path"));
        sniperBulletSprite = createSprite(atlas.findRegion("path"));
        bomberBulletSprite = createSprite(atlas.findRegion("path"));

        if (playButtonUp == null || playButtonDown == null || gunnerTexture == null || zombieTexture == null || pathTexture == null) {
            Gdx.app.error("GameAssets", "One or more texture regions not found!");
            throw new RuntimeException("One or more texture regions not found!");
        }
    }

    public static Sprite createSprite(TextureAtlas.AtlasRegion region) {
        final Sprite sprite = new Sprite(region);
        sprite.flip(false, false);
        return sprite;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }
    @Override
    public void dispose() {
        assetManager.dispose(); // Dispose of the asset manager and by extension all assets it loaded
    }
}
