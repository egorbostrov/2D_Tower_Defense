package inf112.skeleton.app.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.GameUtil;

public abstract class Scene {
    protected SceneController sceneController;
    protected OrthographicCamera camera;
    protected GlyphLayout glyphLayout;
    protected BitmapFont bitmapFont;
    protected StateEnum currentState;



    public Scene(SceneController sceneController) {
        this.sceneController = sceneController;
        bitmapFont = GameUtil.generateBitmapFont(100, Color.WHITE);
        glyphLayout = new GlyphLayout();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
    }

    public void render(SpriteBatch batch, ShapeRenderer sr) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        sr.setProjectionMatrix(camera.combined);
    }

    public SceneController getSceneController() {
        return sceneController;
    }

    public abstract void update(float elapsedTime);
    public abstract void updateInputs(float x, float y);
    public abstract void touchDown(float x, float y, int pointer, int button);
    public abstract void touchUp(float x, float y, int pointer, int button);

    public enum StateEnum {
        PlayScene, PauseScene, MenuScene, GameOverScene, OptionScene
    }
}
