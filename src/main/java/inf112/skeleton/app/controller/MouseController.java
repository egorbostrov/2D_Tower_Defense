package inf112.skeleton.app.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import java.util.List;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.scene.CameraManager;
import inf112.skeleton.app.tower.BaseDefender;

import inf112.skeleton.app.util.GameConstants;

public class MouseController implements InputProcessor {
    private final TowerController towerController;

    private final Level level;
    private final EnemyController enemyController;

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
            getCameraManager().getCamera().unproject(worldCoordinates);

            List<Enemy> currentEnemies = this.enemyController.getEnemyList();
            DefenderType selectedType = towerController.getSelectedTowerType();

            if (towerController.buildTower(worldCoordinates.x, worldCoordinates.y, currentEnemies, selectedType) > 0) {
                towerController.clearSelectedTower();
                return true;
            }

        } else if (button == Input.Buttons.LEFT && !towerController.isTowerSelected()) {
            for (BaseDefender defender : towerController.getDefenderList()) {
                if (defender.getBoundingRectangle().contains(screenX, GameConstants.SCREEN_HEIGHT - screenY)) {
                    towerController.setSelectedTowerUpgrade(defender);
                    return true;
                } else {
                    towerController.clearSelectedTower();
                    towerController.clearSelectedDefenderUpgrade();
                }
            }
        }
        return false;
    }




    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
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
