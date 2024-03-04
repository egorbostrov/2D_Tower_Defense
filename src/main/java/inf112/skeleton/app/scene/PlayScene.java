package inf112.skeleton.app.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.level.Level;

public class PlayScene extends Scene{
    private final Level level;
    private boolean gamePaused = false;


    public PlayScene(SceneController sceneController) {
        super(sceneController);
        level = new Level(this);
    }

    public void gameOver() {
        // MusicHandler.stopBackgroundMusic();
        level.restart();
        sceneController.setScene(SceneEnum.GameOverScene);
        //MusicHandler.playGameoverMusic();
    }

    public void pause(){
        gamePaused = true;
    }

    public void resume(){
        gamePaused = false;
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb,sr);
        sb.begin();
        level.render(sb);
        sb.end();

        sr.begin(ShapeRenderer.ShapeType.Line);
        level.render(sr);
        sr.end();
    }

    public void restart() {
        level.restart();
    }

    @Override
    public void update(float elapsedTime) {
        if (!gamePaused) {
            level.update(Gdx.graphics.getDeltaTime());
        }
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
