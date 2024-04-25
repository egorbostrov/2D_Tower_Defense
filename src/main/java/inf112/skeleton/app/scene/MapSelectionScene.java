package inf112.skeleton.app.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.util.GameConstants;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MapSelectionScene extends AbstractGameScene{

    private final Level level;
    private Skin uiMenuSkin;

    private Stage stage;
    private Button mapOneButton;
    private Button mapTwoButton;
    private Button exitButton;
    private Image bgimg;
    public MapSelectionScene(Game game, Level level){
        super(game);
        this.level = level;
    }

    private void build(){
        uiMenuSkin = new Skin(Gdx.files.internal(GameConstants.SKIN_UI),
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

    private Table buildBg() {   // move this to menuscenemenu later on.
        Table layer = new Table();
        layer.setFillParent(true);
        // + Background
        bgimg = new Image(uiMenuSkin, "background");
        bgimg.setScaling(Scaling.stretch);
        bgimg.setFillParent(true);
        layer.add(bgimg).expand().fill();
        return layer;
    }

    private Table buildControls() { // move this to menuscenemenu later on.
        Table layer = new Table();
        // + Map one button
        mapOneButton = new Button(uiMenuSkin, "Map 1");
        layer.add(mapOneButton);
        mapOneButton.addListener(new ChangeListener() { // todo: lage general lambda-expression for listeners
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("play was pressed");
                //Add level createmap here
            }
        });
        layer.row();

        //+ Map two button
        mapTwoButton = new Button(uiMenuSkin, "options");
        layer.add(mapTwoButton);
        mapTwoButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        layer.row();

        //+ exit button
        exitButton = new Button(uiMenuSkin, "exit");
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

    @Override
    public void render(float deltaTime) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }
}
