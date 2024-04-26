package inf112.skeleton.app.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
    private Image bgimg;
    private Level level;

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
        bgimg = new Image(uiskin, "background");
        bgimg.setScaling(Scaling.stretch);
        bgimg.setFillParent(true);
        layer.add(bgimg).expand().fill();
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

        // + Play Button
        playButton = new Button(uiskin, "play"); // make a new reset button
        layer.add(playButton);
        playButton.addListener(new ChangeListener() { // todo: lage general lambda-expression for listeners
            @Override
            public void changed(ChangeEvent event, Actor actor) {onResetClicked();}
        });
        layer.row();


        //+ Options Button
        optionsButton = new Button(uiskin, "options"); // make a new menu button
        layer.add(optionsButton);
        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onMenuClicked();
            }
        });
        layer.row();

        //+ exit button
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

    private void onResetClicked() {
        if (level != null) {
            level.restart();
        }
        game.setScreen(new PlayScene(game,1));  // Pass the reset level to the new PlayScene
    }

    private void onMenuClicked () {
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
        GameSettings prefs = GameSettings.instance;
        stage = new Stage(new StretchViewport(GameConstants.UI_WIDTH, GameConstants.UI_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        build();
        prefs.load();   // load preferences.
        MusicManager.changeMusicVolume(); // change volume (placeholder for when i will complete musicmanager).
        if(!GameSettings.getMusic()) {  // if music gets disabled in settings...
            MusicManager.stopCurrentMusic(); // stop the music.
        } else {
            MusicManager.play("menumusic.ogg", true);
        }

    }
    @Override
    public void pause () {

    }
}
