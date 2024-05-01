package inf112.skeleton.app.map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.level.Level;
import inf112.skeleton.app.map.Map;

import inf112.skeleton.app.util.GameAssets;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.badlogic.gdx.files.FileHandle;
import java.util.LinkedList;
import java.util.Random;

import static inf112.skeleton.app.util.GameConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MapTest {

    @Mock
    private Map mockMap;

    @Mock
    private FileHandle mockFileHandle;

    private static HeadlessApplication application;


    @BeforeAll
    public static void setupBeforeAll(){
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);
    }

    @BeforeEach
    void setUp(){

        mockMap = new Map(1);

    }

    @Test
    void testInitializeMap(){
        assertNotNull(mockMap);

    }

    @Test
    void testBoardCreationWithValidData() {
        assertNotNull(mockMap.getBoard());
    }

    @Test
    void testHandleInvalidMapSelection() {
        Random random = new Random();
        int randomNegativeNumber = random.nextInt(100) * -1;
        int randomOverThree = 3 + random.nextInt(100);
        assertThrows(IllegalArgumentException.class, () -> new Map(randomNegativeNumber));
        assertThrows(IllegalArgumentException.class, () -> new Map(randomOverThree));
    }

    @AfterAll
    public static void tearDown() {
        if(application != null) {
            application.exit();
            application = null;
        }
    }


}