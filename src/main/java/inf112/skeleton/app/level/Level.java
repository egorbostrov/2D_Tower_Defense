package inf112.skeleton.app.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.scene.PlayScene;
import inf112.skeleton.app.tower.BaseDefender;
import inf112.skeleton.app.map.Map;
public class Level {
    private final PlayScene scene;
    public Level(PlayScene scene) {
        this.scene = scene;
        init();
    }
    private void init() {

    }

    public void render(ShapeRenderer renderer) {
    }
    public void render(SpriteBatch batch) {
    }
    public void update(float elapsedTime) {
    }
    public void updateInputs(float x, float y) {
    }
    public void createTowerClicked(float x, float y, BaseDefender.DefenderType type) {
    }
    public void enemyPassedTheCheckPoint() {
    }
    public void enemyKilled(int bounty){
    }
    public void newWaveCreated(int size) {
    }
    public void touchDown(float x, float y) {
    }
    public void touchRelease(float x, float y) {
    }
    public void selectGrid(float x, float y) {
    }
    public BaseDefender getSelectedDefender() {
        return null;
    }
    public Map getMap(){
        return null;
    }
    public int getEnemyHealth() {
        return 0;
    }
    public int getEnemyNumber() {
        return 0;
    }
    public void renderTiles(boolean bool) {
    }
    public void nextWaveCountDown(int x) {
    }
    public void upgradeAttackClicked() {
    }
    public void upgradeRangeClicked() {
    }
    public void upgradeSpeedClicked() {
    }
    public void restart() {
    }
    public void pause() {
    }
    public void resume() {
    }
    public void doubleSpeedClicked() {
    }
    public void normalSpeedClicked() {
    }
    public void menuClicked() {
    }
    public void addMoney(int amount) {
    }
    public void removeMoved(int amount) {
    }
}
