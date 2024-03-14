package inf112.skeleton.app.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.enums.SceneEnum;
import inf112.skeleton.app.util.GameConstants;



public class PlayScene extends Scene{
    private final Level level;
    private boolean gamePaused = false;


    public PlayScene(SceneController sceneController) {
        super(sceneController);
        level = new Level(this);
    }

    public void gameOver() {
        level.restart();
        sceneController.setScene(SceneEnum.GameOverScene);
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

        renderInfo(sb);
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

    public void renderInfo(SpriteBatch batch){
        bitmapFont.draw(batch, "Score: " + level.getScore(), 10, GameConstants.SCREEN_HEIGHT - 20);
        bitmapFont.draw(batch, "Money: " + level.getMoney(), 100, GameConstants.SCREEN_HEIGHT - 20);
        bitmapFont.draw(batch, "Wave: " + level.getCurrentWave(), 210, GameConstants.SCREEN_HEIGHT - 20);
        bitmapFont.draw(batch, "Enemies killed: " + level.getEnemiesKilled(), 310, GameConstants.SCREEN_HEIGHT - 20);
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
