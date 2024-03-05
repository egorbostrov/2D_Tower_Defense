package inf112.skeleton.app.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.ui.buttons.ButtonFactory;
import inf112.skeleton.app.ui.buttons.OButton;
import inf112.skeleton.app.ui.buttons.OButtonListener;
import inf112.skeleton.app.ui.buttons.OToggleButton;
import inf112.skeleton.app.ui.components.Pressable;
import inf112.skeleton.app.ui.components.SimpleLayout;
import inf112.skeleton.app.util.GameUtil;
import inf112.skeleton.app.util.GameConstants;
import inf112.skeleton.app.resourceHandler.MyAtlas;

import java.util.ArrayList;
import java.util.List;

import static inf112.skeleton.app.util.GameConstants.TILE_HEIGHT;
import static inf112.skeleton.app.util.GameConstants.TILE_WIDTH;

public class OptionScene extends Scene {

    private final String stateName = "OPTION MENU";

    private OToggleButton btnMusic;
    private OToggleButton btnSound;
    private OButton btnBack;
    private final List<Pressable> buttons;
    private final SimpleLayout simpleLayout;

    public OptionScene(SceneController sceneController) {
        super(sceneController);
        glyphLayout.setText(bitmapFont, stateName);
        simpleLayout = new SimpleLayout(
                TILE_WIDTH * 3,
                TILE_HEIGHT * 4,
                TILE_WIDTH * 10,
                TILE_HEIGHT * 3,
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtil.renderCenter(stateName, sb, bitmapFont);
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

//    @Override
//    public void scrolled(int amount) {
//
//    }

    private void initButtons() {
        final ButtonFactory bf = new ButtonFactory(TILE_WIDTH * 1.5f, TILE_HEIGHT * 1.5f);
//        btnSound = bf.createToggleButton(MyAtlas.SOUND_ON, MyAtlas.SOUND_OFF);
//        btnMusic = bf.createToggleButton(MyAtlas.MUSIC_ON, MyAtlas.MUSIC_OFF);
        btnBack = bf.createOButton("BACK", MyAtlas.GROUND_TILE, true);

//        buttons.add(btnSound);
//        buttons.add(btnMusic);
        buttons.add(btnBack);

        simpleLayout.addComponents(btnBack);
        simpleLayout.pack();
    }

    private void setListeners() {
//        btnSound.setToggleListener(isToggled -> MusicHandler.setSoundOnOff(!isToggled));
//        btnMusic.setToggleListener(isToggled -> MusicHandler.setMusicOnOff(!isToggled));
        btnBack.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                getSceneController().goBack();
        });

    }

}
