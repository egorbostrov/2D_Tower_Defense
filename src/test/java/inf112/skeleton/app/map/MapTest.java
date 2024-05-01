package inf112.skeleton.app.map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import inf112.skeleton.app.enums.GridType;
import inf112.skeleton.app.util.GameConstants;
import org.junit.jupiter.api.*;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;


public class MapTest {
    private Map map;
    private static HeadlessApplication application;

    private int randomNumber;
    @BeforeAll
    public static void setupBeforeAll(){
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);
    }

    @BeforeEach
    void setUp(){
        Random random = new Random();
        randomNumber = 1 + random.nextInt(2);
        map = new Map(randomNumber);
    }

    @Test
    void testInitializeMap(){
        assertNotNull(map);
        assertEquals(map.getFilehandle(), Gdx.files.internal("maps/map" + randomNumber + ".txt"));
    }

    @Test
    void testBoardCreationWithValidData() {
        assertNotNull(map.getBoard());
    }

    @Test
    void testHandleInvalidMapSelection() {
        Random random = new Random();
        int randomNegativeNumber = random.nextInt(100) * -1;
        int randomOverThree = 3 + random.nextInt(100);
        assertThrows(IllegalArgumentException.class, () -> new Map(randomNegativeNumber));
        assertThrows(IllegalArgumentException.class, () -> new Map(randomOverThree));
    }

    @Test
    void testSelectedTileOffMap(){
        Random random = new Random();
        int randomNegativeNumberOne = random.nextInt(100) * -1;
        int randomNegativeNumberTwo = random.nextInt(100) * -1;
        Tile tileOffMap = map.getSelectedTile(randomNegativeNumberOne, randomNegativeNumberTwo);
        assertEquals(tileOffMap.getX(), -1);
        assertEquals(tileOffMap.getY(), -1);
        assertEquals(tileOffMap.getType(), GridType.ILLEGALPLACEMENT);
    }

    @RepeatedTest(100)
    void testSelectedTile(){
        Random random = new Random();
        int randomNumberOne = random.nextInt((int) GameConstants.MAP_WIDTH);
        int randomNumberTwo =(int) GameConstants.UI_HEIGHT +  random.nextInt((int) GameConstants.MAP_HEIGHT);
        Tile tileOnMap = map.getSelectedTile(randomNumberOne, randomNumberTwo);
        assertNotEquals(tileOnMap.getType(), GridType.ILLEGALPLACEMENT);
    }

    @AfterAll
    public static void tearDown() {
        if(application != null) {
            application.exit();
            application = null;
        }
    }


}