package inf112.skeleton.app.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import inf112.skeleton.app.scene.Scene.GameState;

import java.util.HashMap;
import java.util.Map;

public class SceneController extends InputAdapter {

    private final Map<GameState, Scene> sceneMap;
    private Scene currentScene;
    private Scene previousScene;

    public SceneController() {
        sceneMap = new HashMap<>();
        Gdx.input.setInputProcessor(this);
    }

    public void setScene(GameState gameState) {
        previousScene = currentScene;
        currentScene = getScene(gameState);
    }

    /**
     * Goes back to previous state, which means we have to swap current and previous state.
     * */
    public void goBack() {
        Scene tmp = previousScene;
        previousScene = currentScene;
        currentScene = tmp;
    }

    /**
     * Return the Scene, if it already exist or make a new one.
     * */
    public Scene getScene(GameState gameState){
        return sceneMap.computeIfAbsent(gameState, this::createScene);
    }

    public void render(SpriteBatch sb, ShapeRenderer sr) {
        currentScene.render(sb,sr);
    }

    public void update(float deltaTime) {
        currentScene.update(deltaTime);
        Vector2 result = unproject(Gdx.input.getX(),Gdx.input.getY());
        currentScene.updateInputs(result.x,result.y);
    }


    private Vector2 unproject(int x, int y){
        final OrthographicCamera c = currentScene.camera;
        Vector3 r = c.unproject(new Vector3(x,y,1));
        return new Vector2(r.x,r.y);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        final Vector2 result = unproject(screenX,screenY);
        currentScene.touchDown(result.x,result.y,pointer,button);
        return false;
    }

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
    private Scene createScene(GameState gameState) {
        switch (gameState) {
//            case PLAY:
//                return new PlayScene(this);
//            case GAMEOVER:
//                return new GameOverScene(this);
            case MENU:
                return new MenuScene(this);
//            case OPTION:
//                return new OptionScene(this);
//            case PAUSE:
//                return new PauseScene(this);
            default:
                throw new IllegalArgumentException("Invalid state enum: " + gameState);
        }
    }
}
