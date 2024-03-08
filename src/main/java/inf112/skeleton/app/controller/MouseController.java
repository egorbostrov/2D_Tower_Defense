package inf112.skeleton.app.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MouseController implements InputProcessor {

    private boolean isDragging;
    private int lastX, lastY;

    /**
     * Not in use yet, but might come in handy later.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT){
            lastX = screenX;
            lastY = screenY;
            isDragging = true;
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
