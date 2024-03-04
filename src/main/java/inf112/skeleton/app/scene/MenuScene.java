package inf112.skeleton.app.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.resourceHandler.MyAtlas;
import inf112.skeleton.app.ui.buttons.ButtonFactory;
import inf112.skeleton.app.ui.buttons.OButton;
import inf112.skeleton.app.ui.buttons.OButtonListener;
import inf112.skeleton.app.ui.components.SimpleLayout;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.util.GameUtil;

import java.util.ArrayList;
import java.util.List;

public class MenuScene extends Scene{

    private final String sceneName = "MENU";
    private OButton btnPlay;
    private OButton btnOptions;
    private OButton btnExit;
    private final List<OButton> buttons;
    private final SimpleLayout simpleLayout;

    public MenuScene(SceneController sceneController) {
        super(sceneController);
        glyphLayout.setText(bitmapFont, sceneName);
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

    private void setListeners() {
        btnPlay.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                getSceneController().setScene(StateEnum.PlayScene);
//                MusicHandler.playBackgroundMusic();
//                MusicHandler.stopMenuMusic();
            }
        });
        btnOptions.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                getSceneController().setScene(StateEnum.OptionScene);
            }
        });
        btnExit.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                Gdx.app.exit();
            }
        });
    }

    private void initButtons() {
        final ButtonFactory bf = new ButtonFactory(GameConstants.TILE_WIDTH * 1.5f,
                GameConstants.TILE_HEIGHT * 1.5f);
        btnPlay = bf.createOButton(MyAtlas.PATH_TILE);
        btnOptions = bf.createOButton("OPTIONS", MyAtlas.GROUND_TILE, true);
        btnExit = bf.createOButton("EXIT", MyAtlas.GROUND_TILE, true);

        buttons.add(btnPlay);
        buttons.add(btnOptions);
        buttons.add(btnExit);

        simpleLayout.addComponents(buttons);
        simpleLayout.pack();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb, sr);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        GameUtil.renderCenter(sceneName, sb, bitmapFont);
        simpleLayout.render(sb);
        sb.end();
    }

    @Override
    public void update(float deltaTime) {

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
}
