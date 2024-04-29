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
    public static TextureAtlas.AtlasRegion sniperTexture;
    public static TextureAtlas.AtlasRegion bomberTexture;
    public static TextureAtlas.AtlasRegion bombTexture;
    public static TextureAtlas.AtlasRegion zombieTexture;
    public static TextureAtlas.AtlasRegion slowedzombieTexture;
    public static TextureAtlas.AtlasRegion slowedtankTexture;
    public static TextureAtlas.AtlasRegion slowedrunnerTexture;
    public static TextureAtlas.AtlasRegion tankTexture;
    public static TextureAtlas.AtlasRegion quickZombieTexture;

    public static TextureAtlas.AtlasRegion pathTexture;

    public static TextureAtlas.AtlasRegion groundTexture;
    public static TextureAtlas.AtlasRegion groundTexture2;
    public static TextureAtlas.AtlasRegion groundTexture3;
    public static TextureAtlas.AtlasRegion groundTexture4;

    public static Sprite bomberSprite;
    public static Sprite zombieSprite;
    public static Sprite zombieslowedSprite;
    public static Sprite tankSprite;
    public static Sprite tankslowedSprite;
    public static Sprite quickzombieSprite;
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
        gunnerTexture = atlas.findRegion("gunna0");
        sniperTexture = atlas.findRegion("snipa0");
        bomberTexture = atlas.findRegion("bomba0");
        zombieTexture = atlas.findRegion("regularzombie");
        slowedzombieTexture = atlas.findRegion("frozenregularzombie");
        tankTexture = atlas.findRegion(("tankzombie"));
        slowedtankTexture = atlas.findRegion("frozentankzombie");
        quickZombieTexture = atlas.findRegion("runnerzombie");
        slowedrunnerTexture = atlas.findRegion("frozenrunnerzombie");
        pathTexture = atlas.findRegion("path");
        bombTexture = atlas.findRegion("bomb");
        groundTexture = atlas.findRegion("gritty1");
        groundTexture2 = atlas.findRegion("gritty2");
        groundTexture3 = atlas.findRegion("gritty3");
        groundTexture4 = atlas.findRegion("gritty4");

        // sprite form
        gunnerSprite = createSprite(gunnerTexture);
        sniperSprite = createSprite(sniperTexture);
        bomberSprite = createSprite(bomberTexture);

        zombieSprite = createSprite(zombieTexture);//FIX Change atlas to zombie texture, when available :)
        zombieslowedSprite = createSprite(slowedzombieTexture);//FIX Change to frozen zombie when available

        tankSprite = createSprite(tankTexture);
        tankslowedSprite = createSprite(slowedtankTexture);

        quickzombieSprite = createSprite(quickZombieTexture);

        gunnerBulletSprite = createSprite(atlas.findRegion("bullet"));
        sniperBulletSprite = createSprite(atlas.findRegion("bullet"));
        bomberBulletSprite = createSprite(atlas.findRegion("bomb"));

        if (gunnerTexture == null || zombieTexture == null || pathTexture == null) {
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
