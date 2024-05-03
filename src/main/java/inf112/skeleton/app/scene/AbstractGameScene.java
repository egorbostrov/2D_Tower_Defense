package inf112.skeleton.app.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import inf112.skeleton.app.util.GameAssets;

public abstract class AbstractGameScene implements Screen {
    protected Game game;

    /**
     * Constructor for AbstractGameScene
     * @param game the game
     */
    public AbstractGameScene (Game game) {
        this.game = game;
    }

    public abstract void render (float deltaTime);


    public abstract void resize (int width, int height);
    public abstract void show ();
    public abstract void hide ();
    public abstract void pause ();

    public void resume () {
        GameAssets.instance.init();
    }

    public void dispose () {
        GameAssets.instance.dispose();
    }

}