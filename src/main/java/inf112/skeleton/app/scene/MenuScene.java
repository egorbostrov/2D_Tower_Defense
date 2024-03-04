package inf112.skeleton.app.scene;

import com.badlogic.gdx.Gdx;
import inf112.skeleton.app.ui.buttons.ButtonFactory;
import inf112.skeleton.app.ui.buttons.OButton;
import inf112.skeleton.app.ui.buttons.OButtonListener;
import inf112.skeleton.app.ui.components.SimpleLayout;
import inf112.skeleton.app.util.GameConstants;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import java.util.ArrayList;
import java.util.List;

public class MenuScene extends Scene{

    private final String stateName = "MENU";
    private OButton btnPlay;
    private OButton btnOptions;
    private OButton btnExit;
    private final List<OButton> buttons;
    private final SimpleLayout simpleLayout;

    public MenuScene(SceneController sceneController) {
        super(sceneController);
        layout.setText(font, stateName);
        simpleLayout = new SimpleLayout(
                GameConstants.GRID_WIDTH * 3,
                GameConstants.GRID_HEIGHT * 4,
                GameConstants.GRID_WIDTH * 10,
                GameConstants.GRID_HEIGHT * 3,
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
                getSceneController().setScene(GameState.PLAY);
//                MusicHandler.playBackgroundMusic();
//                MusicHandler.stopMenuMusic();
            }
        });
        btnOptions.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                getSceneController().setScene(GameState.OPTION);
            }
        });
        btnExit.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                Gdx.app.exit();
            }
        });
    }

    private void initButtons() {
        final ButtonFactory bf = new ButtonFactory(GameConstants.GRID_WIDTH * 1.5f,
                GameConstants.GRID_HEIGHT * 1.5f);
        btnPlay = bf.createOButton("PLAY", true);
        btnOptions = bf.createOButton("OPTIONS", true);
        btnExit = bf.createOButton("EXIT", true);

        buttons.add(btnPlay);
        buttons.add(btnOptions);
        buttons.add(btnExit);

        simpleLayout.addComponents(buttons);
        simpleLayout.pack();
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void updateInputs(float x, float y) {

    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {

    }

    @Override
    public void touchUp(float x, float y, int pointer, int button) {

    }

    @Override
    public void scrolled(int amount) {

    }
}
