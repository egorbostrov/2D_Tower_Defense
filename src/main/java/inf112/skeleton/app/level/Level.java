package inf112.skeleton.app.level;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.controller.EnemyEvents;
import inf112.skeleton.app.controller.WaveController;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.scene.CameraManager;
import inf112.skeleton.app.tower.BaseDefender;
import inf112.skeleton.app.ui.menu.InformationMenu;
import inf112.skeleton.app.ui.menu.MainControlMenu;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.GameUtil;
import inf112.skeleton.app.controller.EnemyController;
import inf112.skeleton.app.controller.TowerController;
import inf112.skeleton.app.map.Map;
import inf112.skeleton.app.map.Tile;
import inf112.skeleton.app.enums.GridType;

public class Level implements EnemyEvents {
    private int currentWave;
    private int score;
    private int money;
    private int numberOfEnemies;
    private int enemiesKilled;
    private int enemyHealth;
    private int userHealth;
    private Map map;
    private EnemyController enemyController;
    private WaveController waveController;
    private TowerController towerController;
    private MainControlMenu towerSelectionMenu;
    private InformationMenu infoMenu;
    private boolean changeTimeAndWaveNumber = false;
    private int timeLeft;
    private final BitmapFont bitmapFont;
    private Game game;
    private OrthographicCamera camera;
    private CameraManager cameraManager;
    private boolean isPaused;
    private boolean isDoubleSpeedActive = false;

    private int mapNumber;

    public Level(Game game, int mapNumber) {
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.setToOrtho(false);
        this.cameraManager = new CameraManager(camera);
        this.game = game;
        this.bitmapFont = GameUtil.generateBitmapFont(80, Color.BLACK);
        this.mapNumber = mapNumber;
        start();
    }

    /**
     * Sets the start values for the start of the game and creates new map, controllers and menus
     */
    private void start() {
        isPaused = false;
        currentWave = 0;
        score = 0;
        money = GameConstants.START_MONEY;
        numberOfEnemies = 10;
        enemyHealth = 5;
        userHealth = GameConstants.REMAINING_HEALTH;

        map = new Map(mapNumber);
        this.enemyController = EnemyController.getInstance(this);
        waveController = new WaveController(enemyController, 1, true);
        this.towerController = TowerController.getInstance(this);
        towerSelectionMenu = new MainControlMenu(this);
        infoMenu = new InformationMenu();

    }

    /**
     * Renders the rectangles for the tiles
     * @param renderer the shape to be rendered
     */
    public void render(ShapeRenderer renderer) {
        map.render(renderer);
        enemyController.render(renderer);
        towerController.render(renderer);
        towerSelectionMenu.render(renderer);
    }

    /**
     * Renders the given batch on map, enemyController, towerController, towerSelectionMenu and infoMenu.
     *
     * @param batch given batch
     */
    public void render(SpriteBatch batch) {
        map.render(batch);
        enemyController.render(batch);
        towerController.render(batch);
        towerSelectionMenu.render(batch);
        infoMenu.render(batch);
        if (changeTimeAndWaveNumber){
            GameUtil.renderCenter("Wave: " + currentWave + " loading...", batch, bitmapFont);

        }
    }

    /**
     *
     * @param elapsedTime time between last frame and current frame
     */
    public void update(float elapsedTime) {
        if (isPaused) {
            return;
        }

        map.update(elapsedTime);
        enemyController.update(elapsedTime);
        towerController.update(elapsedTime);

        if(enemyController.getEnemyList().isEmpty()) {
            nextWave();
        }
    }

    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    private void nextWave() {
        currentWave++;
        waveController.newWave(this);
    }

    /**
     * @param x x
     * @param y y
     */
    public void updateInputs(float x, float y) {
        towerSelectionMenu.updateInputs(x, y);
    }

    /**
     * Removes users health when enemies manage to go through the whole path.
     * Also changes scene to game over if user has 0 health left.
     */
    @Override
    public void enemyCompletedPath() {
        userHealth--;
        towerSelectionMenu.fireHealthChanged(userHealth);
        if (userHealth == 0){
            pause();
        }
    }

    /**
     * Increases score and money when enemy is killed.
     * @param reward money gathered from killing the enemy
     */
    @Override
    public void enemyKilled(int reward){
        score += GameConstants.SCORE_INCREASE;
        numberOfEnemies -= 1;
        enemiesKilled += 1;
        addMoney(reward);
        infoMenu.fireScoreChanged(this.score);
        towerSelectionMenu.fireEnemyNumberChanged(numberOfEnemies);
    }

    /**
     * Creates new enemies with new number of enemies
     * @param waveEnemies number of enemies in the new wave
     */
    public void newWaveCreated(int waveEnemies) {
        currentWave++;
        numberOfEnemies = waveEnemies;
        changeTimeAndWaveNumber = false;
    }


    /**
     *
     * @return the selected tower
     */
    public BaseDefender getSelectedDefender() {
        return towerController.getSelectedDefender();//GULP
    }

    /**
     *
     * @return the map which contains the different types of tiles on the board
     */
    public Map getMap(){
        return map;
    }






    /**
     * Sets the time left of the current wave
     * @param seconds is the time left of the current wave
     */
    public void nextWaveCountDown(int seconds) {
        changeTimeAndWaveNumber = true;
        timeLeft = seconds;
    }

    /**
     * If user has enough money, attack will be upgraded for the selected
     * tower. The info for the selected tower will also be updated.
     * The cost of the upgrade will be removed from the money balance.
     */
    public void upgradeAttackClicked() {
        towerController.upgradeDamage();
    }

    /**
     * If user has enough money, range will be upgraded for the selected tower.
     * Money balance will also be updated as well as the info for tower.
     */
    public void upgradeRangeClicked() {
        towerController.upgradeRange();
    }

    /**
     * When user clicks upgrade speed on one of the towers,
     * it first checks if user has enough money. If user doesn't have
     * enough, then nothing happens. If user does,
     * the tower gets faster shootingSpeed, balance gets updated and
     * towerInfo gets updated to new stats of the tower.
     */
    public void upgradeSpeedClicked() {
        towerController.upgradeSpeed();
    }

    /**
     * Game is restarted, and all values
     * are set back to initial values from the start() method.
     */
    public void restart() {
       start();
       userHealth = GameConstants.REMAINING_HEALTH;
       enemyController.clearEnemies();
       towerController.clearDefenders();
    }


    /**
     * Sets the speed of the game to 2x of normal speed
     */
    public void doubleSpeedClicked() {
        isDoubleSpeedActive = true;
        towerController.doubleSpeedClicked();
        enemyController.doubleSpeedClicked();
    }

    /**
     * Sets the speed of the game back to normal speed.
     */
    public void normalSpeedClicked() {
        isDoubleSpeedActive = false;
        towerController.normalSpeedClicked();
        enemyController.normalSpeedClicked();
    }

    public boolean isDoubleSpeedActive() {
        return isDoubleSpeedActive;
    }


    /**
     * Adds money to your bank and calls methods in infoMenu and
     * towerSelection menu to update your current balance
     * @param amount to be added to your balance
     */
    public void addMoney(int amount) {
        this.money += amount;
        infoMenu.fireMoneyChanged(money);
        towerSelectionMenu.moneyChanged(money);
    }

    /**
     * Removes given amount from your balance
     * @param amount to be removed from your balance
     */
    public void removeMoney(int amount) {
        this.money -= amount;
        infoMenu.fireMoneyChanged(money);
        towerSelectionMenu.moneyChanged(money);
    }

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }

    public int getScore(){
        return this.score;
    }

    public int getMoney(){
        return this.money;
    }

    public int getCurrentWave(){
        return this.currentWave;
    }

    public int getEnemiesKilled(){
        return this.enemiesKilled;
    }
    public int getUserHealth(){
        return this.userHealth;
    }
}
