package inf112.skeleton.app.scene;

import com.badlogic.gdx.Gdx;

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
        if (!gamePaused){
            level.update(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void updateInputs(float x, float y) {
        level.updateInputs(x, y);
    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {
        level.touchDown(x, y);
    }

    @Override
    public void touchUp(float x, float y, int pointer, int button) {
        level.touchRelease(x, y);
    }

    public void pause() {
        gamePaused = true;
    }

    public void resume(){
        gamePaused = false;
    }

    public void restart(){
        level.restart();
    }
}

