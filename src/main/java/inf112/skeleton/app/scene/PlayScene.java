package inf112.skeleton.app.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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


    /**
     * Renders info about current score, money, wave number and number of enemies killed
     * @param batch Spritebatch
     */
    public void renderInfo(SpriteBatch batch){
        String scoreText = "Score: " + level.getScore();
        String moneyText = "Money: " + level.getMoney();
        String waveText = "Wave: " + level.getCurrentWave();
        String enemiesText = "Enemies killed: " + level.getEnemiesKilled();

        float xCord = 10;
        float yCord = GameConstants.SCREEN_HEIGHT - 20;
        int padding = 20;

        GlyphLayout glyphScore = bitmapFont.draw(batch, scoreText, xCord, yCord);
        xCord += glyphScore.width + padding;

        GlyphLayout glyphMoney = bitmapFont.draw(batch, moneyText, xCord, yCord);
        xCord += glyphMoney.width + padding;

        bitmapFont.draw(batch, enemiesText, xCord, yCord);

        xCord = GameConstants.SCREEN_WIDTH / 2;
        GlyphLayout glyphWave = new GlyphLayout();
        glyphWave.setText(bitmapFont, waveText);

        bitmapFont.draw(batch, waveText, xCord - glyphWave.width / 2, yCord);
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
