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
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.GameSettings;

public class OptionScene extends AbstractGameScene {
    private final String stateName = "OPTION MENU";
    private Stage stage;
    private Skin skin;
    private TextButton saveButton;
    private TextButton cancelButton;
    private CheckBox chkSound;
    private Slider sldSound;
    private CheckBox chkMusic;
    private Slider sldMusic;
    private CheckBox chkFullscreen;
    private static boolean fromPlayScene;
    private Image bgImg;
    private Skin uiskin;
    private int mapNumber;

    public OptionScene(Game game, int currentMapNumber) {
        super(game);
        this.mapNumber = currentMapNumber;
    }

    public OptionScene(Game game) { // Constructor for cases without a mapNumber
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

    // Getter and setter to see if options was triggered from PlayScene
    public static boolean getFromPlayScene() {
        return fromPlayScene;
    }

    public static void setFromPlayScene() {
        fromPlayScene = true;
    }
    // Method for Save button
    private void onSaveClicked() {
        saveSettings();
        onCancelClicked();
    }

    // Method for Cancel button
    private void onCancelClicked() {
        if (fromPlayScene && mapNumber >= 0) { // Check if called from PlayScene and a valid map number exists
            game.setScreen(new PlayScene(game, mapNumber));
        } else {
            game.setScreen(new MenuScene(game));
        }
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
