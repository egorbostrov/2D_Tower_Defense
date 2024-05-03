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
import inf112.skeleton.app.util.MusicManager;


public class HowToPlayScene extends AbstractGameScene{

    private Stage stage;
    private Skin uiskin;
    private TextureAtlas atlas;

    //BUTTONS
    private Button backButton;
    private Image image;

    /**
     * Constructor for HowToPlayScene that shows info on how to play the game
     * @param game the game
     */
    public HowToPlayScene(Game game) {
        super(game);
    }

    private void build() {
        uiskin = new Skin(Gdx.files.internal(GameConstants.SKIN_UI),
                new TextureAtlas(GameConstants.TEXTURE_ATLAS_UI));
        atlas = new TextureAtlas(Gdx.files.internal("zombie.atlas"));

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


        // Title
        Label titleLabel = new Label("How to Play", uiskin, "default-font", Color.ORANGE);
        titleLabel.setFontScale(2);
        layer.add(titleLabel).padTop(10).center().colspan(5);
        layer.row();

        // How to play text
        Label howToPlayLabel = new Label("The goal of the game is to survive as long as possible.", uiskin);
        layer.add(howToPlayLabel).pad(1).center().colspan(5);
        layer.row();

        Label howToPlayLabel2 = new Label("You can build towers to help you defend yourself.", uiskin);
        layer.add(howToPlayLabel2).pad(1).center().colspan(5);
        layer.row();

        Label howToPlayLabel3 = new Label("You can also upgrade your towers to make them stronger.", uiskin);
        layer.add(howToPlayLabel3).pad(1).center().colspan(5);
        layer.row();


        // Descriptions for zombies
        Image regularZomIMG = new Image(atlas.findRegion("regularzombie"));
        layer.add(regularZomIMG).pad(2);
        Label regularZom = new Label("Regular zombie: Normal speed and health", uiskin);
        layer.add(regularZom).pad(2).colspan(5).center();
        layer.row();

        Image tankZomIMG = new Image(atlas.findRegion("tankzombie"));
        layer.add(tankZomIMG).pad(2);
        Label tankZom = new Label("Tank zombie: Slow speed and high health", uiskin);
        layer.add(tankZom).pad(2).colspan(5).center();
        layer.row();

        Image speedZomIMG = new Image(atlas.findRegion("runnerzombie"));
        layer.add(speedZomIMG).pad(2);
        Label speedZom = new Label("Speed zombie: Fast speed and low health", uiskin);
        layer.add(speedZom).pad(2).colspan(5).center();
        layer.row();

        // Descriptions for towers
        Image gunnerTowerIMG = new Image(atlas.findRegion("board_gunna"));
        layer.add(gunnerTowerIMG).pad(2);
        Label gunnerTower = new Label("Gunner Tower: Shoots fast, low damage, normal range", uiskin);
        layer.add(gunnerTower).pad(2);
        layer.row();

        Image sniperTowerIMG = new Image(atlas.findRegion("board_snipa"));
        layer.add(sniperTowerIMG).pad(2);
        Label sniperTower = new Label("Sniper Tower: Shoots slow, high damage, big range", uiskin);
        layer.add(sniperTower).pad(2);
        layer.row();

        Image bomberTowerIMG = new Image(atlas.findRegion("board_bomba"));
        layer.add(bomberTowerIMG).pad(2);
        Label bomberTower = new Label("Bomber Tower: Normal shooting, area damage, low range", uiskin);
        layer.add(bomberTower).pad(2);
        layer.row();

        // + Back button
        backButton = new Button(uiskin, "back"); // make a new back button
        layer.add(backButton).pad(10).center().colspan(5);
        backButton.addListener(new ChangeListener() { // todo: lage general lambda-expression for listeners
            @Override
            public void changed(ChangeEvent event, Actor actor) {onBackClicked();}
        });

        return layer;
    }


    private void onBackClicked() {
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
