  package inf112.skeleton.app.level;

  import static org.junit.Assert.*;
  import com.badlogic.gdx.ApplicationListener;
  import com.badlogic.gdx.Gdx;
  import com.badlogic.gdx.backends.headless.HeadlessApplication;
  import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
  import com.badlogic.gdx.graphics.GL20;
  import com.badlogic.gdx.math.Vector2;
  import inf112.skeleton.app.map.Board;
  import inf112.skeleton.app.util.GameConstants;
  import org.junit.Before;
  import org.junit.BeforeClass;
  import org.junit.Test;
  import org.junit.jupiter.api.Disabled;
  import org.mockito.Mockito;
  import com.badlogic.gdx.Graphics;

  import java.util.Set;

  public class LevelTest {
      private static HeadlessApplication application;
      private Level level;
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

      @Test
      public void testRestartGameResetsValues() {
          level.addMoney(100);
          level.enemyKilled(50);
          level.restart();
          testInitialConditions(); // Re-test initial conditions after restart
      }

      @Test
      @Disabled("Does not test anything useful yet")
      public void testNextWaveIncreasesWaveCount() {
          int currentWave = level.getCurrentWave();
          //level.newWaveCreated(10);
          assertEquals(currentWave + 1, level.getCurrentWave());
      }
  }
