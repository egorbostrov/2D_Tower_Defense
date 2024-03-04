package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.scene.SceneController;

import inf112.skeleton.app.resources.MyAtlas;
import inf112.skeleton.app.scene.Scene.GameState;
import inf112.skeleton.app.util.GameConstants;

public class TDGame extends ApplicationAdapter {

    private SceneController sceneController;
    private ShapeRenderer sr;
    private SpriteBatch sb;

    @Override
    public void create() {
        //MusicHandler.init();
        //MyAtlas.init();
        sceneController = new SceneController();
        sceneController.setScene(GameState.MENU);
        sb = new SpriteBatch();
        sr = new ShapeRenderer();
        //MusicHandler.playMenuMusic();
    }

    @Override
    public void render() {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sceneController.render(sb,sr);
        sceneController.update(Gdx.graphics.getDeltaTime());
    }

//    @Override
//    public void dispose() {
//        MyAtlas.dispose();
//    }
}
