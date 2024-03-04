package inf112.skeleton.app.scene;

import inf112.skeleton.app.level.Level;

public class PlayScene extends Scene{
    private final Level level;
    private boolean gamePaused = false;

    public PlayScene(SceneController sceneController) {
        super(sceneController);
        level = new Level(this);
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
