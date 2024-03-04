package inf112.skeleton.app.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.ui.buttons.ButtonFactory;
import inf112.skeleton.app.ui.buttons.OButton;
import inf112.skeleton.app.ui.buttons.OButtonListener;
import inf112.skeleton.app.ui.components.SimpleLayout;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.GameUtil;
import inf112.skeleton.app.resourceHandler.MyAtlas;


import java.util.ArrayList;
import java.util.List;

public class PauseScene extends Scene {

    private final String stateName = "PAUSED MENU";
    private OButton btnResume;
    private OButton btnRestart;
    private OButton btnOptions;
    private final List<OButton> buttons;
    private final SimpleLayout simpleLayout;

    public PauseScene(SceneController sceneController) {
        super(sceneController);
        glyphLayout.setText(bitmapFont, stateName);
        simpleLayout = new SimpleLayout(
                GameConstants.TILE_WIDTH * 3,
                GameConstants.TILE_HEIGHT * 4,
                GameConstants.TILE_WIDTH * 10,
                GameConstants.TILE_HEIGHT * 3,
                110,
                50
        );
        buttons = new ArrayList<>();
        initButtons();
        setListeners();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb, sr);
        //Gdx.gl.glClearColor(RED, GREEN, BLUE, ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtil.renderCenter(stateName, sb, bitmapFont);
        simpleLayout.render(sb);
        sb.end();
    }

    @Override
    public void update(float deltaTime) {
    }

    private void initButtons() {
        final ButtonFactory bf = new ButtonFactory(GameConstants.TILE_WIDTH * 1.5f,
                GameConstants.TILE_HEIGHT * 1.5f);
        btnRestart = bf.createOButton(MyAtlas.PATH_TILE);
        btnResume = bf.createOButton(MyAtlas.GROUND_TILE);
        btnOptions = bf.createOButton("Options", MyAtlas.GROUND_TILE, true);

        buttons.add(btnRestart);
        buttons.add(btnResume);
        buttons.add(btnOptions);

        simpleLayout.addComponents(buttons);
        simpleLayout.pack();

    }


    @Override
    public void updateInputs(float x, float y) {
    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {
        buttons.stream()
                .filter(b -> b.contains(x, y))
                .findFirst()
                .ifPresent(b -> b.touchDown(x, y));
    }

    @Override
    public void touchUp(float x, float y, int pointer, int button) {
        buttons.forEach(b -> b.setPressed(false));
        buttons.stream()
                .filter(b -> b.contains(x, y))
                .findFirst()
                .ifPresent(b -> b.touchRelease(x, y));
    }

//    @Override
//    public void scrolled(int amount) {
//
//    }


    private void setListeners() {
        btnRestart.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                PlayScene state = (PlayScene) getSceneController().getScene(StateEnum.PlayScene);
                getSceneController().setScene(StateEnum.PlayScene);
                state.restart();
            }
        });
        btnResume.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                getSceneController().setScene(StateEnum.PlayScene);
        });
        btnOptions.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                getSceneController().setScene(StateEnum.OptionScene);
        });
    }
}
