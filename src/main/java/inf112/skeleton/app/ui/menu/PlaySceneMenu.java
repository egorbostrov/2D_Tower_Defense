//package inf112.skeleton.app.ui.menu;
//
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import inf112.skeleton.app.level.Level;
//import inf112.skeleton.app.ui.buttons.ButtonFactory;
//import inf112.skeleton.app.ui.buttons.OButton;
//import inf112.skeleton.app.ui.buttons.OButtonListener;
//import inf112.skeleton.app.ui.buttons.OToggleButton;
//import inf112.skeleton.app.ui.components.SimpleLayout;
//import inf112.skeleton.app.ui.components.Pressable;
//import inf112.skeleton.app.util.GameConstants;
//import inf112.skeleton.app.resourceHandler.MyAtlas;
//import inf112.skeleton.app.ui.components.UIComponents;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static inf112.skeleton.app.util.GameConstants.TILE_WIDTH;
//
//public class PlaySceneMenu {
//    private static final float FUNC_BUTTON_WH = TILE_WIDTH * (0.8f);
//    private OToggleButton btnPauseResume;
//    private OToggleButton btnDoubleSpeed;
//
//    private OButton btnRestart;
//    private OButton btnExit;
//    private final List<Pressable> menuButtons;
//    private final SimpleLayout layoutFunctionalButtons;
//    private final Level level;
//
//    public PlaySceneMenu(Level level) {
//        this.level = level;
//        this.layoutFunctionalButtons =
//                new SimpleLayout(
//                        TILE_WIDTH * 2,
//                        GameConstants.TILE_HEIGHT * 2,
//                        2,
//                        5);
//        menuButtons = new ArrayList<>();
//        initButtons();
//    }
//
//    private void initButtons() {
//        final ButtonFactory bf = new ButtonFactory(FUNC_BUTTON_WH, FUNC_BUTTON_WH);
//        btnPauseResume = bf.createToggleButton(MyAtlas.BOMBER, MyAtlas.GUNNER);
//        btnDoubleSpeed = bf.createToggleButton(MyAtlas.GROUND_TILE, MyAtlas.GUNNER);
//        btnExit = bf.createOButton(MyAtlas.GROUND_TILE);
//        btnRestart = bf.createOButton(MyAtlas.PATH_TILE);
//
//        menuButtons.add(btnPauseResume);
//        menuButtons.add(btnDoubleSpeed);
//        menuButtons.add(btnRestart);
//        menuButtons.add(btnExit);
//
//        layoutFunctionalButtons.addComponents(btnPauseResume,btnDoubleSpeed,btnRestart,btnExit);
//        layoutFunctionalButtons.pack();
//
//        initButtonListener();
//    }
//
//    private void initButtonListener() {
//        btnPauseResume.setToggleListener(isToggled -> {
//            if (isToggled) {
//                level.pause();
//            } else {
//                level.resume();
//            }
//        });
//
//        btnDoubleSpeed.setToggleListener(isToggled -> {
//            if (isToggled) {
//                level.doubleSpeedClicked();
//            } else {
//                level.normalSpeedClicked();
//            }
//        });
//
//        btnRestart.setButtonListener((event, x, y) -> {
//            if (event == OButtonListener.TouchEvent.RELEASE)
//                level.restart();
//        });
//
//        btnExit.setButtonListener((event, x, y) -> {
//            if (event == OButtonListener.TouchEvent.RELEASE)
//                level.menuClicked();
//        });
//    }
//
//    public void render(ShapeRenderer sr) {
//        layoutFunctionalButtons.render(sr);
//    }
//
//
//    public void render(SpriteBatch sb) {
//        layoutFunctionalButtons.render(sb);
//    }
//
//    public void touchDown(float x, float y) {
//        menuButtons.stream().
//                filter(b -> b.contains(x, y)).
//                findFirst().
//                ifPresent(b -> b.touchDown(x, y));
//    }
//
//    public void touchRelease(float x, float y) {
//        menuButtons.stream().
//                filter(b -> b.contains(x, y)).
//                findFirst().
//                ifPresent(b -> b.touchRelease(x, y));
//    }
//
//    public UIComponents getLayout() {
//        return layoutFunctionalButtons;
//    }
//}
