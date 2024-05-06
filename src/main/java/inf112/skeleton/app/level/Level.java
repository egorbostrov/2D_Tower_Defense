package inf112.skeleton.app.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

import inf112.skeleton.app.controller.EnemyEvents;
import inf112.skeleton.app.controller.WaveController;
import inf112.skeleton.app.scene.CameraManager;
import inf112.skeleton.app.util.MoneyPopup;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.controller.EnemyController;
import inf112.skeleton.app.controller.TowerController;
import inf112.skeleton.app.map.Map;

import java.util.ArrayList;
import java.util.List;

public class Level implements EnemyEvents {
    private int currentWave;
    private int score;
    private int money;
    private int enemiesKilled;
    private int userHealth;
    private Map map;
    private EnemyController enemyController;
    private WaveController waveController;
    private TowerController towerController;
    private final OrthographicCamera camera;
    private final CameraManager cameraManager;
    private boolean isPaused;
    private boolean isDoubleSpeedActive = false;
    private final int mapNumber;
    private final List<MoneyPopup> popups = new ArrayList<>();

    /**
     * Creates a new Level.
     * @param mapNumber The number of the map for the level.
     */
    public Level(int mapNumber) {
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.setToOrtho(false);
        this.cameraManager = new CameraManager(camera);
        this.mapNumber = mapNumber;
        start();
    }

    /**
     * Sets the start values for the start of the game and creates new map, controllers and menus
     */
    private void start() {
        map = new Map(mapNumber);

        this.enemyController = new EnemyController(this);
        waveController = new WaveController(enemyController, mapNumber, false);

        this.towerController = new TowerController(this);

        isPaused = false;
        this.currentWave = 0;
        this.score = 0;
        this.money = GameConstants.START_MONEY;
        this.userHealth = GameConstants.REMAINING_HEALTH;
    }


    /**
     * Updates the level based on the elapsed time.
     * @param elapsedTime The time between the last frame and the current frame.
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

    /**
     * Returns the camera of the level.
     * @return The camera of the level.
     */
    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    private void nextWave() {
        currentWave++;
        createWavePopup("Started wave: " + currentWave, Color.WHITE, 0, 0);
        waveController.newWave(this);
    }

    /**
     * Removes users health when enemies manage to go through the whole path.
     * Also changes scene to game over if user has 0 health left.
     */
    @Override
    public void enemyCompletedPath() {
        userHealth--;
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
        enemiesKilled += 1;
        addMoney(reward);
    }

    /**
     *
     * @return the map which contains the different types of tiles on the board
     */
    public Map getMap(){
        return map;
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

    /**
     * Returns if the game is in double speed mode or not
     * @return true if double speed is active, false otherwise
     */
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
        createMoneyPopup("+$" + amount, Color.GREEN, true);
    }

    /**
     * Removes given amount from your balance
     * @param amount to be removed from your balance
     */
    public void removeMoney(int amount) {
        if (this.money >= amount) {
            this.money -= amount;
            if (!towerController.isSelectedTowerUpgrade()) {
                createMoneyPopup("-$" + amount, Color.RED, true);
            } else {
                createMoneyPopup("-$" + amount, Color.RED, false);
            }
        }
    }

    private void createMoneyPopup(String text, Color color, boolean useDefaultPosition) {
        float x = 135;
        float y = Gdx.graphics.getHeight() - 120;

        if (!useDefaultPosition && towerController.getSelectedDefenderUpgrade() != null) {
            x = towerController.getSelectedDefenderUpgrade().center.x;
            y = towerController.getSelectedDefenderUpgrade().center.y + 40;
        }

        MoneyPopup popup = new MoneyPopup(text, x, y, color, 2.0f);
        popups.add(popup);
    }

    private void createWavePopup(String text, Color color, float x, float y) {
        x = Gdx.graphics.getWidth() / 2.3f;
        y = Gdx.graphics.getHeight() / 1.18f;
        MoneyPopup popup = new MoneyPopup(text, x, y, color, 2.0f);
        popups.add(popup);
    }

    /**
     * Sets the TowerController for the level.
     * @param towerController The TowerController to set.
     */
    public void setTowerController(TowerController towerController) {
        this.towerController = towerController;
    }

    /**
     * Pauses the level.
     */
    public void pause() {
        isPaused = true;
    }

    /**
     * Resumes the level.
     */
    public void resume() {
        isPaused = false;
    }

    /**
     * Returns the list of money popups for the level.
     * @return The list of money popups for the level.
     */
    public List<MoneyPopup> getPopups() {
        return popups;
    }

    /**
     * Returns the score for the level.
     * @return The score for the level.
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Returns the money for the level.
     * @return The money for the level.
     */
    public int getMoney(){
        return this.money;
    }

    /**
     * Returns the current wave for the level.
     * @return The current wave for the level.
     */
    public int getCurrentWave(){
        return this.currentWave;
    }

    /**
     * Returns the number of enemies killed in the level.
     * @return The number of enemies killed in the level.
     */
    public int getEnemiesKilled(){
        return this.enemiesKilled;
    }

    /**
     * Returns the user's health for the level.
     * @return The user's health for the level.
     */
    public int getUserHealth(){
        return this.userHealth;
    }

    /**
     * @return the enemy controller for the level.
     */
    public EnemyController getEnemyController() {
        return enemyController;
    }

    /**
     * @return the tower controller for the level.
     */
    public TowerController getTowerController() {
        return towerController;
    }

}
