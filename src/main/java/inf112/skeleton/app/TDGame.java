package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.resourceHandler.MyAtlas;
import inf112.skeleton.app.scene.Scene;
import inf112.skeleton.app.scene.Scene.StateEnum;
import inf112.skeleton.app.scene.SceneController;

public class TDGame extends ApplicationAdapter {

    private SceneController sceneController;
    private ShapeRenderer render;
    private SpriteBatch batch;

    @Override
    public void create() {
        //MyAtlas.init();
        sceneController = new SceneController();
        sceneController.setScene(StateEnum.MenuScene);
        batch = new SpriteBatch();
        render = new ShapeRenderer();
    }

    @Override
    public void render() {
        sceneController.render(batch,render);
        sceneController.update(Gdx.graphics.getDeltaTime());
    }
}
