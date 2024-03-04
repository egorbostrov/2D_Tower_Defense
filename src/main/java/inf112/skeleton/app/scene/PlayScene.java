package inf112.skeleton.app.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.resourceHandler.MyAtlas;

public class PlayScene extends Scene{
    private final Level level;
    private boolean gamePaused = false;

    public PlayScene(SceneController sceneController) {
        super(sceneController);
        level = new Level(this);
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer render) {
        super.render(batch, render);
        batch.begin();
        map.render()
    }

    @Override
    public void update(float elapsedTime) {

    }

    @Override
    public void updateInputs(float x, float y) {

    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {

    }

    @Override
    public void touchUp(float x, float y, int pointer, int button) {

    }
}
