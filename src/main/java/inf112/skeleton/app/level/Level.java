package inf112.skeleton.app.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.compression.lzma.Base;

import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.scene.PlayScene;
import inf112.skeleton.app.enums.SceneEnum;
import inf112.skeleton.app.tower.BaseDefender;
import inf112.skeleton.app.ui.menu.InformationMenu;
import inf112.skeleton.app.ui.menu.MainControlMenu;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.GameUtil;
import inf112.skeleton.app.controller.EnemyController;
import inf112.skeleton.app.controller.TowerController;
import inf112.skeleton.app.entity.GameObject;
import inf112.skeleton.app.map.Map;
import inf112.skeleton.app.map.Tile;
import inf112.skeleton.app.enums.GridType;
public class Level {
    private final PlayScene scene;
    private int currentWave;
    private int score;
    private int money;
    private int numberOfEnemies;
    private int enemyHealth;
    private int remainingHealth;
    private Map map;
    private EnemyController enemyController;
    private TowerController towerController;
    private MainControlMenu towerSelectionMenu;
    private InformationMenu infoMenu;
    private boolean checkRenderAndWaveValues = false;
    private int timeLeft;
    private final BitmapFont bitmapFont;

    public Level(PlayScene scene) {
        this.scene = scene;
        this.bitmapFont = GameUtil.generateBitmapFont(80, Color.BLACK);
        init();
    }
    private void init() {
        currentWave = 1;
        score = 0;
        money = GameConstants.START_MONEY;
        numberOfEnemies = 10;
        enemyHealth = 100;
        remainingHealth = GameConstants.REMAINING_HEALTH;

        map = new Map();
        enemyController = new EnemyController(this);
        towerController = new TowerController();
        towerSelectionMenu = new MainControlMenu(this);
        infoMenu = new InformationMenu();

    }

    public void render(ShapeRenderer renderer) {
        map.render(renderer);
        enemyController.render(renderer);
        towerController.render(renderer);
        towerSelectionMenu.render(renderer);
    }
    public void render(SpriteBatch batch) {
        map.render(batch);
        enemyController.render(batch);
        towerController.render(batch);
        towerSelectionMenu.render(batch);
        infoMenu.render(batch);
        if (checkRenderAndWaveValues){
            GameUtil.renderCenter("Wave: " + currentWave + "in: " + timeLeft + " second", batch, bitmapFont);

        }
    }
    public void update(float elapsedTime) {
        map.update(elapsedTime);
        enemyController.update(elapsedTime);
        towerController.update(elapsedTime);
    }
    public void updateInputs(float x, float y) {
        towerSelectionMenu.updateInputs(x, y);
    }
    public void createTowerClicked(float x, float y, DefenderType type) {

        Tile tile = map.getSelectedTile(x, y);
        if (tile == null){
            return;
        }
        switch (tile.getType()){
            case TOWER:
                System.out.println("KAN IKKE SETTE TÅRN SOM ALLEREDE FINNES");
                break;
            case GROUND:
                int cost = towerController.buildTower(tile.getPositionOfObject().x, tile.getPositionOfObject().y, enemyController.getEnemyList(), type, money);
                if (cost != 0){
                    tile.setType(GridType.TOWER);
                    removeMoney(cost);
                }
                this.map.getBoard().setRender(false);
                break;
            case PATH:
                System.out.println("KAN IKKE BYGGE PATH");
            default:
                break;
        }
    }
    public void enemyPassedTheCheckPoint() {
        remainingHealth--;
        towerSelectionMenu.fireHealthChanged(remainingHealth);
        if (remainingHealth == 0){
            scene.gameOver();
        }
    }
    public void enemyKilled(int bounty){
        score += GameConstants.SCORE_INCREASE;
        numberOfEnemies -= 1;
        addMoney(bounty);
        infoMenu.fireScoreChanged(this.score);
        towerSelectionMenu.fireEnemyNumberChanged(numberOfEnemies);
    }

    public void newWaveCreated(int waveEnemies) {
        currentWave++;
        numberOfEnemies = waveEnemies;
        checkRenderAndWaveValues = false;
    }
    public void touchDown(float x, float y) {

        if (towerSelectionMenu.contains(x, y)){
            towerSelectionMenu.touchDown(x, y);
        } else {
            selectTile(x, y);
        }
    }
    public void touchRelease(float x, float y) {
        towerSelectionMenu.touchRelease(x, y);
    }
    public void selectTile(float x, float y) {

        Tile tile = this.map.getSelectedTile(x, y);
        if (tile == null){
            return;
        }
        switch (tile.getType()){
            case TOWER:
                BaseDefender defender = towerController.getSelectedTower(tile.getPositionOfObject());
                infoMenu.updateTowerInfo(defender);
                towerSelectionMenu.updateUpgradeButtons(money);
                break;

            case GROUND:
                towerController.clearSelectedTower();
                infoMenu.clearInfo();
                towerSelectionMenu.clearSelectedTower();
                break;

            case PATH:
                towerController.clearSelectedTower();
                towerSelectionMenu.clearSelectedTower();
                break;

            default:
                break;
        }
    }
    public BaseDefender getSelectedDefender() {
        return towerController.getSelectedTower();
    }
    public Map getMap(){
        return map;
    }
    public int getEnemyHealth() {
        return enemyHealth;
    }
    public int getEnemyNumber() {
        return numberOfEnemies;
    }

    public void renderTiles(boolean bool) {
        this.map.getBoard().renderSwitch(bool);
    }
    public void nextWaveCountDown(int x) {
        this.timeLeft = x;
        checkRenderAndWaveValues = true;
    }
    public void upgradeAttackClicked() {
        BaseDefender defender = towerController.getSelectedTower();
        int cost = defender.getAttackCost();

        if (cost <= money){
            towerController.upgradeDamage();
            removeMoney(cost);
            infoMenu.updateTowerInfo(defender);
        }
    }

    public void upgradeRangeClicked() {
        BaseDefender defender = towerController.getSelectedTower();
        int cost = defender.getRangePrice();
        if (cost <= money){
            towerController.upgradeRange();
            removeMoney(cost);
            infoMenu.updateTowerInfo(defender);
        }
    }

    public void upgradeSpeedClicked() {
        BaseDefender defender = towerController.getSelectedTower();
        int cost = defender.getSpeedPrice();
        if (cost <= money){
            towerController.upgradeSpeed();
            removeMoney(cost);
            infoMenu.updateTowerInfo(defender);
        }
    }

    public void restart() {
        init();
    }
    public void pause() {
        scene.pause();
    }
    public void resume() {
        scene.resume();
    }
    public void doubleSpeedClicked() {
        towerController.doubleSpeedClicked();
        enemyController.doubleSpeedClicked();
    }
    public void normalSpeedClicked() {
        towerController.normalSpeedClicked();
        enemyController.normalSpeedClicked();
    }
    public void menuClicked() {
        scene.getSceneController().setScene(SceneEnum.PauseScene);
    }

    public void addMoney(int amount) {
        this.money += amount;
        infoMenu.fireMoneyChanged(money);
        towerSelectionMenu.moneyChanged(money);
    }
    public void removeMoney(int amount) {
        this.money -= amount;
        infoMenu.fireMoneyChanged(money);
        towerSelectionMenu.moneyChanged(money);
    }
}
