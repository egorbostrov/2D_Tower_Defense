package inf112.skeleton.app.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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
import inf112.skeleton.app.map.Map;
import inf112.skeleton.app.map.Tile;
import inf112.skeleton.app.enums.GridType;

public class Level {
    private final PlayScene scene;
    private int currentWave;
    private int score;
    private int money;
    private int numberOfEnemies;
    private int enemiesKilled;
    private int enemyHealth;
    private int userHealth;
    private Map map;
    private EnemyController enemyController;
    private TowerController towerController;
    private MainControlMenu towerSelectionMenu;
    private InformationMenu infoMenu;
    private boolean changeTimeAndWaveNumber = false;
    private int timeLeft;
    private final BitmapFont bitmapFont;


    public Level(PlayScene scene) {
        this.scene = scene;
        this.bitmapFont = GameUtil.generateBitmapFont(80, Color.BLACK);
        start();
    }

    /**
     * Sets the start values for the start of the game and creates new map, controllers and menues
     */
    private void start() {
        currentWave = 8;
        score = 0;
        money = GameConstants.START_MONEY;
        numberOfEnemies = 10;
        enemyHealth = 5;
        userHealth = GameConstants.REMAINING_HEALTH;

        map = new Map();
        enemyController = new EnemyController(this);
        towerController = new TowerController(); //this should be replaced later
        towerController.buildTower(200, 200, enemyController.getEnemyList(), DefenderType.GUNNER, money);
        //towerController.buildTower(150, 150, enemyController.getEnemyList(), DefenderType.BOMBER, money);
        //towerController.buildTower(250, 250, enemyController.getEnemyList(), DefenderType.SNIPER, money);
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
     * Renders the given batch on map, enemycontroller, towercontroller, towerselectionmenu and infomenu.
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
            GameUtil.renderCenter("Wave: " + currentWave + " starts in: " + timeLeft + " seconds", batch, bitmapFont);

        }
    }

    /**
     *
     * @param elapsedTime time between last frame and current frame
     */
    public void update(float elapsedTime) {
        map.update(elapsedTime);
        enemyController.update(elapsedTime);
        towerController.update(elapsedTime);

        if (!changeTimeAndWaveNumber && enemyController.getEnemyList().isEmpty()) {
            nextWaveCountDown(100);
        }
        if (changeTimeAndWaveNumber) {
            timeLeft -= elapsedTime % 2;
            System.out.println("Time left: " + timeLeft);
            if (timeLeft <= 0) {
                changeTimeAndWaveNumber = false;
                initiateNewWave();
            }
        }
    }

    /**
     *
     * @param x
     * @param y
     */
    public void updateInputs(float x, float y) {
        towerSelectionMenu.updateInputs(x, y);
    }

    /**
     * Manages where user is able to put the new tower. If on path or existing tower, tower will not be placed.
     * Tower can only be placed on the GROUND tiles.
     * @param x coordinate for tile
     * @param y coordinate for tile
     * @param type type of tower
     */
    public void createTowerClicked(float x, float y, DefenderType type) {

        Tile tile = map.getSelectedTile(x, y);
        if (tile == null){
            return;
        }
        switch (tile.getType()){
            case TOWER:
                System.out.println("KAN IKKE SETTE PÅ EKSISTERENDE TÅRN");
                break;
            case GROUND:
                int cost = towerController.buildTower(tile.getPositionOfObject().x, tile.getPositionOfObject().y, enemyController.getEnemyList(), type, money);
                if (cost != 0){
                    tile.setType(GridType.TOWER);
                    removeMoney(cost);
                }
                this.map.getBoard().renderSwitch(false);
                break;
            case PATH:
                System.out.println("KAN IKKE BYGGE PÅ PATH");
                break;
            default:
                break;
        }
    }

    public void initiateNewWave() {
        currentWave++;
        // Determine the pattern for the new wave. This is a simple example;
        // more complex logic could be used to adjust difficulty.
        String newPattern = generateWavePattern(currentWave);

        // Update the pattern in the WaveEnemyFactory
        enemyController.updateWavePattern(newPattern);

        // Spawn new enemies based on the new pattern
        enemyController.spawnNewWave();
    }

    private String generateWavePattern(int wave) {
        StringBuilder patternBuilder = new StringBuilder();

        // Base number of enemies increases each wave
        int baseEnemies = 5 + (wave * 2); // Starts with 5 enemies, increases by 2 each wave

        // Example adjustment for enemy types based on wave number
        for (int i = 0; i < baseEnemies; i++) {
            if (wave < 3) {
                // Early waves: More 'R' type enemies
                patternBuilder.append("R");
            } else if (wave < 6) {
                // Intermediate waves: Mix 'R' and 'T' types
                // For every fourth enemy, add a 'T' to introduce difficulty
                if (i % 4 == 0) {
                    patternBuilder.append("T");
                } else {
                    patternBuilder.append("R");
                }
            } else {
                // Later waves: More 'T' type enemies and 'R' for variety
                if (i % 3 == 0) {
                    patternBuilder.append("R");
                } else {
                    patternBuilder.append("T");
                }
            }
        }

        return patternBuilder.toString();
    }


    /**
     * Removes users health when enemies manage to go through the whole path.
     * Also changes scene to game over if user has 0 health left.
     */
    public void enemyCompletedPath() {
        userHealth--;
        towerSelectionMenu.fireHealthChanged(userHealth);
        if (userHealth == 0){
            scene.gameOver();
        }
    }

    /**
     * Increases score and money when enemy is killed.
     * @param reward money gathered from killing the enemy
     */
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


    /*public void touchDown(float x, float y) {

        if (towerSelectionMenu.contains(x, y)){
            towerSelectionMenu.touchDown(x, y);
        } else {
            selectTile(x, y);
        }
    }*/


    /*public void touchRelease(float x, float y) {
        towerSelectionMenu.touchRelease(x, y);
    }*/

    /**
     * Select the tile user has clicked. If tower, then infomenu and towermenu showes.
     * If path or ground is clicked, then the menus disappear.
     * @param x value of the tile
     * @param y value of the tile
     */
    public void selectTile(float x, float y) {

        Tile tile = this.map.getSelectedTile(x, y);
        if (tile == null){
            return;
        }
        switch (tile.getType()){
            case TOWER:
                BaseDefender defender = towerController.getSelectedDefender(tile.getPositionOfObject());
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

    /**
     *
     * @return the selected tower
     */
    public BaseDefender getSelectedDefender() {
        return towerController.getSelectedDefender();
    }

    /**
     *
     * @return the map which contains the different types of tiles on the board
     */
    public Map getMap(){
        return map;
    }


    /**
     *
     * @return starthealth of the enemies
     */
    public int getEnemyHealth() {
        return enemyHealth;
    }

    /**
     *
     * @return number of current enemies
     */
    public int getEnemyNumber() {
        return numberOfEnemies;
    }

    /**
     * Render tile only if bool is set to true. This is made to not render the board
     * if not necessary which optimizes the game.
     * @param bool is ture when board is changed and needs to render and false else.
     */
    public void renderTiles(boolean bool) {
        this.map.getBoard().renderSwitch(bool);
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
        BaseDefender defender = towerController.getSelectedDefender();
        int cost = defender.getAttackCost();

        if (cost <= money){
            towerController.upgradeDamage();
            removeMoney(cost);
            infoMenu.updateTowerInfo(defender);
        }
    }

    /**
     * If user has enough money, range will be upgraded for the selected tower.
     * Money balance will also be updated as well as the info for tower.
     */
    public void upgradeRangeClicked() {
        BaseDefender defender = towerController.getSelectedDefender();
        int cost = defender.getRangePrice();
        if (cost <= money){
            towerController.upgradeRange();
            removeMoney(cost);
            infoMenu.updateTowerInfo(defender);
        }
    }

    /**
     * When user clicks upgrade speed on one of the towers,
     * it first checks if user has enough money. If user doesn't have
     * enough, then nothing happens. If user does,
     * the tower gets faster shootingspeed, balance gets updated and
     * towerinfo gets updated to new stats of the tower.
     */
    public void upgradeSpeedClicked() {
        BaseDefender defender = towerController.getSelectedDefender();
        int cost = defender.getSpeedPrice();
        if (cost <= money){
            towerController.upgradeSpeed();
            removeMoney(cost);
            infoMenu.updateTowerInfo(defender);
        }
    }

    /**
     * Game is restarted, and all values
     * are set back to initial values from the start() method.
     */
    public void restart() {
        start();
    }

    /**
     * Game gets paused when the method is called
     */
    public void pause() {
        scene.pause();
    }

    /**
     * If the game is paused, the game will be resumed when this method is called
     */
    public void resume() {
        scene.resume();
    }

    /**
     * Sets the speed of the game to 2x of normal speed
     */
    public void doubleSpeedClicked() {
        towerController.doubleSpeedClicked();
        enemyController.doubleSpeedClicked();
    }

    /**
     * Sets the speed of the game back to normal speed.
     */
    public void normalSpeedClicked() {
        towerController.normalSpeedClicked();
        enemyController.normalSpeedClicked();
    }

    /**
     * When the menu icon is cliced, pausescene is active
     */
    public void menuClicked() {
        scene.getSceneController().setScene(SceneEnum.PauseScene);
    }

    /**
     * Adds money to your bank and calls methods in infomenu and
     * towerselection menu to update your current balance
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
}
