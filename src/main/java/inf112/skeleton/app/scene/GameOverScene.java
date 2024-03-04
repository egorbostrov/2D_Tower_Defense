package inf112.skeleton.app.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.ui.buttons.ButtonFactory;
import inf112.skeleton.app.ui.buttons.OButton;
import inf112.skeleton.app.ui.buttons.OButtonListener;
import inf112.skeleton.app.ui.components.SimpleLayout;
import inf112.skeleton.app.util.GameUtil;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.resourceHandler.MyAtlas;

import java.util.ArrayList;
import java.util.List;

import static inf112.skeleton.app.util.GameConstants.TILE_HEIGHT;
import static inf112.skeleton.app.util.GameConstants.TILE_WIDTH;

public class GameOverScene extends Scene {


    private final String stateName = "GAME OVER";

    private OButton btnReplay;
    private OButton btnMenu;
    private final List<OButton> buttons;
    private final SimpleLayout layout;

    public GameOverScene(SceneController sceneController) {
        super(sceneController);

        bitmapFont = GameUtil.generateBitmapFont(100, Color.WHITE);
        glyphLayout.setText(bitmapFont, stateName);
        layout = new SimpleLayout(
                TILE_WIDTH * 3,
                TILE_HEIGHT * 4,
                TILE_WIDTH * 10,
                TILE_HEIGHT * 3,
                180,
                50
        );
        buttons = new ArrayList<>();
        initButtons();
        setListeners();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb, sr);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtil.renderCenter(stateName, sb, bitmapFont);
        layout.render(sb);
        sb.end();

    }

    @Override
    public void update(float deltaTime) {
    }

    private void initButtons() {

        final ButtonFactory bf = new ButtonFactory(TILE_WIDTH * 1.5f, TILE_HEIGHT * 1.5f);
        btnReplay = bf.createOButton(MyAtlas.PATH_TILE);
        btnMenu = bf.createOButton(MyAtlas.GROUND_TILE);

        buttons.add(btnReplay);
        buttons.add(btnMenu);

        layout.addComponents(buttons);
        layout.pack();

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
//    }

    private void setListeners() {
        btnReplay.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                getSceneController().setScene(StateEnum.PlayScene);
                //MusicHandler.playBackgroundMusic();
            }
        });
        btnMenu.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                getSceneController().setScene(StateEnum.MenuScene);
                //MusicHandler.playMenuMusic();
            }
        });

    }

}
