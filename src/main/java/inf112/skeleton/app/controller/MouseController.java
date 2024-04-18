package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import java.util.List;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.ui.menu.MainControlMenu;

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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touched");
        int flippedY = Gdx.graphics.getHeight() - screenY;
        if (button == Input.Buttons.LEFT && towerController.isTowerSelected()) {
            Vector3 worldCoordinates = new Vector3(screenX, flippedY, 0);
            List<Enemy> currentEnemies = this.enemyController.getEnemyList();
            DefenderType selectedType = DefenderType.valueOf(towerController.getSelectedTowerType());
            int availableMoney = this.level.getMoney();  // Assume getMoney() method provides current money

            // Check if a non-zero cost is returned, indicating a tower was built
            if (towerController.buildTower(worldCoordinates.x, worldCoordinates.y, currentEnemies, selectedType, availableMoney) > 0) {
                towerController.clearSelectedTower();  // Clear selection after placing
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
