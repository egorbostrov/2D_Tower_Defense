package inf112.skeleton.app.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import inf112.skeleton.app.enums.SceneEnum;
import inf112.skeleton.app.controller.Render;

import java.util.HashMap;
import java.util.Map;

public class SceneController extends InputAdapter{

    private final Map<SceneEnum, Scene> sceneMap;
    private Scene currentScene;
    private Scene previousScene;

    public SceneController() {
        sceneMap = new HashMap<>();
        Gdx.input.setInputProcessor(this);
    }

    public void setScene(SceneEnum enumScene) {
        previousScene = currentScene;
        currentScene = getScene(enumScene);
    }

    /**
     * Reverts to the previous scene, swapping the current and previous scenes.
     */
    public void goBack() {
        Scene tmp = previousScene;
        previousScene = currentScene;
        currentScene = tmp;
    }

    /**
     * Retrieves the scene associated with the given scene enum.
     * If the scene does not exist, it creates a new one.
     *
     * @param enumState The scene enum of the requested scene.
     * @return The scene associated with the given enum.
     */
    public Scene getScene(SceneEnum enumState){
        return sceneMap.computeIfAbsent(enumState, this::createScene);
    }

    /**
     * Renders the current scene using the provided SpriteBatch and ShapeRenderer.
     *
     * @param sb The SpriteBatch used for drawing sprites.
     * @param sr The ShapeRenderer used for drawing shapes.
     */
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        currentScene.render(sb,sr);
    }

    /**
     * Updates the current scene and its inputs based on the given delta time.
     *
     * @param deltaTime The time since the last frame in seconds.
     */
    public void update(float deltaTime) {
        currentScene.update(deltaTime);
        Vector2 result = unproject(Gdx.input.getX(),Gdx.input.getY());
        currentScene.updateInputs(result.x,result.y);
    }

    /**
     * Unprojects screen coordinates to world coordinates relative to the current scene's camera.
     *
     * @param x The x-coordinate on the screen.
     * @param y The y-coordinate on the screen.
     * @return The unprojected world coordinates as a Vector2.
     */
    private Vector2 unproject(int x, int y){
        final OrthographicCamera c = currentScene.camera;
        Vector3 r = c.unproject(new Vector3(x,y,1));
        return new Vector2(r.x,r.y);
    }

    /**
     * Processes touch down input events, forwarding the event to the current scene.
     *
     * @param screenX  The x-coordinate of the touch down.
     * @param screenY  The y-coordinate of the touch down.
     * @param pointer  The pointer for the event.
     * @param button   The button that was pressed.
     * @return false to indicate the event has not been consumed.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        final Vector2 result = unproject(screenX,screenY);
        currentScene.touchDown(result.x,result.y,pointer,button);
        return false;
    }

    /**
     * Processes touch up input events, forwarding the event to the current scene.
     *
     * @param screenX  The x-coordinate of the touch up.
     * @param screenY  The y-coordinate of the touch up.
     * @param pointer  The pointer for the event.
     * @param button   The button that was released.
     * @return false to indicate the event has not been consumed.
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        final Vector2 result = unproject(screenX,screenY);
        currentScene.touchUp(result.x,result.y,pointer,button);
        return false;
    }

//    @Override
//    public boolean scrolled(int amount) {
//        currentScene.scrolled(amount);
//        return false;
//    }

    /**
     * Creates a new scene based on the specified scene enum.
     *
     * @param enumScene The scene enum indicating which scene to create.
     * @return The newly created scene.
     * @throws IllegalArgumentException if the enumScene does not correspond to a valid scene.
     */
    private Scene createScene(SceneEnum enumScene) {
        switch (enumScene) {
            case PlayScene:
                return new PlayScene(this);
            case GameOverScene:
                return new GameOverScene(this);
            case MenuScene:
                return new MenuScene(this);
            case OptionScene:
                return new OptionScene(this);
            case PauseScene:
                return new PauseScene(this);
            default:
                throw new IllegalArgumentException("Invalid state enum: " + enumScene);
        }
    }
}
