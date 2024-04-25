package inf112.skeleton.app.scene;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.controller.EnemyController;
import inf112.skeleton.app.controller.MouseController;
import inf112.skeleton.app.controller.TowerController;
import inf112.skeleton.app.enums.DefenderType;
import inf112.skeleton.app.level.Level;
//import inf112.skeleton.app.scene.WorldController;
import inf112.skeleton.app.ui.menu.MainControlMenu;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.GameSettings;
import inf112.skeleton.app.util.MusicManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


import static inf112.skeleton.app.util.GameConstants.*;

public class PlayScene extends AbstractGameScene {
    private boolean paused;
    private Stage stage;
    private Skin uimenuskin;
    private Button gunnerButton;
    private Button sniperButton;
    private Button bomberButton;
    private SpriteBatch spriteBatch;
    private Level level;
    private EnemyController enemyController;
    private TowerController towerController;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private CameraManager cameraManager;
    private MainControlMenu controlMenu;
    private BitmapFont bitmapFont;

    public PlayScene(Game game, int mapNumber) {
        super(game);
        initializeResources();
        setupUI();
        setupInput(); // Now setupInput can be called safely
        this.level = new Level(game, mapNumber);
        initializeGameControllers();

    }

    private void initializeResources() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        bitmapFont = new BitmapFont();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        stage = new Stage(new ScreenViewport(camera), spriteBatch);


    }

    private void setupInput() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                System.out.println("Key Pressed: " + Keys.toString(keycode));
                switch (keycode) {
                    case Keys.LEFT:
                        cameraManager.moveCamera(-10, 0);
                        break;
                    case Keys.RIGHT:
                        cameraManager.moveCamera(10, 0);
                        break;
                    case Keys.UP:
                        cameraManager.moveCamera(0, 10);
                        break;
                    case Keys.DOWN:
                        cameraManager.moveCamera(0, -10);
                        break;
                    case Keys.COMMA:
                        cameraManager.adjustZoom(-0.1f);
                        break;
                    case Keys.PERIOD:
                        cameraManager.adjustZoom(0.1f);
                        break;
                }
                return true;
            }
        });
        inputMultiplexer.addProcessor(stage); // Make sure UI is still responsive
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void setupUI() {
        uimenuskin = new Skin(Gdx.files.internal(GameConstants.SKIN_UI),
                new TextureAtlas(GameConstants.TEXTURE_ATLAS_UI));
        Table layerControls = buildControls();
        Stack stack = new Stack();
        stage.addActor(stack);
        stack.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stack.add(layerControls);
    }

    private void initializeGameControllers() {
        this.enemyController = EnemyController.getInstance(this.level);
        this.towerController = TowerController.getInstance(this.level);

        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, new MouseController(towerController, enemyController, level));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private Table buildControls() { // move this to playscenemenu later on.
        Table layer = new Table();
        layer.setFillParent(true);
        layer.bottom();

        gunnerButton = new Button(uimenuskin, "gunnertower");
        layer.add(gunnerButton).padBottom(10);  // Add padding at the bottom if needed
        gunnerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onTowerClicked(DefenderType.GUNNER);
            }
        });

        sniperButton = new Button(uimenuskin, "snipertower");
        layer.add(sniperButton).padBottom(10);  // Add padding at the bottom if needed
        sniperButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onTowerClicked(DefenderType.SNIPER);
            }
        });

        bomberButton = new Button(uimenuskin, "bombertower");
        layer.add(bomberButton).padBottom(10);  // Add padding at the bottom if needed
        bomberButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onTowerClicked(DefenderType.BOMBER);
            }
        });
        layer.row();
        return layer;
    }

    private void onTowerClicked (DefenderType type) { // adding UI button for creating towers in playscene for now, will implement to maincontrolmenu soon. WIP
        towerController.setTowerSelected(type);

    }
    //private String getClickedTowerType () {

    //}
    @Override
    public void render (float deltaTime) { // main renderer for the playscene.
        // Do not update game world when paused.
//        if (!paused) {
//            // Update game world by the time that has passed
//            // since last rendered frame.
//            worldController.update(deltaTime);
//        }
        level.update(deltaTime);
        spriteBatch.setProjectionMatrix(camera.combined);

        //controlMenu.updateInputs(Gdx.input.getX(), Gdx.input.getY());
        // Sets the clear screen color to: Cornflower Blue
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f,0xed / 255.0f, 0xff / 255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        if (level != null) {
            level.getMap().render(spriteBatch);
        }
        renderInfo(spriteBatch);
//        if (worldController != null){
//            worldController.render(spriteBatch);
//        }
        if (level != null){
            level.render(spriteBatch);
        }

        //controlMenu.render(spriteBatch);
        spriteBatch.end();
        //worldController.renderHitboxes(shapeRenderer);
        // Render game world to screen
        stage.act(deltaTime);
        stage.draw();
        if (level.getUserHealth() <= 0){
            game.setScreen(new MenuScene(game));
        }
    }


    private void renderInfo(SpriteBatch batch){
        String scoreText = "Score: " + level.getScore();
        String moneyText = "Money: " + level.getMoney();
        String waveText = "Wave: " + level.getCurrentWave();
        String enemiesText = "Enemies killed: " + level.getEnemiesKilled();
        String userHealthText = "Your lives: " + level.getUserHealth();

        float xCord = 10;
        float yCord = SCREEN_HEIGHT - 20;
        int padding = 20;

        GlyphLayout glyphScore = bitmapFont.draw(batch, scoreText, xCord, yCord);
        xCord += glyphScore.width + padding;

        GlyphLayout glyphMoney = bitmapFont.draw(batch, moneyText, xCord, yCord);
        xCord += glyphMoney.width + padding;

        bitmapFont.draw(batch, enemiesText, xCord, yCord);

        xCord = SCREEN_WIDTH / 2;
        GlyphLayout glyphWave = new GlyphLayout();
        glyphWave.setText(bitmapFont, waveText);

        bitmapFont.draw(batch, waveText, xCord - glyphWave.width / 2, yCord);

        GlyphLayout glyphUserHealth = new GlyphLayout();
        glyphUserHealth.setText(bitmapFont, userHealthText);

        bitmapFont.draw(batch, userHealthText, SCREEN_WIDTH - (glyphUserHealth.width + padding), yCord);
    }



    @Override
    public void resize (int width, int height) {
        if (stage != null) {
            stage.getViewport().update(width, height, true);
            camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
            camera.update();
        }
    }

    @Override
    public void show() {
        //controlMenu = new MainControlMenu(level, towerController);
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, new MouseController(towerController, enemyController, level));
        Gdx.input.setInputProcessor(inputMultiplexer);
        if(GameSettings.getMusic() == true) {
            MusicManager.play("gamemusic.ogg", true);
        }

        // temporary multiplexer to use both stage ui buttons and configurer mousecontroller bs so that i can place the towers. (temp temp temp!!!)
    }
    @Override
    public void hide () {
        MusicManager.stopCurrentMusic();
        spriteBatch.dispose();
        Gdx.input.setCatchBackKey(false);
        stage.dispose();
        uimenuskin.dispose();
    }

    @Override
    public void pause () {
        paused = true;
    }

    @Override
    public void resume () {
        super.resume();
    }
}