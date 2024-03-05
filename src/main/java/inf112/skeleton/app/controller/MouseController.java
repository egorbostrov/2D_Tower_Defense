package inf112.skeleton.app.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MouseController implements InputProcessor {

    private boolean isDragging;
    private int lastX, lastY;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT){
            lastX = screenX;
            lastY = screenY;
            isDragging = true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT){
            isDragging = false;
            return true;
        }
        return false;
    }

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

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        float mouseX = screenX;
        float mouseY = screenY;

        return false;
    }

    // Methods below are not used
    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }
}
