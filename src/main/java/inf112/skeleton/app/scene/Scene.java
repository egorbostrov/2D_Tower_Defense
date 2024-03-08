package inf112.skeleton.app.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.GameUtil;

public abstract class Scene {
    protected SceneController sceneController;
    protected OrthographicCamera camera;
    protected GlyphLayout glyphLayout;
    protected BitmapFont bitmapFont;


    public Scene(SceneController sceneController) {
        this.sceneController = sceneController;
        bitmapFont = GameUtil.generateBitmapFont(100, Color.WHITE);
        glyphLayout = new GlyphLayout();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
    }

    /**
     * Renders the current screen using both sprites and shapes
     * @param batch SpriteBatch used
     * @param sr ShapeRenderer used
     */
    public void render(SpriteBatch batch, ShapeRenderer sr) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        sr.setProjectionMatrix(camera.combined);
    }


    public SceneController getSceneController() {
        return sceneController;
    }


    /**
     * Updates the state of the scene or object based on the elapsed time.
     * This method should contain logic for updating the game state, animations, or any time-sensitive operations.
     *
     * @param elapsedTime The time in seconds that has elapsed since the last update.
     */
    public abstract void update(float elapsedTime);

    /**
     * Updates the input handling state based on the current position of the input (e.g., mouse or touch position).
     * This method is intended to process continuous input states, such as cursor movement or touch drag.
     *
     * @param x The x-coordinate of the input position.
     * @param y The y-coordinate of the input position.
     */
    public abstract void updateInputs(float x, float y);

    /**
     * Processes the event of a touch or mouse click being pressed down.
     * This method is intended for handling initial input actions, such as button presses or touch start events.
     *
     * @param x       The x-coordinate where the touch or click occurred.
     * @param y       The y-coordinate where the touch or click occurred.
     * @param pointer The pointer index for multi-touch events (ignored for mouse input).
     * @param button  The button code (for mouse input) or 0 for touch input.
     */
    public abstract void touchDown(float x, float y, int pointer, int button);

    /**
     * Processes the event of a touch or mouse click being released.
     * This method is intended for handling the conclusion of an input action, such as button releases or touch end events.
     *
     * @param x       The x-coordinate where the touch or click was released.
     * @param y       The y-coordinate where the touch or click was released.
     * @param pointer The pointer index for multi-touch events (ignored for mouse input).
     * @param button  The button code (for mouse input) or 0 for touch input.
     */
    public abstract void touchUp(float x, float y, int pointer, int button);


}
