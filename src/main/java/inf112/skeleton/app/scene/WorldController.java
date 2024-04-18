package inf112.skeleton.app.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import inf112.skeleton.app.controller.EnemyController;
import inf112.skeleton.app.controller.TowerController;
import inf112.skeleton.app.controller.WaveEnemyFactory;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.enums.GridType;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;
import inf112.skeleton.app.map.Tile;
import inf112.skeleton.app.scene.CameraManager;
import inf112.skeleton.app.tower.GunnerDefender;
import inf112.skeleton.app.util.GameAssets;
import inf112.skeleton.app.scene.PlayScene;
import inf112.skeleton.app.util.GameConstants;

import static inf112.skeleton.app.util.GameConstants.*;

public class WorldController extends InputAdapter {
    private final Game game;
    private final Level level;
    private final Map map;
    private final EnemyController enemyController;
    private final TowerController towerController;
    private final ShapeRenderer shapeRenderer;

    public WorldController(Game game, Level level, EnemyController enemyController, TowerController towerController) {
        this.game = game;
        this.level = level;
        this.map = level.getMap();
        this.enemyController = enemyController;
        this.towerController = towerController;
        this.shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(this);
    }

    public void update(float deltaTime) {
        map.update(deltaTime);
        enemyController.update(deltaTime);
        towerController.update(deltaTime);
        level.update(deltaTime);
    }

    public void render(SpriteBatch batch) {
        level.render(batch);
        enemyController.render(batch);
        towerController.render(batch);
    }

    public void renderHitboxes(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (Enemy enemy : enemyController.getEnemyList()) {
            Rectangle hitbox = enemy.getBoundsRectangle();
            shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
        shapeRenderer.end();
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {
            game.setScreen(new MenuScene(game));
        }
        return true;
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}

