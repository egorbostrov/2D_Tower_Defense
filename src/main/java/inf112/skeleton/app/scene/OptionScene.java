package inf112.skeleton.app.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.GameSettings;
import inf112.skeleton.app.util.MusicManager;

public class OptionScene extends AbstractGameScene {
    private final String stateName = "OPTION MENU";
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private TextButton saveButton;
    private TextButton cancelButton;
    private CheckBox chkSound;
    private Slider sldSound;
    private CheckBox chkMusic;
    private Slider sldMusic;
    private CheckBox chkFullscreen;

    private Image bgImg;
    private Skin uiskin;

    public OptionScene(Game game) {
        super(game);
    }

    // Build the scene
    private void build() {
        skin = new Skin(Gdx.files.internal(GameConstants.SKIN_UI),
                new TextureAtlas(GameConstants.TEXTURE_ATLAS_UI));
        uiskin = new Skin(Gdx.files.internal(GameConstants.PLACEHOLDER_UI),
                new TextureAtlas(GameConstants.PLACEHOLDER_ATLAS));

        Table layerBackground = buildBg();
        Table layerControls = buildControls();

        stage.clear();
        Stack stack = new Stack();
        stage.addActor(stack);
        stack.setSize(GameConstants.UI_WIDTH, GameConstants.UI_HEIGHT);
        stack.add(layerBackground);
        stack.add(layerControls);

    }

    // Build the controls layer.
    private Table buildControls () {
        Table layer = new Table();
        // audio-options
        layer.pad(10, 10, 0, 10);
        layer.add(new Label("Options", uiskin, "default-font", Color.ORANGE)).colspan(3);
        layer.row();
        layer.columnDefaults(0).padRight(10);
        layer.columnDefaults(1).padRight(10);
        // + Checkbox, "Sound" label, sound volume slider
        chkSound = new CheckBox("Sound", uiskin);
        layer.add(chkSound);
        layer.add(new Label("Volume", uiskin));
        sldSound = new Slider(0.0f, 1.0f, 0.1f, false, uiskin);
        layer.add(sldSound);
        layer.row();
        // + Checkbox, "Music" label, music volume slider
        chkMusic = new CheckBox("Music", uiskin);
        layer.add(chkMusic);
        layer.add(new Label("Volume", uiskin));
        sldMusic = new Slider(0.0f, 1.0f, 0.1f, false, uiskin);
        layer.add(sldMusic);
        layer.row();
        saveButton = new TextButton("Save", uiskin);
        layer.add(saveButton).padRight(30);
        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                onSaveClicked();
            }});
        cancelButton = new TextButton("Cancel", uiskin);
        layer.add(cancelButton);
        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onCancelClicked();
            }
        });
        // video-options
        chkFullscreen = new CheckBox("Fullscreen", uiskin);
        layer.add(chkFullscreen);

        return layer;

    }

    // Builds the background-layer.
    private Table buildBg() {
        Table layer = new Table();
        layer.setFillParent(true);
        // + Background
        bgImg = new Image(skin, "background");
        bgImg.setScaling(Scaling.stretch);
        bgImg.setFillParent(true);
        layer.add(bgImg).expand().fill();
        return layer;
    }


    // Load settings from My Preferences and change the buttons/sliders to their corresponding values.
    private void loadSettings() {
        GameSettings prefs = GameSettings.instance;
        prefs.load();
        chkSound.setChecked(prefs.getSound());
        sldSound.setValue(prefs.getVolSound());
        chkMusic.setChecked(prefs.getMusic());
        sldMusic.setValue(prefs.getVolMusic());
        chkFullscreen.setChecked(prefs.getFullscreen());
    }

    // Save selected settings to My Preferences, using the corresponding values set by the user.
    private void saveSettings() {
        GameSettings prefs = GameSettings.instance;
        prefs.setSound(chkSound.isChecked());;
        prefs.setVolSound(sldSound.getValue());
        prefs.setMusic(chkMusic.isChecked());
        prefs.setVolMusic(sldMusic.getValue());
        prefs.setFullscreen(chkFullscreen.isChecked());
        prefs.save();
    }

    // Method for Save button
    private void onSaveClicked() {
        saveSettings();
        onCancelClicked();
    }

    // Method for Cancel button
    private void onCancelClicked() {
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
    public void show () {
        stage = new Stage(new StretchViewport(GameConstants.UI_WIDTH, GameConstants.UI_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        build();
        loadSettings();
    }

    @Override
    public void hide () {
        stage.dispose();
        skin.dispose();
    }
    @Override
    public void pause() {

    }
}
