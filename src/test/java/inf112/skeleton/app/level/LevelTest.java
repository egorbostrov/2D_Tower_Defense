  package inf112.skeleton.app.level;
//
//  import inf112.skeleton.app.scene.PlayScene;
//  import inf112.skeleton.app.scene.Scene;
//  import inf112.skeleton.app.scene.SceneController;
//
//  import org.junit.jupiter.api.BeforeAll;
//  import org.junit.jupiter.api.BeforeEach;
//  import org.junit.jupiter.api.Test;
//  import org.mockito.Mockito;
//  import static org.mockito.Mockito.*;
//
//  import com.badlogic.gdx.ApplicationAdapter;
//  import com.badlogic.gdx.Files;
//  import com.badlogic.gdx.Gdx;
//  import com.badlogic.gdx.backends.headless.HeadlessApplication;
//  import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
//  import com.badlogic.gdx.backends.headless.HeadlessFiles;
//  import com.badlogic.gdx.files.FileHandle;
//
//  import inf112.skeleton.app.enums.DefenderType;
//  import inf112.skeleton.app.enums.GridType;
//  import inf112.skeleton.app.map.Tile;
//
//  import inf112.skeleton.app.util.GameConstants;
//  import inf112.skeleton.app.level.Level;
//
//
//  import static org.junit.jupiter.api.Assertions.assertEquals;
//
//  public class LevelTest {
//
//      private Level level;
//      private final float x = 5.0f;
//      private final float y = 5.0f;
//
//      @BeforeEach
//      public void setUpClass() {
//          Gdx.files = Mockito.mock(Files.class);
//          FileHandle mockFileHandle = Mockito.mock(FileHandle.class);
//          Mockito.when(Gdx.files.classpath(Mockito.anyString())).thenReturn(mockFileHandle);
//          SceneController controller = new SceneController();
//          PlayScene scene = new PlayScene(controller);
//          new Level(scene);
//      }
//
//
//      @Test
//      public void testMoneyDeductedOnTowerPlacement() {
//          int initialMoney = GameConstants.START_MONEY;
//          int towerCost = GameConstants.TOWER_PRICE; // Arbitrary amount for testing
//          this.level.removeMoney(towerCost); // This ensures the money is set to initialMoney
//
//
//          // Manually set the tile at (x, y) to GROUND to ensure a tower can be placed
//          Tile groundTile = new Tile(x, y, GameConstants.TILE_WIDTH, GameConstants.TILE_HEIGHT, GridType.GROUND);
//          level.getMap().getBoard().getGameBoard().add(groundTile); // Using getGameBoard() here
//
//          // Attempt to place a tower
//          level.createTowerClicked(x, y, DefenderType.GUNNER);
//
//          // Assert money has been deducted appropriately
//          assertEquals(level.getMoney(), initialMoney - towerCost, "Money should be deducted correctly after placing a tower");
//      }
//  }
//
// **********Work in progress**********

  import static org.junit.Assert.*;
  import com.badlogic.gdx.ApplicationListener;
  import com.badlogic.gdx.Game;
  import com.badlogic.gdx.Gdx;
  import com.badlogic.gdx.backends.headless.HeadlessApplication;
  import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
  import com.badlogic.gdx.graphics.GL20;
  import com.badlogic.gdx.math.Vector2;
  import inf112.skeleton.app.enums.DefenderType;
  import inf112.skeleton.app.map.Board;
  import inf112.skeleton.app.util.GameConstants;
  import org.junit.Before;
  import org.junit.BeforeClass;
  import org.junit.Test;
  import org.mockito.Mockito;
  import com.badlogic.gdx.Graphics;

  import java.util.Set;

  public class LevelTest {
      private static HeadlessApplication application;
      private Level level;
      private Game game;

      private Board board;
      private Set<Vector2> mockPathPoints;

      @BeforeClass
      public static void init() {
          HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
          application = new HeadlessApplication(new ApplicationListener() {
              @Override
              public void create() {
                  // initialize resources specific to your application
              }

              @Override
              public void resize(int width, int height) {
                  // handle resizing
              }

              @Override
              public void render() {
                  // render your game elements here
              }

              @Override
              public void pause() {
                  // handle pause
              }

              @Override
              public void resume() {
                  // handle resume
              }

              @Override
              public void dispose() {
                  // dispose of resources and cleanup here
              }
          }, config);

          // Mocking the Gdx components
          Gdx.gl = Mockito.mock(GL20.class);
          Gdx.gl20 = Gdx.gl;
          Gdx.graphics = Mockito.mock(Graphics.class);

          // Setting up the expected responses from the mocked Graphics object
          Mockito.when(Gdx.graphics.getWidth()).thenReturn(800);
          Mockito.when(Gdx.graphics.getHeight()).thenReturn(600);
      }

      @Before
      public void setUp() {
          // Initialize the game mock and the Level class instance

          level = new Level(1);
      }

      @Test
      public void testInitialConditions() {
          assertEquals(0, level.getScore());
          assertEquals(GameConstants.START_MONEY, level.getMoney());
          assertEquals(GameConstants.REMAINING_HEALTH, level.getUserHealth());
          assertEquals(0, level.getCurrentWave());
          assertNotNull(level.getMap());
      }

      @Test
      public void testAddMoney() {
          int initialMoney = level.getMoney();
          level.addMoney(100);
          assertEquals(initialMoney + 100, level.getMoney());
      }

      @Test
      public void testRemoveMoney() {
          int initialMoney = level.getMoney();
          level.removeMoney(50);
          assertEquals(initialMoney - 50, level.getMoney());
      }

      @Test
      public void testEnemyKilledUpdates() {
          int initialScore = level.getScore();
          int initialMoney = level.getMoney();
          int initialEnemies = level.getEnemiesKilled();

          level.enemyKilled(100); // assuming reward is 100 money units

          assertEquals(initialScore + GameConstants.SCORE_INCREASE, level.getScore());
          assertEquals(initialMoney + 100, level.getMoney());
          assertEquals(initialEnemies + 1, level.getEnemiesKilled());
      }

//      @Test
//      public void testCreateTowerClicked() {
//          // You should set up the map and tile conditions accordingly
//          level.createTowerClicked(10, 10, DefenderType.BOMBER);
//          // Assert tower creation conditions and money spent if applicable
//          // You might need to mock or simulate the map and tiles
//      }

      @Test
      public void testUpdateInputs() {
          float x = 5.0f;
          float y = 5.0f;

          // Verify that updateInputs modifies the state correctly
      }

      @Test
      public void testRestartGameResetsValues() {
          level.addMoney(100);
          level.enemyKilled(50);
          level.restart();
          testInitialConditions(); // Re-test initial conditions after restart
      }

      @Test
      public void testNextWaveIncreasesWaveCount() {
          int currentWave = level.getCurrentWave();
          level.newWaveCreated(10); // Start new wave with 10 enemies
          assertEquals(currentWave + 1, level.getCurrentWave());
      }
  }
