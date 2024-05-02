package inf112.skeleton.app.util;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class MoneyPopupTest {

    private static HeadlessApplication application;
    @BeforeAll
    public static void setupBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        when(Gdx.gl.glGenTexture()).thenReturn(1);
    }

    @Test
    void testNewMoneyPopup() {
        MoneyPopup popup = new MoneyPopup("Text", GameConstants.TOWER_DAMAGE_BOMBER, GameConstants.TOWER_DAMAGE_BOMBER, new Color(1),
                GameConstants.TOWER_DAMAGE_BOMBER);
        assertNotNull(popup);
    }

    @Test
    void updateTest() {
        MoneyPopup popup = new MoneyPopup("Text", GameConstants.TOWER_DAMAGE_BOMBER, GameConstants.TOWER_DAMAGE_BOMBER, new Color(1),GameConstants.TOWER_DAMAGE_BOMBER);
        assertEquals(popup.getTimer(), 10, "Timer was not set right");
        popup.update(2f);
        assertEquals(popup.getTimer(), 8, "2 seconds should have passet");
        assertFalse(popup.update(7), "timer should have ran out");
    }
}
