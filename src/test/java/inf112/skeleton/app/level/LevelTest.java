//  package inf112.skeleton.app.level;

//  import inf112.skeleton.app.scene.PlayScene;
//  import inf112.skeleton.app.scene.Scene;
//  import inf112.skeleton.app.scene.SceneController;

//  import org.junit.jupiter.api.BeforeAll;
//  import org.junit.jupiter.api.BeforeEach;
//  import org.junit.jupiter.api.Test;
//  import org.mockito.Mockito;
//  import static org.mockito.Mockito.*;

//  import com.badlogic.gdx.ApplicationAdapter;
//  import com.badlogic.gdx.Files;
//  import com.badlogic.gdx.Gdx;
//  import com.badlogic.gdx.backends.headless.HeadlessApplication;
//  import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
//  import com.badlogic.gdx.backends.headless.HeadlessFiles;
//  import com.badlogic.gdx.files.FileHandle;

//  import inf112.skeleton.app.enums.DefenderType;
//  import inf112.skeleton.app.enums.GridType;
//  import inf112.skeleton.app.map.Tile;

//  import inf112.skeleton.app.util.GameConstants;
//  import inf112.skeleton.app.level.Level;


//  import static org.junit.jupiter.api.Assertions.assertEquals;

//  public class LevelTest {

//      private Level level;
//      private final float x = 5.0f;
//      private final float y = 5.0f;

//      @BeforeEach
//      public void setUpClass() {
//          Gdx.files = Mockito.mock(Files.class);
//          FileHandle mockFileHandle = Mockito.mock(FileHandle.class);
//          Mockito.when(Gdx.files.classpath(Mockito.anyString())).thenReturn(mockFileHandle);
//          SceneController controller = new SceneController();
//          PlayScene scene = new PlayScene(controller);
//          new Level(scene);
//      }


//      @Test
//      public void testMoneyDeductedOnTowerPlacement() {
//          int initialMoney = GameConstants.START_MONEY;
//          int towerCost = GameConstants.TOWER_PRICE; // Arbitrary amount for testing
//          this.level.removeMoney(towerCost); // This ensures the money is set to initialMoney
        

//          // Manually set the tile at (x, y) to GROUND to ensure a tower can be placed
//          Tile groundTile = new Tile(x, y, GameConstants.TILE_WIDTH, GameConstants.TILE_HEIGHT, GridType.GROUND);
//          level.getMap().getBoard().getGameBoard().add(groundTile); // Using getGameBoard() here

//          // Attempt to place a tower
//          level.createTowerClicked(x, y, DefenderType.GUNNER);

//          // Assert money has been deducted appropriately
//          assertEquals(level.getMoney(), initialMoney - towerCost, "Money should be deducted correctly after placing a tower");
//      }
//  }

// **********Work in progress**********