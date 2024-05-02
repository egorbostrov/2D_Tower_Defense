// package inf112.skeleton.app.tower;

// import com.badlogic.gdx.ApplicationAdapter;
// import com.badlogic.gdx.backends.headless.HeadlessApplication;
// import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.ApplicationListener;
// import com.badlogic.gdx.math.Vector2;
// import inf112.skeleton.app.entity.Enemy;
// import inf112.skeleton.app.enums.Direction;
// import inf112.skeleton.app.util.GameAssets;
// import com.badlogic.gdx.graphics.GL20;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.Sprite;

// import org.junit.BeforeClass;
// import org.junit.Before;
// import org.junit.Test;
// import org.mockito.*;

// import java.util.ArrayList;
// import java.util.LinkedList;
// import java.util.List;

// import static inf112.skeleton.app.util.GameConstants.ENEMY_HEIGHT;
// import static inf112.skeleton.app.util.GameConstants.ENEMY_WIDTH;
// import static org.mockito.Mockito.*;
// import static org.junit.Assert.*;

// public class BaseDefenderTest {

//     @Mock
//     private Enemy mockEnemy;
//     private List<Enemy> enemies;
//     private TestableDefender defender;
//     private Enemy enemy;

//     @BeforeClass
//     public static void init() {
//         // Create a headless application to initialize the LibGDX environment
//         HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
//         new HeadlessApplication(new ApplicationAdapter() {}, config);

//         // Mock out the GL20 instance to prevent NPE when graphics calls are made
//         Gdx.gl = Mockito.mock(GL20.class);
//         Gdx.gl20 = Mockito.mock(GL20.class);
//     }

//     @Before
//     public void setUp() {
//         MockitoAnnotations.initMocks(this);
//         enemies = new ArrayList<>();

//         // Create an actual instance of Enemy for testing
//         LinkedList<Direction> directions = new LinkedList<>(); // Assume some directions are added here
//         Enemy enemy = new Enemy('T', 0, 0, ENEMY_WIDTH, ENEMY_HEIGHT, 100, directions, 10, 1, 0, GameAssets.tankSprite, false);

//         // Now, enemy.position.x and enemy.position.y will refer to the actual values set by the constructor
//         enemies.add(enemy);

//         defender = new TestableDefender(50, 50, enemies);

//         Sprite texture = new Sprite(new Texture("path/to/texture.png"));

//         enemy = new Enemy('R', 0, 0, 10, 10, 100, directions, 50, 1.0f, 0, texture, false);
//     }

//     @Test
//     public void testEnemyInteraction() {
//         // Call the update method which should now work with the actual Enemy instance

//         defender.update(1.0f);

//         // Perform assertions based on the expected interactions between the defender and the enemy
//         // For example, if the defender is supposed to target the enemy, you can check if it is the enemy that has been targeted
//         assertTrue("Enemy should be targeted by defender", defender.enemy == enemy);
//         // Add more assertions as needed to verify the behavior of your defender when interacting with an enemy
//     }

//     @Test
//     public void testInitialization() {
//         assertNotNull("Defender should be initialized", defender);
//     }

//     @Test
//     public void testUpdateWithNoEnemyTargeted() {
//         // Set up any initial conditions needed before the update call
//         // For example, ensuring that no enemy is targeted initially
//         assertNull("Initial enemy should be null", defender.enemy);

//         // Call update with a deltaTime, which will trigger the internal calls
//         defender.update(1.0f); // You can use an appropriate deltaTime value here

//         // After calling update, we can check if an enemy was found and targeted
//         assertNotNull("Enemy should be targeted after update", defender.enemy);
//         // Make further assertions as necessary, based on the behavior of the update method
//     }

//     @Test
//     public void testUpdateWithAliveEnemy() {
//         // Prepare the defender with a known enemy
//         when(mockEnemy.isAlive()).thenReturn(true);
//         defender.enemy = mockEnemy; // Directly set the enemy for this test scenario

//         // Call update with a deltaTime
//         defender.update(1.0f); // The deltaTime triggers the internal behavior

//         // Verify that checkRotation and startFiring are called appropriately
//         // Since they are internal methods and their effects are private, you would
//         // instead verify their effects, such as changes to the defender's state,
//         // or interactions with the mockEnemy or bullets.
//         // ...
//     }

// // You would continue to write more test cases to cover the different branches
// // and functionalities within the update method.


//     @Test
//     public void testDamageUpgrade() {
//         float initialDamage = defender.getDamage();
//         defender.damageUpgrade();
//         assertTrue("Damage should increase after upgrade", defender.getDamage() > initialDamage);
//     }
// }

package inf112.skeleton.app.tower;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.entity.Enemy;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.util.GameAssets;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static inf112.skeleton.app.util.GameConstants.ENEMY_HEIGHT;
import static inf112.skeleton.app.util.GameConstants.ENEMY_WIDTH;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BaseDefenderTest {

    @Mock
    private Enemy mockEnemy;
    private List<Enemy> enemies;
    private TestableDefender defender;
    private Enemy enemy;

    @BeforeClass
    public static void init() {
        // Create a headless application to initialize the LibGDX environment
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new ApplicationAdapter() {}, config);

        // Mock out the GL20 instance to prevent NPE when graphics calls are made
        Gdx.gl = Mockito.mock(GL20.class);
        Gdx.gl20 = Mockito.mock(GL20.class);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        enemies = new ArrayList<>();

        // Create an actual instance of Enemy for testing
        LinkedList<Direction> directions = new LinkedList<>();
        // Assume some directions are added here for the enemy to follow
        Sprite texture = Mockito.mock(Sprite.class); // Mocked texture for the enemy
        enemy = new Enemy('T', 0, 0, ENEMY_WIDTH, ENEMY_HEIGHT, 100, directions, 10, 1, 0, texture, false);
        enemies.add(enemy);

        defender = new TestableDefender(50, 50, enemies);
    }

    @Test
    public void testEnemyInteraction() {
        // Call the update method which should now work with the actual Enemy instance
        defender.update(1.0f);

        // Perform assertions based on the expected interactions between the defender and the enemy
        // For example, if the defender is supposed to target the enemy, you can check if it is the enemy that has been targeted
        assertTrue("Enemy should be targeted by defender", defender.enemy == enemy);
        // Add more assertions as needed to verify the behavior of your defender when interacting with an enemy
    }

    @Test
    public void testInitialization() {
        assertNotNull("Defender should be initialized", defender);
    }

    @Test
    public void testUpdateWithNoEnemyTargeted() {
        // Set up any initial conditions needed before the update call
        // For example, ensuring that no enemy is targeted initially
        assertNull("Initial enemy should be null", defender.enemy);

        // Call update with a deltaTime, which will trigger the internal calls
        defender.update(1.0f); // You can use an appropriate deltaTime value here

        // After calling update, we can check if an enemy was found and targeted
        assertNotNull("Enemy should be targeted after update", defender.enemy);
        // Make further assertions as necessary, based on the behavior of the update method
    }

    @Test
    public void testUpdateWithAliveEnemy() {
        // Prepare the defender with a known enemy
        when(mockEnemy.isAlive()).thenReturn(true);
        defender.enemy = mockEnemy; // Directly set the enemy for this test scenario

        // Call update with a deltaTime
        defender.update(1.0f); // The deltaTime triggers the internal behavior

        // Verify that checkRotation and startFiring are called appropriately
        // Since they are internal methods and their effects are private, you would
        // instead verify their effects, such as changes to the defender's state,
        // or interactions with the mockEnemy or bullets.
        // ...
    }

    // You would continue to write more test cases to cover the different branches
    // and functionalities within the update method.

    @Test
    public void testDamageUpgrade() {
        float initialDamage = 10.0f; // Assuming you can set this value directly for testing
        defender.setDamage(initialDamage);

        defender.damageUpgrade();

        float upgradedDamage = defender.getDamage();
        assertTrue("Damage should increase after upgrade, was: " + upgradedDamage + ", but expected more than: " + initialDamage,
                upgradedDamage > initialDamage);
    }

    @Test
    public void testSpeedUpgrade() {
        float oldSpeed = defender.getSpeed();
        defender.speedUpgrade();
        assertTrue("Speed should increase after upgrade", defender.getSpeed() > oldSpeed);
    }

    @Test
    public void testRangeUpgrade() {
        float oldRange = defender.getRange();
        defender.rangeUpgrade();
        assertTrue("Range should increase after upgrade", defender.getRange() > oldRange);
    }

}

