package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import java.util.List;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.scene.CameraManager;
import inf112.skeleton.app.ui.menu.MainControlMenu;
import inf112.skeleton.app.util.GameConstants;

public class MouseController implements InputProcessor {
    private TowerController towerController;
    private MainControlMenu controlMenu;
    private boolean isDragging;
    private Level level;
    private int lastX, lastY;
    private EnemyController enemyController;

    public MouseController(TowerController towerController, EnemyController enemyController, Level level) {
        this.towerController = towerController;
        this.enemyController = enemyController;
        this.level = level;
    }

    private CameraManager getCameraManager() {
        return level.getCameraManager();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT && towerController.isTowerSelected()) {
            Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
            getCameraManager().getCamera().unproject(worldCoordinates); // Use CameraManager's camera to unproject
            float towerSize = GameConstants.TOWER_SIZE;
            float centeredSizeX = worldCoordinates.x - towerSize / 2;
            float centeredSizeY = worldCoordinates.y - towerSize / 2;

            List<Enemy> currentEnemies = this.enemyController.getEnemyList();
            DefenderType selectedType = towerController.getSelectedTowerType();
            int availableMoney = this.level.getMoney();

            if (towerController.buildTower(centeredSizeX, centeredSizeY, currentEnemies, selectedType, availableMoney) > 0) {
                towerController.clearSelectedTower();
                return true;
            }
        }
        return false;
    }




    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT){
            isDragging = false;
            return true;
        }
        return false;
    }
    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (isDragging) {
            float deltaX = screenX - lastX;
            float deltaY = screenY - lastY;
            lastX = screenX;
            lastY = screenY;
            return true;
        }
        return false;
    }
    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        float mouseX = screenX;
        float mouseY = screenY;

        return false;
    }
    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }
    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean keyDown(int i) {
        return false;
    }
    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean keyUp(int i) {
        return false;
    }
    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean keyTyped(char c) {
        return false;
    }
}
