package inf112.skeleton.app.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.GameSettings;
import inf112.skeleton.app.util.MusicManager;

public class GameOverScene extends AbstractGameScene{
    private Stage stage;
    private Skin uiskin;
    //BUTTONS
    private Button playButton;
    private Button exitButton;
    private Button optionsButton;
    private Image image;
    private final Level level;

    public GameOverScene(Game game, Level level) {
        super(game);
        this.level = level;
    }

    private void build() {
        uiskin = new Skin(Gdx.files.internal(GameConstants.SKIN_UI),
                new TextureAtlas(GameConstants.TEXTURE_ATLAS_UI));

        Table layerBackground = buildBg();
        Table layerControls = buildControls();

        stage.clear();
        Stack stack = new Stack();
        stage.addActor(stack);
        stack.setSize(GameConstants.UI_WIDTH, GameConstants.UI_HEIGHT);
        stack.add(layerBackground);
        stack.add(layerControls);

    }

    private Table buildBg() {
        Table layer = new Table();
        layer.setFillParent(true);
        // + Background
        image = new Image(uiskin, "background");
        image.setScaling(Scaling.stretch);
        image.setFillParent(true);
        layer.add(image).expand().fill();
        return layer;
    }

    private Table buildControls() {
        Table layer = new Table();
        layer.pad(10, 10, 0, 10);

        // Score
        String scoreText = "Score: " + level.getScore();
        layer.add(new Label(scoreText, uiskin));
        layer.row();

        // Enemies Killed
        String enemiesText = "Enemies Killed: " + level.getEnemiesKilled();
        layer.add(new Label(enemiesText, uiskin));
        layer.row();

        // Waves
        String waveText = "Wave: " + level.getCurrentWave();
        layer.add(new Label(waveText, uiskin));
        layer.row();

        // + Back Button
        playButton = new Button(uiskin, "back");
        layer.add(playButton);
        playButton.addListener(new ChangeListener() { // todo: lage general lambda-expression for listeners
            @Override
            public void changed(ChangeEvent event, Actor actor) {onMenuClicked();}
        });
        layer.row();

        //+ Exit button
        exitButton = new Button(uiskin, "exit");
        layer.add(exitButton);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onExitClicked();
            }
        });
        return layer;
    }

    private void onExitClicked() {
        System.exit(0);
    }

    private void onMenuClicked() {
        level.restart();
        game.setScreen(new MenuScene(game));

    }


    @Override
    public void render (float deltaTime) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    @Override
    public void hide () {
        stage.dispose();
        uiskin.dispose();
    }
    @Override
    public void show () {
        GameSettings preference = GameSettings.instance;
        stage = new Stage(new StretchViewport(GameConstants.UI_WIDTH, GameConstants.UI_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        build();
        preference.load();
        MusicManager.changeMusicVolume();
        if(!GameSettings.getMusic()) {
            MusicManager.stopCurrentMusic();
        } else {
            MusicManager.play("menumusic.ogg", true);
        }

    }
    @Override
    public void pause () {

    }
}
