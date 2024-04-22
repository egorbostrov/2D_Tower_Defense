package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.scene.MenuScene;
import inf112.skeleton.app.util.GameAssets;

public class TDGame extends Game {
    private Level level; // No longer initialized here
    private SpriteBatch batch;
    private ShapeRenderer renderer;

    @Override
    public void create() {
        // Initialize rendering tools
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();

        // Load assets
        GameAssets.instance.init();

        // Start at menu screen, where the user can trigger game start
        setScreen(new MenuScene(this));
    }

    @Override
    public void render() {
        super.render(); // Let the current screen handle rendering
    }

    @Override
    public void dispose() {
        if (batch != null) batch.dispose();
        if (renderer != null) renderer.dispose();
        GameAssets.instance.dispose();
    }

}
